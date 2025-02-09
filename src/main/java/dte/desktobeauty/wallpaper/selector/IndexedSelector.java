package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.wallpaper.Wallpaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Selects the next wallpaper based on the current wallpaper.
 * <p>
 * The (overridable) default implementation starts at index 0 and progresses by 1. When the end of the list is reached, it resets back to 0.
 */
public abstract class IndexedSelector extends AbstractSelector
{
    private final Map<List<Wallpaper>, Integer> currentIndexes = new HashMap<>();

    protected IndexedSelector(String name)
    {
        super(name);
    }

    @Override
    public Wallpaper selectFrom(List<Wallpaper> wallpapers)
    {
        int nextIndex = selectNextIndex(wallpapers);
        this.currentIndexes.put(wallpapers, nextIndex);

        return wallpapers.get(nextIndex);
    }

    private int selectNextIndex(List<Wallpaper> wallpapers)
    {
        Integer currentIndex = this.currentIndexes.get(wallpapers);

        //use the first index for the first selection and for repeating the cycle
        if(currentIndex == null || currentIndex == wallpapers.size()-1)
            return selectFirstIndex(wallpapers);

        return selectNextIndex(wallpapers, currentIndex);
    }

    protected int selectFirstIndex(List<Wallpaper> wallpapers)
    {
        return 0;
    }

    protected int selectNextIndex(List<Wallpaper> wallpapers, int currentIndex)
    {
        return ++currentIndex;
    }
}
