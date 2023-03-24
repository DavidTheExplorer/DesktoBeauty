package dte.desktobeauty.utils;

import static java.time.Duration.ZERO;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

public class TimeUtils 
{
	public static Duration time(Runnable runnable) 
	{
		long before = System.currentTimeMillis();
		runnable.run();
		long elapsed = System.currentTimeMillis() - before;

		return Duration.ofMillis(elapsed);
	}

	public static Duration parseDuration(String time) 
	{
		String[] components = time.replace(",", "").replace(".", "").split(" ");

		return IntStream.iterate(0, i -> i < components.length-1, i -> i+2)
				.mapToObj(i -> 
				{
					int amount = Integer.parseInt(components[i]);
					ChronoUnit unit = parseChronoUnit(components[i+1].toUpperCase());

					return Duration.of(amount, unit);
				})
				.reduce(ZERO, Duration::plus);
	}

	public static String describe(Duration duration) 
	{
		String result = 
				describeUnit("days", duration.toDaysPart()) + 
				describeUnit("hours", duration.toHoursPart()) + 
				describeUnit("minutes", duration.toMinutesPart()) + 
				describeUnit("seconds", duration.toSecondsPart()) + 
				describeUnit("millis", duration.toMinutesPart());
		
		if(result.endsWith(", "))
			result = result.substring(0, result.length()-2);

		return result;
	}

	private static String describeUnit(String unitName, long amount) 
	{
		if(amount == 0)
			return "";

		if(amount == 1 && unitName.endsWith("s"))
			unitName = unitName.substring(0, unitName.length()-1);

		return String.format("%d %s, ", amount, unitName);
	}

	private static ChronoUnit parseChronoUnit(String unitName) 
	{
		if(!unitName.endsWith("S"))
			unitName += "S";

		return ChronoUnit.valueOf(unitName);
	}
}