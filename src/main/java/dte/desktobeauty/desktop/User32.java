package dte.desktobeauty.desktop;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public interface User32 extends Library
{
	boolean SystemParametersInfo(int one, int two, String text, int three);
	
	User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);
}