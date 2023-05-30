package dte.desktobeauty.utils;

import static dte.desktobeauty.utils.UncheckedExceptions.unchecked;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AlertUtils
{
	public static void info(String... message)
	{
		display(JOptionPane.INFORMATION_MESSAGE, message);
	}
	
	public static void error(String... message) 
	{
		display(JOptionPane.ERROR_MESSAGE, message);
	}
	
	private static void display(int messageType, String... message) 
	{
		JOptionPane.showMessageDialog(null, message, "DesktoBeauty", messageType);
	}
	
	
	
	static 
	{
		unchecked(() -> UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())).run();
	}
}
