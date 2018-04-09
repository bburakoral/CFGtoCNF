package Model;

import Model.ContextFreeGrammar;
import Model.State;

public class CFGtoCNF {
    private ContextFreeGrammar contextFreeGrammar;

    public CFGtoCNF() {

        this.contextFreeGrammar = new ContextFreeGrammar();

        String a = "a";
        String b = "b";


        State S = new State();
        State A = new State();
        State B = new State();
        State C = new State();
        State D = new State();

        S.setName("S");
        A.setName("A");
        B.setName("B");
        C.setName("C");
        D.setName("D");



    }

    public ContextFreeGrammar getContextFreeGrammar() {
        return contextFreeGrammar;
    }

    public void setContextFreeGrammar(ContextFreeGrammar contextFreeGrammar) {
        this.contextFreeGrammar = contextFreeGrammar;
    }
}
