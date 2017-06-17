/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractController;

/**
 *
 * @author u27brvz04
 */
public class rowcountController extends AbstractController {
    
    private final String tableName;
    
    public rowcountController(String tableName){
        this.tableName = tableName;
    }
    
    @Override
    public String getTableName() throws Exception {
        return this.tableName;
    }
}
