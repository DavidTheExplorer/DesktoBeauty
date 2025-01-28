package dte.desktobeauty.wallpaperselector;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Selects a random wallpaper without any limits.
 */
public class RandomSelector extends AbstractWallpaperSelector
{
	public RandomSelector()
	{
		super("Random");
	}

	@Override
	public Path selectFrom(List<Path> wallpaperFiles)
	{
		int randomIndex = ThreadLocalRandom.current().nextInt(wallpaperFiles.size());
		
		return wallpaperFiles.get(randomIndex);
	}
}