package com.sweng861.genius;

// REST API Endpoints
public class Endpoints {
	final static String BASE_URL = "https://genius-song-lyrics1.p.rapidapi.com/";
	final static String SEARCH_ENDPOINT = "search/?q=QUERY&per_page=10&page=1";
	final static String ARTIST_DETAILS_ENDPOINT = "artist/details/?id=QUERY";
	final static String ARTIST_SONGS_ENDPOINT = "artist/songs/?id=QUERY&sort=popularity&per_page=5&page=1";
	final static String SONG_DETAILS_ENDPOINT = "song/details/?id=QUERY";
}
