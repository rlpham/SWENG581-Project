package com.sweng861.genius;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.sweng861.artist.ArtistDetails;
import com.sweng861.song.SongDetails;


// This class is the service that handles all api calls and data parsing
public class GeniusService {
	
	// Instance variables
	String apiKey;
	String apiHost;
	boolean isArtist;
	String artistId;
	String songId;
	String artist;

	// Constructor
	public GeniusService(String api_key, String api_host) {
		this.apiKey = api_key;
		this.apiHost = api_host;
	}
	
	// Do a REST call and parse the data if it is an artist or song
	// If it is an artist store the artistId to use for a second api call for artistDetails
	// If it is a song store the songId to use for a second api call for songDetails
	public boolean isArtist(String q) throws Exception {
		try {
			JSONObject json = get(q, Endpoints.SEARCH_ENDPOINT);
			String artist = getArtist(json);
			if(q.toLowerCase().equals(artist.toLowerCase())) {
				this.isArtist = true;
				this.artistId = getArtistId(json);
			} else {
				this.isArtist = false;
				this.songId = getSongId(json);
			}
			return this.isArtist;
		} catch(Exception e) {
			throw new Exception("Could not find song or artist with that input. Exiting....");
		}
	}
	
	// Base get method to use for REST calls. Performs request and returns JSON from the API
	public JSONObject get(String q, String endpoint) throws IOException, InterruptedException {
		String uri = getPath(endpoint, q);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("X-RapidAPI-Key", this.apiKey)
				.header("X-RapidAPI-Host", this.apiHost)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		JSONObject json = new JSONObject(response.body());
		
		return json;
	}
	
	// Returns URL for rest calls 
	public String getPath(String path, String q) {
		return Endpoints.BASE_URL + path.replace("QUERY", q.replace(" ", "%20"));
	}
	
	// Called to get details of an artist by using artistId
	// Since top songs also have to be in the output, another call is made to get list of songs of the artist
	public ArtistDetails getArtistDetails() throws IOException, InterruptedException {
		JSONObject artistDetailsData = get(this.artistId, Endpoints.ARTIST_DETAILS_ENDPOINT);
		JSONObject artistSongsData = get(this.artistId, Endpoints.ARTIST_SONGS_ENDPOINT);
		return new ArtistDetails(artistDetailsData, artistSongsData);
	}
	
	// Return the artist name from the JSON returned by the get request to the api
	public String getArtist(JSONObject json) {
		return json.getJSONArray("hits").getJSONObject(0).getJSONObject("result").getJSONObject("primary_artist").getString("name");
	}

	// Return the artist id from the JSON returned by the get request to the api
	public String getArtistId(JSONObject json) {
		return String.valueOf(json.getJSONArray("hits").getJSONObject(0).getJSONObject("result").getJSONObject("primary_artist").getInt("id"));
	}
	
	// Return the song id from the JSON returned by the get request to the api
	public String getSongId(JSONObject json) {
		return String.valueOf(json.getJSONArray("hits").getJSONObject(0).getJSONObject("result").getInt("id"));
	}
	
	// Called to get details of a song by using songId
	public SongDetails getSongDetails() throws IOException, InterruptedException {
		JSONObject songDetailsData = get(this.songId, Endpoints.SONG_DETAILS_ENDPOINT);
		return new SongDetails(songDetailsData);
	}
	
}
