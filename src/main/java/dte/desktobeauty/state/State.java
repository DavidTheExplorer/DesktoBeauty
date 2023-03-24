package dte.desktobeauty.state;

public enum State
{
	/**
	 * Describes the period when the program is initializing stuff invisible to the user.
	 */
	STATELESS,
	
	/**
	 * Describes the period when the program is setting the System Tray Icon.
	 */
	SETTING_SYSTEM_TRAY,
	
	/**
	 * Describes the period when the program is changing background pictures.
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
