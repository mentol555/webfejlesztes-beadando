package controller;

import java.util.Set;

public class CarUnSupportedFieldPatchException extends RuntimeException {

    public CarUnSupportedFieldPatchException(String name) {
        super("Field " + name + " update is not allow.");
    }

}