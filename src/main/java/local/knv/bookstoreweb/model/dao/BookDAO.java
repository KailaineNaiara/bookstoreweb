/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.knv.bookstoreweb.model.dao;
import local.knv.bookstoreweb.model.bean.Book;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author devsys-b
 */
public class BookDAO {
    private static final String SQL_INSERT = "INSERT INTO book (email"
            + " password, fullname ) "
            + " VALUES ( ? , ? , ?)";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM book ";
    private static final String SQL_SELECT_ID = "SELECT * FROM book "
            + "WHERE id = ?";
    
    private static final String SQL_UPDATE = "UPDATE produto SET titulo = ?,"
            + " autor = ?, preco = ? WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM book WHERE id = ?";
    private PreparedStatement smtm;
    private Object sQLException;
    
    
    public void create (Book b){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement (SQL_INSERT);
            stmt.setString (1, b.getEmail());
            stmt.setString (2, b.getPassword());
            stmt.setString (3,b.getFullname());
            
            //Executa a query
            
            int auxRetorno = stmt.executeUpdate ();
            
            Logger.getLogger (BookDAO.class.getName()).log(Level.INFO,null, "inclusão :" +auxRetorno);
        
            } catch (SQLException SQLException){
                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE,null, SQLException);
            } finally {
    
            //Encerra a conexão com o banco e o statement
            MySQLConnection.closeConnection(conn,stmt);
        }
    }
    
    public List <Book> getResults () {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        List <Book> listaBooks = null;
        
        try {
            
        //prepara a string de SELECT e executa a query
        
        stmt = conn.prepareStatement (SQL_SELECT_ALL);
        rs = stmt.executeQuery();

        //Carrega os dados resultSet rs, converte em Book e adiciona a lista de retorno

        listaBooks = new ArrayList <>();

        while (rs.next()){
            b = new Book();
            b.setId (rs.getInt("id"));
            b.setEmail (rs.getString("email"));
            b.setPassword (rs.getString ("password"));
            b.setFullname (rs.getString ("fullname"));
            listaBooks.add(b);
        }
        } catch (SQLException ex){
                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE,null,ex);
                } 
        return listaBooks;
    
    }
    
    
    public Book getResultById (int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        
        try{
            smtm = conn.prepareStatement (SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                b = new Book();
                b.setId (rs.getInt("id"));
                b.setEmail (rs.getString("email"));
                b.setPassword (rs.getString ("password"));
                b.setFullname (rs.getString ("fullname"));
                
            }
            
        } catch (SQLException ex){
          Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE,null,sQLException);
          
    //Encerra a conexão com o banco statement
    
        } finally {
            MySQLConnection.closeConnection (conn,  stmt, rs);
        }
        return b;
}
    //Atualiza um registro na tabela produto
    
    public void update ( Book b ) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt.setString (1, b.getEmail());
            stmt.setString (2, b.getPassword());
            stmt.setString (3,b.getFullname());
            
    //Executa a query
            
            int auxRetorno =  stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO,null, "Update :" + auxRetorno );
            
        } catch (SQLException sQLException ){
            Logger.getLogger (BookDAO.class.getName()).log(Level.SEVERE,null,sQLException);
        } finally{
    //Encerra a conexão com o banco de dados 
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    //Exclui um produto com base do id fornecido
    
    public void delete ( int id ){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt (1,id);
            
    //Executa query
            
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log (Level.INFO,null, "Delete:"+ auxRetorno);
             } catch (SQLException sQLException ){
             Logger.getLogger (BookDAO.class.getName()).log(Level.SEVERE,null,sQLException);
             
            } finally{
    //Encerra a conexão com o banco de dados 
            MySQLConnection.closeConnection(conn, stmt);
        
        }
    }
}

