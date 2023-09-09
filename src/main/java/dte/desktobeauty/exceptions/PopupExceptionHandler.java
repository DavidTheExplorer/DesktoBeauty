package dte.desktobeauty.exceptions;

import java.io.IOException;
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
		case RUNNING:
			AlertUtils.error("Error while switching a Background Picture:", stackTrace);
			break;

		case SETTING_SYSTEM_TRAY:
			String action = (throwable instanceof IOException ? "reading" : "displaying");

			AlertUtils.error(String.format("Error while %s the System-Tray's Image:", action), stackTrace);
			break;

		default:
			AlertUtils.error(String.format("NO handler was defined to handle exceptions within the '%s' state:", State.current().name()), stackTrace);
			break;
		}

		System.exit(1);
	}
}