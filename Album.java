import java.util.Date;

public class Album {
  int id;
  String name;
  int genre_id;
  int artist_id;
  int timestamp;
  Date date;
  String genre;
  String artist;

  public Album(String data) {
    String arr[] = data.split(",");
    this.id = Integer.valueOf(arr[0]);
    this.name = arr[1];
    this.genre_id = Integer.valueOf(arr[2]);
    this.artist_id = Integer.valueOf(arr[3]);
    this.timestamp = Integer.valueOf(arr[4]);
    date = new Date(timestamp * 1000L);
  }

  public void setGenre(Genre newGenre){
    this.genre = newGenre.name;
  }

  public void setArtist(Artist newArtist){
    this.artist = newArtist.name;
  }

  public void print() {
    System.out.println("Name : " + name);
    System.out.println("Genre : " + genre);
    System.out.println("Artist : " + artist);
    System.out.println("Release Date : " + date);
    System.out.println("--------------------------------");
  }
}