import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OOP2_FinalProject {
    public static void main(String[] args) throws FileNotFoundException {
        File pitcherFile = new File("22PitcherStats.csv");
        Scanner input = new Scanner(pitcherFile);
        input.useDelimiter(",");
        String headerLine = input.nextLine();
        System.out.println(input.next());

    }
}
