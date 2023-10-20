package com.sweng861.artist;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// This class is responsible for processing artist details/information after the REST api call has been made and JSON has been returned
public class ArtistDetails {
	
	public String artistName;
	public String description;
	public String instagram;
	public String twitter;
	public String topSongs;

	//Constructor to receive JSON data about the artist and sets member variables from data values
	public ArtistDetails(JSONObject artistDetailsData, JSONObject artistSongsData) {
		this.artistName = parseArtistName(artistDetailsData);
		this.description = parseDescription(artistDetailsData);
		this.instagram = parseInstagram(artistDetailsData);
		this.twitter = parseTwitter(artistDetailsData);
		this.topSongs = parseTopSongs(artistSongsData);
	}
	
	// Parses artist's name from JSON
	String parseArtistName(JSONObject data) {
		String artistName = data.getJSONObject("artist").getString("name");
		return artistName;
	}
	
	// Parses artist description from JSON	
	String parseDescription(JSONObject data) {
		String description = data.getJSONObject("artist").getString("description_preview").replace("\n\n", "\n");
		return description;
	}
	
	// Parses artist's instagram from JSON
	String parseInstagram(JSONObject data) {
		String instagram = data.getJSONObject("artist").getString("instagram_name");
		return "@" + instagram;
	}
	
	// Parses artist's twitter from JSON
	String parseTwitter(JSONObject data) {
		String twitter = data.getJSONObject("artist").getString("twitter_name");
		return "@" + twitter;
	}
	
	// Parses artist's top songs from JSON
	String parseTopSongs(JSONObject data) {
		StringBuilder sb = new StringBuilder("");
		JSONArray songsArray = data.getJSONArray("songs");
		for(int i = 0; i < songsArray.length(); i++) {
			String song = songsArray.getJSONObject(i).getString("title");
			sb.append(String.format("%s. %s\n", i+1, song));
		}
		return sb.toString();
	}
	
	
	// Outputs formatted artist details
	public String toString() {
		String output = String.format("Search: %s\nKind: Artist\nDescription: %s\nInstagram: %s\nTwitter: %s\nTop Songs:\n%s", this.artistName, this.description, this.instagram, this.twitter, this.topSongs);
		return output;
	}
	
}
