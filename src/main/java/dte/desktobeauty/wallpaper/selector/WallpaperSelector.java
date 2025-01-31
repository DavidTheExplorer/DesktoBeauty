package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.wallpaper.Wallpaper;

import java.util.List;
import java.util.stream.Stream;

/**
 * This interface encapsulates the logic of selecting a wallpaper from a {@link java.util.List}.
 */
public interface WallpaperSelector
{
	String getName();
	Wallpaper selectFrom(List<Wallpaper> wallpapers);
	
	
	/**
	 * Returns a {@code WallpaperSelector} that corresponds to the provided {@code name}.
	 * @param selectorName The name of the selector to search.
	 * 
	 * @return A selector whose name matches the provided name.
	 * @throws IllegalArgumentException If no such selector was found.
	 */
	static WallpaperSelector fromName(String selectorName)
	{
		return Stream.of(new RandomSelector(), new RandomOrderSelector())
				.filter(selector -> selector.getName().equalsIgnoreCase(selectorName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Couldn't find a wallpaper selector named '%s'!", selectorName)));
	}
}