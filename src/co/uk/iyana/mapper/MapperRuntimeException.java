/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.uk.iyana.mapper;

/**
 *
 * @author fgyara
 */
public class MapperRuntimeException extends RuntimeException {
    

    public MapperRuntimeException(String mesg, Exception ex) {
        super(mesg, ex);
    }
    
    public MapperRuntimeException(String mesg) {
        super(mesg);
    }
}
