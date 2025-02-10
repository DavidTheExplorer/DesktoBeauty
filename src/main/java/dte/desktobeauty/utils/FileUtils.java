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

	public static String getFileNameWithoutExtension(Path path)
	{
		String fileName = path.getFileName().toString();
		int extensionIndex = fileName.indexOf(getExtension(path));

		return fileName.substring(0, extensionIndex -1);
	}

	public static boolean isPicture(Path path)
	{
		return PICTURE_EXTENSIONS.contains(getExtension(path));
	}
}
