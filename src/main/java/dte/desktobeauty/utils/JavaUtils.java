package dte.desktobeauty.utils;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class JavaUtils
{
	public static void sleep(Duration duration) throws InterruptedException 
	{
		Thread.sleep(duration.toMillis());
	}
	
	public static String getExtension(Path path)
	{
		String pathString = path.toString();
		
		return pathString.substring(pathString.lastIndexOf('.')+1).toLowerCase();
	}
	
	public static <E, L extends List<E>> L randomized(L list)
	{
		Collections.shuffle(list);

		return list;
	}
}
