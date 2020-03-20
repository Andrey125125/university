package laba1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        /*
        8.      Знайти ті слова, що мають більше голосних ніж приголосних літер.

         */

        List<String> words = new ArrayList<>();
        try {
            words = Analyzer.parser("C:\\Users\\Andrey.LAPTOP-26CASLG5\\IdeaProjects\\java labs knu\\src\\laba1\\text.txt");
        } catch (IOException e){
            System.out.println("please provide proper path to the file either absolute or relative");
        }

        Set<String> set = Analyzer.hasMoreVowels(words);  //returns words that have more vowels than consonants


        System.out.println(set);  // prints answer here
    }
}
