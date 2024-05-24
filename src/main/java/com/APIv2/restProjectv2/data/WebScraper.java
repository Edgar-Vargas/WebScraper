package com.APIv2.restProjectv2.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.APIv2.restProjectv2.Dao.SongDao;
import com.APIv2.restProjectv2.model.Song;

public class WebScraper {
    private static String URL = "https://www.lyrics.com";
    //TODO this should be combines with artist name
    private static String ARTIST_URL = "https://www.lyrics.com/artist/";
    public WebScraper(){
   
        // addAlbumToDB("The Divine Feminine", "Mac Miller");

    }

    public ArrayList<Song> addAlbumToDB(String albumName, String artist){
        ArrayList<Song> songList = new ArrayList<>();
        //spaces use dashes in the Lyrics.com URLs
        String urlArtist = artist.replace(" ", "-");
        //String urlAlbumName = albumName.replace(" ", "-");
        String urlAlbumName = albumName;
        //url paths for songs of the album
        ArrayList<String> songPaths = new ArrayList<>();
        //helper methods : find album path for URL
        String albumPath = webCrawlForAlbumPath(urlAlbumName, urlArtist);
        //find URL song paths that contain lyrics 
        songPaths = webCrawlThroughAlbumPage(albumPath);
        //get lyrics with array of song paths 
        songList = webCrawlThroughSongPage(songPaths);
        
        //adding to database
        SongDao songDao = new SongDao();
        for(Song song : songList){
            song.setAlbumName(albumName);
            song.setArtistName(artist);
            songDao.addSong(song);
        }
         return songList;
    }

 
   /**
    * Find the album path from the artist's web page on Lyrics.com
    * @param albumName name of album being searched for from the artist
    * @return album name 
    */
    public String webCrawlForAlbumPath(String albumName, String artist){
        // Example Path: "https://www.lyrics.com/artist/" + artist;
        String query = "a:contains(" + albumName + ")";
        String albumPath = "";
        System.out.println(query);
        try{
            Document document = Jsoup.connect(ARTIST_URL + artist).get();
           
            Element lyrics = document.selectFirst(query);
            albumPath = lyrics.attr("href");
            System.out.println(albumPath);
            return albumPath;

        }catch(IOException e){
            e.printStackTrace();
        }
        return albumPath; 
    }
    /**
     * Goes through the album page from Lyrics.com and grabs the song redirect paths for each song in the album 
     * @param albumPath URL for the album web page on Lyrics.com
     * @return array of song path URLs
     */
    public ArrayList<String> webCrawlThroughAlbumPage(String albumPath){
        //Example path: "/album/3693933/Swimming";
        String pathURL = URL + albumPath;
        ArrayList<String> songLyricPaths = new ArrayList<>();
        try{
            Document document = Jsoup.connect(pathURL).get();
            String selector = "";
            String songSelector = "";

            //gets selector for which column "Song" is on the song list table in the album page
            Element songColumn = document.selectFirst("th:contains(Song)");
            //add 1 because method returns from an arraay starting at 0 and selectors start at 1
            int nameIndex = songColumn.elementSiblingIndex() + 1;

            //find number of songs in the album
            String findNumberQuery = "#content-body > div > div.lyric.clearfix > div.tdata-ext > table > tbody > tr";
            Elements findNumberSelector = document.select(findNumberQuery);
            int songCount = findNumberSelector.size();
            //count starts at 1 on Lyrics.com song table
            for(int currentSongIndex = 1; currentSongIndex < songCount + 1; currentSongIndex++ ){

                selector = "#content-body > div > div.lyric.clearfix > div.tdata-ext > table > tbody > tr:nth-child(%1$s) > td:nth-child(%2$s) > div > strong > a";
                //add song index variables to child element variables for the selector string
                songSelector = String.format(selector, currentSongIndex,nameIndex);
                Elements songNameEl = document.select(songSelector);

                for(Element currentSongSelector : songNameEl){
                    songLyricPaths.add(currentSongSelector.attr("href"));
                }                
                
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return songLyricPaths;
    }

    /**
     * Helper method that crawls through the album page of Lyrics.com by using the song names 
     * as an identifier
     * @param lyricPaths title of songs for album
     * on album page
     * @return A list of created Song objects that contain song name and lyrics
     */
    public ArrayList<Song> webCrawlThroughSongPage(ArrayList<String> lyricPaths){
        //example path: "/lyric/35282862/Come+Back+to+Earth";
        ArrayList<Song> songList = new ArrayList<>();
        String pathURL;
        String lyrics = "";
        try{
            for(String lyricPath : lyricPaths){
                pathURL = URL + lyricPath;
                Document document = Jsoup.connect(pathURL).get();
                // element that contains the title of song 
                String findTitleQuery = "#lyric-title-text";
                Elements titleEl = document.select(findTitleQuery);
                String songName = titleEl.text();
                // element that contains lyric text 
                String findLyricQuery = "#lyric-body-text";
                Elements lyricBlock = document.select(findLyricQuery);
                lyrics = lyricBlock.text();

                Song songToAdd = new Song(songName, lyrics);
                //add to the songList that will be returned 
                songList.add(songToAdd);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return songList;
    }
        
}
