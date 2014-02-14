package com.andrew.apollo;

import java.io.IOException;

import com.andrew.apollo.utils.ApolloUtils;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;

public class CompatMediaPlayer extends MediaPlayer {
    public static boolean mCompatMode = !ApolloUtils.hasJellyBean();
    private MediaPlayer mNextPlayer = null;

    public void onCompletion(MediaPlayer mp) throws IllegalStateException, IOException {
	if (mCompatMode && mNextPlayer != null) {
	    mNextPlayer.prepare();
	    mNextPlayer.start();
	}
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setNextMediaPlayer(MediaPlayer next) {
	if (mCompatMode) {
	    mNextPlayer = next;
	} else {
	    super.setNextMediaPlayer(next);
	}
    }
}
