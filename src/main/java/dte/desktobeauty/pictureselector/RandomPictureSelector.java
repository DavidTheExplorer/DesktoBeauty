package dte.desktobeauty.pictureselector;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Selects a random picture without any limits.
 */
public class RandomPictureSelector extends AbstractPictureSelector
{
	public RandomPictureSelector()
	{
		super("Random");
	}

	@Override
	public Path selectFrom(List<Path> pictures)
	{
		return pictures.get(ThreadLocalRandom.current().nextInt(pictures.size()));
	}
}