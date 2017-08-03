/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataObjects;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author u27brvz04
 */
public class ejrdok implements Serializable {

    private int idd;
    private String rdk;
    private String rdn;
    private Date rdd;
    private String nazz;
    private String rdsh;
    private String ordk;
    private String ordn;
    private Date ordd;
    private Date dvd;
    private String nzak;
    private String prim;
    private List<uplfile> uplfiles;
    private String filesjson;
    private DateFormat dateFormater = new SimpleDateFormat("dd.MM.yyyy");

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getRdk() {
        return rdk;
    }

    public void setRdk(String rdk) {
        this.rdk = rdk;
    }

    public String getRdn() {
        return rdn;
    }

    public void setRdn(String rdn) {
        this.rdn = rdn;
    }

    public Date getRdd() {
        return rdd;
    }

    public void setRdd(Date rdd) {
        this.rdd = rdd;
    }

    public String getNazz() {
        return nazz;
    }

    public void setNazz(String nazz) {
        this.nazz = nazz;
    }

    public String getRdsh() {
        return rdsh;
    }

    public void setRdsh(String rdsh) {
        this.rdsh = rdsh;
    }

    public String getOrdk() {
        return ordk;
    }

    public void setOrdk(String ordk) {
        this.ordk = ordk;
    }

    public String getOrdn() {
        return ordn;
    }

    public void setOrdn(String ordn) {
        this.ordn = ordn;
    }

    public Date getOrdd() {
        return ordd;
    }

    public void setOrdd(Date ordd) {
        this.ordd = ordd;
    }

    public Date getDvd() {
        return dvd;
    }

    public void setDvd(Date dvd) {
        this.dvd = dvd;
    }

    public String getNzak() {
        return nzak;
    }

    public void setNzak(String nzak) {
        this.nzak = nzak;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public List<uplfile> getUplfiles() {
        return uplfiles;
    }

    public void setUplfiles(List<uplfile> uplfiles) {
        this.uplfiles = uplfiles;
    }

    public String getFilesjson() {
        return filesjson;
    }

    public void setFilesjson(String jsonUpf) {
        this.filesjson = jsonUpf;
    }

    @Override
    public String toString() {
        return "{"
                + "\"idd\":" + idd
                + ",\"rdk\":\"" + rdk + "\""
                + ",\"rdn\":\"" + rdn + "\""
                + ",\"rdd\":\"" + dateFormater.format(rdd) + "\""
                + ",\"nazz\":\"" + nazz + "\""
                + ",\"rdsh\":\"" + rdsh + "\""
                + ",\"ordk\":\"" + ordk + "\""
                + ",\"ordn\":\"" + ordn + "\""
                + ",\"ordd\":\"" + dateFormater.format(ordd) + "\""
                + ",\"dvd\":\"" + dateFormater.format(dvd) + "\""
                + ",\"nzak\":\"" + nzak + "\""
                + ",\"prim\":\"" + prim + "\""
                + ",\"files\":" + filesjson
                + "}";
    }
}
