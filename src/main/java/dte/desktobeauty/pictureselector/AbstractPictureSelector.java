package dte.desktobeauty.pictureselector;

public abstract class AbstractPictureSelector implements PictureSelector
{
	private final String name;
	
	protected AbstractPictureSelector(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName() 
	{
		return this.name;
	}
}
