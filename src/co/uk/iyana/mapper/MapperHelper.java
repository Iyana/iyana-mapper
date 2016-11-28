/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.uk.iyana.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author fgyara
 */
class MapperHelper {

    static String getPrefix(String namespace, String delimiter) {
        String prefix = null;
        if (namespace != null) {
            prefix = namespace;
        }

        if ((prefix != null) && (delimiter != null)) {
            prefix = prefix + delimiter;
        }
        
        return prefix;
    }
    
    static Object castParameterValue(FieldConfig fieldConfig, String value, Class clazz, Locale locale, MapperConfiguration config) {

        // null and empty strings
        if (value == null) return null;
        if (value.trim().equals("")) return null;
        
        try {
            if( Boolean.class == clazz || Boolean.TYPE == clazz) return getBooleanValue( fieldConfig, value, config );

            if( Byte.class == clazz || Byte.TYPE == clazz) return Byte.parseByte( value );
            if( Short.class == clazz || Short.TYPE == clazz) return Short.parseShort( value );
            if( Integer.class == clazz || Integer.TYPE == clazz) return Integer.parseInt( value );
            if( Long.class == clazz || Long.TYPE == clazz) return Long.parseLong( value );
            
            if( Float.class == clazz || Float.TYPE == clazz) return Float.parseFloat( value );
            if( Double.class == clazz || Double.TYPE == clazz) return Double.parseDouble( value );

            if (String.class == clazz) return value;

            if (BigDecimal.class == clazz) return new BigDecimal(value);
            if (BigInteger.class == clazz) return new BigInteger(value);
            
            if (Date.class == clazz) return getDateValue( fieldConfig, value, locale, config);
            
        } catch (NumberFormatException e) {
            throw new MapperRuntimeException("Could not cast value " + value + " to " + clazz.getName(), e);
        }        
        
        throw new MapperRuntimeException("Could not cast to " + clazz.getName());
    }

    private static Boolean getBooleanValue(FieldConfig fieldConfig, String value, MapperConfiguration config) {
        ArrayList<String> trueList = config.getMapToTrue();
        ArrayList<String> falseList = config.getMapToFalse();
        
        if (fieldConfig != null) {
            if (!fieldConfig.getMapToTrue().isEmpty()) trueList = fieldConfig.getMapToTrue();
            if (!fieldConfig.getMapToFalse().isEmpty()) falseList = fieldConfig.getMapToFalse();
        }
        
        if (trueList.contains(value.toUpperCase())) return true;
        
        if (falseList.contains(value.toUpperCase())) return false;
        
        return null;
    }
    
    
    private static Date getDateValue(FieldConfig fieldConfig, String value, Locale locale, MapperConfiguration config) {
        ArrayList<String> formats = config.getDateFormat();
        
        if (fieldConfig != null) {
            if (!fieldConfig.getDateFormat().isEmpty()) formats = fieldConfig.getDateFormat();
        }
        
        // loop through the formats until one of them works
        for (String format : formats) {
            DateFormat df = new SimpleDateFormat(format, locale);
            try {
                return df.parse(value);
            } catch (ParseException ex) {
                // try the next one
            }
        }
        return null;
    }    
    
}
