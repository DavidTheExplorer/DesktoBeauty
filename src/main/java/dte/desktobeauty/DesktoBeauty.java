package dte.desktobeauty;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dte.desktobeauty.elementselector.ElementSelector;
import dte.desktobeauty.elementselector.RandomElementSelector;
import dte.desktobeauty.elementselector.RandomOrderSelector;
import dte.desktobeauty.jna.DesktopPicture;

public class DesktoBeauty
{
	public static void main(String[] args) 
	{
		List<File> backgroundPictures = parseBackgroundPictures(args[0]);
		ElementSelector<File> pictureSelector = parseElementSelector(args[1]);

		while(true) 
		{
			File picture = pictureSelector.selectFrom(backgroundPictures);
			DesktopPicture.set(picture);
			
			//sleep for 1 second
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}
	}

	private static ElementSelector<File> parseElementSelector(String name)
	{
		switch(name.toLowerCase()) 
		{
		case "random":
			return new RandomElementSelector<>();
		case "random-order":
			return new RandomOrderSelector<>();
		default:
			throw new IllegalArgumentException(String.format("Couldn't find a picture selector named '%s'!", name));
		}
	}

	private static List<File> parseBackgroundPictures(String path)
	{
		File backgroundsFolder = new File(path);

		if(!backgroundsFolder.exists())
		{
			backgroundsFolder.mkdir();
			System.out.println(String.format("The Backgrounds Folder was generated at: %s", backgroundsFolder.getPath()));
			System.exit(0);
		}

		File[] pictures = backgroundsFolder.listFiles();

		if(pictures.length == 0)
		{
			System.err.println("Background Folder is Empty - Closing.");
			System.exit(1);
		}
		
		return new ArrayList<>(Arrays.asList(pictures));
	}
}