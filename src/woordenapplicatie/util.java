package woordenapplicatie;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileReader;
        import java.io.IOException;
        import java.text.BreakIterator;
        import java.util.*;

public class util {


    public static ArrayList<String> stringToWordList(String _taInput) {
        return new ArrayList<>(Arrays.asList(_taInput.split("\\s*(=>|,|\\s)\\s*")));
    }

    public static ArrayList<String> sortWords(ArrayList<String> wordList){
        wordList.sort(Collections.reverseOrder());
        TreeSet<String> wordTree = new TreeSet<>(wordList);
        wordTree.descendingSet();
        return wordList;
    }

    public static Map<String, Integer> getWordFrequency(String text) {
        Map<String, Integer> frequency = new HashMap<>();

        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();

            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                String word = text.substring(firstIndex, lastIndex);
                if (!frequency.containsKey(word)) {  // first time we've seen this string
                    frequency.put(word, 1);
                } else {
                    int count = frequency.get(word);
                    frequency.put(word, count + 1);
                }
                //concordance.computeIfPresent(word, count -> {return Integer.parseInt(count + 1);});
            }
        }

        return frequency;
    }

    public static Map<String, Set<Integer>> wordConcordance(String string) { // O(N^2)
        int off = 0;
        int lineNext;
        int lineNumber = 0;
        string = string.replaceAll("[\\t\\n\\r]+", "\n").replace('é', 'e');
        HashMap<String, Set<Integer>> map = new HashMap<>();
        while ((lineNext = string.indexOf('\n', off)) != -1) {
            lineNumber += 1;
            // loops through each line
            int wordOff = 0;
            int wordNext = 0;
            String line = string.substring(off, lineNext).replaceAll("[^A-Z a-z]+", "").toLowerCase() + " ";
            while ((wordNext = line.indexOf(' ', wordOff)) != -1) {
                String word = line.substring(wordOff, wordNext);
                Set<Integer> lineSet = map.getOrDefault(word, new HashSet<>());
                lineSet.add(lineNumber);
                map.put(word, lineSet);
                wordOff = wordNext + 1;
            }
            off = lineNext + 1;
        }

        if (off == 0) {
            return new HashMap<>();
        }

        int wordOff = 0;
        int wordNumber = 0;
        String line = string.substring(off, string.length());
        while ((wordNumber = line.indexOf(' ', wordOff)) != -1) {
            String word = line.substring(wordOff, wordNumber);
            Set<Integer> lineSet = map.getOrDefault(word, new HashSet<>());
            lineSet.add(lineNumber + 1);
            map.put(word, lineSet);
            wordOff = wordNumber + 1;
        }
        return map;
    }

    public static HashSet<String> removeDuplicates(List<String> wordList) {
        return new HashSet<>(wordList);
    }

    public static String usingBufferedReader(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

}
