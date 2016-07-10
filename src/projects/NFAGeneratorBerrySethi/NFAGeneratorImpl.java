package projects.NFAGeneratorBerrySethi;

import nfa.TransitionTable;
import projects.NFAGenerator;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;
import regex.RegularExpression;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class NFAGeneratorImpl implements NFAGenerator {

    @Override
    public TransitionTable nfaFromRegex(RegularExpression regex) {
        throw new NotImplementedException();
    }

    public TransitionTableImpl nfaFromRegex(NodeInterface rootNode) {

        System.out.println("Compute helper attributes.");

        System.out.println("Empty attributes ...");
        AttributeCalculator.emptydfs(rootNode);
        System.out.println("First attributes ...");
        AttributeCalculator.firstdfs(rootNode);
        System.out.println("Next attributes ...");
        AttributeCalculator.nextdfs(rootNode);
        System.out.println("Last attributes ...");
        AttributeCalculator.lastdfs(rootNode);

        System.out.println("Create transition table.");
        TransitionTableImpl table = new TransitionTableImpl();

        table.addStart(-1);

        if (rootNode.getEmpty()) {
            table.addEnd(-1);
        }

        for (int i : rootNode.getLast()) {
            table.addEnd(i);
        }

        ArrayList<NodeInterface> leafs = new ArrayList<>();
        getLeafs(rootNode, leafs);

        for (NodeInterface node_i : leafs) {
            if (rootNode.getFirst().contains(node_i.getId())) {
                table.addEntry(-1, node_i.getId());
            }

            for (NodeInterface node_j : leafs) {
                if (node_i.getNext().contains(node_j.getId())) {
                    table.addEntry(node_i.getId(), node_j.getId());
                }
            }
        }

        return table;
    }

    private void getLeafs(NodeInterface node, ArrayList<NodeInterface> leafs) {
        if (node.isLeaf()) {
            leafs.add(node);
            return;
        }

        getLeafs(node.getLeft(), leafs);
        if (node.hasPair())
            getLeafs(node.getRight(), leafs);
    }
}
