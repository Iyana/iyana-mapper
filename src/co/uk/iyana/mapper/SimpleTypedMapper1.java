package co.uk.iyana.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fgyara
 * @param <T>
 */
public abstract class SimpleTypedMapper1<T> extends HttpMapper<T> {
//    private final Class<T> clazz;
//    
//    public SimpleTypedMapper1(Class<T> clazz) {
//        if (clazz == null) {
//            throw new MapperRuntimeException("class cannot be null");
//        }
//        
//        this.clazz = clazz;
//    }
//    
//    @Override
//    public T map(HttpServletRequest request, String namespace, String delimiter) {
//        if (request == null) {
//            throw new MapperRuntimeException("request cannot be null");
//        }
//        
//        try {
//            T vo = clazz.newInstance();
//            this.introspect(vo, request, namespace, delimiter);
//            return vo;
//        } catch (InstantiationException | IllegalAccessException ex) {
//            throw new MapperRuntimeException("Cannot instantiate class " + clazz.getName(), ex);
//        }
//    }
//
//    private void introspect(T vo, HttpServletRequest request, String namespace, String delimiter) {
//
//        // work out the prefix
//        String prefix = MapperHelper.getPrefix(namespace, delimiter);
//        
//        // find all the fields in the VO
//        Method[] methods = vo.getClass().getMethods();
//        for (Method method: methods) {
//            
//            String methodName = method.getName();
//            
//            if (!(methodName.startsWith("set"))) {
//                continue;
//            }
//            
//            Class parameters[] = method.getParameterTypes();
//            if (parameters.length != 1) {
//                continue;
//            }
//            
//            // check for any annotations there
//            String parameterName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
//            if (prefix != null) parameterName = prefix + parameterName;
//            
//            String parameterStringValue = request.getParameter(parameterName);
//            if ((parameterStringValue == null)||(parameterStringValue.equals(""))) {
//                continue;
//            }
//
//            Object paramValue = castParameterValue(parameterStringValue, parameters[0]);
//            if (paramValue != null) {
//                try {
//                    method.invoke(vo, paramValue);
//                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//                    throw new MapperRuntimeException("Cannot call method to set parameter " + method.getName(), ex);
//                }
//            }
//        }
//    }
//
//    Object castParameterValue(String value, Class clazz) {
//
//        // null and empty strings
//        if (value == null) return null;
//        if (value.trim().equals("")) return null;
//        
//        try {
//            if( Boolean.class == clazz || Boolean.TYPE == clazz) return Boolean.parseBoolean(value );
//
//            if( Byte.class == clazz || Byte.TYPE == clazz) return Byte.parseByte( value );
//            if( Short.class == clazz || Short.TYPE == clazz) return Short.parseShort( value );
//            if( Integer.class == clazz || Integer.TYPE == clazz) return Integer.parseInt( value );
//
//            if( Long.class == clazz || Long.TYPE == clazz) return Long.parseLong( value );
//            if( Float.class == clazz || Float.TYPE == clazz) return Float.parseFloat( value );
//            if( Double.class == clazz || Double.TYPE == clazz) return Double.parseDouble( value );
//
//            if (String.class == clazz) return value;
//
//            if (BigDecimal.class == clazz) return new BigDecimal(value);
//            if (BigInteger.class == clazz) return new BigInteger(value);
//        } catch (NumberFormatException e) {
//            throw new MapperRuntimeException("Could not cast value " + value + " to " + clazz.getName(), e);
//        }        
//        
//        throw new MapperRuntimeException("Could not cast to " + clazz.getName());
//    }
}
