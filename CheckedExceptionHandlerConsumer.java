package com.company;

@FunctionalInterface
public interface CheckedExceptionHandlerConsumer<T, ExceptionObject extends Exception> {
    /* IF the Interface has one Method it is a Functional Interface */
    public void accept(T input) throws ExceptionObject;
}
