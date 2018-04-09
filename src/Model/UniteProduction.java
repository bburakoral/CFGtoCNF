package Model;

import Model.ContextFreeGrammar;
import Model.State;

import java.util.ArrayList;

public class UniteProduction {
    private ContextFreeGrammar contextFreeGrammar;

    public UniteProduction() {

        this.contextFreeGrammar = new ContextFreeGrammar();

        String a = "a";
        String b = "b";


        State S = new State();
        State A = new State();
        State B = new State();
        State C = new State();
        State D = new State();
        State E = new State();

        S.setName("S");
        A.setName("A");
        B.setName("B");
        C.setName("C");
        D.setName("D");
        E.setName("E");

        ArrayList<Object> S_Value1 = new ArrayList<>();
        S_Value1.add(A);
        S_Value1.add(B);
        S.addObject(S_Value1);

        A.addObject(a);
        B.addObject(C);
        B.addObject(b);
        C.addObject(D);
        D.addObject(E);
        E.addObject(A);


        contextFreeGrammar.addState(S);
        contextFreeGrammar.addState(A);
        contextFreeGrammar.addState(B);
        contextFreeGrammar.addState(C);
        contextFreeGrammar.addState(D);
        contextFreeGrammar.addState(E);


    }

    public ContextFreeGrammar getContextFreeGrammar() {
        return contextFreeGrammar;
    }

    public void setContextFreeGrammar(ContextFreeGrammar contextFreeGrammar) {
        this.contextFreeGrammar = contextFreeGrammar;
    }
}
