package dte.desktobeauty.exceptions;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.commons.lang3.exception.ExceptionUtils;

import dte.desktobeauty.utils.AlertUtils;

public class PopupExceptionHandler implements UncaughtExceptionHandler
{
	@Override
	public void uncaughtException(Thread thread, Throwable throwable)
	{
		AlertUtils.error(createErrorMessage(throwable));
		System.exit(1);
	}

	private static String[] createErrorMessage(Throwable throwable)
	{
		String stackTrace = ExceptionUtils.getStackTrace(throwable);

		return new String[]{"The program has encountered an error and will shutdown.", "Please report the following:", " ", stackTrace};
	}
}