package projects.NFAGeneratorBerrySethi.TransitionTable;

import nfa.State;
import nfa.Transitions;

import java.util.HashMap;

public class TransitionsImpl implements Transitions {

    private HashMap<String, State> transitions = new HashMap<>();

    public void addTransition(String character, State state) {
        transitions.put(character, state);
    }

    @Override
    public State getStateForCharacter(String character) {
        return transitions.get(character);
    }
    
    public HashMap<String, State> getTransitions(){
    	return transitions;
    }

    public HashMap<String, State> getTransitions() {
        return transitions;
    }

}
