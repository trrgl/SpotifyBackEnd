import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ArrayList<Album> albumList = new ArrayList<>();
    ArrayList<Artist> artistList = new ArrayList<>();
    ArrayList<Genre> genreList = new ArrayList<>();
    ArrayList<Song> songList = new ArrayList<>();

    FileHandler albums = new FileHandler("Album.csv");
    FileHandler artists = new FileHandler("Artist.csv");
    FileHandler genres = new FileHandler("Genre.csv");
    FileHandler songs = new FileHandler("Song.csv");
    


    for (String artist : artists.getData()) {
      artistList.add(new Artist(artist));
    }
    
    for (String genre : genres.getData()) {
      genreList.add(new Genre(genre));
    }
    
    for (String album : albums.getData()){
      Album newAlbum = new Album(album);
      for (Genre genre : genreList){
        if (newAlbum.genre_id == genre.id) {
          newAlbum.setGenre(genre);
        } 
      }
      for (Artist artist : artistList) {
        if (newAlbum.artist_id == artist.id) {
          newAlbum.setArtist(artist);
        }
      }
      albumList.add(newAlbum);
    }
    
    for (String song : songs.getData()) {
      Song newSong = new Song(song);
      for (Genre genre : genreList){
        if (newSong.genre_id == genre.id) {
          newSong.setGenre(genre);
        } 
      }
      for (Artist artist : artistList) {
        if (newSong.artist_id == artist.id) {
          newSong.setArtist(artist);
        }
        String featured = "";
        for(int i : newSong.feature_id) {
          if (newSong.feature_id[0] == artist.id) {
            newSong.addFeatures(artist);
          }
        }
      }
      for (Album album : albumList) {
        if (newSong.album_id == album.id) {
          newSong.setAlbum(album);
        }
      }
      songList.add(newSong);
    }

    albumList.get(5).print();
    artistList.get(6).print();
    genreList.get(4).print();
    songList.get(7).print();
  }
}