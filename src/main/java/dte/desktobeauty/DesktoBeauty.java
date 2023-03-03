package dte.desktobeauty;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dte.desktobeauty.desktop.DesktopPicture;
import dte.desktobeauty.elementselector.ElementSelector;
import dte.desktobeauty.exceptions.UnsupportedExtensionException;
import dte.desktobeauty.utils.FileUtils;

public class DesktoBeauty
{
	private static final Logger LOGGER = LogManager.getLogger(DesktoBeauty.class);
	
	public static void main(String[] args) throws Exception
	{
		Duration changeDelay = Duration.ofMinutes(Integer.valueOf(args[0]));
		ElementSelector<Path> pictureSelector = ElementSelector.fromName(args[1]);
		List<Path> backgroundPictures = parseBackgroundPictures(args[2]);
		
		LOGGER.info("Starting to change your Desktop's background every {} minutes!", changeDelay.toMinutes());
		LOGGER.info("");

		while(true) 
		{
			//wait before setting a new background picture
			Thread.sleep(changeDelay.toMillis());
			
			//select a picture
			Path selectedPicture = pictureSelector.selectFrom(backgroundPictures);
			
			//set it & print the result(success or failure)
			String pictureName = FileUtils.getNameWithoutExtension(selectedPicture);
			
			try 
			{
				DesktopPicture.set(selectedPicture);
				LOGGER.info("Setting a new background: \"{}\"", pictureName);
			}
			catch(UnsupportedExtensionException exception) 
			{
				LOGGER.error("Failed to set the background to \"{}\" because it has an unsupported extension: '{}'", pictureName, exception.getExtension());
			}
		}
	}

	private static List<Path> parseBackgroundPictures(String folderPath) throws IOException
	{
		Path path = Path.of(folderPath);

		if(!Files.isDirectory(path))
		{
			LOGGER.error("Cannot find the specified backgrounds folder: '{}'", path);
			System.exit(1);
		}

		List<Path> backgrounds = Files.list(path).collect(toList());

		if(backgrounds.isEmpty())
		{
			LOGGER.error("The backgrounds folder is empty - You have to insert at least one!");
			LOGGER.error("Closing...");
			System.exit(1);
		}
		
		return backgrounds;
	}
}