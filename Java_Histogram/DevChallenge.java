import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Comparator;
/**
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class DevChallenge {

    private static boolean DESC = false;
    public static void main(String args[]) throws Exception {
        BufferedReader infile = new BufferedReader(new FileReader("Java_Histogram/input.txt"));
        PrintStream fileOut = new PrintStream("Java_Histogram/output.txt");
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

        System.setOut(fileOut);
        infile.close();

        Map<String, Integer> sortedMapDesc = sortByValue(histogram, DESC);
        printHistogram(sortedMapDesc);
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> histogram, final boolean order) {
        // Sort needs a list so lets convert entrySet into a list
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(histogram.entrySet());
        // Create a custom comparator to sort entries based upon value
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        // Copy entries from list to map
        for (Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
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
