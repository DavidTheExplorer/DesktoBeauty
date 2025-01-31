package dte.desktobeauty.utils;

import java.nio.file.Path;
import java.util.Set;

public class FileUtils 
{
	public static final Set<String> PICTURE_EXTENSIONS = Set.of("png", "jpg", "jpeg", "bmp");

	public static String getExtension(Path path)
	{
		String pathString = path.toString();

		return pathString.substring(pathString.lastIndexOf('.') +1).toLowerCase();
	}

	public static boolean isPicture(Path path)
	{
		return PICTURE_EXTENSIONS.contains(getExtension(path));
	}
}
