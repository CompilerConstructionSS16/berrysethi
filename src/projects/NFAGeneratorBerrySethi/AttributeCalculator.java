package projects.NFAGeneratorBerrySethi;

/**
 * Provides static recursive functions for computing the node attributes.
 */
public class AttributeCalculator {

    /**
     * Post-order.
     */
    public static void emptydfs(NodeInterface node) {
        if (node.isLeaf()) {
            node.setEmpty(false);
            return;
        }

        // Non-terminal
        emptydfs(node.getLeft());
        if (node.hasPair())
            emptydfs(node.getRight());
        empty(node);
    }

    private static void empty(NodeInterface node) {
        if (node.isOr()) {

            node.setEmpty(node.getLeft().getEmpty() | node.getRight().getEmpty());

        } else if (node.isConcat()) {

            node.setEmpty(node.getLeft().getEmpty() && node.getRight().getEmpty());

        } else if (node.isAsterisk()) {

            node.setEmpty(true);

        } else if (node.isQMark()) {

            node.setEmpty(true);

        } else if (node.isPlus()) {

            node.setEmpty(false);

        }
    }

    /**
     * Post-order.
     */
    public static void firstdfs(NodeInterface node) {
        if (node.isLeaf()) {
            node.addFirst(node.getId());
            return;
        }

        // Non-terminal
        firstdfs(node.getLeft());
        if (node.hasPair())
            firstdfs(node.getRight());
        first(node);
    }

    private static void first(NodeInterface node) {
        if (node.isOr()) {

            node.addAllFirst(node.getLeft().getFirst());
            node.addAllFirst(node.getRight().getFirst());

        } else if (node.isConcat()) {

            if (node.getLeft().getEmpty()) {
                node.addAllFirst(node.getRight().getFirst());
            }
            node.addAllFirst(node.getLeft().getFirst());

        } else if (node.isAsterisk()) {

            node.addAllFirst(node.getLeft().getFirst());

        } else if (node.isQMark()) {

            node.addAllFirst(node.getLeft().getFirst());

        } else if (node.isPlus()) {

            node.addAllFirst(node.getLeft().getFirst());

        }
    }

    /**
     * Pre-order.
     */
    public static void nextdfs(NodeInterface node) {
        if (node.isLeaf()) {
            // Nothing to do here, next attribute is inherited.
            return;
        }

        // next == empty if root node.

        // Non-terminal
        next(node);

        nextdfs(node.getLeft());
        if (node.hasPair())
            nextdfs(node.getRight());
    }

    private static void next(NodeInterface node) {
        if (node.isOr()) {

            node.getLeft().addAllNext(node.getNext());
            node.getRight().addAllNext(node.getNext());

        } else if (node.isConcat()) {
            node.getRight().addAllNext(node.getNext());

            if (node.getRight().getEmpty()) {
                node.getLeft().addAllNext(node.getNext());
            }
            node.getLeft().addAllNext(node.getRight().getFirst());

        } else if (node.isAsterisk()) {

            node.getLeft().addAllNext(node.getNext());
            node.getLeft().addAllNext(node.getLeft().getFirst());

        } else if (node.isQMark()) {

            node.getLeft().addAllNext(node.getNext());

        } else if (node.isPlus()) {

            node.getLeft().addAllNext(node.getNext());
            node.getLeft().addAllNext(node.getLeft().getFirst());

        }
    }

    /**
     * Post-order.
     */
    public static void lastdfs(NodeInterface node) {
        if (node.isLeaf()) {
            node.addLast(node.getId());
            return;
        }

        // Non-terminal
        lastdfs(node.getLeft());
        if (node.hasPair())
            lastdfs(node.getRight());
        last(node);
    }

    private static void last(NodeInterface node) {
        if (node.isOr()) {

            node.addAllLast(node.getLeft().getLast());
            node.addAllLast(node.getRight().getLast());

        } else if (node.isConcat()) {

            if (node.getRight().getEmpty()) {
                node.addAllLast(node.getLeft().getLast());
            }
            node.addAllLast(node.getRight().getLast());

        } else if (node.isAsterisk()) {

            node.addAllLast(node.getLeft().getLast());

        } else if (node.isQMark()) {

            node.addAllLast(node.getLeft().getLast());

        } else if (node.isPlus()) {

            node.addAllLast(node.getLeft().getLast());

        }
    }

}
