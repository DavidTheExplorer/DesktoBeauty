package dte.desktobeauty.desktop;

import java.nio.file.Path;
import java.util.Set;

import dte.desktobeauty.utils.FileUtils;

public class DesktopWallpaper
{
	private static final Set<String> ALLOWED_EXTENSIONS = Set.of("png", "jpg", "jpeg", "bmp");

	/**
	 * Sets a new desktop wallpaper.
	 * 
	 * @param wallpaperFile The new wallpaper.
	 * @throws IllegalArgumentException If the provided {@code wallpaper} file's extension doesn't represent a picture.
	 */
	public static void set(Path wallpaperFile)
	{
		if(!isAllowedExtension(wallpaperFile))
			throw new IllegalArgumentException(String.format("Cannot set the desktop wallpaper to a \"%s\" file; The allowed extensions are: %s.", FileUtils.getExtension(wallpaperFile), formatAllowedExtensions()));
		
		User32.INSTANCE.SystemParametersInfo(0x0014, 0, wallpaperFile.toString(), 1);
	}

	/**
	 * Checks whether the provided {@code wallpaper path} represents a picture.
	 * 
	 * @param wallpaperFile The wallpaper file to check.
	 * @return Whether the wallpaper file represents a picture.
	 */
	public static boolean isAllowedExtension(Path wallpaperFile)
	{
		return ALLOWED_EXTENSIONS.contains(FileUtils.getExtension(wallpaperFile));
	}

	private static String formatAllowedExtensions()
	{
		return '[' + String.join(", ", ALLOWED_EXTENSIONS) + ']';
	}
}
