import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayThreePartOne {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/alexstone/IdeaProjects/Advent-Of-Code-2023/day3/part1/example.txt");
        Scanner sc = new Scanner(file);

        List<String> fileAsList = new ArrayList<>();
        int rowSize = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            rowSize = line.length();
            for (int i = 0; i < line.length(); ++i) {
                fileAsList.add(String.valueOf(line.charAt(i)));
            }
        }

        int partNumberSum = 0;

        int i = 0;
        while (i < fileAsList.size()) {

            if (isNumber(fileAsList.get(i))) {
                int startIndex = i;

                int endIndex = i;
                while (endIndex < fileAsList.size() - 1) {
                    if (isNumber(fileAsList.get(endIndex + 1))) {
                        ++endIndex;
                    } else {
                        if(isNumberValid(startIndex, endIndex, rowSize, fileAsList)) {
                            System.out.println("Valid Number" + getNumberFound(startIndex, endIndex, fileAsList));
                        }
//                        System.out.println("Number Found: " + getNumberFound(startIndex, endIndex, fileAsList));
                        i = endIndex + 1;
                        break;
                    }
                }

            } else {
                ++i;
            }
        }

    }

    private static boolean isNumberValid(int startIndex, int endIndex, int rowSize, List<String> fileAsList) {
        // Left
        if (startIndex > 0) {
            if (isSpecialSymbol(fileAsList.get(startIndex - 1))) {
                return true;
            }
        }
        // Right
        if (startIndex != fileAsList.size() - 1) {
            if (isSpecialSymbol(fileAsList.get(startIndex + 1))) {
                return true;
            }
        }
        // Above
        int aboveStart = startIndex - rowSize - 1;
        int aboveEnd = endIndex - rowSize + 1;

        // Below
        int belowStart = startIndex + 10 - 1;
        int belowEnd = startIndex + 10 + 1;

        int index = belowStart;
        while(index <= belowEnd) {
            if(index < fileAsList.size()) {
                if (isSpecialSymbol(fileAsList.get(index))) {
                    return true;
                }
            }
            ++index;
        }

        // Not valid
        return false;
    }

    private static String getNumberFound(int startIndex, int endIndex, List<String> fileAsList) {
        String number = "";
        for (int i = startIndex; i < endIndex + 1; ++i) {
            number = number.concat(fileAsList.get(i));
        }
        return number;
    }

    private static boolean isSpecialSymbol(String str) {
        String nonSpecialSymbols = "123456789.";

        if (!nonSpecialSymbols.contains(str)) {
            System.out.println("Special str: " + str);
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNumber(String str) {
        String numbers = "123456789";

        if (numbers.contains(str)) {
//            System.out.println("Number : " + str);
            return true;
        } else {
            return false;
        }
    }
}