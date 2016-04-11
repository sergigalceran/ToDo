package edu.upc.eetac.dsa.todo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Sergi1 on 25/02/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Items {
    private String id;
    private String listaid;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListaid() {
        return listaid;
    }

    public void setListaid(String listaid) {
        this.listaid = listaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
