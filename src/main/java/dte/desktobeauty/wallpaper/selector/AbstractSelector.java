package dte.desktobeauty.wallpaper.selector;

public abstract class AbstractSelector implements WallpaperSelector
{
	private final String name;
	
	protected AbstractSelector(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName() 
	{
		return this.name;
	}
}
