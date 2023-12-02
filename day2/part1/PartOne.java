import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/alexstone/IdeaProjects/Advent-Of-Code-2023/day2/part1/input.txt");
        Scanner sc = new Scanner(file);

        int gameCount = 0;
        while (sc.hasNextLine()) {

            String line = sc.nextLine();

            int gameNumber = Integer.parseInt(line.split(":")[0].replace("Game ", ""));

            List<String> setOfGames = List.of(line.split(": ")[1].split("; "));

            boolean isValid = true;
            for(String game : setOfGames) {
                List<String> colors = List.of(game.split(", "));

                int reds = 0;
                int greens = 0;
                int blues = 0;

                for(String color: colors) {
                    if (color.contains("red"))
                        reds += Integer.parseInt(color.split(" ")[0]);
                    else if (color.contains("green"))
                        greens += Integer.parseInt(color.split(" ")[0]);
                    else if (color.contains("blue"))
                        blues += Integer.parseInt(color.split(" ")[0]);
                }

                if (!isValid(reds, greens, blues)) {
                    isValid = false;
                    break;
                }

            }
            if(isValid) {
                gameCount += gameNumber;
            }

        }
        System.out.println("GameCount: " + gameCount);
    }

    public static boolean isValid (int reds, int greens, int blues) {
        boolean isValidGame = true;
        if (reds > 12) {
            isValidGame = false;
        } else if (greens > 13) {
            isValidGame = false;
        } else if (blues > 14) {
            isValidGame = false;
        }
        return isValidGame;
    }
}