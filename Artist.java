public class Artist {
  int id;
  String name;
  int monthly_listeners;
  int follower_count;

  public Artist(String data) {
    String arr[] = data.split(",");
    this.id = Integer.valueOf(arr[0]);
    this.name = arr[1];
    this.monthly_listeners = Integer.valueOf(arr[2]);
    this.follower_count = Integer.valueOf(arr[3]);
  }

  public void print() {
    System.out.println("Name : " + name);
    System.out.println("Monthly Listeners : " + monthly_listeners);
    System.out.println("Follower Count : " + follower_count);
    System.out.println("--------------------------------");
  }
}