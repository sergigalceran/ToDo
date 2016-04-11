package edu.upc.eetac.dsa.todo.dao;

import edu.upc.eetac.dsa.todo.entity.Lista;
import edu.upc.eetac.dsa.todo.entity.ListaCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ListResourceBundle;

/**
 * Created by Sergi1 on 29/02/2016.
 */
public class ListaDAOImpl implements ListaDAO {

    @Override
    public Lista createLista(String userid, String title) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(ListaDAOQuery.CREATE_LISTA);
            stmt.setString(1, id);
            stmt.setString(2, userid);
            stmt.setString(3, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return getListaById(id);
    }

    @Override
    public Lista getListaById(String id) throws SQLException {
        Lista lista = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(ListaDAOQuery.GET_LISTA_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                lista = new Lista();
                lista.setId(rs.getString("listaid"));
                lista.setUserid(rs.getString("userid"));
                lista.setTitle(rs.getString("title"));
                lista.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                lista.setLastModified(rs.getTimestamp("last_modified").getTime());
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return lista;
    }

    @Override
    public ListaCollection getListas() throws SQLException {
        ListaCollection listaCollection = new ListaCollection();

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement(ListaDAOQuery.GET_LISTAS);

            ResultSet rs = stmt.executeQuery();
            boolean first = true;
            while (rs.next()) {
                Lista lista = new Lista();
                lista.setId(rs.getString("listaid"));
                lista.setUserid(rs.getString("userid"));
                lista.setTitle(rs.getString("title"));
                lista.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                lista.setLastModified(rs.getTimestamp("last_modified").getTime());
                if (first) {
                    listaCollection.setNewestTimestamp(lista.getLastModified());
                    first = false;
                }
                listaCollection.setOldestTimestamp(lista.getLastModified());
                listaCollection.getListas().add(lista);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return listaCollection;
    }

    @Override
    public Lista updateLista(String id, String title) throws SQLException {
        Lista lista = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(ListaDAOQuery.UPDATE_LISTA);
            stmt.setString(1, title);
            stmt.setString(2, id);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                lista = getListaById(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return lista;
    }

    @Override
    public boolean deleteLista(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(ListaDAOQuery.DELETE_LISTA);
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
}
