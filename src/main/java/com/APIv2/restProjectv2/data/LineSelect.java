package com.APIv2.restProjectv2.data;

import java.util.ArrayList;

import com.APIv2.restProjectv2.model.Song;

public class LineSelect {

    public String selectLines(int numberOfLines, ArrayList<String> lyricsArray){

       String randomLyrics = "";
        //iterate through songList 
        for(int i = 0; i < numberOfLines; i++){
             //random number between 0 and the legnth of the lyric array with filtered lyrics
            int random = (int)(Math.random() * lyricsArray.size());
                
            //get random lyric line from lyric array and insert it into its own array 
            randomLyrics = randomLyrics + lyricsArray.get(random) + "\n";

        }

        return randomLyrics; 
    }


    
}
