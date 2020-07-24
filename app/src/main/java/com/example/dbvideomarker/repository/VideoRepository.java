package com.example.dbvideomarker.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.dbvideomarker.database.AppDatabase;
import com.example.dbvideomarker.database.dao.VideoDao;
import com.example.dbvideomarker.database.entitiy.PlayList;
import com.example.dbvideomarker.database.entitiy.Video;

import java.util.List;

public class VideoRepository {

    private static final String TAG = VideoRepository.class.getSimpleName();

    private VideoDao videoDao;
    private LiveData<List<Video>> allVideo;

    public VideoRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        videoDao = db.videoDao();
        allVideo = videoDao.findAllVideo();
    }

    public LiveData<List<Video>> getAllVideo() { return allVideo; }

    public void insertVideo(Video video) {
        new AsyncTask<Video, Void, Long>() {
            @Override
            protected Long doInBackground(Video... videos) {
                if(videoDao == null)
                    return -1L;
                return videoDao.insertVideo(videos[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Video video1 = new Video();
                String name = video1.getvName();
                Log.d(TAG, "insert : " + aLong + "|||||" + name);
            }
        }.execute(video);
    }
}
