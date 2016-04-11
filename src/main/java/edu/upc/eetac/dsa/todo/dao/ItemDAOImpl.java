package edu.upc.eetac.dsa.todo.dao;

import edu.upc.eetac.dsa.todo.entity.Items;
import edu.upc.eetac.dsa.todo.entity.Lista;

import javax.ws.rs.PathParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sergi1 on 10/03/2016.
 */
public class ItemDAOImpl implements ItemsDAO {
    Items item =null;

    @Override
    public Items createItem(String userid, String listaid, String name) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String itemid = null;
        int status =0;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                itemid = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(ItemDAOQuery.CREATE_ITEM);
            stmt.setString(1, itemid);
            stmt.setString(2, listaid);
            stmt.setString(3, name);
            stmt.setInt(4, status);
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
        return getItem(itemid);
    }
    @Override
    public Items deleteItem(String id) throws SQLException{
        return item;
    }
    @Override
    public Items updateItem(String id, String name) throws SQLException{
        return  item;
    }
    @Override
    public Items getItem(String id) throws SQLException{
        return item;
    }

}
