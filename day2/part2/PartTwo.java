import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/alexstone/IdeaProjects/Advent-Of-Code-2023/day2/part2/input.txt");
        Scanner sc = new Scanner(file);

        int sumOfPower = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            List<String> setOfGames = List.of(line.split(": ")[1].split("; "));

            int redMax = 0;
            int greenMax = 0;
            int blueMax = 0;

            for (String game : setOfGames) {

                List<String> colors = List.of(game.split(", "));

                for (String color : colors) {
                    if (color.contains("red")) {
                        int count = Integer.parseInt(color.split(" ")[0]);
                        if (count > redMax) {
                            redMax = count;
                        }
                    } else if (color.contains("green")) {
                        int count = Integer.parseInt(color.split(" ")[0]);
                        if (count > greenMax) {
                            greenMax = count;
                        }
                    } else if (color.contains("blue")) {
                        int count = Integer.parseInt(color.split(" ")[0]);
                        if (count > blueMax) {
                            blueMax = count;
                        }
                    }
                }
            }
            sumOfPower += redMax * greenMax * blueMax;
        }
        System.out.println("SumOfPower: " + sumOfPower);
    }
}