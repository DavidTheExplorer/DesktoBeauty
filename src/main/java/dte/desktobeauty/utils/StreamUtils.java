package dte.desktobeauty.utils;

import java.util.Collections;
import java.util.List;

public class StreamUtils 
{
	public static <E, L extends List<E>> L randomized(L list)
	{
		Collections.shuffle(list);

		return list;
	}
}
