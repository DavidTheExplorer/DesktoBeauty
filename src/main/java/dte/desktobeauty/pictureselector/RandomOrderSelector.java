package dte.desktobeauty.pictureselector;

import static java.util.stream.Collectors.toList;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Selects a random picture, but the returned ones cannot repeat until the entire list is consumed.
 * <p>
 * Example of consecutive calls where each number represents an index -> <i>3, 1, 2, 1, 3, 2</i> So the third index cannot repeat until 2 and 1 are returned.
 */
public class RandomOrderSelector extends AbstractPictureSelector
{
	private final Map<List<Path>, IndexSelector> selectors = new HashMap<>();
	
	public RandomOrderSelector()
	{
		super("Random Order");
	}

	@Override
	public Path selectFrom(List<Path> pictures)
	{
		int nextIndex = this.selectors.computeIfAbsent(pictures, IndexSelector::new).next();

		return pictures.get(nextIndex);
	}


	
	private static class IndexSelector
	{
		private final List<Path> source;
		private final Queue<Integer> indexesLeft = new LinkedList<>();
		
		public IndexSelector(List<Path> source)
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