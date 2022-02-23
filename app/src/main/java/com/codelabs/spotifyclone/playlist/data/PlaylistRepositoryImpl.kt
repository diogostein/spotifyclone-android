package com.codelabs.spotifyclone.playlist.data

import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.data.api.response.toPlaylist
import com.codelabs.spotifyclone.common.domain.ExceptionHandler
import com.codelabs.spotifyclone.common.domain.model.Playlist
import com.codelabs.spotifyclone.common.repositoryResultHandler
import com.codelabs.spotifyclone.playlist.domain.PlaylistRemoteDataSource
import com.codelabs.spotifyclone.playlist.domain.PlaylistRepository

class PlaylistRepositoryImpl(
    private val remoteDataSource: PlaylistRemoteDataSource,
    private val dataExceptionHandler: ExceptionHandler,
) : PlaylistRepository {

    override suspend fun getMyPlaylists(): Result<Nothing, List<Playlist>> {
        return repositoryResultHandler(dataExceptionHandler) {
            val result = remoteDataSource.getMyPlaylists()

            Result.Success(result.items.map { it.toPlaylist() })
        }
    }

}