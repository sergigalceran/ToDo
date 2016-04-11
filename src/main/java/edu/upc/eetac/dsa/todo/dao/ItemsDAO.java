package edu.upc.eetac.dsa.todo.dao;

import com.mysql.jdbc.exceptions.MySQLDataException;
import edu.upc.eetac.dsa.todo.entity.Items;

import java.sql.SQLException;

/**
 * Created by Sergi1 on 03/03/2016.
 */
public interface ItemsDAO {
    public Items getItem(String id) throws SQLException;
    public Items createItem(String userid, String listaid, String name) throws SQLException;
    public Items deleteItem(String id) throws SQLException;
    public Items updateItem(String id, String name) throws SQLException;
}
