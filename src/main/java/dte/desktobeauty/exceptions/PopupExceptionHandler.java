package dte.desktobeauty.exceptions;

import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

import dte.desktobeauty.state.State;
import dte.desktobeauty.utils.AlertUtils;

public class PopupExceptionHandler implements UncaughtExceptionHandler
{
	@Override
	public void uncaughtException(Thread thread, Throwable throwable)
	{
		String message = throwable.getMessage();
		State state = State.current();

		switch(state) 
		{
		case RUNNING:
			AlertUtils.error("Error while switching a Background Picture:", message);
			break;

		case SETTING_SYSTEM_TRAY:
			String action = (throwable instanceof IOException ? "reading" : "displaying");

			AlertUtils.error(String.format("Error while %s the System-Tray's Image:", action), message);
			break;

		default:
			AlertUtils.error(String.format("NO handler was defined to handle exceptions within the '%s' state:", state.name()), message);
			break;
		}

		System.exit(1);
	}
}
