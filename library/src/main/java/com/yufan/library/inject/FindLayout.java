package com.yufan.library.inject;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Created by mengfantao on 18/7/26.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FindLayout {
    int layout()default 0;
    int statusLayoutParent() default 0;
    String layoutName()default "";
    String statusLayoutParentName()default "";
}