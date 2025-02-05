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
	INITIALIZATION,
	
	/**
	 * When the program is changing wallpapers.
	 */
	RUNNING;
	
	private static State currentState;
	
	public static State current() 
	{
		return currentState;
	}
	
	public static void set(State currentState) 
	{
		State.currentState = currentState;
	}
}
