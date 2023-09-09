package dte.desktobeauty.elementselector;

import java.util.Arrays;
import java.util.List;

/**
 * This interface represents logic of selecting an element from a {@link java.util.List}.
 *
 * @param <T> The type of the elements in the list.
 */
public interface ElementSelector<T>
{
	String getName();
	T selectFrom(List<T> list);
	
	
	/**
	 * Returns an {@code ElemenetSelector} that corresponds to the provided {@code name}.
	 * @param <T> The type of objects the selector should treat.
	 * @param selectorName The name of the selector to search.
	 * 
	 * @return A selector whose name matches the provided name.
	 * @throws IllegalArgumentException If no such selector was found.
	 */
	public static <T> ElementSelector<T> fromName(String selectorName)
	{
		return Arrays.asList(new RandomElementSelector<T>(), new RandomOrderSelector<T>())
				.stream()
				.filter(selector -> selector.getName().equalsIgnoreCase(selectorName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Couldn't find an element selector named '%s'!", selectorName)));
	}
}