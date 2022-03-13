package com.codelabs.spotifyclone.core.helper

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.codelabs.spotifyclone.SpotifyCloneApplication

object GlideHelper {

    fun load(url: String?, imageView: ImageView) {
        Glide
            .with(SpotifyCloneApplication.instance.applicationContext)
            .load(url)
            .transition(getFadeTransition())
            .into(imageView)
    }

    fun load(bitmap: Bitmap?, imageView: ImageView) {
        Glide
            .with(SpotifyCloneApplication.instance.applicationContext)
            .load(bitmap)
            .transition(getFadeTransition())
            .into(imageView)
    }

    private fun getFadeTransition() = DrawableTransitionOptions.withCrossFade(
        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
    )
}