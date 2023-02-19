package org.capitole.util.exception;

public record ErrorMessage(Integer statusCode, String message) {
}