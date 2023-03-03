package dte.desktobeauty.elementselector;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import dte.desktobeauty.utils.StreamUtils;

public class RandomOrderSelector<T> implements ElementSelector<T>
{
	private final Map<List<T>, ListData> listsData = new HashMap<>();
	
	@Override
	public T selectFrom(List<T> list)
	{
		int index = this.listsData.computeIfAbsent(list, ListData::new).nextIndex();
		
		return list.get(index);
	}
	
	private static class ListData
	{
		private final int originalSize;
		private LinkedList<Integer> indexesLeft = new LinkedList<>();
		
		public ListData(List<?> list) 
		{
			this.originalSize = list.size();
		}
		
		public boolean isEmpty() 
		{
			return this.indexesLeft.isEmpty();
		}
		
		public void regenerate() 
		{
			this.indexesLeft = IntStream.range(0, this.originalSize)
					.boxed()
					.collect(collectingAndThen(toCollection(LinkedList::new), StreamUtils::randomized));
		}
		
		public int nextIndex() 
		{
			if(isEmpty())
				regenerate();
			
			return this.indexesLeft.pollFirst();
		}
	}
}