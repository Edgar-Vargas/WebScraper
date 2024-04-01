package com.APIv2.restProjectv2.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APIv2.restProjectv2.FileManager;
import com.APIv2.restProjectv2.model.Song;
import com.APIv2.restProjectv2.repository.MySqlRepository;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private MySqlRepository mySqlRepository;

    private FileManager mainTest;
    @Override
    public List<Song> getAllSongs() {

        return mySqlRepository.findAll();
        // TODO Auto-generated method stub

    }
    @Override
    public void saveSong(Song song) {
        this.mySqlRepository.save(song);
        // TODO Auto-generated method stub
    }
 
    
}
