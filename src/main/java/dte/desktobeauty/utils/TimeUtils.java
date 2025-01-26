package dte.desktobeauty.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TimeUtils 
{
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

	private static ChronoUnit parseChronoUnit(String unitName) 
	{
		if(!unitName.endsWith("S"))
			unitName += "S";

		return ChronoUnit.valueOf(unitName);
	}
}