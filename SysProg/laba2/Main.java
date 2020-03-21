package laba2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * Знайти всі слова (без періодичних фрагментів), що сприймаються заданим скінченим автоматом.
 *
 * text.txt description:
 * 4
 * a b c d                     – вхідний алфавіт,
 * 8
 * 0 1 2 3 4 5 6 7             – множина станів
 * 0                           – початковий стан
 * 0 b 2                       - функція переходів
 * 0 c 3
 * 1 c 2
 * 2 b 1
 * 2 c 7
 * 3 a 2
 * 3 b 6
 * 3 d 4
 * 4 a 7
 * 4 c 5
 * 5 c 3
 */
public class Main {

    static List<Node> nodes = new ArrayList<>();  // list that contain nodes
    static Set<String> words;  // words that automat can use
    static Set<String> paths = new HashSet<>(); // moves between nodes
    static int startNode;  //start node

    public static void main(String[] args){

        try {  // read automat.txt and do initialization
            initialize("C:\\Users\\Andrey.LAPTOP-26CASLG5\\IdeaProjects\\java labs knu\\src\\laba2\\automat.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        doStuff("0", 0);  //generate all possible path here
        words = pathToWord(paths);  // convert paths to words

        System.out.println(words);  //print answer here

    }

    /**
     * most important method of all program
     * works recursively!
     * go thru all possible paths and add them to List
     * @param path current path
     * @param currentNode current node
     */
    public static void doStuff(String path, int currentNode){


        if (nodes.get(currentNode).edges.keySet().size() == 0) {  //if no moves exist from current node
            paths.add(path);
            return;
        }
        Set<Integer> available =  new HashSet<Integer>(nodes.get(currentNode).edges.keySet());
        available.removeAll(usedEdges(path, currentNode));  // remove used edges to avoid LOOPS
        if (available.size() == 0){  // if all edges already used
            paths.add(path);
            return;
        }

        List<Integer> list = new ArrayList<>(available);  // write all available edges to list
        for (Integer i: list){
            doStuff(path + i, i);  // i used recursion here to go thru all
                                         // possible paths in graph
        }



    }

    /**
     *
     * this function ensure that no LOOPS are in path
     *
     * @param path  path
     * @param currentNode current node
     * @return  set of integers
     */
    public static Set<Integer> usedEdges(String path, int currentNode){
        int len = path.length();
        Set<Integer> set = new HashSet();
        for (int i = 0; i < len; i++) {
            if (Character.getNumericValue(path.charAt(i)) == currentNode && i + 1 < len){
                i++;
                set.add(Character.getNumericValue(path.charAt(i)));
            }
        }
        return set;
    }

    /**
     *  initialize automat from text.txt
     *
     * @param path path to input file
     * @throws FileNotFoundException
     */
    public static void initialize(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));

        scanner.nextLine();
        scanner.nextLine();
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            nodes.add(new Node(scanner.nextInt()));
        }
        startNode = scanner.nextInt();

        while (scanner.hasNextLine()){

            String line = scanner.nextLine();
            if (line.length() != 0){
                nodes.get(Character.getNumericValue(line.charAt(0))).edges.
                        put(Character.getNumericValue(line.charAt(4)), line.charAt(2));
            }

        }


    }


    /**
     * convert paths to words
     *
     * @param paths all possible paths
     * @return all possible words that can be used by automat
     */
    public static Set<String> pathToWord(Set<String> paths){
        Set<String> set = new HashSet<>();
        for (String path: paths){
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < path.length() - 1; i++) {
                str.append(nodes.get(Character.getNumericValue(path.charAt(i))).edges.get(Character.getNumericValue(path.charAt(i+ 1))));
            }
            set.add(str.toString());
        }
        return set;
    }
}

