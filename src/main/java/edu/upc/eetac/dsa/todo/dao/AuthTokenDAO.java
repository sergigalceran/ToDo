package edu.upc.eetac.dsa.todo.dao;

import edu.upc.eetac.dsa.todo.auth.UserInfo;
import edu.upc.eetac.dsa.todo.entity.AuthToken;

import java.sql.SQLException;

/**
 * Created by Sergi1 on 25/02/2016.
 */
public interface AuthTokenDAO {
    public UserInfo getUserByAuthToken(String token) throws SQLException;
    public AuthToken createAuthToken(String userid) throws SQLException;
    public void deleteToken(String userid) throws  SQLException;
}