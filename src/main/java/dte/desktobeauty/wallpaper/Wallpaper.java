package dte.desktobeauty.wallpaper;

import dte.desktobeauty.utils.PictureUtils;
import dte.desktobeauty.utils.User32;
import dte.desktobeauty.utils.FileUtils;

import java.nio.file.Path;

public class Wallpaper
{
    private final Path file;
    private final double brightness;

    private Wallpaper(Path file, double brightness)
    {
        this.file = file;
        this.brightness = brightness;
    }

    public static Wallpaper of(Path file)
    {
        if(!isValidFile(file))
            throw new IllegalArgumentException(String.format("File \"%s\" cannot be used as a wallpaper; The allowed extensions are: %s.", FileUtils.getExtension(file), formatAllowedExtensions()));

        double brightness = PictureUtils.calculateLuminance(file) / 255 * 100;

        return new Wallpaper(file, brightness);
    }

    public static void setForDesktop(Wallpaper wallpaper)
    {
        String filePath = wallpaper.file.toString();

        User32.INSTANCE.SystemParametersInfo(0x0014, 0, filePath, 1);
    }

    public static boolean isValidFile(Path file)
    {
        return FileUtils.isPicture(file);
    }

    public Path getFile()
    {
        return this.file;
    }

    /**
     * Returns this wallpaper's brightness as a percentage from 0 to 100.
     * The lower the value, the darker this wallpaper is.
     *
     * @return This wallpaper's brightness percentage.
     */
    public double getBrightness()
    {
        return this.brightness;
    }

    public double getBrightnessDifferenceTo(Wallpaper wallpaper)
    {
        return Math.abs(this.brightness - wallpaper.brightness);
    }

    @Override
    public String toString()
    {
        return String.format("Wallpaper [file=%s, brightness=%s]", this.file, this.brightness);
    }

    private static String formatAllowedExtensions()
    {
        return '[' + String.join(", ", FileUtils.PICTURE_EXTENSIONS) + ']';
    }
}
