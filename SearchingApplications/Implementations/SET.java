package SearchingApplications.Implementations;

import java.util.Iterator;

//API -> SHELL
public abstract class SET<Key extends Comparable<Key>> {
    // create an empty set
    // public SET(){}

    public abstract void add(Key key); // add the key to the set

    public abstract boolean contains(Key key);// is the key in the set?

    public abstract void remove(Key key);// remove the key from the set

    public abstract int size();// return the number of keys in the set

    public abstract Iterator<Key> iterator();// iterator through keys in the set
}