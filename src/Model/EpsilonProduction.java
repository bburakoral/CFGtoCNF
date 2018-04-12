package Model;

import Model.ContextFreeGrammar;
import Model.State;

import java.util.ArrayList;

/*
        S-> ABAC
        A-> aA
        A-> <epsilon>
        B-> bB
        B-> <epsilon>
        C-> c
*/

public class   EpsilonProduction {
   private   ContextFreeGrammar epsilonProduction;

    public EpsilonProduction() {

       epsilonProduction = new ContextFreeGrammar();

        String a = "a";
        String b = "b";
        String c = "c";
        String epsilon = "<epsilon>";

        State S = new State();
        State A = new State();
        State B = new State();
        State C = new State();


        S.setName("S");
        A.setName("A");
        B.setName("B");
        C.setName("C");

        ArrayList<Object> S_Value1 = new ArrayList<Object>();
        S_Value1.add(A);
        S_Value1.add(B);
        S_Value1.add(A);
        S_Value1.add(C);

        S.addObject(S_Value1);

        ArrayList<Object> A_Value1 = new ArrayList<Object>();
        A_Value1.add(a);
        A_Value1.add(A);
        A.addObject(A_Value1);
        A.addObject(epsilon);

        ArrayList<Object> B_Value1= new ArrayList<Object>();
        B_Value1.add(b);
        B_Value1.add(B);

        B.addObject(B_Value1);
        B.addObject(epsilon);


        C.addObject(c);


        epsilonProduction.addState(S);
        epsilonProduction.addState(A);
        epsilonProduction.addState(B);
        epsilonProduction.addState(C);
    }


    public ContextFreeGrammar getEpsilonProduction() {
        return epsilonProduction;
    }

    public void setEpsilonProduction(ContextFreeGrammar epsilonProduction) {
        this.epsilonProduction = epsilonProduction;
    }
}
