import java.io.*;
import java.util.*;

public class WordAnalizator {
    final List<Character> vowels = Arrays.asList(new Character[]{ 'a', 'e', 'i', 'o', 'u', 'y' });
    final List<Character> consonants =
            Arrays.asList(new Character[]{ 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z' });
    private ArrayList<Character> separators;
    final String filePath;

    public WordAnalizator(String path)
    {
        filePath = path;
        fillSeparators();
    }

    private void fillSeparators()
    {
        separators = new ArrayList<Character>();
        for (int i = 0; i < 48; i++)
        {
            separators.add((char)i);
        }
        for (int i = 58; i < 65; i++)
        {
            separators.add((char)i);
        }
        for (int i = 91; i < 97; i++)
        {
            separators.add((char)i);
        }
        for (int i = 123; i < 127; i++)
        {
            separators.add((char)i);
        }
    }

    private boolean isWordHaveMoreVowelsThanConsonants(String word)
    {
        int vowelsCount = 0;
        int consolantsCount = 0;
        for(int i = 0; i < word.length(); i++)
        {
            char letter = word.charAt(i);
            if(vowels.contains(letter))
            {
                vowelsCount++;
            }
            else
            {
                consolantsCount++;
            }
        }
        return vowelsCount > consolantsCount;
    }
    public HashSet<String> getWordsWithMoreVowelsThanConsonants()
    {
        HashSet<String> appropriateWords = new HashSet<String>();
        try {
            File fileToRead = new File(filePath);
            InputStream in = new FileInputStream(fileToRead);
            Reader reader = new InputStreamReader(in);
            Reader buffer = new BufferedReader(reader);
            StringBuilder wordSb = new StringBuilder();
            int symbolNum;
            try {
                while ((symbolNum = buffer.read()) != -1) {
                    char symbol = (char) symbolNum;
                    if (separators.contains(symbol)) {
                        addIfAppropriate(wordSb.toString(), appropriateWords);
                        wordSb.setLength(0);
                    } else {
                        wordSb.append(symbol);
                    }
                }
            }
            catch(IOException ex)
            {
                System.out.println("An error occurred.");
                ex.printStackTrace();
            }
            addIfAppropriate(wordSb.toString(),appropriateWords);
        }
        catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
        return appropriateWords;
    }

    private void addIfAppropriate (String word, HashSet<String> wordsList)
    {
        if (isWordHaveMoreVowelsThanConsonants(word))
        {
            wordsList.add(word);
        }
    }
}
