package dte.desktobeauty.utils;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

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

		Duration result = Duration.ZERO;
		
		for(int i = 0; i < components.length-1; i += 2) 
		{
			int amount = Integer.parseInt(components[i]);
			ChronoUnit unit = parseChronoUnit(components[i+1].toUpperCase());
			
			result = result.plus(Duration.of(amount, unit));
		}
		
		return result;
	}

	public static String describe(Duration duration) 
	{
		String result = "";
		long totalSeconds = duration.getSeconds();
		
		for(ChronoUnit unit : new ChronoUnit[]{DAYS, HOURS, MINUTES, SECONDS}) 
		{
			long unitSeconds = unit.getDuration().getSeconds();
			long containedUnits = totalSeconds / unitSeconds;
			totalSeconds -= (unitSeconds * containedUnits);
			
			result += describeUnit(unit, containedUnits);
		}
		
		//if there are milliseconds in the duration, add them separately because the loop couldn't account them
		result += describeUnit(MILLIS, TimeUnit.NANOSECONDS.toMillis(duration.getNano()));
		
		//remove the ", " from the end
		if(result.endsWith(", "))
			result = result.substring(0, result.length()-2);

		return result;
	}

	private static String describeUnit(ChronoUnit unit, long amount) 
	{
		if(amount == 0)
			return "";

		String unitName = unit.name().toLowerCase();
		
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