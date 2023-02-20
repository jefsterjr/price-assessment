package org.capitole.util.exception;

public class PriceNotFoundException extends RuntimeException{

    public PriceNotFoundException() {
        super("Price information not found");
    }
}
