package edu.upc.eetac.dsa.todo.dao;

import edu.upc.eetac.dsa.todo.entity.User;

import java.sql.SQLException;

/**
 * Created by Sergi1 on 25/02/2016.
 */
public interface UserDAO {

    public User createUser(String loginid, String password, String email, String fullname) throws SQLException, UserAlreadyExistsException;

    public User updateProfile(String id, String email, String fullname) throws SQLException;

    public User getUserById(String id) throws SQLException;

    public User getUserByLoginid(String loginid) throws SQLException;

    public boolean deleteUser(String id) throws SQLException;

    public boolean checkPassword(String id, String password) throws SQLException;
}
