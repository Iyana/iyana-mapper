package co.uk.iyana.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fgyara
 * @param <T>
 */
public class SimpleTypedMapper<T> extends HttpMapper<T> {
    private final Class<T> clazz;
    
    private final ArrayList<Method> methods = new ArrayList();
    private final MapperConfiguration config;
    
    public SimpleTypedMapper(Class<T> clazz) {
        this (clazz, new MapperConfiguration());
    }

    public SimpleTypedMapper(Class<T> clazz, MapperConfiguration config) {
        if (clazz == null) {
            throw new MapperRuntimeException("class cannot be null");
        }
        
        this.clazz = clazz;
        
        // set the config
        config.compile();
        this.config = config;
        
        this.introspect();
    }
    
    @Override
    public T map(HttpServletRequest request, String namespace, String delimiter, Locale locale) {
        if (request == null) {
            throw new MapperRuntimeException("request cannot be null");
        }
        
        try {
            T vo = clazz.newInstance();
            this.populateVO(vo, request, namespace, delimiter, locale);
            return vo;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new MapperRuntimeException("Cannot instantiate class " + clazz.getName(), ex);
        }
    }

    private void populateVO(T vo, HttpServletRequest request, String namespace, String delimiter, Locale locale) {
        
        // work out the prefix
        String prefix = MapperHelper.getPrefix(namespace, delimiter);
        
        // find all the fields in the VO
        for (Method method: this.methods) {
            
            String methodName = method.getName();
            
            String fieldName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
            String parameterName = this.config.getParamNameFromFieldName(fieldName);
            
            if (prefix != null) parameterName = prefix + parameterName;
            
            String parameterStringValue = request.getParameter(parameterName);
            if ((parameterStringValue == null)||(parameterStringValue.equals(""))) {
                continue;
            }

            try {
                FieldConfig fieldConfig = this.config.fieldConfigsByFieldName.get(fieldName);
                Object paramValue = MapperHelper.castParameterValue(fieldConfig, parameterStringValue, method.getParameterTypes()[0], locale, config);
                method.invoke(vo, paramValue);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new MapperRuntimeException("Cannot call method to set parameter " + method.getName(), ex);
            }
        }
    }

    
    private void introspect() {
        // find all the fields in the VO
        Method[] classMethods = this.clazz.getMethods();
        for (Method method: classMethods) {
            
            String methodName = method.getName();
            
            // skip methods that do not start with "set"
            if (!(methodName.startsWith("set"))) {
                continue;
            }
            
            // skip methods that require multiple parameters
            Class parameters[] = method.getParameterTypes();
            if (parameters.length != 1) {
                continue;
            }
            
            // skip methods that are to be ignored by configuration
            String parameterName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
            if (this.config.ignoreParameter(parameterName)) {
                continue;
            }

            this.methods.add(method);
        }
        
    }

}
