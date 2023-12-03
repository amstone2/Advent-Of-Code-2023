import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayThreePartOne {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Alex\\IdeaProjects\\Advent-Of-Code-2023\\day3\\part1\\input.txt");
        Scanner sc = new Scanner(file);


        int rows = 140;
        int columns = 140;
        String[][] schematic = new String[rows][columns];
        while (sc.hasNextLine()) {
            for (String[] strings : schematic) {
                String[] line = sc.nextLine().trim().split("");
                System.arraycopy(line, 0, strings, 0, line.length);
            }
        }


        int totalPartNumbers = 0;
        int i = 0;
        while (i < rows) {
            int j = 0;
            while (j < columns) {
                if (isNumber(schematic[i][j])) {
                    int numberStart = j;
                    int numberEnd = getNumberEnd(i, j, schematic);
                    j = numberEnd + 1;

                    if (isNumberValid(numberStart, numberEnd, i, schematic)) {
                        String number = getNumber(i, numberStart, numberEnd, schematic);
                        totalPartNumbers += Integer.parseInt(number);
                    }
                } else {
                    ++j;
                }
            }
            ++i;
        }

        System.out.println("totalPartNumbers: " + totalPartNumbers);
    }

    private static String getNumber(int i, int numberStart, int numberEnd, String[][] schematic) {
        String number = "";

        int index = numberStart;
        while(index <= numberEnd) {
            number = number.concat(schematic[i][index]);
            ++index;
        }
        return number;
    }

    private static int getNumberEnd(int i, int j, String[][] schematic) {
        int numberEnd = j;

        int index = j + 1;
        while (index < schematic[0].length - 1) {
            if (isNumber(schematic[i][index])) {
                ++index;
                ++numberEnd;
            }
            else {
                break;
            }
        }
        return numberEnd;
    }

    private static boolean isNumberValid(int numberStart, int numberEnd, int row, String[][] schematic) {
        for (int i = numberStart; i <= numberEnd; ++i) {
            // Left
            if (i > 0) {
                if (isSpecialSymbol(schematic[row][i - 1])) {
                    return true;
                }
            }
            // Right
            if (i != schematic[row].length - 1) {
                if (isSpecialSymbol(schematic[row][i + 1])) {
                    return true;
                }
            }
            // Above
            if (row > 0) {
                if (isSpecialSymbol(schematic[row - 1][i])) {
                    return true;
                }
            }
            // Below
            if (row + 1 < schematic[0].length) {
                if (isSpecialSymbol(schematic[row + 1][i])) {
                    return true;
                }
            }
            // Above right
            if (row > 0 && i != schematic[row].length - 1) {
                if (isSpecialSymbol(schematic[row - 1][i + 1])) {
                    return true;
                }
            }
            // Above left
            if (row > 0 && i > 0) {
                if (isSpecialSymbol(schematic[row - 1][i - 1])) {
                    return true;
                }
            }
            // Below right
            if (row + 1 < schematic[0].length && i != schematic[row].length - 1) {
                if (isSpecialSymbol(schematic[row + 1][i + 1])) {
                    return true;
                }
            }
            // Below left
            if (row + 1 < schematic[0].length && i > 0) {
                if (isSpecialSymbol(schematic[row + 1][i - 1])) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isSpecialSymbol(String str) {
        String nonSpecialSymbols = "1234567890.";
        if (!nonSpecialSymbols.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNumber(String str) {
        String numbers = "1234567890";

        if (numbers.contains(str)) {
            return true;
        } else {
            return false;
        }
    }
}