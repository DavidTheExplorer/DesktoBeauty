package dte.desktobeauty;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dte.desktobeauty.desktop.DesktopPicture;
import dte.desktobeauty.elementselector.ElementSelector;
import dte.desktobeauty.elementselector.RandomElementSelector;
import dte.desktobeauty.utils.FileUtils;

public class DesktoBeauty
{
	private static final Logger LOGGER = LogManager.getLogger(DesktoBeauty.class);
	
	public static void main(String[] args) throws Exception
	{
		List<Path> backgroundPictures = parseBackgroundPictures(args[0]);
		ElementSelector<Path> pictureSelector = parseElementSelector(args);

		while(true) 
		{
			Path selectedPicture = pictureSelector.selectFrom(backgroundPictures);
			
			LOGGER.info("Setting a new background: \"{}\"", FileUtils.getNameWithoutExtension(selectedPicture));
			
			DesktopPicture.set(selectedPicture);
			
			TimeUnit.SECONDS.sleep(1);
		}
	}

	private static ElementSelector<Path> parseElementSelector(String[] args)
	{
		if(args.length == 0) 
			return new RandomElementSelector<>();
			
		return ElementSelector.fromName(args[0]);
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