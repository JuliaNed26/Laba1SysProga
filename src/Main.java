public class Main {
    public static void main(String[] args) {
        WordAnalizator wa = new WordAnalizator("C:\\Users\\nedav\\IdeaProjects\\Laba1\\in.txt");
        for(var word : wa.getWordsWithMoreVowelsThanConsonants())
        {
            System.out.println(word);
        }
    }
}