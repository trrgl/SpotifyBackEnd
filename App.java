import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class App {
  FileWriter songWriter = new FileWriter("Song.csv");
  Random rand = new Random();
  String[] greetings = {"Let’s hit play and let the music take over—Spotify is ready!",
                        "Time to jam! What’s your mood today?",
                        "Spotify loaded—your soundtrack for the day starts now!",
                        "Discover, vibe, repeat. Let’s dive into the tunes!",
                        "Music therapy incoming—let’s see what Spotify’s got!",
                        "The playlist of your life starts now—Spotify engaged.",
                        "Cue the beats—Spotify’s here to keep things moving.",
                        "Feel the rhythm! Spotify’s locked and loaded.",
                        "Where words fail, music speaks—Spotify’s at your service.",
                        "Your next sonic adventure starts right here—play it loud!"};
  int index = rand.nextInt(10);  
  Scanner inputObj = new Scanner(System.in); 
  int input;

  ArrayList<Album> albumList = new ArrayList<>();
  ArrayList<Artist> artistList = new ArrayList<>();
  ArrayList<Genre> genreList = new ArrayList<>();
  ArrayList<Song> songList = new ArrayList<>();

  FileHandler albums;
  FileHandler artists;
  FileHandler genres;
  FileHandler songs;

  public App(String[] fileNames) {
    this.albums = new FileHandler(fileNames[0]);
    this.artists = new FileHandler(fileNames[1]);
    this.genres = new FileHandler(fileNames[2]);
    this.songs = new FileHandler(fileNames[3]);
  }

  public void loadData() {
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
  }

  public void start() {
    System.out.println("--------------------------------");
    System.out.println(greetings[index]);
    home();
  }

  public void home() {
    System.out.println("--------------------------------");
    System.out.println("1. Songs");
    System.out.println("2. Artists");
    System.out.println("3. Albums");
    System.out.println("4. Miscellaneous");
    System.out.println("5. Shut Down");
    switch (input(5)) {
      case 1:
        songMenu();
        break;
      case 2:
        artistMenu();
        break;
      case 3:
        albumMenu();
        break;
      case 4:
        miscMenu();
        break;
      case 5:
        shutDown();
        break;
    };
  }

  public void songMenu() {
    System.out.println("--------------------------------");
    System.out.println("1. Show List");
    System.out.println("2. Search");
    System.out.println("3. Add Song");
    System.out.println("4. Edit Song");
    System.out.println("5. Delete Song");
    System.out.println("6. Go Back");
    switch (input(6)) {
      case 1:
        for (int i=0; i<songList.size(); i++) {
          songList.get(i).print();
        }
        songMenu();
        break;
      case 2:
        songSearch();
        break;
      case 3:
        addSong();
        break;
      case 4:
        editSong();
        break;
      case 5:
        delSong();
        break;
      case 6:
        home();
        break;
    };
  }

  public void songSearch() {
    System.out.println("songSearch");
  }

  public void addSong() {
    songWriter.write("test");
  }

  public void editSong() {
    System.out.println("editSong");
  }

  public void delSong() {
    System.out.println("delSong");
  }

  public void artistMenu() {
    System.out.println("--------------------------------");
    System.out.println("1. Show List");
    System.out.println("2. Search");
    System.out.println("3. Register Artist");
    System.out.println("4. Edit Artist");
    System.out.println("5. Delete Artist");
    System.out.println("6. Go Back");
    switch (input(6)) {
      case 1:
        for (int i=0; i<artistList.size(); i++) {
          artistList.get(i).print();
        }
        artistMenu();
        break;
      case 2:
        artistSearch();
        break;
      case 3:
        addArtist();
        break;
      case 4:
        editArtist();
        break;
      case 5:
        delArtist();
        break;
      case 6:
        home();
        break;
    };
  }

  public void artistSearch() {
    System.out.println("artistSearch");
  }

  public void addArtist() {
    System.out.println("addArtist");
  }

  public void editArtist() {
    System.out.println("editArtist");
  }

  public void delArtist() {
    System.out.println("delArtist");
  }

  public void albumMenu() {
    System.out.println("--------------------------------");
    System.out.println("1. Show List");
    System.out.println("2. Search");
    System.out.println("3. Add Album");
    System.out.println("4. Edit Album");
    System.out.println("5. Delete Album");
    System.out.println("6. Go Back");
    switch (input(6)) {
      case 1:
        for (int i=0; i<albumList.size(); i++) {
          albumList.get(i).print();
        }
        albumMenu();
        break;
      case 2:
        albumSearch();
        break;
      case 3:
        addAlbum();
        break;
      case 4:
        editAlbum();
        break;
      case 5:
        delAlbum();
        break;
      case 6:
        home();
        break;
    };
  }

  public void albumSearch() {
    System.out.println("albumSearch");
  }

  public void addAlbum() {
    System.out.println("addAlbum");
  }

  public void editAlbum() {
    System.out.println("editAlbum");
  }

  public void delAlbum() {
    System.out.println("delAlbum");
  }

  public void miscMenu() {
    System.out.println("--------------------------------");
    System.out.println("1. Most Streamed Song");
    System.out.println("2. Most Streamed Artist");
    System.out.println("3. Most Streamed Album");
    System.out.println("4. Most Follwed Artist");
    System.out.println("5. Most Liked Song");
    System.out.println("6. Go Back");
    switch (input(6)) {
      case 1:
        miscMenu();
        break;
      case 2:
        miscMenu();
        break;
      case 3:
        miscMenu();
        break;
      case 4:
        miscMenu();
        break;
      case 5:
        miscMenu();
        break;
      case 6:
        home();
        break;
    };
  }

  public void shutDown() {
    System.out.println("--------------------------------");
    System.out.println("Thanks for tuning in! See you next time, and let the music play on!");
    System.out.println("--------------------------------");
  }

  public int input(int limit) {
    System.out.println("--------------------------------");
    System.out.print("Choose Your Option : ");
    input = inputObj.nextInt();
    if (input < 1 || input > limit) {
      System.out.println("The Category doesn't exist!, Try Again.");
      input(limit);
    }
    return input;
  }
}