package org.example;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(Exception exception) {
        super("Invalid file", exception);
    }
}