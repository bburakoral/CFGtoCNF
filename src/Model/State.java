package Model;

import java.util.ArrayList;

public class State  {

    private String name;

    ArrayList<Object> values;

    public State() {
        this.values = new ArrayList<Object>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Object> getValues() {
        return values;
    }

    public void setValues(ArrayList<Object> values) {
        this.values = values;
    }

    public void addObject(Object object){
        this.values.add(object);
    }





}
