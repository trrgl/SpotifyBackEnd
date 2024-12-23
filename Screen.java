import java.util.Scanner;

public class Screen {
    Scanner inputObj = new Scanner(System.in);
    
    public Screen(String[] Options) {
        line();
        for(int i=0; i<Options.length; i++) System.out.println(i+1 + ". " + Options[i]);
    }

    public int input(int limit) {
        line();
        System.out.print("Choose Your Option : ");
        int input = inputObj.nextInt();
        if (input < 1 || input > limit) {
            System.out.println("The Category doesn't exist!, Try Again.");
            input(limit);
        }
        return input;
    }

    public void line() {
        System.out.println("--------------------------------");
    }
}