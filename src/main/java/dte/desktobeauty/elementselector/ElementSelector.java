package dte.desktobeauty.elementselector;

import java.util.List;

@FunctionalInterface
public interface ElementSelector<T>
{
	T selectFrom(List<T> list);
	
	
	
	public static <T> ElementSelector<T> fromName(String selectorName)
	{
		switch(selectorName.toLowerCase()) 
		{
		case "random":
			return new RandomElementSelector<>();
			
		case "random-order":
			return new RandomOrderSelector<>();
			
		default:
			throw new IllegalArgumentException(String.format("Couldn't find an element selector named '%s'!", selectorName));
		}
	}
}