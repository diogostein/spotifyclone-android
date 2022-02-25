package com.codelabs.spotifyclone.playlist.domain.usecase

import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.UseCase
import com.codelabs.spotifyclone.common.domain.model.Playlist
import com.codelabs.spotifyclone.playlist.domain.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlaylistDetail(
    private val playlistRepository: PlaylistRepository,
) : UseCase<String, Result<Nothing, Playlist>> {

    override fun execute(input: String): Flow<Result<Nothing, Playlist>> = flow {
        emit(playlistRepository.getPlaylistDetail(input))
    }

}