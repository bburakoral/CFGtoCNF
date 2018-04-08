package Model;

import java.util.HashSet;
import java.util.Set;

public class ContextFreeGrammar {
    Set<State> states;

    public ContextFreeGrammar() {
        this.states = new HashSet<State>();
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public void addState(State state){
        this.states.add(state);
    }
}
