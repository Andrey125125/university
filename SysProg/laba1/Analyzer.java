package laba1;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Analyzer {
    public static List<String> parser(String pathname ) throws IOException {
        Scanner scanner = new Scanner(new File(pathname));

        List<String> words = new ArrayList<>();  // this list will contain all words from the text

        while (scanner.hasNext()){
            String next = scanner.next();  //scan for next token
            next = next.replaceAll("[^\\w\\s[_]]", "");
            String[] wordsInToken = next.split("[.,)(}{!?:\"]?\\s+[.,)(}{!?:\"]?");
            words.addAll(Arrays.asList(wordsInToken));

        }
        return words;

    }

    public static Set<String> hasMoreVowels(Iterable<String> words){
        Set<String> set = new TreeSet<>();

        for (String word: words){
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(ch == 'a'|| ch == 'e'|| ch == 'i' ||ch == 'o' ||ch == 'u'||ch == ' '){  // check if vowel
                    count ++;
                }

            }

            if (count > word.length() - count){  // check if vowels more than consonant
                set.add(word);
            }

        }
        return set;

    }

}
