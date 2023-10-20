package com.sweng861.main;

import java.time.Duration;
import java.time.Instant;

import com.sweng861.artist.ArtistDetails;
import com.sweng861.genius.GeniusService;
import com.sweng861.io.UserInput;
import com.sweng861.song.SongDetails;

public class Main {
	
	// Main Method	
	public static void main(String[] args) throws Exception {
		
		
		// Start timer
		long startTime = System.currentTimeMillis();
		System.out.println("\r\n"
				+ "   _______          ________ _   _  _____  ___    __ __                          _   _     _       _______                      _____                     _       _______          _ \r\n"
				+ "  / ____\\ \\        / /  ____| \\ | |/ ____|/ _ \\  / //_ |              /\\        | | (_)   | |     / / ____|                    / ____|                   | |     |__   __|        | |\r\n"
				+ " | (___  \\ \\  /\\  / /| |__  |  \\| | |  __| (_) |/ /_ | |  ______     /  \\   _ __| |_ _ ___| |_   / / (___   ___  _ __   __ _  | (___   ___  __ _ _ __ ___| |__      | | ___   ___ | |\r\n"
				+ "  \\___ \\  \\ \\/  \\/ / |  __| | . ` | | |_ |> _ <| '_ \\| | |______|   / /\\ \\ | '__| __| / __| __| / / \\___ \\ / _ \\| '_ \\ / _` |  \\___ \\ / _ \\/ _` | '__/ __| '_ \\     | |/ _ \\ / _ \\| |\r\n"
				+ "  ____) |  \\  /\\  /  | |____| |\\  | |__| | (_) | (_) | |           / ____ \\| |  | |_| \\__ \\ |_ / /  ____) | (_) | | | | (_| |  ____) |  __/ (_| | | | (__| | | |    | | (_) | (_) | |\r\n"
				+ " |_____/    \\/  \\/   |______|_| \\_|\\_____|\\___/ \\___/|_|          /_/    \\_\\_|   \\__|_|___/\\__/_/  |_____/ \\___/|_| |_|\\__, | |_____/ \\___|\\__,_|_|  \\___|_| |_|    |_|\\___/ \\___/|_|\r\n"
				+ "                                                                                                                        __/ |                                                        \r\n"
				+ "                                                                                                                       |___/                                                         \r\n"
				+ "");
		
		// Instantiation of User Input class to get user input
		UserInput ui = new UserInput();
		
		// Instantiation of Genius Service (this object will be making all REST api calls)
		GeniusService gs = new GeniusService("API_KEY", "genius-song-lyrics1.p.rapidapi.com");
		// Get user input
		String q = ui.getUserInput().toLowerCase();
		// Local variables for end of program statistics
		int artistCount = 0;
		int runCount = 0;
		int songCount = 0;
		
		// Loop until user inputs "exit" to console
		while(!q.equals("exit")) {
			runCount++;
			
			// Logic for artist or song
			if(gs.isArtist(q)) {
				// Increment the artist count
				artistCount++;
				// Get artist details and print them
				ArtistDetails artistDetails = gs.getArtistDetails();
				System.out.println(artistDetails.toString());
			} else {
				// Increment the song count
				songCount++;
				// Get song details and print them
				SongDetails songDetails = gs.getSongDetails();
				System.out.println(songDetails.toString());
			}
			// Get user input again
			q = ui.getUserInput().toLowerCase();
		}
		
		// Stop timer and print out statistics
		long stopTime = System.currentTimeMillis();
		double elapsedTime = ((double)(stopTime- startTime)) / 1000;
		System.out.println("#######################");
		System.out.println("Stats");
		System.out.println("#######################");
		System.out.println(String.format("# of Searches: %d", runCount));
		System.out.println(String.format("Artists Searched: %d", artistCount));
		System.out.println(String.format("Songs Searched: %d", songCount));
		System.out.println("Application Runtime: " + elapsedTime + "s");
		
	}
	
}
