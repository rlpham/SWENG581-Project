package com.sweng861.song;

import org.json.JSONArray;
import org.json.JSONObject;

//This class is responsible for processing song details/information after the REST api call has been made and JSON has been returned
public class SongDetails {
	
	public String song;
	public String artist;
	public String releaseDate;
	public String recordingLocation;
	public String videoURL;
	public String producers;
	
	//Constructor to receive JSON data about the song and sets member variables from data values
	public SongDetails(JSONObject data) {
		this.song = parseSong(data);
		this.artist = parseArtistName(data);
		this.releaseDate = parseReleaseDate(data);
		this.recordingLocation = parseRecordingLocation(data);
		this.videoURL = parseVideoURL(data);
		this.producers = parseProducers(data);
	}
	
	// Parses song's producers from JSON
	private String parseProducers(JSONObject data) {
		StringBuilder sb = new StringBuilder("");
		JSONArray producers = data.getJSONObject("song").getJSONArray("producer_artists");
		for(int i = 0; i < producers.length(); i++) {
			String producer = producers.getJSONObject(i).getString("name");
			sb.append(String.format("%s. %s\n", i+1, producer));
		}
		return sb.toString();
	}
	
	// Parses song's name from JSON
	public String parseSong(JSONObject data) {
		return data.getJSONObject("song").getString("title");
	}	

	// Parses song's music video URL from JSON
	public String parseVideoURL(JSONObject data) {
		return data.getJSONObject("song").getString("youtube_url");
	}

	// Parses song's studio recording location from JSON
	public String parseRecordingLocation(JSONObject data) {
		return data.getJSONObject("song").getString("recording_location");
	}

	// Parses song's release date from JSON
	public String parseReleaseDate(JSONObject data) {
		return data.getJSONObject("song").getString("release_date_for_display");
	}

	// Parses the artist who wrote the song from JSON
	public String parseArtistName(JSONObject data) {		
		return data.getJSONObject("song").getString("artist_names");
	}
	
	// Outputs formatted song details
	public String toString() {
		String output = String.format("Search: %s\nKind: Song\nArtist: %s\nRelease Date: %s\nRecording Location: %s\nMusic Video: %s\nProducers: \n%s", this.song, this.artist, this.releaseDate, this.recordingLocation, this.videoURL, this.producers);
		return output;
	}
}
