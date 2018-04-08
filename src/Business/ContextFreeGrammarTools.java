package Business;

import Model.ContextFreeGrammar;
import Model.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ContextFreeGrammarTools {


    public ContextFreeGrammar EliminateEpsilonProduction(ContextFreeGrammar contextFreeGrammar){

      ContextFreeGrammar newContextFreeGrammar = new ContextFreeGrammar();
      Set<State> newstates = new HashSet<>();

      ArrayList<String> setOfStateWithinEpsilon =  findEpsilons(contextFreeGrammar);

      // States
      Set<State> states =  contextFreeGrammar.getStates();
      Iterator<State> stateIterator = states.iterator();

      while (stateIterator.hasNext()){

          // Current State
          State state = stateIterator.next();

          State newState = new State();
          newState.setName(state.getName());

          ArrayList<Object> values = state.getValues();
          Iterator<Object> valuesIterator = values.iterator();

          while (valuesIterator.hasNext()){
                Object value = valuesIterator.next();

                if(value instanceof ArrayList){
                    ArrayList<Object> arrayList = (ArrayList)value;
                    newState.addObject(arrayList);
                    int i=0;
                    for(Iterator<Object> iterator = arrayList.iterator(); iterator.hasNext(); i++){
                        Object object = iterator.next();

                        if(object instanceof  State){
                            State state1 = (State) object;
                            int stateIndex = i;

                           if(checkState(setOfStateWithinEpsilon,state1.getName())){
                               ArrayList<Object> newValues = new ArrayList<>();

                               for(int j=0; j<arrayList.size(); j++){
                                   Object object1 = arrayList.get(j);
                                   if(j==stateIndex){
                                   }else{
                                       newValues.add(object1);
                                   }
                               }
                               newState.addObject(newValues);
                           }
                        }
                    }
                }else{
                    if(value.toString().contains("<epsilon>")){

                    }else{
                        newState.addObject(value);
                    }


                }

                newstates.add(newState);
          }
      }

      newContextFreeGrammar.setStates(newstates);

      return  newContextFreeGrammar;
    }

    public boolean checkState(ArrayList<String> arrayList, String state){
        Iterator<String> stringIterator = arrayList.iterator();
        while (stringIterator.hasNext()){
            String string = stringIterator.next();
           if(string.equals(state)){
               return true;
            }
        }
        return  false;
    }


    public ArrayList<String> findEpsilons(ContextFreeGrammar contextFreeGrammar){

        ArrayList<String> sefOfStateWithinEpsilon = new ArrayList<>();

        Set<State> stateSet = contextFreeGrammar.getStates();
        Iterator<State> stateIterator = stateSet.iterator();

        while (stateIterator.hasNext()){

            // current state
            State state= stateIterator.next();

            // Current State values
            ArrayList<Object> objects = state.getValues();

            // Current State Values Iterator
            Iterator<Object> objectIterator = objects.iterator();

            while (objectIterator.hasNext()){

                // Current Value
                Object object = objectIterator.next();

                // Current Value tye check
                if(object instanceof ArrayList){
                    ArrayList<Object> values = (ArrayList<Object>)object;

                    Iterator<Object> valueIterator = values.iterator();

                    while (valueIterator.hasNext()){
                        Object value = valueIterator.next();

                        if(value.getClass() != State.class){
                            String valueString = value.toString();

                            if(valueString.contains("<epsilon>")){
                                sefOfStateWithinEpsilon.add(state.getName());
                            }
                        }
                    }
                }else{

                    if(object instanceof State){

                    }else{
                        String string = object.toString();
                        if(string.contains("<epsilon>")){
                            sefOfStateWithinEpsilon.add(state.getName());
                        }
                    }

                }
            }

        }



        return sefOfStateWithinEpsilon;
    }

}
