package com.acme.facemash.util;

import com.acme.facemash.util.Functionnal.Function;
import javax.ejb.Stateless;

@Stateless
public class ApplicationBoundary {
    
    public <T, R> R apply(Function<T, R> function, T arg) {
        return function.apply(arg);
    } 
}
