/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.uk.iyana.mapper.annotation;

/**
 *
 * @author fgyara
 */
public @interface Mapper {
    boolean ignore() default false;
    String[] format() default {};
    String mapTrue() default "";
    String mapFalse() default "";
}
