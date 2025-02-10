package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.wallpaper.Wallpaper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Selects a random wallpaper whose brightness is moderately higher than the previous one;
 * This prevents the annoying flash when a bright picture is selected right after a dark one.
 */
public class GradualRandomSelector extends IndexedSelector
{
    private static final int MAX_BRIGHTNESS_DIFFERENCE = 20;

    public GradualRandomSelector()
    {
        super("Gradual Random");
    }

    @Override
    protected int selectFirstIndex(List<Wallpaper> wallpapers)
    {
        return ThreadLocalRandom.current().nextInt(wallpapers.size());
    }

    @Override
    protected int selectNextIndex(List<Wallpaper> wallpapers, int currentIndex)
    {
        Wallpaper currentWallpaper = wallpapers.get(currentIndex);

        int[] candidateIndexes = IntStream.range(0, wallpapers.size())
                .filter(i -> i != currentIndex) //skip the current wallpaper
                .filter(i -> currentWallpaper.getBrightnessDifferenceTo(wallpapers.get(i)) <= MAX_BRIGHTNESS_DIFFERENCE)
                .toArray();

        return candidateIndexes[ThreadLocalRandom.current().nextInt(candidateIndexes.length)];
    }
}
