/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

import java.io.Serializable;

/**
 *
 * @author u27brvz04
 */
public class backendError implements Serializable {

    private int status;
    private String text;
    private String details;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "{\"error\":{"
                + "\"status\":" + status
                + ",\"text\":\"" + text + "\""
                + ",\"details\":\"" + details.replace("\n", ";").replace("\"", "\\\"") + "\""
                + "}}";
    }
}
