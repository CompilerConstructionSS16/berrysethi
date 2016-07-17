package helper;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import nfa.State;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionTableImpl;
import projects.NFAGeneratorBerrySethi.TransitionTable.TransitionsImpl;

import javax.swing.*;
import java.util.HashMap;

public class Viz extends JFrame {

    public Viz(TransitionTableImpl table) {
        super("NFA");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {

            HashMap<State, Object> vertices = new HashMap<>();

            HashMap<State, TransitionsImpl> transitions = table.getStates();

            int numberOfStates = transitions.size();
            int div = numberOfStates / 2;

            int x = 50;
            int y = 50;

            int w = 80;
            int h = 40;

            //vertices.put(table.getStart(), graph.insertVertex(parent, null, "e", x, y, w, h));

            //y += h + 50;

            int it = 0;
            for (State state : table.getAllStates()) {
                vertices.put(state, graph.insertVertex(parent, null, Integer.toString(it), x, y, w, h));
                x += w + 50;
                if (++it == div) {
                    x = 50;
                    y += h + 50;
                }
            }

            for (State state_i : transitions.keySet()) {
                TransitionsImpl tr = transitions.get(state_i);

                for (String str : tr.getTransitions().keySet()) {
                    State state_j = tr.getTransitions().get(str);
                    graph.insertEdge(parent, null, str, vertices.get(state_i), vertices.get(state_j));
                }
            }

        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false);

        getContentPane().add(graphComponent);
    }

}
