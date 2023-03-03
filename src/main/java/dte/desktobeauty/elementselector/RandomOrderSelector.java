package dte.desktobeauty.elementselector;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Selects a random element from a list, but the selected elements cannot repeat until the entire list is consumed.
 * <p>
 * Example of calls when applied on the list [1, 2, 3] -> <i>3, 1, 2, 1, 3, 2</i> So the number 3 cannot repeat until 2 and 1 are returned.
 *
 * @param <T> The type of the elements in the list.
 */
public class RandomOrderSelector<T> implements ElementSelector<T>
{
	private final Map<List<T>, IndexSelector<T>> selectors = new HashMap<>();
	
	@Override
	public T selectFrom(List<T> list)
	{
		return this.selectors.computeIfAbsent(list, IndexSelector::new).next();
	}
	
	private static class IndexSelector<T>
	{
		private final List<T> source;
		private final Queue<Integer> indexesLeft = new LinkedList<>();
		
		public IndexSelector(List<T> source) 
		{
			this.source = source;
		}
		
		public T next() 
		{
			if(this.indexesLeft.isEmpty())
				regenerate();
			
			return this.source.get(this.indexesLeft.poll());
		}
		
		public void regenerate() 
		{
			List<Integer> randomIndexes = IntStream.range(0, this.source.size()).boxed().collect(toList());
			Collections.shuffle(randomIndexes);
			
			this.indexesLeft.addAll(randomIndexes);
		}
	}
}