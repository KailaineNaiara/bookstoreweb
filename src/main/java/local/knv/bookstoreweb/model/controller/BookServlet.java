/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.knv.bookstoreweb.model.controller;

import java.io.IOException;

//import java.sql.SQLException;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import local.knv.bookstoreweb.model.bean.Book;
import local.knv.bookstoreweb.model.dao.BookDAO;
/**
 *
 * @author devsys-b
 */
public class BookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        listBook (request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType ("text/html;charset=UTF-8");
        
        String action = request.getPathInfo();
        Logger.getLogger(BookServlet.class.getName()).log(Level.INFO, "Path solicitado: (0)", action);
        
        try {
            switch (action){
                case "/new":
                    showNewBookForm (request, response);
                    break;
            
            
            case "/insert" :
            insertBookAction(request, response);
            break;
            
            case "/list":
            default:
                listBook (request, response);
                break;
            }
            
        } catch (SQLException ex){
            throw new ServletException(ex);
        } catch (SLQException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold> 

    private void listBook(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        BookDAO bookDAO = new BookDAO();
        List<Book> listaBook = bookDAO.getResults();
        
        Logger.getLogger(BookDAO.class.getName()).log(Level.INFO,
                "tOTAL DE REGISTROS: {0}", listaBook.size());
        
        request.setAttribute ("listaBook", listaBook);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
        dispatcher.forward (request, response);
    }
    
    private void showNewBookForm (HttpServletRequest request, HttpServletResponse response)
            throws SLQException, IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertBookAction (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        BookDAO bookDAO = new BookDAO ();
        Book novoBook = new Book ();
        novoBook.setTitulo(request.getParameter("formEmail"));
        novoBook.setAutor(request.getParameter("formPassword"));
        novoBook.setPreco(Double.parseDouble (request.getParameter("formFullname")));
        
        bookDAO.create(novoBook);
        response.sendRedirect ("list");
    }

    private static class SLQException extends Exception {

        public SLQException() {
        }
    }
    
    private void showEditBooKform (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BookDAO bookDAO = new BookDAO();
        Book atualizaBook = bookDAO.getResultById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookForm.jsp");
        request.setAttribute("book", atualizaBook);
        dispatcher.forward(request, response);
    }
    
    private void updateBookAction (HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        BookDAO bookDAO = new BookDAO();
        Book bookAtualizado = new Book ();
        
        bookAtualizado.setId (Integer.parseInt(request.getParameter("formId")));
        
        bookAtualizado.setEmail (request.getParameter("formEmail"));
        bookAtualizado.setPassword(request.getParameter("formPassword"));
        bookAtualizado.setFullname (request.getParameter("formFullname"));
        
        bookDAO.update (bookAtualizado);
        response.sendRedirect ("list");
    }
}
