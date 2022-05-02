package com.codelabs.spotifyclone.features.playlist.domain.usecase

import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.domain.UseCase
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.features.playlist.domain.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlaylistDetail(
    private val playlistRepository: PlaylistRepository,
) : UseCase<String, Result<Nothing, Playlist>> {

    override fun invoke(input: String): Flow<Result<Nothing, Playlist>> = flow {
        emit(playlistRepository.getPlaylistDetail(input))
    }

}