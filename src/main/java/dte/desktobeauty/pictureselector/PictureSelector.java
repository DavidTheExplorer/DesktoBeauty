package dte.desktobeauty.pictureselector;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * This interface represents logic of selecting a picture from a {@link java.util.List}.
 */
public interface PictureSelector
{
	String getName();
	Path selectFrom(List<Path> pictures);
	
	
	/**
	 * Returns a {@code PictureSelector} that corresponds to the provided {@code name}.
	 * @param selectorName The name of the selector to search.
	 * 
	 * @return A selector whose name matches the provided name.
	 * @throws IllegalArgumentException If no such selector was found.
	 */
	static PictureSelector fromName(String selectorName)
	{
		return Stream.of(new RandomPictureSelector(), new RandomOrderSelector())
				.filter(selector -> selector.getName().equalsIgnoreCase(selectorName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Couldn't find a picture selector named '%s'!", selectorName)));
	}
}