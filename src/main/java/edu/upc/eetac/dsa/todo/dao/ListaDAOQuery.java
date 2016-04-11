package edu.upc.eetac.dsa.todo.dao;

/**
 * Created by Sergi1 on 29/02/2016.
 */
public interface ListaDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_LISTA = "insert into lista (listaid, userid, title) values (UNHEX(?), unhex(?), ?)";
    public final static String GET_LISTA_BY_ID = "select hex(s.listaid) as listaid, hex(s.userid) as userid, s.title, s.creation_timestamp, s.last_modified, u.fullname from lista s, users u where s.listaid=unhex(?) and u.id=s.userid";
    public final static String GET_LISTAS = "select hex(listaid) as listaid, hex(userid) as userid, title, creation_timestamp, last_modified from lista";
    public final static String UPDATE_LISTA = "update lista set title=? where listaid=unhex(?)";
    public final static String DELETE_LISTA = "delete from lista where listaid=unhex(?)";
}
