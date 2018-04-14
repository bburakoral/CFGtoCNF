package Model;

import Model.ContextFreeGrammar;
import Model.State;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.ArrayList;

public class CFGtoCNF {
    private ContextFreeGrammar contextFreeGrammar;

    public CFGtoCNF() {

        this.contextFreeGrammar = new ContextFreeGrammar();

        String a = "a";
        String b = "b";
        String epsilon= "<epsilon>";


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
        S_Value1.add(a);
        S_Value1.add(A);
        S_Value1.add(a);

        S.addObject(S_Value1);

       ArrayList<Object> S_Value2= new ArrayList<>();
       S_Value2.add(b);
       S_Value2.add(B);
       S_Value2.add(b);

       S.addObject(S_Value2);
       S.addObject(epsilon);


       A.addObject(C);
       A.addObject(a);

       B.addObject(C);
       B.addObject(b);

       ArrayList<Object> C_Value1 = new ArrayList<>();
       C_Value1.add(C);
       C_Value1.add(D);
       C_Value1.add(E);

       C.addObject(E);
       C.addObject(epsilon);

       D.addObject(A);
       D.addObject(B);

       ArrayList<Object> D_Value1 = new ArrayList<>();
       D_Value1.add(a);
       D_Value1.add(b);

       D.addObject(D_Value1);


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
