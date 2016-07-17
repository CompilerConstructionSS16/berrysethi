package helper;

import projects.NFAGeneratorBerrySethi.Node.Node;

/**
 * Creates a syntax tree based on a regular expression.
 */
public class TreeBuilder {

    // Used for '.', '|'
    public static Node Concatify(Node node1, Node node2, Node.Type type) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        Node nodeRoot = new Node(type);
        node1.addParent(nodeRoot);
        node2.addParent(nodeRoot);
        return nodeRoot;
    }

    // Used for '*', '+', '?'
    public static Node Parentify(Node node, Node.Type type) {
        if (node == null) return null;
        Node nodeRoot = new Node(type);
        node.addParent(nodeRoot);
        return nodeRoot;
    }

    // This function returns the projects.NFAGeneratorBerrySethi.Node.Node from last of terminal in the string
    // or calculates the parse tree of the last parenthesis in the string
    // it depends on what is on the end
    public static Node LastNode(String input) {
        if (input.length() == 0) return null;

        char c = input.charAt(input.length() - 1);

        // When it is end of a parenthesis find the start and calculate tree inside.
        if (c == ')') {
            int indexOpen = StringToolkit.ParenthesisOpeningIndex(input);
            Node node = Parse(input.substring(indexOpen + 1, input.length() - 1));
            node.firstCharIndex = indexOpen;
            return node;
        } else {
            Node node = new Node(Node.Type.LEAF);
            node.firstCharIndex = input.length() - 1;
            node.terminal = c;
            return node;
        }
    }

    public static Node Parse(String input) {
        System.out.println("Regex input: " + input);

        int z = input.length() - 1;
        Node node;

        if (z < 0) return null;

        // input is just one terminal
        if (z == 0) {
            node = new Node(Node.Type.LEAF);
            node.firstCharIndex = 0;
            node.terminal = input.charAt(0);
            return node;
        }

        switch (input.charAt(z)) {

			/* find the last terminal or parenthesis not including the
            end character and parent it with one of '*','?','+' nodes */
            case '*':
                node = Parentify(LastNode(input.substring(0, z)), Node.Type.ASTERISK);
                break;
            case '?':
                node = Parentify(LastNode(input.substring(0, z)), Node.Type.Q_MARK);
                break;
            case '+':
                node = Parentify(LastNode(input.substring(0, z)), Node.Type.PLUS);
                break;

			/* find the last terminal or parenthesis anywhere */
            default:
                node = LastNode(input);
                break;
        }

        if (node == null) return null;

        // firstCharIndex of terminal is its index in the string
        // firstCharIndex of parenthesis is where '(' is at
        int i = node.firstCharIndex;

        if (i == 0) {
            return node;
        } else if (i > 0 && input.charAt(i - 1) == '|') {
            return Concatify(Parse(input.substring(0, i - 1)), node, Node.Type.OR);
        } else {
            return Concatify(Parse(input.substring(0, i)), node, Node.Type.CONCAT);
        }
    }

}
