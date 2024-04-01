package com.APIv2.restProjectv2.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.APIv2.restProjectv2.model.Song;

public class SongDao {
    String USERNAME = "root";
    String PASSWORD = "CBhh62D53eEeaAGgedFB1C365B1D4C-1";
    String URL = "jdbc:mysql://roundhouse.proxy.rlwy.net:43628/railway";

    public SongDao(){
        // createTable();
    //    addSong(null);
    // testMethod();
    // testMethod();
    }

    public void testMethod(){
        String sql = "DROP TABLE song_db";
       
       
        try{
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
    
    
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void createTable(){
        String sql = "CREATE TABLE song_db (song_id SERIAL, artist_name varchar(255), album_name varchar(255),  song_name varchar(255)," 
                    + " lyrics LONGTEXT, PRIMARY KEY (song_id) )";
        try{
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
    
    
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public Song addSong(Song song){

        String sql = "INSERT INTO song_db (artist_name, album_name, song_name, lyrics) VALUES (?,?,?,?)";
        try{
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement prepStatement = con.prepareStatement(sql);
            prepStatement.setString(1, song.getArtistName());
            prepStatement.setString(2, song.getAlbumName());
            prepStatement.setString(3, song.getSongName());
            prepStatement.setString(4, song.getLyrics());

           prepStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
