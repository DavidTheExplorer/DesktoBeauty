package dte.desktobeauty;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dte.desktobeauty.desktop.DesktopPicture;
import dte.desktobeauty.elementselector.ElementSelector;
import dte.desktobeauty.elementselector.RandomElementSelector;

public class DesktoBeauty
{
	public static void main(String[] args) throws Exception
	{
		List<Path> backgroundPictures = parseBackgroundPictures(args[0]);
		ElementSelector<Path> pictureSelector = parseElementSelector(args);

		while(true) 
		{
			DesktopPicture.set(pictureSelector.selectFrom(backgroundPictures));
			
			TimeUnit.SECONDS.sleep(1);
		}
	}

	private static ElementSelector<Path> parseElementSelector(String[] args)
	{
		if(args.length == 0) 
			return new RandomElementSelector<>();
			
		return ElementSelector.fromName(args[0]);
	}

	private static List<Path> parseBackgroundPictures(String path)
	{
		File backgroundsFolder = new File(path);

		if(!backgroundsFolder.exists())
		{
			System.err.println(String.format("Cannot find the specified directory: %s", path));
			System.exit(0);
		}

		File[] pictures = backgroundsFolder.listFiles();

		if(pictures.length == 0)
		{
			System.err.println("The backgrounds folder is empty!");
			System.err.println("Closing...");
			System.exit(1);
		}
		
		return Arrays.stream(pictures)
				.map(File::toPath)
				.collect(toList());
	}
}