import org.junit.Test;
import projects.NFAGeneratorBerrySethi.Node.Node;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

class TTable {

    private HashMap<Integer, ArrayList<Integer>> transitions = new HashMap();
    private ArrayList<Integer> startStates = new ArrayList();
    private ArrayList<Integer> endStates = new ArrayList();

    public void addEntry(int fromId, int toId) {
        if (transitions.get(fromId) == null) {
            transitions.put(fromId, new ArrayList<>());
        }

        transitions.get(fromId).add(toId);
    }

    public void addStart(int id) {
        startStates.add(id);
    }

    public void addEnd(int id) {
        endStates.add(id);
    }

    public String toString() {
        return transitions.toString();
    }
}

public class NFAGeneratorBerrySethiTest {

    @Test
    public void testNFAGenerator() {
        System.out.println("Parse regular expression.");

        buildtreeAndParse("(a|b)*a(a|b)");

/*
        buildtreeAndParse("(ab)*|e+fg?");
        buildtreeAndParse("ab*");
        buildtreeAndParse("((ab)*)");
        buildtreeAndParse("(ab)?|(e)*(dg+h)");
        buildtreeAndParse("(ab)?|(e)*(dg+h)");
        buildtreeAndParse("(a|b)*a(a|b)");
        buildtreeAndParse("ab|xe");
*/

        assertEquals(1, 1);
    }

    private void buildtreeAndParse(String regex) {
        System.out.println("Parse regular expression.");
        Node rootNode = TreeBuilder.Parse(StringToolkit.Orify(regex));

        System.out.println("Compute helper attributes.");
        rootNode.computeAttributes();
        Node.printAttributes(rootNode);

        TTable table = new TTable();
        table.addStart(-1);

        if (rootNode.getEmpty()) {
            table.addEnd(-1);
        }

        for (int i : rootNode.getLast()) {
            table.addEnd(i);
        }

        ArrayList<Node> leafs = new ArrayList<>();
        getLeafs(rootNode, leafs);

        for (Node node : leafs) {
            if (rootNode.getFirst().contains(node.getId())) {
                table.addEntry(-1, node.getId());
            }

            for (Node node2 : leafs) {

                if (node.getNext().contains(node2.getId())) {
                    System.out.println(node.getId());
                    table.addEntry(node.getId(), node2.getId());
                }
            }
        }

        System.out.println(table);

    }

    private void getLeafs(Node node, ArrayList<Node> leafs) {
        if (node.isLeaf()) {
            leafs.add(node);
            return;
        }

        getLeafs(node.getLeft(), leafs);
        if (node.hasPair())
            getLeafs(node.getRight(), leafs);
    }

}
