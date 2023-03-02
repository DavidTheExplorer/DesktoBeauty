package dte.desktobeauty.elementselector;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomElementSelector<T> implements ElementSelector<T>
{
	@Override
	public T selectFrom(List<T> list)
	{
		int randomIndex = ThreadLocalRandom.current().nextInt(list.size());
		
		return list.get(randomIndex);
	}
}