package edu.upc.eetac.dsa.todo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Sergi1 on 24/02/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoError {
    private int status;
    private String reason;
    public TodoError() {
    }
    public TodoError(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
