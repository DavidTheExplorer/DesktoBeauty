package dte.desktobeauty.exceptions;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.commons.lang3.exception.ExceptionUtils;

import dte.desktobeauty.state.State;
import dte.desktobeauty.utils.AlertUtils;

public class PopupExceptionHandler implements UncaughtExceptionHandler
{
	@Override
	public void uncaughtException(Thread thread, Throwable throwable)
	{
		String stackTrace = ExceptionUtils.getStackTrace(throwable);
		
		switch(State.current()) 
		{
			case INITIALIZATION -> AlertUtils.error("Error during initialization:", stackTrace);
			case RUNNING -> AlertUtils.error("Error while switching a Background Picture:", stackTrace);
		}

		System.exit(1);
	}
}