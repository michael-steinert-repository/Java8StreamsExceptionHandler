package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {
    /* Exception Handling:
         1) Problem occurs
         2) Create Exception
         3) Throw Exception
         4) Handle Exception
    */
    public static void main(String[] args) {
        List<String> list = Arrays.asList("42", "43", "MS");

        /*
        list.forEach(string -> {
            try {
                System.out.println(Integer.parseInt(string));
            } catch(Exception exception) {
                System.out.println(String.format("Exception: %s", exception.getMessage()));
            }
        });
        */
        /* Typed Approach */
        list.forEach(handleStringException(string -> System.out.println(Integer.parseInt(string))));
        /* Generic Approach */
        list.forEach(handleGenericException(string -> System.out.println(Integer.parseInt(string)), NumberFormatException.class));
        List<Integer> numberList = Arrays.asList(42, 0);
        numberList.forEach(handleGenericException(integer -> System.out.println(42 / integer), ArithmeticException.class));

        /* Handling Exception for checked Exceptions */
        /*
        numberList.forEach(integer -> {
            try {
                Thread.sleep(integer);
                System.out.println(integer);
            } catch(InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        */
        numberList.forEach(handleGenericCheckedExceptionConsumer(integer->{
            Thread.sleep(integer);
            System.out.println(integer);
        }));

        // List<Integer> integerList = list.stream().map(string -> Integer.parseInt(string)).collect(Collectors.toList());
        // System.out.println(integerList);
    }

    /* Exception Handling for String Inputs */
    static Consumer<String> handleStringException(Consumer<String> input) {
        return (string) -> {
            try {
                input.accept(string);
            } catch (Exception exception) {
                System.out.printf("Exception: %s%n", exception.getMessage());
            }
        };
    }

    /* Generic Exception Handling for Inputs */
    static <T, ExceptionObject extends Exception> Consumer<T> handleGenericException(Consumer<T> input, Class<ExceptionObject> exceptionObjectClass) {
        return (object) -> {
            try {
                input.accept(object);
            } catch (Exception exception) {
                ExceptionObject exceptionObject = exceptionObjectClass.cast(exception);
                System.out.printf("Exception: %s%n", exceptionObject.getMessage());
            }
        };
    }

    static <T> Consumer<T> handleGenericCheckedExceptionConsumer(CheckedExceptionHandlerConsumer<T, Exception> handlerConsumer){
        return (object) -> {
            try {
                handlerConsumer.accept(object);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }
}
