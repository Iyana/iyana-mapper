/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.uk.iyana.mapper;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fgyara
 */
public class SimpleMapMapper extends HttpMapper<Map<String,Object>> {
    private final MapperConfiguration config;
    
    public SimpleMapMapper() {
        this(new MapperConfiguration());
    }
    
    public SimpleMapMapper(MapperConfiguration config) {
        config.compile();
        this.config = config;
    }
    
    @Override
    public Map<String, Object> map(HttpServletRequest request, String namespace, String delimiter, Locale locale) {
        
        if (request == null) {
            throw new MapperRuntimeException("request cannot be null");
        }
        
        String prefix = MapperHelper.getPrefix(namespace, delimiter);
        
        Map<String, Object> toRet = new HashMap();
        
        Map<String, String[]> params = request.getParameterMap();
        
        for (String paramName : params.keySet()) {
            // should we ignore this ?
            if (prefix == null) {
                // no filtering
            } else {
                // skip the ones that dont start with the namespace
                if (!(paramName.startsWith(prefix))) {
                    continue;
                }
            }
            
            // if a namespace is specified, we strip it out in the map
            String paramNameToUse = paramName;
            if (prefix != null) {
                paramNameToUse = paramName.substring(prefix.length());
            }
            
            // skip params that are to be ignored by configuration
            if (this.config.ignoreParameter(paramNameToUse)) {
                continue;
            }

            // if the param is mapped by the config, change the paramNameToUse
            String fieldName = this.config.getFieldNameFromParamName(paramNameToUse);
            
            String parameterStringValue = request.getParameter(paramName);
            if ((parameterStringValue == null)||(parameterStringValue.equals(""))) {
                continue;
            }
            
            FieldConfig fieldConfig = this.config.fieldConfigsByParamName.get(paramNameToUse);
            if ((fieldConfig == null)||(fieldConfig.getType() == null)) {
                toRet.put(fieldName, parameterStringValue);
            } else {
                Object objParamValue = MapperHelper.castParameterValue(fieldConfig, parameterStringValue, fieldConfig.getType(), locale, this.config);
                toRet.put(fieldName, objParamValue);
            }
        }
        
        return toRet;
    }

}
