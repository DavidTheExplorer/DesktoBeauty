package dte.desktobeauty.utils;

import com.machinezoo.noexception.Exceptions;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AlertUtils
{
	static
	{
		Exceptions.sneak().run(() -> UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()));
	}
	
	public static void error(String... message) 
	{
		display(JOptionPane.ERROR_MESSAGE, message);
	}
	
	private static void display(int messageType, String... message)
	{
		JOptionPane.showMessageDialog(null, message, "DesktoBeauty", messageType);
	}
}
