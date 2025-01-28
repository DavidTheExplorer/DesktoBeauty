package dte.desktobeauty.wallpaperselector;

public abstract class AbstractWallpaperSelector implements WallpaperSelector
{
	private final String name;
	
	protected AbstractWallpaperSelector(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName() 
	{
		return this.name;
	}
}
