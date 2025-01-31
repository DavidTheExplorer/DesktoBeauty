package dte.desktobeauty.wallpaper;

import dte.desktobeauty.utils.User32;
import dte.desktobeauty.utils.FileUtils;

import java.nio.file.Path;
import java.util.Set;

public record Wallpaper(Path file)
{
    private static final Set<String> PICTURE_EXTENSIONS = Set.of("png", "jpg", "jpeg", "bmp");

    public Wallpaper
    {
        if(!isValid(file))
            throw new IllegalArgumentException(String.format("File \"%s\" cannot be used as a wallpaper; The allowed extensions are: %s.", FileUtils.getExtension(file), formatAllowedExtensions()));
    }

    public static boolean isValid(Path file)
    {
        return PICTURE_EXTENSIONS.contains(FileUtils.getExtension(file));
    }

    public static void setForDesktop(Wallpaper wallpaper)
    {
        String filePath = wallpaper.file.toString();

        User32.INSTANCE.SystemParametersInfo(0x0014, 0, filePath, 1);
    }

    private static String formatAllowedExtensions()
    {
        return '[' + String.join(", ", PICTURE_EXTENSIONS) + ']';
    }
}
