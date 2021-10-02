package br.com.fiap.epictask.exception;

public class TaskNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(String string) {
		super(string);
	}


}
