package com.interzonedev.dataporter.service.importer;

public class DataImporterException extends Exception {

    private static final long serialVersionUID = 1L;

    public DataImporterException() {
    }

    public DataImporterException(String message) {
        super(message);
    }

    public DataImporterException(Throwable t) {
        super(t);
    }

    public DataImporterException(String message, Throwable t) {
        super(message, t);
    }

}
