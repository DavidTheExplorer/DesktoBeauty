package dte.desktobeauty;

import static dte.desktobeauty.state.State.INITIALIZATION;
import static dte.desktobeauty.state.State.RUNNING;
import static java.util.stream.Collectors.toList;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import javax.imageio.ImageIO;

import com.machinezoo.noexception.Exceptions;
import dte.desktobeauty.desktop.DesktopWallpaper;
import dte.desktobeauty.exceptions.PopupExceptionHandler;
import dte.desktobeauty.state.State;
import dte.desktobeauty.utils.AlertUtils;
import dte.desktobeauty.utils.TimeUtils;
import dte.desktobeauty.utils.TrayIconBuilder;
import dte.desktobeauty.wallpaperselector.WallpaperSelector;

public class DesktoBeauty
{
	private static final Path WALLPAPER_FOLDER = Paths.get(System.getProperty("user.home"), "DesktoBeauty", "Desktop Wallpapers");

	public static void main(String[] args) throws Exception
	{
		initialize();

		//parse the arguments
		Duration changeDelay = TimeUtils.parseDuration(args[0]);
		WallpaperSelector wallpaperSelector = WallpaperSelector.fromName(args[1]);
		List<Path> wallpapersFiles = loadWallpaperFiles();

		State.set(RUNNING);
		
		while(true) 
		{
			DesktopWallpaper.set(wallpaperSelector.selectFrom(wallpapersFiles));

			//wait before setting a new wallpaper`
			Thread.sleep(changeDelay.toMillis());
		}
	}

	private static void initialize() throws Exception
	{
		State.set(INITIALIZATION);
		Thread.setDefaultUncaughtExceptionHandler(new PopupExceptionHandler());
		showTrayIcon();
	}

	private static List<Path> loadWallpaperFiles() throws IOException
	{
		if(Files.notExists(WALLPAPER_FOLDER))
		{
			Files.createDirectories(WALLPAPER_FOLDER);
			AlertUtils.info("Successfully created your Wallpaper Folder!", "Click OK to open it.");
			openWallpaperFolder();
			
			System.exit(0);
		}

		List<Path> wallpaperFiles = Files.walk(WALLPAPER_FOLDER)
				.filter(DesktopWallpaper::isAllowedExtension)
				.collect(toList());

		if(wallpaperFiles.isEmpty())
		{
			AlertUtils.error("Your Wallpaper Folder is empty!", "You have to insert at least one.", " ", "Click OK to open it.");
			openWallpaperFolder();
			
			System.exit(1);
		}
		
		return wallpaperFiles;
	}

	private static void showTrayIcon() throws AWTException, IOException
	{
		Image image = ImageIO.read(DesktoBeauty.class.getResource("/System Tray.png"));

		new TrayIconBuilder()
		.withTooltip("DesktoBeauty")
		.withImage(image)
		.withMenuItem("Open Wallpaper Folder", unused -> openWallpaperFolder())
		.withMenuItem("Stop", unused -> System.exit(0))
		.display();
	}
	
	private static void openWallpaperFolder()
	{
		Exceptions.sneak().run(() -> Desktop.getDesktop().open(WALLPAPER_FOLDER.toFile()));
	}
}