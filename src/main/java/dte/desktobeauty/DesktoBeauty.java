package dte.desktobeauty;

import static dte.desktobeauty.state.State.RUNNING;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import dte.desktobeauty.desktop.DesktopWallpaper;
import dte.desktobeauty.state.State;
import dte.desktobeauty.wallpaperselector.WallpaperSelector;

public class DesktoBeauty
{
	private final List<Path> wallpapersFiles;
	private final WallpaperSelector wallpaperSelector;
	private final Duration delay;

	public DesktoBeauty(List<Path> wallpapersFiles, WallpaperSelector wallpaperSelector, Duration delay)
	{
		this.wallpapersFiles = wallpapersFiles;
		this.wallpaperSelector = wallpaperSelector;
		this.delay = delay;
	}

	public void start()
	{
		State.set(RUNNING);
		
		while(true)
		{
			DesktopWallpaper.set(selectNextWallpaper());
			delay();
		}
	}

	private Path selectNextWallpaper()
	{
		return this.wallpaperSelector.selectFrom(this.wallpapersFiles);
	}

	private void delay()
	{
		try
		{
			Thread.sleep(this.delay.toMillis());
		}
		catch(Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}
}