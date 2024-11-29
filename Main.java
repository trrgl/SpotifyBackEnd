public class Main {
    public static void main(String[] args) {
        String[] fileNames = {"Album.csv", "Artist.csv", "Genre.csv", "Song.csv"};

        App app1 = new App(fileNames);
        app1.loadData();

        app1.start();
    }
}