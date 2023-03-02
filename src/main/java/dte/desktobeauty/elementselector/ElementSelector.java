package dte.desktobeauty.elementselector;

import java.util.List;

@FunctionalInterface
public interface ElementSelector<T>
{
	T selectFrom(List<T> list);
}