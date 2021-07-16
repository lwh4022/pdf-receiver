package app.services;

import org.springframework.stereotype.Service;

import java.io.InvalidClassException;

public class ProcessingCommander {

    public ProcessingInterface processingInterface;

    public ProcessingCommander(ProcessingInterface processingInterface){
        this.processingInterface = processingInterface;
    }

    public Object process (Object parameter) throws InvalidClassException {
        return this.processingInterface.process(parameter);
    }
}
