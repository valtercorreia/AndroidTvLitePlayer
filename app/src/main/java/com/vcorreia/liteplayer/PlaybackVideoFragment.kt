package com.vcorreia.liteplayer

import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.PlaybackTransportControlGlue
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.leanback.LeanbackPlayerAdapter

/** Handles video playback with media controls. */
class PlaybackVideoFragment : VideoSupportFragment() {

    private lateinit var exoPlayer: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()

        exoPlayer.setVideoSurfaceView(surfaceView)

        val leanbackPlayerAdapter = LeanbackPlayerAdapter(requireContext(), exoPlayer, 16)
        val playerGlue = PlaybackTransportControlGlue(getActivity(), leanbackPlayerAdapter)
        playerGlue.setHost(VideoSupportFragmentGlueHost(this))
        playerGlue.playWhenPrepared()

        val testUri = "http://bofh.nikhef.nl/events/FOSDEM/2021/D.openjdk/modernjava.webm"
        val mediaItem: MediaItem = MediaItem.fromUri(testUri)

        exoPlayer.addMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
    }
}