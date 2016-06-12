/**
 * Provides static recursive functions for computing the node attributes.
 */
public class AttributeCalculator {

    /**
     * Post-order.
     */
    public static void emptydfs(Node node) {
        if (node.id != -1) {
            node.attributes.empty = false;
            return;
        }

        // Non-terminal
        emptydfs(node.getLeft());
        if (node.hasPair())
            emptydfs(node.getRight());
        empty(node);
    }

    /**
     * Post-order.
     */
    public static void firstdfs(Node node) {
        if (node.id != -1) {
            node.attributes.first.add(node.id);
            return;
        }

        // Non-terminal
        firstdfs(node.getLeft());
        if (node.hasPair())
            firstdfs(node.getRight());
        first(node);
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

    /**
     * Post-order.
     */
    public static void lastdfs(Node node) {
        if (node.id != -1) {
            // TODO
            node.attributes.last.add(node.id);
            return;
        }

        // Non-terminal
        firstdfs(node.getLeft());
        if (node.hasPair())
            firstdfs(node.getRight());
        first(node);
    }

    private static void empty(Node node) {
        if (node.type == Node.Type.OR) {
            node.attributes.last.addAll(node.getLeft().getLast());
            node.attributes.last.addAll(node.getRight().getLast());
        } else if (node.type == Node.Type.CONCAT) {
            if (node.getRight().getEmpty()) {
                node.attributes.last.addAll(node.getLeft().getLast());
            }
            node.attributes.last.addAll(node.getRight().getLast());
        } else if (node.type == Node.Type.ASTERISK) {
            node.attributes.last.addAll(node.getLeft().getLast());
        } else if (node.type == Node.Type.Q_MARK) {
            node.attributes.last.addAll(node.getLeft().getLast());
        } else if (node.type == Node.Type.PLUS) {
            // TODO
            node.attributes.last.addAll(node.getLeft().getLast());
        }
    }

    private static void first(Node node) {
        if (node.type == Node.Type.OR) {
            node.attributes.first.addAll(node.getLeft().getFirst());
            node.attributes.first.addAll(node.getRight().getFirst());
        } else if (node.type == Node.Type.CONCAT) {
            if (node.getLeft().getEmpty()) {
                node.attributes.first.addAll(node.getRight().getFirst());
            }
            node.attributes.first.addAll(node.getLeft().getFirst());
        } else if (node.type == Node.Type.ASTERISK) {
            node.attributes.first.addAll(node.getLeft().getFirst());
        } else if (node.type == Node.Type.Q_MARK) {
            node.attributes.first.addAll(node.getLeft().getFirst());
        } else if (node.type == Node.Type.PLUS) {
            node.attributes.first.addAll(node.getLeft().getFirst());
        }
    }

    private static void next(Node node) {
        if (node.type == Node.Type.OR) {
            node.getLeft().getNext().addAll(node.attributes.next);
            node.getRight().getNext().addAll(node.attributes.next);
        } else if (node.type == Node.Type.CONCAT) {
            node.getRight().getNext().addAll(node.attributes.next);

            if (node.getRight().getEmpty()) {
                node.getLeft().getNext().addAll(node.attributes.next);
            }
            node.getLeft().getNext().addAll(node.getRight().getFirst());

        } else if (node.type == Node.Type.ASTERISK) {
            node.getLeft().getNext().addAll(node.attributes.next);
            node.getLeft().getNext().addAll(node.getLeft().getFirst());
        } else if (node.type == Node.Type.Q_MARK) {
            node.getLeft().getNext().addAll(node.attributes.next);
        } else if (node.type == Node.Type.PLUS) {
            //node.getLeft().getNext().addAll(node.attributes.next);
            // TODO
        }
    }

    private static void last(Node node) {
        if (node.type == Node.Type.OR) {
            node.getLeft().getNext().addAll(node.attributes.next);
            node.getRight().getNext().addAll(node.attributes.next);
        } else if (node.type == Node.Type.CONCAT) {
            node.getRight().getNext().addAll(node.attributes.next);

            if (node.getRight().getEmpty()) {
                node.getLeft().getNext().addAll(node.attributes.next);
            }
            node.getLeft().getNext().addAll(node.getRight().getFirst());

        } else if (node.type == Node.Type.ASTERISK) {
            node.getLeft().getNext().addAll(node.attributes.next);
            node.getLeft().getNext().addAll(node.getLeft().getFirst());
        } else if (node.type == Node.Type.Q_MARK) {
            node.getLeft().getNext().addAll(node.attributes.next);
        } else if (node.type == Node.Type.PLUS) {
            //node.getLeft().getNext().addAll(node.attributes.next);
            // TODO
        }
    }

}
