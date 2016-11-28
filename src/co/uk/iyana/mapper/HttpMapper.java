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
public abstract class HttpMapper<T> implements IHttpMapper<T> {

    private static final String DEFAULT_NAMESPACE_DELIMITER = ".";
    
    @Override
    public T map(HttpServletRequest request) {
        return this.map(request, null, null, request.getLocale());
    }

    @Override
    public T map(HttpServletRequest request, String namespace) {
        return this.map(request, namespace, DEFAULT_NAMESPACE_DELIMITER, request.getLocale());
    }    
    
    @Override
    public T map(HttpServletRequest request, Locale locale) {
        return this.map(request, null, null, locale);
    }

    @Override
    public T map(HttpServletRequest request, String namespace, Locale locale) {
        return this.map(request, namespace, DEFAULT_NAMESPACE_DELIMITER, locale);
    }    
    
}
