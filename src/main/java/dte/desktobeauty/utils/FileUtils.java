package dte.desktobeauty.utils;

import java.nio.file.Path;

public class FileUtils 
{
	public static String getExtension(Path path)
	{
		String pathString = path.toString();
		
		return pathString.substring(pathString.lastIndexOf('.')+1).toLowerCase();
	}
	
	public static String getNameWithoutExtension(Path path) 
	{
		String fileName = path.getFileName().toString();
		
		return fileName.substring(0, fileName.indexOf(getExtension(path)) -1);
	}
}
