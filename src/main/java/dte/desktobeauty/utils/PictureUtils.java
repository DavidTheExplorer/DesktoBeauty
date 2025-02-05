package dte.desktobeauty.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public class PictureUtils
{
    public static double calculateLuminance(Path pictureFile)
    {
        BufferedImage image = toBufferedImage(pictureFile);
        int height = image.getHeight(), width = image.getWidth();

        double totalLuminance = 0;

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                totalLuminance += calculatePixelLuminance(image, x, y);

        return totalLuminance / (height * width);
    }

    private static BufferedImage toBufferedImage(Path picturePath)
    {
        try
        {
            return ImageIO.read(picturePath.toFile());
        }
        catch(IOException exception)
        {
            throw new UncheckedIOException(exception);
        }
    }

    /*
        This calculation is the third suggestion of the accepted answer on
        https://stackoverflow.com/questions/596216/formula-to-determine-perceived-brightness-of-rgb-color
     */
    private static double calculatePixelLuminance(BufferedImage image, int x, int y)
    {
        int rgba = image.getRGB(x, y);
        int r = (rgba >> 16) & 255;
        int g = (rgba >> 8) & 255;
        int b = rgba & 255;

        return Math.sqrt((0.299 * Math.pow(r, 2)) + (0.587 * Math.pow(g, 2)) + (0.114 * Math.pow(b, 2)));
    }
}