import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class App {
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
  String adder = "";
  int songId = 1, albumId = 1, artistId = 1, genreId = 1;
  Date date;
  long timestamp;
  String searchString;
  int restructureIndex;
  String restructureString;
  int deleterID;

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
      artistId++;
      artistList.add(new Artist(artist));
    }
    
    for (String genre : genres.getData()) {
      genreId++;
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
      albumId++;
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
      songId++;
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
    System.out.println("4. Genres");
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
        genreMenu();
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
    System.out.println("4. Delete Song");
    System.out.println("5. Go Back");
    switch (input(5)) {
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
        delSong();
        break;
      case 5:
        home();
        break;
    };
  }

  public void songSearch() {
    System.out.println("--------------------------------");
    System.out.print("Please Write The Song Name You Want To Search : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    searchString = inputObj.nextLine();
    System.out.println("--------------------------------");
    System.out.println("Your Search Resulted : ");
    for (Song song : songList) {
      if (song.name.toLowerCase().contains(searchString.toLowerCase())) song.print();
    }
    songMenu();
  }

  public void addSong() {
    adder = adder + songId + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Song Name : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    adder = adder + inputObj.nextLine() + ",";

    for (int i=0; i<genreList.size(); i++) {
      genreList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of Your Song's Genre From The List Above : ");
    adder = adder + inputObj.nextLine() + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Total Number Of Streams Of The Song : ");
    adder = adder + inputObj.nextLine() + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Duration Of The Song (In Seconds) : ");
    adder = adder + inputObj.nextLine() + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Release Date Of The Song (In YYYY-MM-DD Format) : ");
    try {
      date = dateFormat.parse(inputObj.nextLine());
      timestamp = date.getTime() / 1000;
      adder = adder + timestamp + ",";
    } catch (ParseException e) {
      e.printStackTrace();
    }

    for (int i=0; i<albumList.size(); i++) {
      albumList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Song's Album From The List Above (0 If It's Albumless) : ");
    adder = adder + inputObj.nextLine() + ",";

    
    for (int i=0; i<artistList.size(); i++) {
      artistList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Song's Artist From The List Above : ");
    adder = adder + inputObj.nextLine() + ",";

    for (int i=0; i<artistList.size(); i++) {
      artistList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Song's Featuring Artists From The List Above (0 If It Doesn't have any Features) : ");
    adder = adder + "[" + inputObj.nextLine() + "]";

    Song newSong = new Song(adder);
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

    try (FileWriter writer = new FileWriter("Song.csv", true)) {
      writer.append("\n" + adder);
    } catch (IOException e) {
      e.printStackTrace();
    }

    songId++;
    adder = "";
    songMenu();
  }

  public void delSong() {
    for (int i=0; i<songList.size(); i++) {
      songList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Song You Want To Delete From The List Above : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    deleterID = inputObj.nextInt();

    restructureIndex = 1;
    for (String song : songs.getData()) {
      Song newSong = new Song(song);
      if (newSong.id != deleterID) {
        try (FileWriter writer = new FileWriter("Song.csv", restructureIndex != 1)) {
          restructureString = restructureIndex + "," + newSong.name + "," + newSong.genre_id + "," + newSong.streams + "," + newSong.duration + "," + newSong.timestamp + "," + newSong.album_id + "," + newSong.artist_id + ",[" + newSong.feature_string + "]" + "\n";
          writer.write(restructureString);
        } catch (IOException e) {
          e.printStackTrace();
        }
        restructureIndex++;
      }
    }

    reLoadData();

    System.out.println("--------------------------------");
    System.out.println("Song Successfully Deleted.");

    songMenu();
  }

  public void artistMenu() {
    System.out.println("--------------------------------");
    System.out.println("1. Show List");
    System.out.println("2. Search");
    System.out.println("3. Register Artist");
    System.out.println("4. Delete Artist");
    System.out.println("5. Go Back");
    switch (input(5)) {
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
        delArtist();
        break;
      case 5:
        home();
        break;
    };
  }

  public void artistSearch() {
    System.out.println("--------------------------------");
    System.out.print("Please Write The Artist's Name You Want To Search : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    searchString = inputObj.nextLine();
    System.out.println("--------------------------------");
    System.out.println("Your Search Resulted : ");
    for (Artist artist : artistList) {
      if (artist.name.toLowerCase().contains(searchString.toLowerCase())) artist.print();
    }
    artistMenu();
  }

  public void addArtist() {
    adder = adder + artistId + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Artist's Name : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    adder = adder + inputObj.nextLine() + ",";

    System.out.println("--------------------------------");
    System.out.print("How Many Monthly Listeners Does This Artist Have? : ");
    adder = adder + inputObj.nextLine() + ",";

    System.out.println("--------------------------------");
    System.out.print("How Many Followers Does This Artist Have? : ");
    adder = adder + inputObj.nextLine();

    artistList.add(new Artist(adder));

    try (FileWriter writer = new FileWriter("Artist.csv", true)) {
      writer.append("\n" + adder);
    } catch (IOException e) {
      e.printStackTrace();
    }

    artistId++;
    adder = "";
    artistMenu();
  }

  public void delArtist() {
    for (int i=0; i<artistList.size(); i++) {
      artistList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Artist You Want To Delete From The List Above : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    deleterID = inputObj.nextInt();

    restructureIndex = 1;
    for (String artist : artists.getData()) {
      Artist newArtist = new Artist(artist);
      if (newArtist.id != deleterID) {
        try (FileWriter writer = new FileWriter("Artist.csv", restructureIndex != 1)) {
          restructureString = restructureIndex + "," + newArtist.name + "," + newArtist.monthly_listeners + "," + newArtist.follower_count + "\n";
          writer.write(restructureString);
        } catch (IOException e) {
          e.printStackTrace();
        }
        restructureIndex++;
      }
    }

    reLoadData();

    System.out.println("--------------------------------");
    System.out.println("Artist Successfully Deleted.");

    artistMenu();
  }

  public void albumMenu() {
    System.out.println("--------------------------------");
    System.out.println("1. Show List");
    System.out.println("2. Search");
    System.out.println("3. Add Album");
    System.out.println("4. Delete Album");
    System.out.println("5. Go Back");
    switch (input(5)) {
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
        delAlbum();
        break;
      case 5:
        home();
        break;
    };
  }

  public void albumSearch() {
    System.out.println("--------------------------------");
    System.out.print("Please Write The Album Name You Want To Search : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    searchString = inputObj.nextLine();
    System.out.println("--------------------------------");
    System.out.println("Your Search Resulted : ");
    for (Album album : albumList) {
      if (album.name.toLowerCase().contains(searchString.toLowerCase())) album.print();
    }
    albumMenu();
  }

  public void addAlbum() {
    adder = adder + songId + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Album Name : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    adder = adder + inputObj.nextLine() + ",";

    for (int i=0; i<genreList.size(); i++) {
      genreList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Album's Genre From The List Above : ");
    adder = adder + inputObj.nextLine() + ",";
    
    for (int i=0; i<artistList.size(); i++) {
      artistList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Album's Artist From The List Above : ");
    adder = adder + inputObj.nextLine() + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Release Date Of The Album (In YYYY-MM-DD Format) : ");
    try {
      date = dateFormat.parse(inputObj.nextLine());
      timestamp = date.getTime() / 1000;
      adder = adder + timestamp;
    } catch (ParseException e) {
      e.printStackTrace();
    }

    Album newAlbum = new Album(adder);
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

    try (FileWriter writer = new FileWriter("Album.csv", true)) {
      writer.append("\n" + adder);
    } catch (IOException e) {
      e.printStackTrace();
    }

    songId++;
    adder = "";
    albumMenu();
  }

  public void delAlbum() {
    for (int i=0; i<albumList.size(); i++) {
      albumList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Album You Want To Delete From The List Above : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    deleterID = inputObj.nextInt();

    restructureIndex = 1;
    for (String album : albums.getData()) {
      Album newAlbum = new Album(album);
      if (newAlbum.id != deleterID) {
        try (FileWriter writer = new FileWriter("Album.csv", restructureIndex != 1)) {
          restructureString = restructureIndex + "," + newAlbum.name + "," + newAlbum.genre_id + "," + newAlbum.artist_id + "," + newAlbum.timestamp + "\n";
          writer.write(restructureString);
        } catch (IOException e) {
          e.printStackTrace();
        }
        restructureIndex++;
      }
    }

    reLoadData();
    
    System.out.println("--------------------------------");
    System.out.println("Album Successfully Deleted.");

    albumMenu();
  }

  public void genreMenu() {
    System.out.println("--------------------------------");
    System.out.println("1. Show List");
    System.out.println("2. Search");
    System.out.println("3. Add Genre");
    System.out.println("4. Delete Genre");
    System.out.println("5. Go Back");
    switch (input(5)) {
      case 1:
        for (int i=0; i<genreList.size(); i++) {
          genreList.get(i).print();
        }
        genreMenu();
        break;
      case 2:
        genreSearch();
        break;
      case 3:
        addGenre();
        break;
      case 4:
        delGenre();
        break;
      case 5:
        home();
        break;
    };
  }

  public void genreSearch() {
    System.out.println("--------------------------------");
    System.out.print("Please Write The Genre's Name You Want To Search : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    searchString = inputObj.nextLine();
    System.out.println("--------------------------------");
    System.out.println("Your Search Resulted : ");
    for (Genre genre : genreList) {
      if (genre.name.toLowerCase().contains(searchString.toLowerCase())) genre.print();
    }
    genreMenu();
  }

  public void addGenre() {
    adder = adder + genreId + ",";

    System.out.println("--------------------------------");
    System.out.print("Please Write The Genre's Name : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    adder = adder + inputObj.nextLine();

    genreList.add(new Genre(adder));

    try (FileWriter writer = new FileWriter("Artist.csv", true)) {
      writer.append("\n" + adder);
    } catch (IOException e) {
      e.printStackTrace();
    }

    genreId++;
    adder = "";
    genreMenu();
  }

  public void delGenre() {
    for (int i=0; i<genreList.size(); i++) {
      genreList.get(i).print();
    }
    System.out.println("--------------------------------");
    System.out.print("Please Write The ID Number Of The Genre You Want To Delete From The List Above : ");
    if (inputObj.hasNextLine()) {
      inputObj.nextLine();
    }
    deleterID = inputObj.nextInt();

    restructureIndex = 1;
    for (String genre : genres.getData()) {
      Genre newGenre = new Genre(genre);
      if (newGenre.id != deleterID) {
        try (FileWriter writer = new FileWriter("Genre.csv", restructureIndex != 1)) {
          restructureString = restructureIndex + "," + newGenre.name + "\n";
          writer.write(restructureString);
        } catch (IOException e) {
          e.printStackTrace();
        }
        restructureIndex++;
      }
    }

    reLoadData();

    System.out.println("--------------------------------");
    System.out.println("Genre Successfully Deleted.");

    genreMenu();
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

  public void reLoadData() {
    albumList.clear();
    artistList.clear();
    songList.clear();
    genreList.clear();
    albumId = 1;
    artistId = 1;
    songId = 1;
    genreId = 1;

    loadData();
  }

  public void shutDown() {
    System.out.println("--------------------------------");
    System.out.println("Thanks for tuning in! See you next time, and let the music play on!");
    System.out.println("--------------------------------");
  }
}