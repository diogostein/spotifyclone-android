package com.codelabs.spotifyclone.core.helper

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition
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

    fun toBitmap(url: String, onResourceReady: (Bitmap) -> Unit) {
        Glide
            .with(SpotifyCloneApplication.instance.applicationContext)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    onResourceReady(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    private fun getFadeTransition() = DrawableTransitionOptions.withCrossFade(
        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
    )
}