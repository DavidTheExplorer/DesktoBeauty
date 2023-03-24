package dte.desktobeauty;

import static dte.desktobeauty.state.State.RUNNING;
import static dte.desktobeauty.state.State.SETTING_SYSTEM_TRAY;
import static java.util.stream.Collectors.toList;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dte.desktobeauty.desktop.DesktopPicture;
import dte.desktobeauty.elementselector.ElementSelector;
import dte.desktobeauty.state.State;
import dte.desktobeauty.utils.FileUtils;
import dte.desktobeauty.utils.SystemTrayBuilder;
import dte.desktobeauty.utils.TimeUtils;

public class DesktoBeauty
{
	private static final Logger LOGGER = LogManager.getLogger(DesktoBeauty.class);
	
	private static final Path BACKGROUNDS_FOLDER_PATH = Path.of(System.getProperty("user.home"), "DesktoBeauty", "Desktop Backgrounds");

	public static void main(String[] args) throws Exception
	{
		setGlobalExceptionLogger();
		showSystemTray();
		State.set(RUNNING);

		Duration changeDelay = TimeUtils.parseDuration(args[0]);
		ElementSelector<Path> pictureSelector = ElementSelector.fromName(args[1]);
		List<Path> backgroundPictures = loadBackgroundPictures();
		
		LOGGER.info("Starting to change your Desktop's background every {}!", args[0]);
		LOGGER.info("-~-Settings -~-");
		LOGGER.info("» Backgrounds Amount: {}", backgroundPictures.size());
		LOGGER.info("» Picture Selector: {}", pictureSelector.getName());
		LOGGER.info("");

		while(true) 
		{
			//wait before setting a new background picture
			Thread.sleep(changeDelay.toMillis());
			
			//select a picture
			Path selectedPicture = pictureSelector.selectFrom(backgroundPictures);
			
			//set it & print the result(success or failure)
			String pictureName = FileUtils.getNameWithoutExtension(selectedPicture);
			
			if(!DesktopPicture.isSupportedExtension(selectedPicture)) 
			{
				LOGGER.error("Failed to set the background to \"{}\" due to an unsupported extension! The allowed ones are: {}.", pictureName, String.join(", ", DesktopPicture.getAllowedExtensions()));
				continue;
			}
			
			DesktopPicture.set(selectedPicture);
			LOGGER.info("New Background: \"{}\"", pictureName);
		}
	}

	private static List<Path> loadBackgroundPictures() throws IOException
	{
		if(Files.notExists(BACKGROUNDS_FOLDER_PATH))
		{
			LOGGER.info("Please wait while creating the Backgrounds Folder at \"{}\"...", BACKGROUNDS_FOLDER_PATH.toString());
			Files.createDirectories(BACKGROUNDS_FOLDER_PATH);
			LOGGER.info("Done!");
			System.exit(0);
		}

		List<Path> backgrounds = Files.list(BACKGROUNDS_FOLDER_PATH).collect(toList());

		if(backgrounds.isEmpty())
		{
			LOGGER.error("The Backgrounds Folder is empty - You have to insert at least one background!");
			System.exit(1);
		}
		
		return backgrounds;
	}

	private static void setGlobalExceptionLogger() 
	{
		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> 
		{
			State state = State.current();

			switch(state) 
			{
			case RUNNING:
				LOGGER.error("Error while switching a Background Picture", throwable);
				break;

			case SETTING_SYSTEM_TRAY:
				String action = (throwable instanceof IOException ? "reading" : "displaying");

				LOGGER.error("Error while {} the System-Tray's Image", action, throwable);
				break;

			default:
				LOGGER.fatal("NO handler was defined to handle exceptions within the '{}' state! {}", state.name(), throwable);
				System.exit(1);
				break;
			}
		});
	}

	private static void showSystemTray() throws AWTException, IOException 
	{
		State.set(SETTING_SYSTEM_TRAY);

		new SystemTrayBuilder()
		.withTooltip("DesktoBeauty")
		.withIcon(ImageIO.read(DesktoBeauty.class.getResource("/System Tray.png")))

		//Open Main Folder
		.withMenuItem("Open Backgrounds Folder", event ->
		{
			try 
			{
				Desktop.getDesktop().open(BACKGROUNDS_FOLDER_PATH.toFile());
			} 
			catch(IOException exception) 
			{
				LOGGER.error("Cannot open the backgrounds folder!", exception);
			}
		})

		//Stop 
		.withMenuItem("Stop", event -> System.exit(0))

		.display();
	}
}