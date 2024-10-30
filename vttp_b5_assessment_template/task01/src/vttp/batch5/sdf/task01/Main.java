package vttp.batch5.sdf.task01;

import java.io.*;
import java.util.*;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		String filename = "day.csv";
		File file = new File(filename);

		if (!file.exists()) {
			System.out.println("File does not exist");
			System.exit(-1);
		}

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String row = br.readLine();
		ArrayList<BikeEntry> entries = new ArrayList<>();


		while ((row = br.readLine()) != null) {
			entries.add(BikeEntry.toBikeEntry(row.split(",")));
		}

		entries.sort((a, b) -> Integer.compare(b.getCasual() + b.getRegistered(), a.getCasual() + a.getRegistered()));
		int i = 0;
		String[] positions = {"highest", "second highest", "third highest", "fourth highest", "fifth highest"};
		String[] weathers = {"Clear, Few clouds, Partly cloudy, Partly cloudy",
							"Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
							"Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
							"Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"};
		for (BikeEntry e: entries) {
			if (i >= 5) {
				break;
			}
			String position = positions[i];
			String season = Utilities.toSeason(e.getSeason());
			String day = Utilities.toWeekday(e.getWeekday());
			String month = Utilities.toMonth(e.getMonth());
			Integer total = e.getCasual() + e.getRegistered();
			String weather = weathers[e.getWeather() - 1];
			String holiday = (e.isHoliday()) ? "a holiday" : "not a holiday";

			System.out.printf("The %s (position) recorded number of cyclists was in %s (season), on a %s (day) in the month of %s (month).\nThere were a total of %d (total) cyclists. The weather was %s (weather).\n%s (day) was %s.\n\n",
								position, season, day, month, total, weather, day, holiday);
			i++;
			
		}

		br.close();
		fr.close();

	}
}
