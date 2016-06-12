import java.util.ArrayList;

class NodeAttributes {
    public boolean empty = false;
    public ArrayList<Integer> first = new ArrayList<>();
    public ArrayList<Integer> next = new ArrayList<>();
    public ArrayList<Integer> last = new ArrayList<>();

    public String toString() {
        String str = new String();

        str += empty + " | ";
        str += first.toString() + " | ";
        str += next.toString() + " | ";
        str += first.toString();

        return str;
    }
}

public class Node {
    public enum Type {
        CONCAT, // ab
        OR, // a|b
        Q_MARK, // a? : Zero or one
        ASTERISK, // a* : Zero or more
        PLUS, // a+ : One or more

        LEAF // Terminal character
    }

    private static int idCounter = 0;

    private Node parent = null;
    public ArrayList<Node> children = new ArrayList<>();

    public Type type;
    public int id;

    public char terminal;
    public int firstCharIndex = -1;

    public NodeAttributes attributes = new NodeAttributes();

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

    public boolean getEmpty() {
        return attributes.empty;
    }

    public ArrayList<Integer> getFirst() {
        return attributes.first;
    }

    public ArrayList<Integer> getNext() {
        return attributes.next;
    }

    public ArrayList<Integer> getLast() {
        return attributes.last;
    }

    public Node getLeft() {
        if (children.size() > 0)
            return children.get(0);
        return null;
    }

    public Node getRight() {
        if (hasPair())
            return children.get(1);
        return null;
    }

    public boolean hasPair() {
        if (children.size() > 1)
            return true;
        return false;
    }

    public Node getParents() {
        return parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public void computeAttributes() {
        System.out.println("Empty attributes ...");
        AttributeCalculator.emptydfs(this);
        System.out.println("First attributes ...");
        AttributeCalculator.firstdfs(this);
        System.out.println("Next attributes ...");
        AttributeCalculator.nextdfs(this);
        System.out.println("Last attributes ...");
        AttributeCalculator.lastdfs(this);
    }

}
