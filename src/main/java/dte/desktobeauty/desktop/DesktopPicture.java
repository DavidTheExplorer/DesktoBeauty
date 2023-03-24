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
		if(!isSupportedExtension(picturePath))
			throw new UnsupportedExtensionException(FileUtils.getExtension(picturePath));
		
		User32.INSTANCE.SystemParametersInfo(0x0014, 0, picturePath.toString(), 1);
	}

	public static boolean isSupportedExtension(Path picturePath) 
	{
		return ALLOWED_EXTENSIONS.contains(FileUtils.getExtension(picturePath));
	}
	
	public static Set<String> getAllowedExtensions()
	{
		return new HashSet<>(ALLOWED_EXTENSIONS);
	}
}
