package dte.desktobeauty.desktop;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dte.desktobeauty.utils.JavaUtils;
import dte.desktobeauty.utils.User32;

public class DesktopPicture 
{
	private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList("png", "jpg", "jpeg"));

	public static void set(Path picturePath) 
	{
		checkExtension(picturePath);
		
		User32.INSTANCE.SystemParametersInfo(0x0014, 0, picturePath.toString(), 1);
	}

	private static void checkExtension(Path picturePath) 
	{
		String extension = JavaUtils.getExtension(picturePath);
		
		if(!ALLOWED_EXTENSIONS.contains(extension))
			throw new IllegalArgumentException(String.format("The provided picture's extension(%s) is not supported!", extension));
	}
}