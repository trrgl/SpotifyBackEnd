import java.util.Arrays;
import java.util.Date;

public class Song {
  int id;
  String name;
  int genre_id;
  String genre;
  String artist;
  String album;
  String features = "";
  long streams;
  int duration;
  int duration_min;
  int duration_sec;
  int timestamp;
  int album_id;
  int artist_id;
  int[] feature_id;
  String feature_string;
  Date date;

  public Song(String data) {
    String arr[] = data.split(",");
    feature_string = arr[8].replace("[", "").replace("]", "");
    String[] features = feature_string.split(",");
    this.id = Integer.valueOf(arr[0]);
    this.name = arr[1];
    this.genre_id = Integer.valueOf(arr[2]);
    this.genre = "";
    this.streams = Long.valueOf(arr[3]);
    this.duration = Integer.valueOf(arr[4]);
    this.duration_min = duration / 60;
    this.duration_sec = duration % 60;
    this.timestamp = Integer.valueOf(arr[5]);
    this.date = new Date(timestamp * 1000L);
    this.album_id = Integer.valueOf(arr[6]);
    this.artist_id = Integer.valueOf(arr[7]);
    this.feature_id = Arrays.stream(features).mapToInt(Integer::parseInt).toArray();
  }

  public void setGenre(Genre newGenre){
    this.genre = newGenre.name;
  }

  public void setArtist(Artist newArtist){
    this.artist = newArtist.name;
  }

  public void setAlbum(Album newAlbum){
    this.album = newAlbum.name;
  }

  public void addFeatures(Artist newFeature) {
    if (this.features != "") this.features += ", ";
    this.features += newFeature.name;
  }

  public void print() {
    System.out.println("--------------------------------");
    System.out.println("ID : " + id);
    System.out.println("Name : " + name);
    System.out.println("Genre : " + genre);
    System.out.println("Streams : " + streams);
    System.out.print("Duration : ");
    if (duration_min > 0) System.out.print(duration_min + "m ");
    System.out.println(duration_sec + "s");
    System.out.println("Release Date : " + date);
    if (album_id != 0) System.out.println("Album : " + album);
    System.out.println("Artist : " + artist);
    if (feature_id[0] != 0) System.out.println("Featuring : " + features);
  }
}