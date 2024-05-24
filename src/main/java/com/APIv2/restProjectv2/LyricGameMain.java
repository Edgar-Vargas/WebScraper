package com.APIv2.restProjectv2;

import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.Line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.APIv2.restProjectv2.FileManager;
import com.APIv2.restProjectv2.Dao.SongDao;
// import com.APIv2.restProjectv2.controller.SongController;
import com.APIv2.restProjectv2.data.LineSelect;
import com.APIv2.restProjectv2.data.WebScraper;
// import com.APIv2.restProjectv2.data.dataService;
// import com.APIv2.restProjectv2.data.databaseService;
import com.APIv2.restProjectv2.model.Song;


@SpringBootApplication
public class LyricGameMain {
	//original song container
	static ArrayList<Song> songContainer = new ArrayList();
	//copy of song container with chosen random lyrics 
	static ArrayList<Song> copyContainer = new ArrayList<>();
	// private SongController songController = new SongController();
	public static void main(String[] args) {
		
		SpringApplication.run(LyricGameMain.class, args);

		//user inputs and prompts
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        System.out.print("Enter album name: ");
        String albumName = scanner.nextLine();
		ArrayList<Song> songList = new ArrayList<>();

		//TODO format album and artist name for case sensitive searches
		//TODO display error if artist or album cant be found 

		// Demo testDemo = new Demo();
		//SongDao songDao = new SongDao();
	
		WebScraper webScraper = new WebScraper();
		//webScraper.addAlbumToDB("Swimming", "Mac Miller");
		songList = webScraper.addAlbumToDB(albumName, artistName);
		//FileManager mainTest = new FileManager();
		for(Song song : songList){
			System.out.println(song.getSongName());
		}
		scanner.close();
		// songContainer = mainTest.fileStorageGetter(FOLDER_PATH);
	
	}

}
