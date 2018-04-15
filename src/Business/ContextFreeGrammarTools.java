package Business;

import Model.ContextFreeGrammar;
import Model.State;

import java.util.*;

public class ContextFreeGrammarTools {


    /**
     *
     * @param contextFreeGrammar
     * @return
     */

    public ContextFreeGrammar eliminateEpsilonProduction(ContextFreeGrammar contextFreeGrammar){

       // it is new context free grammar it will return as a result after eliminate epsilon
      ContextFreeGrammar newContextFreeGrammar = new ContextFreeGrammar();

      // it is for collect new states
      Set<State> newstates = new HashSet<>();

      // the set  of name of state with epsilon
      ArrayList<String> setOfStateWithinEpsilon =  findEpsilons(contextFreeGrammar);

      // given states
      Set<State> states =  contextFreeGrammar.getStates();

      // the iterator for given states
      Iterator<State> stateIterator = states.iterator();


      while (stateIterator.hasNext()){

          //current state
          State state = stateIterator.next();


          //I have created new state because I do not want to change anything in old
          // context free grammar I will use it below
          State newState = new State();

          //setting name of new state with name of the current state
          newState.setName(state.getName());

          // get all values of the current state;
          ArrayList<Object> values = state.getValues();

          /* this is an object iterator for  values of state
           the iterator object because  values can store
           ArrayList, String or Object so we do not know
           type of the value
          */
          Iterator<Object> valuesIterator = values.iterator();

          while (valuesIterator.hasNext()){

                // This is the current value of the state
                Object value = valuesIterator.next();

                // first check if values is arrayList
                if(value instanceof ArrayList){

                    //  S->ABAC
                    //  S->ABC
                    //  S->BAC
                    //  S->AAC
                    //  S->AC
                    //  S->BC
                    //  S->AC
                    //  S->C


                    // if value is a arrayList cast the value to ArrayList
                    ArrayList<Object> arrayList = (ArrayList<Object>)value;

                    // Add first the whole value of the value
                    newState.addObject(arrayList);


                        // This is for keep index of the state that contain epsilon
                        ArrayList<Integer> index = new ArrayList<>();

                        // iterator counter for fallow index
                        int i=0;
                        for(Iterator<Object> iterator = arrayList.iterator(); iterator.hasNext(); i++){

                            /* current element of the arrayList as object because
                             *  arrayList can store State and String */

                            Object object = iterator.next();

                            // check element if it is state or string
                            if(object instanceof  State){

                                // if it is state  cast object to state
                                State state1 = (State) object;

                                // check current state is contain epsilon or not
                                if(isStateContainEpsilon(setOfStateWithinEpsilon,state1.getName())){

                                    // if it contain epsilon put its index in arrayList to store
                                    index.add(i);

                                    // the new ArrayList to keep new values
                                    ArrayList<Object> newValues = new ArrayList<>();


                                    for(int j=0; j<arrayList.size(); j++){

                                        // current values
                                        Object object1 = arrayList.get(j);
                                        // check if current index equal current state index  ignore it
                                        if(j==i){
                                        }else{
                                            newValues.add(object1);
                                        }
                                    }

                                    // after creating new values put it new state's set of value
                                    newState.addObject(newValues);
                                }

                            }else{


                            }
                        }


                        if(index.size()>1){
                            for(int k=0; k<index.size(); k++ ){
                                ArrayList<Object> newValues2 = new ArrayList<>();

                                for(int t =0; t<arrayList.size(); t++){

                                    Object object2 = arrayList.get(t);

                                    if(isInEpsilonIndexSet(index,t) && t == k){
                                        newValues2.add(object2);
                                    }else if(!isInEpsilonIndexSet(index,t)){
                                        newValues2.add(object2);
                                    }
                                }

                                newState.addObject(newValues2);

                            }

                            ArrayList<Object> newValues3 = new ArrayList<>();

                            for(int l=0; l<arrayList.size(); l++){
                                Object object3 = arrayList.get(l);
                                if(isInEpsilonIndexSet(index,l)){
                                }else if(!isInEpsilonIndexSet(index,l)){
                                    newValues3.add(object3);
                                }
                            }
                            newState.addObject(newValues3);
                        }


                }// end of the arrayList of Val
                else{

                    // if it is not check it is State or  String
                    if(value instanceof  State){
                        /*
                        if it is not arrayList and it is state
                        as one value I just add it the new values as a
                        value
                         */
                        State  stateAsValue = (State) value;
                        newState.addObject(stateAsValue);

                    }else{

                        // if it is string and it is not contain epsilon add it to
                        String valuesAsString = value.toString();

                        if(!values.contains("<epsilon>")){
                            newState.addObject(value);
                        }
                    }

                }

                newstates.add(newState);
          }
      }

                newContextFreeGrammar.setStates(newstates);



      return  newContextFreeGrammar;
    }

