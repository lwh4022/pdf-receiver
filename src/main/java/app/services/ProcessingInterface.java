package app.services;

import java.io.InvalidClassException;

public interface ProcessingInterface {

    Object process(Object parameter) throws InvalidClassException;
}
