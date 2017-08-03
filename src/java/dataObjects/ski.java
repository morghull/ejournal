/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataObjects;

import java.io.Serializable;

/**
 *
 * @author u27brvz04
 */
public class ski implements Serializable {

    private String nzak;
    private String osd_t;
    private String osd_r;
    private String osd_c;
    private String kiz;
    private String pri;
    private String nvp;
    private String naim;
    private String nizv;

    public String getNzak() {
        return nzak;
    }

    public void setNzak(String nzak) {
        this.nzak = nzak;
    }

    public String getOsd_t() {
        return osd_t;
    }

    public void setOsd_t(String osd_t) {
        this.osd_t = osd_t;
    }

    public String getOsd_r() {
        return osd_r;
    }

    public void setOsd_r(String osd_r) {
        this.osd_r = osd_r;
    }

    public String getOsd_c() {
        return osd_c;
    }

    public void setOsd_c(String osd_c) {
        this.osd_c = osd_c;
    }

    public String getKiz() {
        return kiz;
    }

    public void setKiz(String kiz) {
        this.kiz = kiz;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getNvp() {
        return nvp;
    }

    public void setNvp(String nvp) {
        this.nvp = nvp;
    }

    public String getNaim() {
        return naim;
    }

    public void setNaim(String naim) {
        this.naim = naim;
    }

    public String getNizv() {
        return nizv;
    }

    public void setNizv(String nizv) {
        this.nizv = nizv;
    }

    @Override
    public String toString() {
        return "{"
                + "\"nzak\":\"" + nzak + "\""
                + ",\"osd_t\":\"" + osd_t + "\""
                + ",\"osd_r\":\"" + osd_r + "\""
                + ",\"osd_c\":\"" + osd_c + "\""
                + ",\"kiz\":\"" + kiz + "\""
                + ",\"pri\":\"" + pri + "\""
                + ",\"ncp\":\"" + nvp + "\""
                + ",\"naim\":\"" + naim + "\""
                + ",\"nizv\":\"" + nizv + "\""
                + "}";
    }
}
