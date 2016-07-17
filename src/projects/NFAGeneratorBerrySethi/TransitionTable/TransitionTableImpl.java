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

    	for( State state : transitions.keySet()){
    		System.out.print( "\t" + ((StateImpl)state).getId());
    	}
    	System.out.println("");
    	for( State state : transitions.keySet()){
    		System.out.print( ((StateImpl)state).getId());
    		TransitionsImpl tra = (TransitionsImpl) getTransitionsFor(state);
        	for( State state2 : transitions.keySet()){
        		if(tra.getTransitions().values().contains(state2)){
        			System.out.print( "\t" + tra.getStateForCharacter(state2));
        		}else{
        			System.out.print( "\t" + "-");
        		}
        	}
        	System.out.println("");
    	}
    }

    public HashMap<State, TransitionsImpl> getStates() {
        return transitions;
    }

}
