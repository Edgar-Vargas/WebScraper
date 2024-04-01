package com.APIv2.restProjectv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.APIv2.restProjectv2.model.Song;

@Repository
public interface MySqlRepository extends JpaRepository<Song, Integer>{
    
}
