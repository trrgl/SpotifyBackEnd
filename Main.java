public class Main {
    public static void main(String[] args) {
        String[] fileNames = {"data/Album.csv", "data/Artist.csv", "data/Genre.csv", "data/Song.csv"};

        App app1 = new App(fileNames);
        app1.loadData();

        app1.start();
    }
}