package Model;

import Model.ContextFreeGrammar;
import Model.State;

import java.util.ArrayList;

/*
    S-> aB
    S-> bX
    A-> Bad
    A-> bSX
    A-> a
    B-> aSB
    B-> bBX
    X-> SBD
    X-> aBX
    X-> ad
 */
public class UselessSymbol {
    ContextFreeGrammar contextFreeGrammar;


    public UselessSymbol() {
        this.contextFreeGrammar = new ContextFreeGrammar();

        String a ="a";
        String b ="b";
        String d ="d";

        State S = new State();
        State A = new State();
        State B = new State();
        State X = new State();
        State D = new State();



        D.setName("D");
        S.setName("S");
        A.setName("A");
        B.setName("B");
        X.setName("X");

        ArrayList<Object> S_Value1 = new ArrayList<Object>();
        S_Value1.add(a);
        S_Value1.add(B);

        S.addObject(S_Value1);

        ArrayList<Object> S_Value2 = new ArrayList<Object>();
        S_Value2.add(b);
        S_Value2.add(X);

        S.addObject(S_Value2);


        ArrayList<Object> A_Value1 = new ArrayList<Object>();
        A_Value1.add(B);
        A_Value1.add(a);
        A_Value1.add(d);

        A.addObject(A_Value1);


        ArrayList<Object> A_Value2 = new ArrayList<Object>();
        A_Value2.add(b);
        A_Value2.add(S);
        A_Value2.add(X);

        A.addObject(A_Value2);
        A.addObject(a);


        ArrayList<Object> B_Value1 = new ArrayList<Object>();
        B_Value1.add(a);
        B_Value1.add(S);
        B_Value1.add(B);
        B.addObject(B_Value1);


        ArrayList<Object> B_Value2 = new ArrayList<Object>();
        B_Value2.add(b);
        B_Value2.add(B);
        B_Value2.add(X);
        B.addObject(B_Value2);


        ArrayList<Object> X_Value1 = new ArrayList<Object>();
        X_Value1.add(S);
        X_Value1.add(B);
        X_Value1.add(D);
        X.addObject(X_Value1);


        ArrayList<Object> X_Value2 = new ArrayList<Object>();
        X_Value2.add(a);
        X_Value2.add(B);
        X_Value2.add(X);
        X.addObject(X_Value2);

        ArrayList<Object> X_Value3= new ArrayList<Object>();
        X_Value3.add(a);
        X_Value3.add(d);
        X.addObject(X_Value3);

        D.setValues(null);

        contextFreeGrammar.addState(S);
        contextFreeGrammar.addState(A);
        contextFreeGrammar.addState(B);
        contextFreeGrammar.addState(D);
        contextFreeGrammar.addState(X);


    }

    public ContextFreeGrammar getContextFreeGrammar() {
        return contextFreeGrammar;
    }

    public void setContextFreeGrammar(ContextFreeGrammar contextFreeGrammar) {
        // Test
        this.contextFreeGrammar = contextFreeGrammar;
    }
}
