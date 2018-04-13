package Business;

import Model.ContextFreeGrammar;
import Model.State;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Printer {

    public void printCFG(ContextFreeGrammar contextFreeGrammar){

        Set<State> states = contextFreeGrammar.getStates();

        Iterator<State> stateIterator = states.iterator();
        while (stateIterator.hasNext()) {
            State state = stateIterator.next();
            if (state.getValues() != null) {
                ArrayList<Object> values = state.getValues();
                Iterator<Object> objectIterator = values.iterator();
                while (objectIterator.hasNext()) {
                    Object object = objectIterator.next();
                    if (object instanceof ArrayList) {
                        ArrayList<Object> objectArrayList = (ArrayList<Object>) object;
                        Iterator<Object> objectIterator1 = objectArrayList.iterator();

                        String print = "";
                        while (objectIterator1.hasNext()) {
                            Object object1 = objectIterator1.next();
                            String inner = "";
                            if (object1 instanceof State) {
                                inner = ((State) object1).getName();
                            } else {
                                inner = (String) object1;
                            }
                            print += inner;

                        }

                        System.out.println(state.getName() + "->" + print);

                    } else {
                        if(object instanceof  State ){
                            System.out.println(state.getName() + "->" + ((State) object).getName());
                        }else{
                            System.out.println(state.getName() + "->" + object.toString());
                        }

                    }
                }

            }
        }
    }
}
