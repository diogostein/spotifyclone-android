package com.codelabs.spotifyclone.features.playlist.domain.usecase

import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.data.api.response.toPlaylist
import com.codelabs.spotifyclone.core.domain.UseCase
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.features.playlist.domain.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMyPlaylists(
    private val playlistRepository: PlaylistRepository,
) : UseCase<UseCase.Empty, Result<Nothing, List<Playlist>>> {

    override fun execute(input: UseCase.Empty): Flow<Result<Nothing, List<Playlist>>> = flow {
        emit(playlistRepository.getMyPlaylists())
    }

}