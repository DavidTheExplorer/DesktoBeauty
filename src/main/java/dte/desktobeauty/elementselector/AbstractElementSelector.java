package dte.desktobeauty.elementselector;

public abstract class AbstractElementSelector<T> implements ElementSelector<T>
{
	private final String name;
	
	protected AbstractElementSelector(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName() 
	{
		return this.name;
	}
}
