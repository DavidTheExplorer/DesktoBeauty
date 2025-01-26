package dte.desktobeauty;

import static dte.desktobeauty.state.State.RUNNING;
import static java.util.stream.Collectors.toList;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import javax.imageio.ImageIO;

import com.machinezoo.noexception.Exceptions;
import dte.desktobeauty.desktop.DesktopPicture;
import dte.desktobeauty.exceptions.PopupExceptionHandler;
import dte.desktobeauty.pictureselector.PictureSelector;
import dte.desktobeauty.state.State;
import dte.desktobeauty.utils.AlertUtils;
import dte.desktobeauty.utils.SystemTrayBuilder;
import dte.desktobeauty.utils.TimeUtils;

public class DesktoBeauty
{
	private static final Path BACKGROUNDS_FOLDER_PATH = Paths.get(System.getProperty("user.home"), "DesktoBeauty", "Desktop Backgrounds");

	public static void main(String[] args) throws Exception
	{
		//init
		Thread.setDefaultUncaughtExceptionHandler(new PopupExceptionHandler());
		showSystemTray();

		//parse the arguments
		Duration changeDelay = TimeUtils.parseDuration(args[0]);
		PictureSelector pictureSelector = PictureSelector.fromName(args[1]);
		List<Path> backgroundPictures = loadBackgroundPictures();

		State.set(RUNNING);
		
		while(true) 
		{
			//wait before setting a new background picture
			Thread.sleep(changeDelay.toMillis());
			
			//set a new picture
			DesktopPicture.set(pictureSelector.selectFrom(backgroundPictures));
		}
	}

	private static List<Path> loadBackgroundPictures() throws IOException
	{
		if(Files.notExists(BACKGROUNDS_FOLDER_PATH))
		{
			Files.createDirectories(BACKGROUNDS_FOLDER_PATH);
			AlertUtils.error("Successfully created your Backgrounds Folder at:", BACKGROUNDS_FOLDER_PATH.toString(), " ", "Click on OK to open it.");
			openBackgroundsFolder();
			
			System.exit(0);
		}

		List<Path> backgrounds = Files.walk(BACKGROUNDS_FOLDER_PATH)
				.filter(DesktopPicture::isSupportedExtension)
				.collect(toList());

		if(backgrounds.isEmpty())
		{
			AlertUtils.error("Your Backgrounds Folder is empty!", "You have to insert at least one background.", " ", "Opening...");
			openBackgroundsFolder();
			
			System.exit(1);
		}
		
		return backgrounds;
	}

	private static void showSystemTray() throws AWTException, IOException 
	{
		new SystemTrayBuilder()
		.withTooltip("DesktoBeauty")
		.withIcon(ImageIO.read(DesktoBeauty.class.getResource("/System Tray.png")))
		.withMenuItem("Open Backgrounds Folder", event -> openBackgroundsFolder())
		.withMenuItem("Stop", event -> System.exit(0))
		.display();
	}
	
	private static void openBackgroundsFolder()
	{
		Exceptions.sneak().run(() -> Desktop.getDesktop().open(BACKGROUNDS_FOLDER_PATH.toFile()));
	}
}