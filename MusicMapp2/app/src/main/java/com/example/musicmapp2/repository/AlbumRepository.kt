package com.example.musicmapp2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.musicmapp2.data.database.AlbumDatabase
import com.example.musicmapp2.data.database.entitys.*
import com.example.musicmapp2.data.dataclasses.*
import com.example.musicmapp2.data.network.MusicMappApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


class AlbumsRepository(private val database: AlbumDatabase) {
    val localAlbums: LiveData<List<Album>> =
        Transformations.map(database.albumDao.getAllAlbumsWithTracks()) { albumsList ->
            albumsList.map { dbAlbum -> dbAlbum.toAlbum() }
        }

    private val _topAlbums = MutableLiveData<List<TopAlbum>>()
    val topAlbums: LiveData<List<TopAlbum>> = _topAlbums

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    suspend fun saveAlbum(savealbum: Album) {
        withContext(Dispatchers.IO) {
            database.albumDao.putAlbumWithTracks(savealbum.toDatabaseAlbumWithTracks())
        }
    }

    suspend fun saveTopAlbum(topAlbum: TopAlbum) {
        withContext(Dispatchers.IO) {
            getAlbum(topAlbum.artist.name, topAlbum.name)
            val savealbum = album.value
            if (savealbum != null) saveAlbum(savealbum)
        }
    }

    suspend fun removeAlbum(album: Album) {
        withContext(Dispatchers.IO) {
            database.albumDao.removeAlbumWithTracks(album.toDatabaseAlbumWithTracks())
        }
    }

    suspend fun getTopAlbums(artist: String) {
        val fetchTopAlbums = MusicMappApi.retrofitService
            .getTopAlbums(artist)
            .await()
        _topAlbums.value = fetchTopAlbums.topalbums.album
    }

    suspend fun getAlbum(albumArtist: String, albumName: String) {
        val fetchAlbum = MusicMappApi.retrofitService
            .getAlbumInformation(albumArtist, albumName)
            .await()
        _album.postValue(fetchAlbum.album)
    }
}

fun Album.toDatabaseAlbumWithTracks() =
    DatabaseAlbumWithTracks(
        DatabaseAlbum(url, artist, name, DatabaseImage(image[0].size, image[0].text)),
        tracks.track.map {
            DatabaseTrack(
                it.url,
                it.trackOrder.rank,
                DatabaseArtist(it.artist.name, it.artist.url),
                it.duration,
                it.name,
                url
            )
        }
    )


fun DatabaseAlbumWithTracks.toAlbum() =
    Album(
        url = album.albumUrl,
        artist = album.albumArtist,
        image = listOf(Image(album.albumImage.imageSize, album.albumImage.imageText)),
        name = album.albumName,
        tracks = Tracks(tracks.map {
            Track(
                artist = Artist(it.trackArtist.artistName, it.trackArtist.artistUrl),
                trackOrder = TrackOrder(it.trackOrder),
                duration = it.trackDuration,
                name = it.trackName,
                url = it.trackUrl,
            )
        })
    )
