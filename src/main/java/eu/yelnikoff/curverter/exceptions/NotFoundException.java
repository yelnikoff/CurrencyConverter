package eu.yelnikoff.curverter.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Not found");
    }

}
