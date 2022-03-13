package com.codelabs.spotifyclone.features.playlist.domain.usecase

import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.domain.UseCase
import com.codelabs.spotifyclone.core.domain.model.Track
import com.codelabs.spotifyclone.features.playlist.domain.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlaylistTracks(
    private val playlistRepository: PlaylistRepository,
) : UseCase<String, Result<Nothing, List<Track>>> {

    override fun execute(input: String): Flow<Result<Nothing, List<Track>>> = flow {
        emit(playlistRepository.getPlaylistTracks(input))
    }

}