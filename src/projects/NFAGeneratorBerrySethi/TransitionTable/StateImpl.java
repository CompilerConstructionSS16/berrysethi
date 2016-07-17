package projects.NFAGeneratorBerrySethi.TransitionTable;

import nfa.State;

public class StateImpl implements State {

	private boolean isStart;
	private boolean isFinal;
	private int id;
	public StateImpl( int id, boolean isStartState, boolean isFinalState){
		isStart = isStartState;
		isFinal = isFinalState;
		this.id = id;
	}
	
	@Override
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return isFinal;
	}

	@Override
	public boolean isStart() {
		// TODO Auto-generated method stub
		return isStart;
	}
	
	public int id(){
		return id;
	}

}
