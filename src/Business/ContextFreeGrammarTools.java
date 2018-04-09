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

                           if(isStateContainEpsilon(setOfStateWithinEpsilon,state1.getName())){
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

    public boolean isStateContainEpsilon(ArrayList<String> arrayList, String state){
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


    public ContextFreeGrammar EliminateUselessSymbol(ContextFreeGrammar contextFreeGrammar){

        ArrayList<String> setOfNongeneratingState =  findNongeneratingState(contextFreeGrammar);

        ContextFreeGrammar newContextFreeGrammarWithoutNonGeneratingState = EliminiateNongenerating(contextFreeGrammar,setOfNongeneratingState);



        return null;
    }

    public ContextFreeGrammar EliminiateNongenerating(ContextFreeGrammar contextFreeGrammar,ArrayList<String> setOfNongeneratingState){

        ContextFreeGrammar newContextFreeGrammer = new ContextFreeGrammar();
        Set<State> newStates = new HashSet<>();

        Set<State> states = contextFreeGrammar.getStates();
        Iterator<State> stateIterator = states.iterator();


        // Main Loop
        while (stateIterator.hasNext()){

            // Current State
            State state = stateIterator.next();

            State newState = new State();
            newState.setName(state.getName());

            // check the current state values is null or not
            if(state.getValues() != null) {

                ArrayList<Object> values = state.getValues();
                ArrayList<Object> newValues = new ArrayList<>();
                Iterator<Object> valuesIterator = values.iterator();

                // create a loop is not null
                while (valuesIterator.hasNext()) {
                  Object object = valuesIterator.next();

                  // check the first value of state's values if it is ArrayList creat new loop to travers in all
                  if(object instanceof  ArrayList){
                      ArrayList<Object>  objectArrayList = (ArrayList) object;
                      Iterator<Object> objectIterator = objectArrayList.iterator();

                      boolean occousion = false;

                      // Create a loop if it is ArrayList
                      while (objectIterator.hasNext()){
                          Object object1 = objectIterator.next();

                          // check if it is State or a value
                          if(object1 instanceof  State){

                              // if it is state cast object to state
                              State state1 = (State) object1;

                              // check the state it is in  set of nongenerating state or no
                              if(isStateNongenerating(setOfNongeneratingState,state1.getName())){
                                  occousion = true;
                              }

                          }else {

                              // if it is not state then it is string



                          }
                      }
                      if(occousion != true){
                          newValues.add(objectArrayList);
                      }

                  }else{

                      if(object instanceof  State){

                      }else{

                      }
                  }

                }

                newState.addObject(newValues);

            }else{
                newState.setValues(null);

            }

            newStates.add(newState);

        }


        return newContextFreeGrammer;
    }


    public ContextFreeGrammar EliminateNonreacble(ContextFreeGrammar contextFreeGrammar){

        Set<State> states = contextFreeGrammar.getStates();
        Iterator<State> stateIterator = states.iterator();

        while (stateIterator.hasNext()){
            State state = stateIterator.next();

            

        }



        return null;
    }

    /**
     *
     * @param contextFreeGrammar given context free grammar to find  non-generating states
     * @return  the function look for non-generating state then create a ArrayList  and return ArrayList
     */
    public ArrayList<String> findNongeneratingState(ContextFreeGrammar contextFreeGrammar){
        ArrayList<String> setOfNongeneratingState = new ArrayList<>();
        Set<State> states = contextFreeGrammar.getStates();
        Iterator<State> stateIterator = states.iterator();

        while (stateIterator.hasNext()){
            State state = stateIterator.next();
            ArrayList<Object> objectArrayList = state.getValues();
            if(objectArrayList == null){
                setOfNongeneratingState.add(state.getName());
            }
        }
        return setOfNongeneratingState;
    }


    /**
     *
     * @param setOfNongeneratingState set of nongenerating state to check given state name
     * @param state name of state
     * @return the function check the given state name in in set of non-generating state or not if in state return true if not return false
     */
    public  boolean isStateNongenerating(ArrayList<String> setOfNongeneratingState , String state){
            Iterator<String> stringIterator = setOfNongeneratingState.iterator();
            while (stringIterator.hasNext()){
                String string = stringIterator.next();

                if(string.contains(state)){
                    return true;
                }
            }
        return false;
    }
}
