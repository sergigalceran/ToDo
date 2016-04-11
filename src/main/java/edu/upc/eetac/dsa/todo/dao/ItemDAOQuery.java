package edu.upc.eetac.dsa.todo.dao;

/**
 * Created by Sergi1 on 10/03/2016.
 */
public class ItemDAOQuery {
    public final static String CREATE_ITEM = "insert into items (itemid, listaid, item, status) values (UNHEX(?), unhex(?), ?, ?)";
    public final static String GET_ITEM_BY_ID = "select hex(s.listaid) as listaid, hex(s.userid) as userid, s.title, s.creation_timestamp, s.last_modified, u.fullname from lista s, users u where s.listaid=unhex(?) and u.id=s.userid";
    public final static String GET_ITEMS = "select hex(listaid) as listaid, hex(userid) as userid, title, creation_timestamp, last_modified from lista";
    public final static String UPDATE_ITEM = "update lista set title=? where listaid=unhex(?)";
    public final static String DELETE_ITEM = "delete from lista where listaid=unhex(?)";

}
