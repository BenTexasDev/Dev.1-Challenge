import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.PrintStream;

public class DevChallenge {

    private static boolean DESC = false;

    public static void main(String args[]) throws Exception {
        BufferedReader infile = new BufferedReader(new FileReader("Java_Histogram/input.txt"));
        PrintStream fileout = new PrintStream("Java_Histogram/output.txt");
        String line;
        Map<String, Integer> histogram = new HashMap<String, Integer>();
        while ((line = infile.readLine()) != null) {

            line = line.replace(".", "").replace(",", "").toLowerCase();
            String[] words = line.split(" ");

            for (String word : words) {
                Integer f = histogram.get(word);
                if (f == null) {
                    histogram.put(word, 1);
                } else {
                    histogram.put(word, f + 1);
                }

            }
        }

        System.setOut(fileout);
        infile.close();

        Map<String, Integer> sortedMapDesc = sortByValue(histogram, DESC);
        printHistogram(sortedMapDesc);
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> histogram, final boolean order) {
        List<Entry<String, Integer>> list = new LinkedList<>(histogram.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order
                ? o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey())
                        : o1.getValue().compareTo(o2.getValue())
                : o2.getValue().compareTo(o1.getValue()) == 0 ? o2.getKey().compareTo(o1.getKey())
                        : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    private static void printHistogram(Map<String, Integer> map) {
        for (String m : map.keySet()) {

            int value = map.get(m);
            String separate = "|";
            String occurence = ("  (" + value + ")\n");
            System.out.printf("%-7s %-2s", m, separate);

            for (int i = 0; i < value; i++) {
                System.out.printf("=");
            }
            System.out.printf(occurence);
        }
    }
}
