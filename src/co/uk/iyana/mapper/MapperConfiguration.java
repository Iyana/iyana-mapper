/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.uk.iyana.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author fgyara
 */
public class MapperConfiguration {
    protected final HashMap<String, FieldConfig> fieldConfigsByParamName = new HashMap();
    protected final HashMap<String, FieldConfig> fieldConfigsByFieldName = new HashMap();
    
    private boolean compiled = false;
    
    private ArrayList<String> ignoreParameters;
    
    private final ArrayList<String> mapToTrue = new ArrayList();
    private final ArrayList<String> mapToFalse = new ArrayList();
    private static final String[] DEFAULT_MAP_TO_TRUE = { "TRUE", "1"};
    private static final String[] DEFAULT_MAP_TO_FALSE = { "FALSE", "0"};
    
    private final ArrayList<String> dateFormat = new ArrayList();
    private static final String[] DEFAULT_DATE_FORMAT = { "YYYY-MM-DD"};
    
    public MapperConfiguration configureField(FieldConfig fieldConfig) {
        if (compiled) {
            throw new MapperRuntimeException("Cannot change configuration once it has configured a mapper");
        }
        
        this.fieldConfigsByParamName.put( fieldConfig.getParamName(), fieldConfig);
        
        String fieldName = fieldConfig.getFieldName();
        if (fieldName == null) fieldName = fieldConfig.getParamName();
        this.fieldConfigsByFieldName.put( fieldName, fieldConfig);
        return this;
    }

//    public void dumpConfig() {
//        for (String fieldName: fieldConfigsByParamName.keySet()) {
//            System.out.println("Config for " + fieldName);
//            System.out.println( this.fieldConfigsByParamName.get(fieldName).toString() );
//        }
//    }
    
    public boolean ignoreParameter(String fieldName) {
        return this.ignoreParameters.contains(fieldName);
    }
    
    public void compile() {
        
        // already compiled - no need to do it again
        if (this.compiled) {
            return;
        }
        
        // ensure no more entries are added
        this.compiled = true;
        
        this.ignoreParameters = new ArrayList();
        
        // loop through the fields
        for (FieldConfig fieldConfig : this.fieldConfigsByParamName.values()) {
            // is it on the ignore list ?
            if (fieldConfig.isIgnore()) {
                this.ignoreParameters.add(fieldConfig.getParamName());
            }
        }
        
        // if mapToTrue and mapToFalse are empty, populate them with defaults
        if (mapToTrue.isEmpty()) {
            mapToTrue.addAll( Arrays.asList(DEFAULT_MAP_TO_TRUE));
        }
        
        if (mapToFalse.isEmpty()) {
            mapToFalse.addAll( Arrays.asList(DEFAULT_MAP_TO_FALSE));
        }

        // if the date format is empty, use the default
        if (dateFormat.isEmpty()) {
            dateFormat.addAll( Arrays.asList(DEFAULT_DATE_FORMAT));
        }
        
        System.out.println( "By param Name: " + this.fieldConfigsByParamName.toString());
        System.out.println( "By field Name: " + this.fieldConfigsByFieldName.toString());
    }

    String getFieldNameFromParamName(String paramName) {
        FieldConfig fieldConfig = this.fieldConfigsByParamName.get(paramName);
        
        if (fieldConfig == null) return paramName;
        if (fieldConfig.getFieldName() == null) return paramName;
        
        return fieldConfig.getFieldName();
    }

    String getParamNameFromFieldName(String fieldName) {
        FieldConfig fieldConfig = this.fieldConfigsByFieldName.get(fieldName);
        
        if (fieldConfig == null) return fieldName;
        if (fieldConfig.getFieldName() == null) return fieldName;
        
        return fieldConfig.getParamName();
    }

    public MapperConfiguration mapToTrue(String value) {
        this.mapToTrue.add(value.toUpperCase());
        return this;
    }
    
    public MapperConfiguration mapToFalse(String value) {
        this.mapToFalse.add(value.toUpperCase());
        return this;
    }

    public MapperConfiguration dateFormat(String dateFormat) {
        this.dateFormat.add(dateFormat);
        return this;
    }

    /**
     * @return the dateFormat
     */
    public ArrayList<String> getDateFormat() {
        return dateFormat;
    }    
    
    /**
     * @return the mapToTrue
     */
    public ArrayList<String> getMapToTrue() {
        return mapToTrue;
    }

    /**
     * @return the mapToFalse
     */
    public ArrayList<String> getMapToFalse() {
        return mapToFalse;
    }
    
}
