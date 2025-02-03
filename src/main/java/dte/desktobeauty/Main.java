package dte.desktobeauty;

import com.machinezoo.noexception.Exceptions;
import dte.desktobeauty.exceptions.PopupExceptionHandler;
import dte.desktobeauty.state.State;
import dte.desktobeauty.utils.AlertUtils;
import dte.desktobeauty.utils.TimeUtils;
import dte.desktobeauty.utils.TrayIconBuilder;
import dte.desktobeauty.wallpaper.Wallpaper;
import dte.desktobeauty.wallpaper.WallpaperSelector;

import javax.imageio.ImageIO;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static dte.desktobeauty.state.State.INITIALIZATION;
import static java.util.stream.Collectors.toList;

public class Main
{
    private static final Path WALLPAPER_FOLDER = Paths.get(System.getProperty("user.home"), "DesktoBeauty", "Desktop Wallpapers");

    public static void main(String[] args) throws Exception
    {
        State.set(INITIALIZATION);
        Thread.setDefaultUncaughtExceptionHandler(new PopupExceptionHandler());
        showTrayIcon();

        List<Wallpaper> wallpapers = loadWallpapers();
        WallpaperSelector wallpaperSelector = WallpaperSelector.fromName(args[1]);
        Duration changeDelay = TimeUtils.parseDuration(args[0]);

        new DesktoBeauty(wallpapers, wallpaperSelector, changeDelay).start();
    }

    private static List<Wallpaper> loadWallpapers() throws IOException
    {
        Files.createDirectories(WALLPAPER_FOLDER);

        List<Wallpaper> wallpapers = Files.walk(WALLPAPER_FOLDER)
                .filter(Wallpaper::isValidFile)
                .map(Wallpaper::of)
                .collect(toList());

        if(wallpapers.isEmpty())
        {
            AlertUtils.error("Your Wallpaper Folder is empty!", "You have to insert at least one.", " ", "Click OK to open it.");
            openWallpaperFolder();
            System.exit(1);
        }

        return wallpapers;
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
