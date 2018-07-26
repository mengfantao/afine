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
public @interface FindRecyclerView {
    int value() default 0;
    String recyclerViewName() default "";
}