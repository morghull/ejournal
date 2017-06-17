/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

/**
 *
 * @author u27brvz04
 */
public class SqlDataFilter<T> {
    private String fieldName;
    private T greaterThenOrEqualVal;
    private T greaterThenVal;
    private T lessThenOrEqualVal;
    private T lessThenVal;
    private T equalToVal;
    private T likeVal;
}
