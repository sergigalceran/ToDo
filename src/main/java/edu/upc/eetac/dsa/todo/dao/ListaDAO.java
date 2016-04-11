package edu.upc.eetac.dsa.todo.dao;

import edu.upc.eetac.dsa.todo.entity.Lista;
import edu.upc.eetac.dsa.todo.entity.ListaCollection;

import java.sql.SQLException;

/**
 * Created by Sergi1 on 25/02/2016.
 */
public interface ListaDAO {
    public Lista createLista(String userid, String title) throws SQLException;
    public Lista getListaById(String id) throws SQLException;
    public ListaCollection getListas() throws SQLException;
    public Lista updateLista(String id, String title) throws SQLException;
    public boolean deleteLista(String id) throws SQLException;
}
