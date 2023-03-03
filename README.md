# DesktoBeauty
CLI tool that takes control over your Desktop's Background Picture and changes it every certain amount of time.

## CLI Parameters
The following parameters must be specified in-order:
1. The amount of minutes to wait after changing the background.
2. The background selection algorithm:
    - **Random**: Selects a random picture.
    - **Random Order**: Selects a random picture, but a picture cannot repeat until all the rest were selected.\
    Example using numbers: `1, 3, 2 | 1, 2, 3 | 2, 3, 1`
    
    
## The Backgrounds Folder
It's located at: `your user folder/DesktoBeauty\Desktop Backgrounds`\
On first launch, it will be automatically generated and opened for you - so you can immediately insert your favourite pictures! (don't forget to re-run the tool)
