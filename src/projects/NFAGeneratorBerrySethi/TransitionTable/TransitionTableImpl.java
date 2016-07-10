package projects.NFAGeneratorBerrySethi.TransitionTable;

import java.util.ArrayList;
import java.util.HashMap;

public class TransitionTableImpl {

    private HashMap<Integer, ArrayList<Integer>> transitions = new HashMap<>();
    private ArrayList<Integer> startStates = new ArrayList<>();
    private ArrayList<Integer> endStates = new ArrayList<>();

    private ArrayList<Integer> states = new ArrayList<>();

    public void addEntry(int fromId, int toId) {
        if (!transitions.containsKey(fromId)) {
            transitions.put(fromId, new ArrayList<>());
        }

        transitions.get(fromId).add(toId);

        if (!states.contains(fromId))
            states.add(fromId);
        if (!states.contains(toId))
            states.add(toId);
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

    public HashMap<Integer, ArrayList<Integer>> getTransitions() {
        return transitions;
    }

    public ArrayList<Integer> getStates() {
        return states;
    }

}
