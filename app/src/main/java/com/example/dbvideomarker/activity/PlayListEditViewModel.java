package com.example.dbvideomarker.activity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dbvideomarker.database.entitiy.PlRel;
import com.example.dbvideomarker.database.entitiy.PlRelVideo;
import com.example.dbvideomarker.database.entitiy.PlayList;
import com.example.dbvideomarker.repository.PlayListEditRepository;
import com.example.dbvideomarker.repository.PlayListRepository;

import java.util.List;

public class PlayListEditViewModel extends AndroidViewModel {

    private PlayListEditRepository playListEditRepository;
    private PlayListRepository playListRepository;
    private LiveData<List<PlRel>> allPlayListRelation;
    private LiveData<List<PlRelVideo>> allVideoInPlayList;

    public PlayListEditViewModel(@NonNull Application application) {
        super(application);
        playListEditRepository = new PlayListEditRepository(application);
        playListRepository = new PlayListRepository(application);
        allPlayListRelation = playListEditRepository.getAllPlRel();
    }


    public LiveData<List<PlRelVideo>> findVideoInPlayList(int pid) {
        return playListEditRepository.findVideoInPlayList(pid);
    }

    public LiveData<PlayList> getPlayList(int pid) {
        return playListRepository.getPlayList(pid);
    }

    public void insertPlRelation(PlRel plRel) {
        playListEditRepository.insertPlRelation(plRel);
    }

    public void update(PlayList playList) {
        playListRepository.update(playList);
    }

    public void deletePlRel(int vid) {
        playListEditRepository.deletePlRel(vid);
    }
}
