# DesktoBeauty
Improves the Desktop Experience by periodically changing its background to one of your awesome pictures.

## Setting the Picture Pool
Upon the first launch, the program generates and opens the folder - so you can fill it with pictures.\
In order to later modify it, you can access it using the tray icon, and restart the program **if it's currently running**.

## Configuration
The program takes the following CLI parameters in this order:
1. Delay after a background change.\
    Examples: `"5 seconds"` | `"10 minutes"` | `"1 minute, 10 seconds"`

2. Picture selection Algorithm:
    - **Random**: Selects a random picture.
    - **Random Order**: Selects a random picture, but an already selected picture cannot repeat until the rest were selected.\
    Example using numbers: `1, 3, 2 -> 1, 2, 3 -> 2, 3, 1`

## Download
You can get the program's jar from the latest release.\
Run it using: `"start javaw.exe -jar DesktoBeauty [version].jar [parameters]"`
