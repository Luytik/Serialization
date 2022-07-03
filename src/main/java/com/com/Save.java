package com.com;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Save {
    //Означає чи шукати поля у вкладеному класі. true - продовжувати, false - ні.
    boolean searchInside() default false;
}
