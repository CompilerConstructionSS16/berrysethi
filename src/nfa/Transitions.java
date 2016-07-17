package nfa;

public interface Transitions {
    /**
     * @return null if s is not a valid transition
     */
    State getStateForCharacter(String s);
}
