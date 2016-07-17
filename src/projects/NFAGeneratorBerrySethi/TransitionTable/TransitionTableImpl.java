package projects.NFAGeneratorBerrySethi.TransitionTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import nfa.State;
import nfa.TransitionTable;
import nfa.Transitions;

public class TransitionTableImpl implements TransitionTable {

    private HashMap<State, TransitionsImpl> transitions = new HashMap<>();

    private State startingState;
    
    public TransitionTableImpl( StateImpl startingState){
    	this.startingState = startingState;
    }

    public void addEntry(StateImpl fromState, StateImpl toState, String character) {
        if (!transitions.containsKey(fromState)) {
            transitions.put(fromState, new TransitionsImpl());
        }

        ((TransitionsImpl)transitions.get(fromState)).addTransition(character, toState);
    }
    
    public State getStateFromId(int id){
    	for( State s : transitions.keySet()){
    		if( ((StateImpl)s).id() == id){
    			return s;
    		}
    	}
    	return null;
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

}
