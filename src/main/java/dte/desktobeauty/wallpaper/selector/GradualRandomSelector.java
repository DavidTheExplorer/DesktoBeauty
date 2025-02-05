package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.wallpaper.Wallpaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Selects a random wallpaper whose brightness is moderately higher than the previous one;
 * This prevents the annoying flash when a bright picture is selected right after a dark one.
 */
public class GradualRandomSelector extends AbstractSelector
{
    private final Map<List<Wallpaper>, Integer> currentIndexes = new HashMap<>();

    private static final int MAX_BRIGHTNESS_DIFFERENCE = 20;

    public GradualRandomSelector()
    {
        super("Gradual Random");
    }

    @Override
    public Wallpaper selectFrom(List<Wallpaper> wallpapers)
    {
        int nextIndex = calculateNextIndex(wallpapers);
        this.currentIndexes.put(wallpapers, nextIndex);

        return wallpapers.get(nextIndex);
    }

    private int calculateNextIndex(List<Wallpaper> wallpapers)
    {
        Integer currentIndex = this.currentIndexes.get(wallpapers);

        //first selection is random
        if(currentIndex == null)
            return ThreadLocalRandom.current().nextInt(wallpapers.size());

        Wallpaper currentWallpaper = wallpapers.get(currentIndex);

        //choose a random candidate and return its index
        int[] candidateIndexes = IntStream.range(0, wallpapers.size())
                .filter(i -> isCandidate(currentWallpaper, wallpapers.get(i)))
                .toArray();

        return candidateIndexes[ThreadLocalRandom.current().nextInt(candidateIndexes.length)];
    }

    private static boolean isCandidate(Wallpaper currentWallpaper, Wallpaper wallpaper)
    {
        //skip the current wallpaper
        if(currentWallpaper.equals(wallpaper))
            return false;

        return currentWallpaper.getBrightnessDifferenceTo(wallpaper) <= MAX_BRIGHTNESS_DIFFERENCE;
    }
}
