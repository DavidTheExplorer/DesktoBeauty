package dte.desktobeauty.desktop;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dte.desktobeauty.exceptions.UnsupportedExtensionException;
import dte.desktobeauty.utils.FileUtils;
import dte.desktobeauty.utils.User32;

public class DesktopPicture 
{
	private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList("png", "jpg", "jpeg"));

	public static void set(Path picturePath) throws UnsupportedExtensionException
	{
		checkExtension(picturePath);
		
		User32.INSTANCE.SystemParametersInfo(0x0014, 0, picturePath.toString(), 1);
	}

	private static void checkExtension(Path picturePath) 
	{
		String extension = FileUtils.getExtension(picturePath);
		
		if(!ALLOWED_EXTENSIONS.contains(extension))
			throw new UnsupportedExtensionException(extension);
	}
}
