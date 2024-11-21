import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    try {
      File myObj = new File("Albums.csv");
      Scanner myReader = new Scanner(myObj);
      ArrayList<Albums> albumList = new ArrayList<>();

      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        Albums album = new Albums(data);
        album.print();
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}