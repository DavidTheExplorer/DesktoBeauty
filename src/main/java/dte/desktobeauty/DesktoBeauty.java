package dte.desktobeauty;

import static dte.desktobeauty.state.State.RUNNING;

import java.time.Duration;
import java.util.List;

import dte.desktobeauty.state.State;
import dte.desktobeauty.wallpaper.Wallpaper;
import dte.desktobeauty.wallpaper.selector.WallpaperSelector;

public class DesktoBeauty
{
	private final List<Wallpaper> wallpapers;
	private final WallpaperSelector wallpaperSelector;
	private final Duration delay;

	public DesktoBeauty(List<Wallpaper> wallpapers, WallpaperSelector wallpaperSelector, Duration delay)
	{
		this.wallpapers = wallpapers;
		this.wallpaperSelector = wallpaperSelector;
		this.delay = delay;
	}

	public void start()
	{
		State.set(RUNNING);
		
		while(true)
		{
			Wallpaper.setForDesktop(selectNextWallpaper());
			delay();
		}
	}

	private Wallpaper selectNextWallpaper()
	{
		return this.wallpaperSelector.selectFrom(this.wallpapers);
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