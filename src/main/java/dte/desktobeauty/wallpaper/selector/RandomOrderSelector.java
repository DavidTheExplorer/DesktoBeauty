package dte.desktobeauty.wallpaper.selector;

import dte.desktobeauty.wallpaper.Wallpaper;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Selects a random wallpaper, but the returned ones cannot repeat until the entire list is consumed.
 * <p>
 * Example of consecutive calls where each number represents an index -> <i>3, 1, 2, 1, 3, 2</i> So the third index cannot repeat until 2 and 1 are returned.
 */
public class RandomOrderSelector extends AbstractSelector
{
	private final Map<List<Wallpaper>, IndexSelector> selectors = new HashMap<>();
	
	public RandomOrderSelector()
	{
		super("Random Order");
	}

	@Override
	public Wallpaper selectFrom(List<Wallpaper> wallpapers)
	{
		int nextIndex = this.selectors.computeIfAbsent(wallpapers, IndexSelector::new).next();

		return wallpapers.get(nextIndex);
	}


	
	private static class IndexSelector
	{
		private final List<Wallpaper> source;
		private final Queue<Integer> indexesLeft = new LinkedList<>();
		
		public IndexSelector(List<Wallpaper> source)
		{
			this.source = source;
		}

		public int next()
		{
			if(this.indexesLeft.isEmpty())
				regenerate();
			
			return this.indexesLeft.poll();
		}
		
		public void regenerate() 
		{
			List<Integer> randomIndexes = IntStream.range(0, this.source.size()).boxed().collect(toList());
			Collections.shuffle(randomIndexes);
			
			this.indexesLeft.addAll(randomIndexes);
		}
	}
}