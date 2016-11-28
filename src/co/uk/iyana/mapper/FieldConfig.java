/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.uk.iyana.mapper;

import java.util.ArrayList;

/**
 *
 * @author fgyara
 */
public class FieldConfig {
    private String paramName;
    private String fieldName;
    private boolean ignore;
    private Class type;
    private final ArrayList<String> mapToTrue = new ArrayList();
    private final ArrayList<String> mapToFalse = new ArrayList();
    private final ArrayList<String> dateFormat = new ArrayList();
    
    public FieldConfig paramName(String name) {
        this.paramName = name;
        return this;
    }
    
    

    public FieldConfig fieldName(String mapTo) {
        this.fieldName = mapTo;
        return this;
    }

    public FieldConfig type(Class type) {
        this.type = type;
        return this;
    }


    public FieldConfig ignore() {
        this.ignore = true;
        return this;
    }

    public FieldConfig ignore(boolean ignore) {
        this.ignore = ignore;
        return this;
    }

    /**
     * @return the paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @return the ignore
     */
    public boolean isIgnore() {
        return ignore;
    }

    /**
     * @return the type
     */
    public Class getType() {
        return type;
    }
    
    @Override
    public String toString() {
        String toRet = "[";
        
        toRet += " paramName: " + paramName;
        toRet += " fieldName: " + fieldName;
        toRet += " type: " + type;
        toRet += " ]";
        
        return toRet;
    }

    public FieldConfig mapToTrue(String value) {
        this.getMapToTrue().add(value.toUpperCase());
        return this;
    }
    
    public FieldConfig mapToFalse(String value) {
        this.getMapToFalse().add(value.toUpperCase());
        return this;
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

    public FieldConfig dateFormat(String dateFormat) {
        this.dateFormat.add(dateFormat);
        return this;
    }

    /**
     * @return the dateFormat
     */
    public ArrayList<String> getDateFormat() {
        return dateFormat;
    }


    
}
