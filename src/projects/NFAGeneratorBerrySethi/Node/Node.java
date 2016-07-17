package projects.NFAGeneratorBerrySethi.Node;

import projects.NFAGeneratorBerrySethi.NodeInterface;

import java.util.ArrayList;

class NodeAttributes {
    public boolean empty = false;
    public ArrayList<Integer> first = new ArrayList<>();
    public ArrayList<Integer> next = new ArrayList<>();
    public ArrayList<Integer> last = new ArrayList<>();

    public String toString() {
        String str = "";

        str += empty + " | ";
        str += first.toString() + " | ";
        str += next.toString() + " | ";
        str += first.toString();

        return str;
    }
}

public class Node implements NodeInterface {

    private static int idCounter = 0;
    public ArrayList<Node> children = new ArrayList<>();
    public Type type;
    public int id;
    public char terminal;
    public int firstCharIndex = -1;
    public NodeAttributes attributes = new NodeAttributes();
    private Node parent = null;
    public Node(Type type) {
        this.type = type;

        // Only a leaf gets an valid id.
        if (type != Type.LEAF) {
            id = -1;
        } else {
            id = idCounter;
            idCounter++;
        }
    }
    
    public static void ResetIdCounter(){
    	idCounter = 0;
    }

    public static void printAttributes(Node node) {
        if (node.isLeaf()) {
            System.out.println(node.type + " " + node.terminal + node.id +
                    ": " + node.attributes.toString());
            return;
        }

        System.out.println(node.type + ": " + node.attributes.toString());

        // Non-terminal
        printAttributes(node.getLeft());
        if (node.hasPair())
            printAttributes(node.getRight());
    }

    public void addParent(Node parent) {
        if (parent == null)
            return;

        this.parent = parent;
        parent.children.add(this);

        if (parent.firstCharIndex == -1 || parent.firstCharIndex > firstCharIndex) {
            parent.firstCharIndex = firstCharIndex;
        }
    }

    public void Debug(String tab) {
        tab = tab + "    ";
        if (type == Type.LEAF) {
            System.out.println(tab + terminal + id);
            return;
        }

        System.out.println(tab + type + " {");
        for (int i = 0; i < children.size(); i++) {
            children.get(i).Debug(tab);
        }
        System.out.println(tab + "}");
    }

    @Override
    public boolean getEmpty() {
        return attributes.empty;
    }

    @Override
    public void setEmpty(boolean empty) {
        attributes.empty = empty;
    }

    @Override
    public ArrayList<Integer> getFirst() {
        return attributes.first;
    }

    @Override
    public void addAllFirst(ArrayList<Integer> list) {
        attributes.first.addAll(list);
    }

    @Override
    public void addFirst(int value) {
        attributes.first.add(value);
    }

    @Override
    public ArrayList<Integer> getNext() {
        return attributes.next;
    }

    @Override
    public void addAllNext(ArrayList<Integer> list) {
        attributes.next.addAll(list);
    }

    @Override
    public ArrayList<Integer> getLast() {
        return attributes.last;
    }

    @Override
    public void addAllLast(ArrayList<Integer> list) {
        attributes.last.addAll(list);
    }

    @Override
    public void addLast(int value) {
        attributes.last.add(value);
    }

    @Override
    public Node getLeft() {
        if (children.size() > 0)
            return children.get(0);
        return null;
    }

    @Override
    public Node getRight() {
        if (hasPair())
            return children.get(1);
        return null;
    }

    @Override
    public boolean hasPair() {
        if (children.size() > 1)
            return true;
        return false;
    }

    @Override
    public boolean isLeaf() {
        if (id != -1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isOr() {
        return type == Type.OR;
    }

    @Override
    public boolean isConcat() {
        return type == Type.CONCAT;
    }

    @Override
    public boolean isAsterisk() {
        return type == Type.ASTERISK;
    }

    @Override
    public boolean isQMark() {
        return type == Type.Q_MARK;
    }

    @Override
    public boolean isPlus() {
        return type == Type.PLUS;
    }

    @Override
    public int getId() {
        return id;
    }

    public enum Type {
        CONCAT, // ab
        OR, // a|b
        Q_MARK, // a? : Zero or one
        ASTERISK, // a* : Zero or more
        PLUS, // a+ : One or more

        LEAF // Terminal character
    }

}
