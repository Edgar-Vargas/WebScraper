package com.APIv2.restProjectv2.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.APIv2.restProjectv2.model.Song;

@Component
public interface SongService {
    List<Song> getAllSongs();

    void saveSong(Song song);

    
}
