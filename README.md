# DesktoBeauty
Improves the Desktop Experience by periodically changing its wallpaper to one of your awesome pictures.

## Setting the Wallpaper Pool
Upon the first launch, the program generates and opens the folder - so you can fill it with pictures.\
In order to later modify it, you can access it using the tray icon, and restart the program **if it's currently running**.

## Configuration
The program takes the following CLI parameters in this order:
1. Delay after a background change.\
    Examples: `"5 seconds"` | `"10 minutes"` | `"1 minute, 10 seconds"`

2. Wallpaper Selection Algorithm:
    - **Random**: Selects a random wallpaper.
    - **Numerical**: Allows you to define a selection order by naming your wallpapers with indexes. You can either use an integer, or specify the index at the end surrounded by parenthesis.\
    Example: `"1.jpg" -> "Cute Dog (2).png" -> "Forest (3).jpg" -> "4.png"`
    - **Random Order**: Selects a random wallpaper, but an already selected one cannot repeat until the rest were selected.\
    Example using numbers: `1, 3, 2 -> 1, 2, 3 -> 2, 3, 1`
    - **Gradual Random** - Selects a random wallpaper whose brightness is moderately higher than the previous one;
     This prevents the annoying flash when a bright picture is selected right after a dark one.

## Download
You can get the program's jar from the latest release.\
Run it using: `"start javaw.exe -jar DesktoBeauty [version].jar [parameters]"`
