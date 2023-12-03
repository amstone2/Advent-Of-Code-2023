import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class DayThreePartTwo {

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


        Map<List<Integer>, List<String>> mapStarToNumber = new HashMap<>();
        int i = 0;
        while (i < rows) {
            int j = 0;
            while (j < columns) {
                if (isNumber(schematic[i][j])) {
                    int numberStart = j;
                    int numberEnd = getNumberEnd(i, j, schematic);
                    j = numberEnd + 1;

                    String number = getNumber(i, numberStart, numberEnd, schematic);
                    List<Integer> starLocation = starLocation(numberStart, numberEnd, i, schematic);
                    if (!starLocation.isEmpty()) {
                        if(mapStarToNumber.containsKey(starLocation)) {
                            List<String> strings = mapStarToNumber.get(starLocation);
                            List<String> tmp = new ArrayList<>();
                            tmp.addAll(strings);
                            tmp.add(number);
                            mapStarToNumber.put(starLocation, tmp);
                        }
                        else {
                            mapStarToNumber.put(starLocation, Arrays.asList(number));

                        }
                    }
                } else {
                    ++j;
                }
            }
            ++i;
        }

        int gearRatio = 0;

        for(Map.Entry<List<Integer>, List<String>> entry: mapStarToNumber.entrySet()) {
            if(entry.getValue().size() == 2) {
                gearRatio += Integer.parseInt(entry.getValue().get(0)) * Integer.parseInt(entry.getValue().get(1));
            }
        }

        System.out.println("Gear Ratio: " + gearRatio);
    }

    private static String getNumber(int i, int numberStart, int numberEnd, String[][] schematic) {
        String number = "";

        int index = numberStart;
        while (index <= numberEnd) {
            number = number.concat(schematic[i][index]);
            ++index;
        }
        return number;
    }

    private static int getNumberEnd(int i, int j, String[][] schematic) {
        int numberEnd = j;

        int index = j + 1;
        while (index < schematic[0].length) {
            if (isNumber(schematic[i][index])) {
                ++index;
                ++numberEnd;
            } else {
                break;
            }
        }
        return numberEnd;
    }

    private static List<Integer> starLocation(int numberStart, int numberEnd, int row, String[][] schematic) {
        for (int i = numberStart; i <= numberEnd; ++i) {
            // Left
            if (i > 0) {
                if (isStar(schematic[row][i - 1])) {
                    return Arrays.asList(row, i - 1);
                }
            }
            // Right
            if (i != schematic[row].length - 1) {
                if (isStar(schematic[row][i + 1])) {
                    return Arrays.asList(row, i + 1);
                }
            }
            // Above
            if (row > 0) {
                if (isStar(schematic[row - 1][i])) {
                    return Arrays.asList(row - 1, i);
                }
            }
            // Below
            if (row + 1 < schematic[0].length) {
                if (isStar(schematic[row + 1][i])) {
                    return Arrays.asList(row + 1, i);
                }
            }
            // Above right
            if (row > 0 && i != schematic[row].length - 1) {
                if (isStar(schematic[row - 1][i + 1])) {
                    return Arrays.asList(row - 1, i + 1);
                }
            }
            // Above left
            if (row > 0 && i > 0) {
                if (isStar(schematic[row - 1][i - 1])) {
                    return Arrays.asList(row - 1, i - 1);
                }
            }
            // Below right
            if (row + 1 < schematic[0].length && i != schematic[row].length - 1) {
                if (isStar(schematic[row + 1][i + 1])) {
                    return Arrays.asList(row + 1, i + 1);
                }
            }
            // Below left
            if (row + 1 < schematic[0].length && i > 0) {
                if (isStar(schematic[row + 1][i - 1])) {
                    return Arrays.asList(row + 1, i - 1);
                }
            }
        }
        return new ArrayList<>();
    }

    private static boolean isStar(String str) {
        return str.contains("*");
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