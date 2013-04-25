package com.interzonedev.dataporter.service;

public class DataExportException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataExportException() {
	}

	public DataExportException(String message) {
		super(message);
	}

	public DataExportException(Throwable t) {
		super(t);
	}

	public DataExportException(String message, Throwable t) {
		super(message, t);
	}

}
