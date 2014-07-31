package com.interzonedev.dataporter.service.exporter;

public class DataExporterException extends Exception {

    private static final long serialVersionUID = 1L;

    public DataExporterException() {
    }

    public DataExporterException(String message) {
        super(message);
    }

    public DataExporterException(Throwable t) {
        super(t);
    }

    public DataExporterException(String message, Throwable t) {
        super(message, t);
    }

}
