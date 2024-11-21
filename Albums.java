public class Albums {
  int id;
  String name;
  int genre_id;
  int artist_id;
  int timestamp;

  public Albums(String data) {
    String arr[] = data.split(",");
    this.id = Integer.valueOf(arr[0]);
    this.name = arr[1];
    this.genre_id = Integer.valueOf(arr[2]);
    this.artist_id = Integer.valueOf(arr[3]);
    this.timestamp = Integer.valueOf(arr[4]);
  }

  public void print() {
    System.out.println("Name : " + name);
    System.out.println("Genre : " + genre_id);
    System.out.println("Artist : " + artist_id);
    System.out.println("Date : " + timestamp);
    System.out.println("--------------------------------");
  }
}