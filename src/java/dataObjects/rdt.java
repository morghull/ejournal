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
public class rdt implements Serializable {

    private String rdtk;
    private String rdtn;

    public String getRdtk() {
        return rdtk;
    }

    public void setRdtk(String rdtk) {
        this.rdtk = rdtk;
    }

    public String getRdtn() {
        return rdtn;
    }

    public void setRdtn(String rdtn) {
        this.rdtn = rdtn;
    }

    @Override
    public String toString() {
        return "{"
                + "\"rdtk\":\"" + rdtk + "\""
                + ",\"rdtn\":\"" + rdtn + "\""
                + "}";
    }
}
