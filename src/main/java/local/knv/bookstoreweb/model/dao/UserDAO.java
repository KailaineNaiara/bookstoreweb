package local.knv.bookstoreweb.model.dao;

import local.knv.bookstoreweb.model.bean.User;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author devsys-b
 */

public class UserDAO {

    private static final String SQL_INSERT = "INSERT INTO user ( email,password,fullname ) "
            + " VALUES ( ? , ? , ?)";

    private static final String SQL_SELECT_ALL = "SELECT * FROM user ";
    private static final String SQL_SELECT_ID = "SELECT * FROM user "
            + "WHERE id = ?";

    private static final String SQL_UPDATE = "UPDATE produto SET email = ?,"
            + " passaword = ?, fullname = ?";

    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?";
    private PreparedStatement smtm;
    private Object sQLException;

    public void create(User b) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, b.getEmail());
            stmt.setString(2, b.getPassword());
            stmt.setString(3, b.getFullname());

            //Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "inclusão :" + auxRetorno);

        } catch (SQLException SQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, SQLException);
        } finally {

            //Encerra a conexão com o banco e o statement
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public List<User> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User b = null;
        List<User> listaUser = null;

        try {

            //prepara a string de SELECT e executa a query
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            //Carrega os dados resultSet rs, converte em Book e adiciona a lista de retorno
            listaUser = new ArrayList<>();

            while (rs.next()) {
                b = new User();
                b.setEmail(rs.getString("email"));
                b.setPassword(rs.getString("password"));
                b.setFullname(rs.getString("fullname"));
                listaUser.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUser;

    }

    public User getResultById(int id) throws SQLException {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User b = null;

        try {
            smtm = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                b = new User();
                b.setEmail(rs.getString("email"));
                b.setPassword(rs.getString("password"));
                b.setFullname(rs.getString("fullname"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);

            //Encerra a conexão com o banco statement
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return b;
    }
    //Atualiza um registro na tabela produto

    public void update(User b) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt.setString(1, b.getEmail());
            stmt.setString(2, b.getPassword());
            stmt.setString(3, b.getFullname());

            //Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "Update :" + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            //Encerra a conexão com o banco de dados 
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    //Exclui um produto com base do id fornecido
    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

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

    public User checkLogin(String auxEmail, String auxPassword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        }
