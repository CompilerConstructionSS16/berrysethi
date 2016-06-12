/**
 * Provides static recursive functions for computing the node attributes.
 */
public class AttributeCalculator {

    /**
     * Post-order.
     */
    public static void emptydfs(Node node) {
        if (node.id != -1) {
            node.setEmpty(false);
            return;
        }

        // Non-terminal
        emptydfs(node.getLeft());
        if (node.hasPair())
            emptydfs(node.getRight());
        empty(node);
    }

    private static void empty(Node node) {
        if (node.type == Node.Type.OR) {
            node.setEmpty(node.getLeft().getEmpty() | node.getRight().getEmpty());
        } else if (node.type == Node.Type.CONCAT) {
            node.setEmpty(node.getLeft().getEmpty() && node.getRight().getEmpty());
        } else if (node.type == Node.Type.ASTERISK) {
            node.setEmpty(true);
        } else if (node.type == Node.Type.Q_MARK) {
            node.setEmpty(true);
        } else if (node.type == Node.Type.PLUS) {
            node.setEmpty(false);
        }
    }

    /**
     * Post-order.
     */
    public static void firstdfs(Node node) {
        if (node.id != -1) {
            node.addFirst(node.id);
            return;
        }

        // Non-terminal
        firstdfs(node.getLeft());
        if (node.hasPair())
            firstdfs(node.getRight());
        first(node);
    }

    private static void first(Node node) {
        if (node.type == Node.Type.OR) {
            node.addAllFirst(node.getLeft().getFirst());
            node.addAllFirst(node.getRight().getFirst());
        } else if (node.type == Node.Type.CONCAT) {
            if (node.getLeft().getEmpty()) {
                node.addAllFirst(node.getRight().getFirst());
            }
            node.addAllFirst(node.getLeft().getFirst());
        } else if (node.type == Node.Type.ASTERISK) {
            node.addAllFirst(node.getLeft().getFirst());
        } else if (node.type == Node.Type.Q_MARK) {
            node.addAllFirst(node.getLeft().getFirst());
        } else if (node.type == Node.Type.PLUS) {
            node.addAllFirst(node.getLeft().getFirst());
        }
    }

    /**
     * Pre-order.
     */
    public static void nextdfs(Node node) {
        if (node.id != -1) {
            // TODO
            //node.attributes.next.add(node.id);
            return;
        }

        // next == empty if root node.

        // Non-terminal
        next(node);

        nextdfs(node.getLeft());
        if (node.hasPair())
            nextdfs(node.getRight());
    }

    private static void next(Node node) {
        if (node.type == Node.Type.OR) {

            node.getLeft().addAllNext(node.getNext());
            node.getRight().addAllNext(node.getNext());

        } else if (node.type == Node.Type.CONCAT) {
            node.getRight().addAllNext(node.getNext());

            if (node.getRight().getEmpty()) {
                node.getLeft().addAllNext(node.getNext());
            }
            node.getLeft().addAllNext(node.getRight().getFirst());

        } else if (node.type == Node.Type.ASTERISK) {

            node.getLeft().addAllNext(node.getNext());
            node.getLeft().addAllNext(node.getLeft().getFirst());

        } else if (node.type == Node.Type.Q_MARK) {

            node.getLeft().addAllNext(node.getNext());

        } else if (node.type == Node.Type.PLUS) {

            node.getLeft().addAllNext(node.getNext());
            node.getLeft().addAllNext(node.getLeft().getFirst());

        }
    }

    /**
     * Post-order.
     */
    public static void lastdfs(Node node) {
        if (node.id != -1) {
            node.addLast(node.id);
            return;
        }

        // Non-terminal
        lastdfs(node.getLeft());
        if (node.hasPair())
            lastdfs(node.getRight());
        last(node);
    }

    private static void last(Node node) {
        if (node.type == Node.Type.OR) {
            node.addAllLast(node.getLeft().getLast());
            node.addAllLast(node.getRight().getLast());
        } else if (node.type == Node.Type.CONCAT) {
            if (node.getRight().getEmpty()) {
                node.addAllLast(node.getLeft().getLast());
            }
            node.addAllLast(node.getRight().getLast());

        } else if (node.type == Node.Type.ASTERISK) {
            node.addAllLast(node.getLeft().getLast());
        } else if (node.type == Node.Type.Q_MARK) {
            node.addAllLast(node.getLeft().getLast());
        } else if (node.type == Node.Type.PLUS) {
            node.addAllLast(node.getLeft().getLast());
        }
    }

    public static void printAttributes(Node node) {
        if (node.id != -1) {
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

}
