package com.ym.ms.exception;

/**
 * 校验异常
 *
 * @author sys
 * @date 9.5
 */
public class ValidatParameterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidatParameterException() {
        super();
    }

    public ValidatParameterException(String message) {
        super(message);
    }
}

