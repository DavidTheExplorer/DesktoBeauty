package dte.desktobeauty.state;

import dte.desktobeauty.exceptions.PopupExceptionHandler;

/**
 * Represents the state of the program. Mainly used by {@link PopupExceptionHandler} to determine the correct error message.
 */
public enum State
{
	/**
	 * When the program is initializing stuff invisible to the user.
	 */
	STATELESS,
	
	/**
	 * When the program is setting the System Tray Icon.
	 */
	SETTING_SYSTEM_TRAY,
	
	/**
	 * When the program is changing background pictures.
	 */
	RUNNING;
	
	private static State currentState = STATELESS;
	
	public static State current() 
	{
		return currentState;
	}
	
	public static void set(State currentState) 
	{
		State.currentState = currentState;
	}
}
