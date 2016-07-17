package helper;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.mxgraph.layout.*;

public class Viz extends JFrame {

    public Viz(TransitionTableImpl table) {
        super("NFA");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {

            HashMap<Integer, Object> vertices = new HashMap<>();

            int numberOfStates = table.getStates().size();
            int div = numberOfStates / 2;

            int x = 50;
            int y = 50;

            int w = 80;
            int h = 40;

            int it = 0;
            for (Integer id : table.getStates()) {
                vertices.put(id, graph.insertVertex(parent, null, id.toString(), x, y, w, h));
                x += w + 50;
                if (++it == div) {
                    x = 50;
                    y += h + 50;
                }
            }

            HashMap<Integer, ArrayList<Integer>> transition = table.getTransitions();

            for (Integer nodeid : transition.keySet()) {
                for (Integer node_j : transition.get(nodeid)) {
                    graph.insertEdge(parent, null, node_j.toString(), vertices.get(nodeid), vertices.get(node_j));
                }
            }

        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false);

        //getContentPane().add(graphComponent, BorderLayout.CENTER);
        //new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
        //new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());

        getContentPane().add(graphComponent);
    }

}
