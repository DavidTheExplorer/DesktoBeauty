package dte.desktobeauty.wallpaper;

import dte.desktobeauty.utils.User32;
import dte.desktobeauty.utils.FileUtils;

import java.nio.file.Path;

public record Wallpaper(Path file)
{
    public Wallpaper
    {
        if(!isValid(file))
            throw new IllegalArgumentException(String.format("File \"%s\" cannot be used as a wallpaper; The allowed extensions are: %s.", FileUtils.getExtension(file), formatAllowedExtensions()));
    }

    public static boolean isValid(Path file)
    {
        return FileUtils.isPicture(file);
    }

    public static void setForDesktop(Wallpaper wallpaper)
    {
        String filePath = wallpaper.file.toString();

        User32.INSTANCE.SystemParametersInfo(0x0014, 0, filePath, 1);
    }

    private static String formatAllowedExtensions()
    {
        return '[' + String.join(", ", FileUtils.PICTURE_EXTENSIONS) + ']';
    }
}
