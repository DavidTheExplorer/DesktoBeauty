package dte.desktobeauty.elementselector;

import java.util.Arrays;
import java.util.List;

/**
 * This interface represents logic behind selecting an element from a {@link java.util.List}.
 *
 * @param <T> The type of the elements in the list.
 */
public interface ElementSelector<T>
{
	String getName();
	T selectFrom(List<T> list);
	
	
	
	public static <T> ElementSelector<T> fromName(String selectorName)
	{
		return Arrays.asList(new RandomElementSelector<T>(), new RandomOrderSelector<T>())
				.stream()
				.filter(selector -> selector.getName().equalsIgnoreCase(selectorName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Couldn't find an element selector named '%s'!", selectorName)));
	}
}