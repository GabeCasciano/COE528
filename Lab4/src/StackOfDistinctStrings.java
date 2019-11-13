import java.util.ArrayList;

public class StackOfDistinctStrings {
    /*
     Overview: StacksOfDistinctStrings are mutable, bounded collection
     of distinct strings that operate in LIFO

     The Abstraction Function is:
     AF(c) = strings(0), strings(1), ..., strings(c)
     so long as strings(c) is not null, and adheres to rep invariant

     The rep invariant is:
     strings(c) != null & strings(c) can not equal any of the previous values of strings(c)
     ie. strings(c) != strings(c-1) && strings(c) != strings(c-2) && ... && strings(c) != strings(0)
     */

    //the rep
    private ArrayList<String> items;

    //constructor
    public StackOfDistinctStrings(){
        //EFFECTS: Creeates a new StackOfDistinctStrings Object
        items = new ArrayList<String>();
    }

    public void push(String element) throws Exception{
        /*
        MODIFIES: this
        EFFECTS: Appends the element at the top of the stack if the element is
        not already in the stack, else do nothing.
         */
        if(element == null) throw new Exception();
        if(false == items.contains(element))
            items.add(element);
    }

    public String pop() throws Exception{
        /*
        MODIFIES: this
        EFFECTS: Removes an element from the top of the stack
         */
        if(items.set() == 0) throw new Exception();
        return items.remove(items.size());
    }

    public boolean repOK(){
        //EFFECTS: Returns true if the rep holds true for this object, otw
        //returns false
        for(String temp : items){
            for(int i = 0; i < items.size(); i++)
                if(items.contains(temp)) return false;
        }
        return true;
    }
    @Override
    public String toString(){
        /*
        EFFECTS: Returns a string that contains the strings in the stack and the top element.
        Implements the abstraction function.
         */
        String out = "The Stack has " + items.size() + " elements:" + System.lineSeparator();
        for(int i = items.size(); i >= 0; i--)
            out += " " + items.get(i) + System.lineSeparator();
        return out;
    }

}
