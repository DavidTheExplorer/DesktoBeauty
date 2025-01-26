package dte.desktobeauty.utils;

import java.nio.file.Path;

public class FileUtils 
{
	public static String getExtension(Path path)
	{
		String pathString = path.toString();

		return pathString.substring(pathString.lastIndexOf('.') +1).toLowerCase();
	}
}
