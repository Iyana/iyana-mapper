/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.uk.iyana.mapper;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fgyara
 * @param <T>
 */
public interface IHttpMapper<T> {
    public T map(HttpServletRequest request, String namespace, String delimiter, Locale locale);
    
    public T map(HttpServletRequest request);

    public T map(HttpServletRequest request, String namespace);
    
    public T map(HttpServletRequest request, Locale locale);

    public T map(HttpServletRequest request, String namespace, Locale locale);
}
