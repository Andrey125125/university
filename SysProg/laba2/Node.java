package laba2;

import java.util.HashMap;
import java.util.Map;

/**
 * this class represents nodes in automat.
 * objects of this class contain node name
 * and all it edges
 */
public class Node{
    private final int id;
    Map<Integer, Character> edges = new HashMap<>();

    Node(int id){
        this.id = id;
    }

}