package controller;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super("Car ID not found : " + id);
    }

}
