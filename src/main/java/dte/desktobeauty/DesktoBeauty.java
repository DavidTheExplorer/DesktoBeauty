package dte.desktobeauty;

import static dte.desktobeauty.state.State.RUNNING;

import java.time.Duration;
import java.util.List;

import com.machinezoo.noexception.Exceptions;
import dte.desktobeauty.state.State;
import dte.desktobeauty.wallpaper.Wallpaper;
import dte.desktobeauty.wallpaper.WallpaperSelector;

public class DesktoBeauty
{
	private final List<Wallpaper> wallpapers;
	private final WallpaperSelector wallpaperSelector;
	private final long delayMS;

	public DesktoBeauty(List<Wallpaper> wallpapers, WallpaperSelector wallpaperSelector, Duration delay)
	{
		this.wallpapers = wallpapers;
		this.wallpaperSelector = wallpaperSelector;
		this.delayMS = delay.toMillis();
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
		Exceptions.sneak().run(() -> Thread.sleep(this.delayMS));
	}
}