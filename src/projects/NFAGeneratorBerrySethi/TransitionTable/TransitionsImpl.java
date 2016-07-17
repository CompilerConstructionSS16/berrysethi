package projects.NFAGeneratorBerrySethi.TransitionTable;

import java.util.HashMap;

import nfa.State;
import nfa.Transitions;

public class TransitionsImpl implements Transitions{

    private HashMap<String, State> transitions = new HashMap<>();

    public void addTransition(String character, State state){
    	transitions.put(character, state);
    }
    
	@Override
	public State getStateForCharacter(String character) {
		// TODO Auto-generated method stub
		return transitions.get(character);
	}

}
