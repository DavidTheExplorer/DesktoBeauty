package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.utils.FileUtils;
import dte.desktobeauty.wallpaper.Wallpaper;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericalSelector extends IndexedSelector implements SortedSelector
{
    private static final Pattern INDEX_PATTERN = Pattern.compile("\\((?<index>\\d+)\\)");

    public NumericalSelector()
    {
        super("Numerical");
    }

    @Override
    public Comparator<Wallpaper> getComparator()
    {
        return Comparator.comparingInt(NumericalSelector::getNumericalFileName);
    }

    private static int getNumericalFileName(Wallpaper wallpaper)
    {
        String fileName = FileUtils.getFileNameWithoutExtension(wallpaper.getFile());

        //use the file name if it's a positive integer
        if(StringUtils.isNumeric(fileName))
            return Integer.parseInt(fileName);

        //if not, try to extract the index from the pattern: "fileName (index)"
        Matcher matcher = INDEX_PATTERN.matcher(fileName);

        if(matcher.find())
            return Integer.parseInt(matcher.group("index"));

        //if everything failed, the file is pushed to the end
        return Integer.MAX_VALUE;
    }
}
