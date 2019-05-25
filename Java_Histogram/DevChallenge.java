import java.io.*;
import java.util.*;

public class DevChallenge{

    public static void main(String args[]) throws Exception
    {
        BufferedReader infile = new BufferedReader(new FileReader("Java_Histogram/input.txt"));
        String line;
        HashMap<String,Integer> histogram = new HashMap<String,Integer>();
        while (( line = infile.readLine()) != null )
        {   
            
            line = line.replace(".","").replace(",","").toLowerCase();
            String [] words = line.split(" ");
            //System.out.println(line);

    
            for (String word : words)
            {
                Integer f = histogram.get(word);
                if (f == null) {
                    histogram.put(word,1);
                } else {
                    histogram.put(word,f+1);
                    System.out.println(histogram);
                }
                
            }   
        }
        infile.close();
        printHistogram( histogram );
    }
    private static void printHistogram( HashMap<String,Integer> hm )
    {
        System.out.println(hm);
    }
}
