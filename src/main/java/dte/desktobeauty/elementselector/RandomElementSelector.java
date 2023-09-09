package dte.desktobeauty.elementselector;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Selects a random element without any repetition limits.
 *
 * @param <T> The type of the elements in the list.
 */
public class RandomElementSelector<T> extends AbstractElementSelector<T>
{
	public RandomElementSelector() 
	{
		super("Random");
	}

	@Override
	public T selectFrom(List<T> list)
	{
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
	}
}