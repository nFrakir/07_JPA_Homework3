package com.gmail.silverleaf.annn.exceptions;

public class NotEnoughMoneyException extends Exception {
    @Override
    public String getMessage() {
        return "Not enough money for this operation.";
    }
}
