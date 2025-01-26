package dte.desktobeauty.desktop;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dte.desktobeauty.exceptions.UnsupportedExtensionException;
import dte.desktobeauty.utils.FileUtils;

public class DesktopPicture 
{
	private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList("png", "jpg", "jpeg"));

	/**
	 * Sets a new desktop picture.
	 * 
	 * @param picturePath The path to the new background picture.
	 * @throws UnsupportedExtensionException If the provided path's extension either doesn't represent a picture, or not supported.
	 */
	public static void set(Path picturePath) throws UnsupportedExtensionException
	{
		if(!isSupportedExtension(picturePath))
			throw new UnsupportedExtensionException(FileUtils.getExtension(picturePath));
		
		User32.INSTANCE.SystemParametersInfo(0x0014, 0, picturePath.toString(), 1);
	}

	/**
	 * Checks whether the file at the provided {@code path} can be used as a desktop background.
	 * 
	 * @param picturePath The path to check.
	 * @return Whether the file at the provided path can be used as a desktop background.
	 */
	public static boolean isSupportedExtension(Path picturePath) 
	{
		return ALLOWED_EXTENSIONS.contains(FileUtils.getExtension(picturePath));
	}
}