    public boolean isInEpsilonIndexSet(ArrayList<Integer> indexes, int index){
        Iterator<Integer> object = indexes.iterator();

        while (object.hasNext()){
            int i = object.next();
            if(i==index){
                return true;
            }
        }

        return  false;
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

    public ContextFreeGrammar eliminateUselessSymbol(ContextFreeGrammar contextFreeGrammar){
        ArrayList<String> setOfNongeneratingState =  findNonGeneratingStates(contextFreeGrammar);
        ContextFreeGrammar newContextFreeGrammarWithoutNonGeneratingState = eliminateNonGenerating(contextFreeGrammar,setOfNongeneratingState);
        ContextFreeGrammar newContextFreeGrammarWithoutNonReachableState = eliminateNonReachable(newContextFreeGrammarWithoutNonGeneratingState);
        return newContextFreeGrammarWithoutNonReachableState;
    }

    public ContextFreeGrammar eliminateNonGenerating(ContextFreeGrammar contextFreeGrammar, ArrayList<String> setOfNongeneratingState){

        // this will be my result context free grammar as result
        ContextFreeGrammar newContextFreeGrammar = new ContextFreeGrammar();

        Set<State> newStates = new HashSet<>();

        // This is states of the context free grammar
        Set<State> states = contextFreeGrammar.getStates();

        // this is for iterate  in set of state
        Iterator<State> stateIterator = states.iterator();


        // Main Loop
        while (stateIterator.hasNext()){

            // Current State
            State state = stateIterator.next();




            // check the current state values is null or not
            if(state.getValues() != null) {

                // to create new state
                State newState = new State();
                newState.setName(state.getName());

                // Get all values of the state
                ArrayList<Object> values = state.getValues();

                // create new ArrayList to  keep all of the new values of state
                ArrayList<Object> newValues = new ArrayList<>();

                // this if for iterate in set of values
                Iterator<Object> valuesIterator = values.iterator();

                // create a loop is not null
                while (valuesIterator.hasNext()) {

                  // this is current value of the state
                  Object object = valuesIterator.next();


                  // check the first value of state's values if it is ArrayList creat new loop to travers in all
                  if(object instanceof  ArrayList){

                      // casting element of the values to arrayList
                      ArrayList<Object>  objectArrayList = (ArrayList<Object>) object;

                      // this  for iterate in value of the state if it is arrayList
                      Iterator<Object> objectIterator = objectArrayList.iterator();

                      // it will give to me a change to fallow is there any value in state that is non-generating
                      boolean occousion = false;

                      // Create a loop if it is ArrayList
                      while (objectIterator.hasNext()){

                          // element of the  values
                          Object object1 = objectIterator.next();

                          // check if it is State or a value
                          if(object1 instanceof  State){

                              // if it is state cast object to state
                              State state1 = (State) object1;

                              // check the state it is in  set of nongenerating state or no
                              if(isStateNonGenerating(setOfNongeneratingState,state1.getName())){
                                  occousion = true;
                              }

                          }else {

                              // if it is not state then it is string
                          }
                      }

                      if(!occousion){
                          newValues.add(objectArrayList);
                      }

                  }else{

                      if(object instanceof  State){
                          // if it is state cast object to state
                          State state2 = (State) object;
                          if(isStateNonGenerating(setOfNongeneratingState,state2.getName())){

                          }else{
                              newValues.add(object);
                          }

                      }else{


                          newValues.add(object);

                      }
                  }

                }

                newState.setValues(newValues);


                newStates.add(newState);
            }else{


            }



        }

        newContextFreeGrammar.setStates(newStates);

        return newContextFreeGrammar;
    }

    public ContextFreeGrammar eliminateNonReachable(ContextFreeGrammar contextFreeGrammar){

        // it is for keep new state and return as a result
        ContextFreeGrammar newContextFreeGrammar = new ContextFreeGrammar();

        // get all states in context free grammar
        Set<State> states = contextFreeGrammar.getStates();

        // it is for keep new  state to add new grammar
        Set<State> newStates = new HashSet<>();

        // to keep which state call other state

        HashMap<String,Boolean> reachables= new HashMap<>();
        Iterator<State> stateIterator1 = states.iterator();

        while(stateIterator1.hasNext()){
            State state = stateIterator1.next();
            reachables.put(state.getName(),false);
        }

        /*
            S->true;
            A->false;
            B->false;
            X->false;
         */

        reachables.put("S",true);

        for(int i=0; i<states.size(); i++) {


            Iterator<State> stateIterator = states.iterator();

            while (stateIterator.hasNext()) {

                //  current State
                State state = stateIterator.next();

                if(isReachable(reachables,state.getName())){

                    // values of the current States
                    ArrayList<Object> values = state.getValues();

                    Iterator<Object> valuesIterator = values.iterator();

                    while (valuesIterator.hasNext()) {
                        // Current value of the values
                        Object value = valuesIterator.next();

                        // check if it is arrayList or not
                        if (value instanceof ArrayList) {

                            // value as a arrayList
                            ArrayList<Object> listOfValue = (ArrayList<Object>)value;
                            Iterator<Object> listOfValueIterator = listOfValue.iterator();
                            while (listOfValueIterator.hasNext()) {

                                Object elementOfValue = listOfValueIterator.next();

                                if (elementOfValue instanceof State) {
                                    State state1 = (State) elementOfValue;
                                    reachables.replace(state1.getName(),true);
                                }


                            }

                        } else {
                            if (value instanceof State) {
                                reachables.put(((State) value).getName(),true);
                            }
                        }
                    }
                }
            }
        }




        Iterator<State> stateIterator = states.iterator();

        while (stateIterator.hasNext()){
            State  state = stateIterator.next();

            if(reachables.get(state.getName())){
                newStates.add(state);
            }
        }

        newContextFreeGrammar.setStates(newStates);
        return newContextFreeGrammar;
    }

    public boolean isReachable(HashMap<String,Boolean> hashMap, String stateName){

           if(hashMap.get(stateName)){
               return true;
           }else{
               return false;
           }
    }

    /**
     *
     * @param contextFreeGrammar given context free grammar to find  non-generating states
     * @return  the function look for non-generating state then create a ArrayList  and return ArrayList
     */
    public ArrayList<String> findNonGeneratingStates(ContextFreeGrammar contextFreeGrammar){

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
    public  boolean isStateNonGenerating(ArrayList<String> setOfNongeneratingState , String state){
            Iterator<String> stringIterator = setOfNongeneratingState.iterator();
            while (stringIterator.hasNext()){
                String string = stringIterator.next();

                if(string.contains(state)){
                    return true;
                }
            }
        return false;
    }

    public ContextFreeGrammar eliminateUnitProduction(ContextFreeGrammar contextFreeGrammar){
            /*
                The Algorithm

                Step 1: To remove A->B, add production A->X to the grammar rule whenever B->X occurs in the grammar.
                Step 2: Delete A->B from the grammar
                Step 3: Repeat from step 1 until all Unit Productions are removed.

             */

          Set<State> states = contextFreeGrammar.getStates();

          for(int i=0; i<states.size(); i++){
              for(State state:states){

                  ArrayList<Object> valuesOfState = state.getValues();
                  ArrayList<Object> newValues = new ArrayList<Object>();

                  for(Object value : valuesOfState){

                      if(value instanceof  State){

                          for(State state1:states){
                              if(state1.getName().equals(((State) value).getName())){
                                  ArrayList<Object> valuesOfValueState = state1.getValues();

                                  for(Object subValue : valuesOfValueState){

                                      newValues.add(subValue);
                                  }
                              }
                          }
                      }else{

                          newValues.add(value);
                      }
                  }

                  state.setValues(newValues);

              }
          }

          contextFreeGrammar.setStates(states);

          ContextFreeGrammar resultOfUnReacble = eliminateNonReachable(contextFreeGrammar);



        return resultOfUnReacble;
    }

    public ContextFreeGrammar CFGtoCNF(ContextFreeGrammar contextFreeGrammar){

        ContextFreeGrammar resultOfEpsilonProduction = eliminateEpsilonProduction(contextFreeGrammar);
        ContextFreeGrammar resultOfUnitProduction = eliminateUnitProduction(resultOfEpsilonProduction);
        ContextFreeGrammar resulOfUselesSyembols = eliminateUselessSymbol(resultOfUnitProduction);

        Set<State> states = resulOfUselesSyembols.getStates();

        Set<State> newStates = new HashSet<>();

        for(State state: states){
            State state1  = new State();
            state1.setName(state.getName());
            newStates.add(state1);
        }


        int i=1;

        for(State state : states){
            ArrayList<Object> values = state.getValues();

            ArrayList<Object> newValues = new ArrayList<>();

            for(Object value: values){

                if(value instanceof ArrayList){
                    ArrayList<Object> listOfValue = (ArrayList<Object>) value;
                    newValues.add(listOfValue);

                }else{
                    if(value instanceof  State){
                        newValues.add(value);

                    }else{
                        State state1 = new State();
                        state1.setName("X"+Integer.toString(i++));
                        state1.addObject(value);
                        newValues.add(state1);
                        newStates.add(state1);
                    }
                }
            }

            for(State state2 : newStates){
                if(state2.getName().equals(state.getName())){
                    state2.setValues(newValues);
                }
            }
        }


        for(State state1 : newStates){
             ArrayList<Object> objectArrayList = state1.getValues();
             ArrayList<Object> newValues = new ArrayList<>();

             for(Object object:objectArrayList){

                 if(object instanceof ArrayList){

                     ArrayList<Object> objectArrayList1 = (ArrayList<Object>) object;
                     ArrayList<Object> newArrayList = new ArrayList<>();

                     for(Object object1:objectArrayList1){

                         if(object1 instanceof  String){

                             for(State state11: newStates){
                                 ArrayList<Object> valuesOfState = state11.getValues();

                                 if(valuesOfState.size() == 1){
                                     Object object5 = valuesOfState.get(0);
                                     if(object5 instanceof  String){
                                         if(object5.equals(object1)){
                                                newArrayList.add(state11);
                                         }
                                     }
                                 }

                             }


                         }else{

                             newArrayList.add(object1);
                         }
                     }

                     newValues.add(newArrayList);

                 }else{
                     newValues.add(object);
                 }
             }

             state1.setValues(newValues);
        }

        Set<State> finalStates = new HashSet<>();

        int j=1;

        int k = newStates.size();

        for(int t=0; t<k; t++){


            for(State state : newStates){

                State newState =  new State();
                newState.setName(state.getName());

                ArrayList<Object> values = state.getValues();
                ArrayList<Object> newValues = new ArrayList<>();

                for(Object value : values){

                    if(value instanceof  ArrayList){

                        ArrayList<Object> arrayValue = (ArrayList<Object>) value;


                        if(arrayValue.size()>2){

                            ArrayList<Object> newArray = new ArrayList<>();
                            newArray.add(arrayValue.get(0));

                            State newState2 = new State();
                            newState2.setName("Y"+Integer.toString(j++));

                            ArrayList<Object> lastArray = new ArrayList<Object>();

                            for(int g=1; g<3; g++){
                                lastArray.add(arrayValue.get(g));
                            }

                            newState2.addObject(lastArray);

                            finalStates.add(newState2);
                            newArray.add(newState2);
                            newValues.add(newArray);

                        }else{
                            newValues.add(value);
                        }

                    }else{
                        newValues.add(value);
                    }
                }

                newState.setValues(newValues);
                finalStates.add(newState);


            }


            newStates = new HashSet<>();

            for(State state1: finalStates){

                State newState = new State();
                newState.setName(state1.getName());
                ArrayList<Object> values = state1.getValues();
                ArrayList<Object> newValues = new ArrayList<>();

                newValues.addAll(values);
                newState.setValues(newValues);

                newStates.add(newState);

            }

            finalStates = new HashSet<>();


        }

        resulOfUselesSyembols.setStates(newStates);

        return resulOfUselesSyembols;
    }
}
