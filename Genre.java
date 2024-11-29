public class Genre {
  int id;
  String name;

  public Genre(String data) {
    String arr[] = data.split(",");
    this.id = Integer.valueOf(arr[0]);
    this.name = arr[1];;
  }

  public void print() {
    System.out.println("--------------------------------");
    System.out.println("ID : " + id);
    System.out.println("Name : " + name);
  }
}