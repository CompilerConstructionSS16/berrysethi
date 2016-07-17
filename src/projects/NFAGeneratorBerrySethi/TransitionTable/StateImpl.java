package projects.NFAGeneratorBerrySethi.TransitionTable;

import nfa.State;

public class StateImpl implements State {

    private boolean start = false;
    private boolean end = false;
    private int id;

    public StateImpl(int id) {
        this.id = id;
    }

    public StateImpl() {
        this.id = -1;
    }

    public void setStart() {
        start = true;
    }

    public void setFinal() {
        end = true;
    }

    @Override
    public boolean isFinal() {
        return end;
    }

    @Override
    public boolean isStart() {
        return start;
    }

    public int getId() {
        return id;
    }
}
