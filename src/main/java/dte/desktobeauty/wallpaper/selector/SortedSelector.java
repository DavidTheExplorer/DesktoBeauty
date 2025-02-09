package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.wallpaper.Wallpaper;
import dte.desktobeauty.wallpaper.WallpaperSelector;

import java.util.Comparator;

/**
 * Expects the wallpaper list to be sorted according to a custom {@link Comparator} prior to selecting.
 */
public interface SortedSelector extends WallpaperSelector
{
    Comparator<Wallpaper> getComparator();
}
