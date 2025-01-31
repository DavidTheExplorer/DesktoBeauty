package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.wallpaper.Wallpaper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Selects a random wallpaper without any limits.
 */
public class RandomSelector extends AbstractSelector
{
	public RandomSelector()
	{
		super("Random");
	}

	@Override
	public Wallpaper selectFrom(List<Wallpaper> wallpapers)
	{
		int randomIndex = ThreadLocalRandom.current().nextInt(wallpapers.size());

		return wallpapers.get(randomIndex);
	}
}