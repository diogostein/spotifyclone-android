package com.codelabs.spotifyclone

import app.cash.turbine.test
import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.domain.ExceptionCause
import com.codelabs.spotifyclone.core.domain.UseCase
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.core.domain.toMessageRes
import com.codelabs.spotifyclone.core.presentation.UiState
import com.codelabs.spotifyclone.features.playlist.domain.usecase.GetMyPlaylists
import com.codelabs.spotifyclone.features.playlist.presentation.listing.PlaylistListViewModel
import com.codelabs.spotifyclone.utils.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class PlaylistListViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: PlaylistListViewModel

    private val getMyPlaylists = mock<GetMyPlaylists>()
    private val playlists = mock<List<Playlist>>()
    private val resultSuccess = Result.Success(playlists)
    private val resultException = Result.Exception(ExceptionCause.Unknown)

    @Test
    fun `get playlists from use case when instantiated`() {
        viewModel = mockSuccessCase()

        verify(getMyPlaylists, times(1)).execute(UseCase.Empty)
    }

    @Test
    fun `map success result to success ui state with same data when getting playlists`() = runBlockingTest {
        viewModel = mockSuccessCase()

        viewModel.stateFlow.test {
            val result = awaitItem()
            assertTrue(result is UiState.Success && result.data == playlists)
        }
    }

    @Test
    fun `map exception result to error ui state with message when receive exception`() = runBlockingTest {
        viewModel = mockExceptionCase()

        viewModel.stateFlow.test {
            val result = awaitItem()
            assertTrue(result is UiState.Error
                    && result.messageRes == resultException.cause.toMessageRes())
        }
    }

    private fun mockSuccessCase(): PlaylistListViewModel {
        whenever(getMyPlaylists.execute(UseCase.Empty))
            .thenReturn(flow { emit(resultSuccess) })

        return PlaylistListViewModel(getMyPlaylists)
    }

    private fun mockExceptionCase(): PlaylistListViewModel {
        whenever(getMyPlaylists.execute(UseCase.Empty))
            .thenReturn(flow { emit(resultException) })

        return PlaylistListViewModel(getMyPlaylists)
    }

}