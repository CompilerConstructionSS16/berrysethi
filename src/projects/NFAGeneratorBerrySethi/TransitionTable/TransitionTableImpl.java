package projects.NFAGeneratorBerrySethi.TransitionTable;

import nfa.State;
import nfa.TransitionTable;
import nfa.Transitions;

import java.util.HashMap;
import java.util.Iterator;

public class TransitionTableImpl implements TransitionTable {

    private HashMap<State, TransitionsImpl> transitions = new HashMap<>();

    private State startingState;

    public void setStartState(State startingState) {
        this.startingState = startingState;
    }

    public void addEntry(StateImpl fromState, StateImpl toState, String character) {
        if (!transitions.containsKey(fromState)) {
            transitions.put(fromState, new TransitionsImpl());
        }

        transitions.get(fromState).addTransition(character, toState);
    }

    @Override
    public Iterator<State> iterator() {
        return transitions.keySet().iterator();
    }

    @Override
    public Transitions getTransitionsFor(State s) {
        return transitions.get(s);
    }

    @Override
    public State getStart() {
        return startingState;
    }

    public String toString() {
    	System.out.println("Start state: -1");
    	for( State state : transitions.keySet()){
    		System.out.print( ((StateImpl)state).getId()+ ": ");
    		TransitionsImpl tra = (TransitionsImpl) getTransitionsFor(state);
        	
        	System.out.println(tra);
    	}
    	String finalStates = "";
    	for( State state : transitions.keySet()){
    		if(state.isFinal()){
    			finalStates +=  ((StateImpl)state).getId() + ", ";
    		}
    	}

    	System.out.println("Final states: " + finalStates);
    	return "";
    }

    public HashMap<State, TransitionsImpl> getStates() {
        return transitions;
    }

}
