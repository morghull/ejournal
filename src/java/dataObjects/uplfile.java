/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataObjects;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author u27brvz04
 */
public class uplfile implements Serializable {

    private int ufid;
    private String ufname;
    private InputStream ufcontent;
    private int idd;

    public int getUfid() {
        return ufid;
    }

    public void setUfid(int ufid) {
        this.ufid = ufid;
    }

    public String getUfname() {
        return ufname;
    }

    public void setUfname(String ufname) {
        this.ufname = ufname;
    }

    public InputStream getUfcontent() {
        return ufcontent;
    }

    public void setUfcontent(InputStream ufcontent) {
        this.ufcontent = ufcontent;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    @Override
    public String toString() {
        return "{"
                + "\"ufid\":" + ufid
                + ",\"ufname\":\"" + ufname + "\""
                + ",\"idd\":" + idd
                + "}";
    }
}
