package com.codelabs.spotifyclone.playlist.domain.usecase

import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.UseCase
import com.codelabs.spotifyclone.common.domain.model.Track
import com.codelabs.spotifyclone.playlist.domain.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlaylistTracks(
    private val playlistRepository: PlaylistRepository,
) : UseCase<String, Result<Nothing, List<Track>>> {

    override fun execute(input: String): Flow<Result<Nothing, List<Track>>> = flow {
        emit(playlistRepository.getPlaylistTracks(input))
    }

}