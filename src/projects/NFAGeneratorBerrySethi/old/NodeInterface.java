package projects.NFAGeneratorBerrySethi.old;

import java.util.ArrayList;

public interface NodeInterface {

    // Empty attribute

    boolean getEmpty();

    void setEmpty(boolean empty);

    // First attribute

    ArrayList<Integer> getFirst();

    void addAllFirst(ArrayList<Integer> list);

    void addFirst(int value);

    // next attribute

    ArrayList<Integer> getNext();

    void addAllNext(ArrayList<Integer> list);

    // Last attribute

    ArrayList<Integer> getLast();

    void addAllLast(ArrayList<Integer> list);

    void addLast(int value);

    // Node access/traversal

    NodeInterface getLeft();

    NodeInterface getRight();

    boolean hasPair();

    boolean isLeaf();

    boolean isOr();

    boolean isConcat();

    boolean isAsterisk();

    boolean isQMark();

    boolean isPlus();

    // For leaf nodes.
    int getId();
}
