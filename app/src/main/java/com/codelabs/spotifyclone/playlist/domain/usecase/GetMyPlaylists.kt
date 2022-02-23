package com.codelabs.spotifyclone.playlist.domain.usecase

import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.UseCase
import com.codelabs.spotifyclone.common.domain.model.Playlist
import com.codelabs.spotifyclone.playlist.domain.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMyPlaylists(
    private val playlistRepository: PlaylistRepository,
) : UseCase<Nothing, Result<Nothing, List<Playlist>>> {

    override fun execute(input: Nothing?): Flow<Result<Nothing, List<Playlist>>> = flow {
        emit(playlistRepository.getMyPlaylists())
    }

}