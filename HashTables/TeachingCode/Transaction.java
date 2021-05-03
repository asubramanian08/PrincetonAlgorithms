package HashTables.TeachingCode;

import edu.princeton.cs.algs4.Date;

//was implemented in priority queue
public final class Transaction /* implements Comparable<Transaction> */ {
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    // ...

    public boolean equals(Object y) {
        if (getClass() != y.getClass())
            return false;
        Transaction that = (Transaction) y;
        return this.who.equals(that.who) && this.when.equals(that.when) && this.amount == that.amount;
    }

    public int hashCode() {
        int hash = 17; // no zero const
        // 31 -> typically a small prime
        hash = 31 * hash + who.hashCode(); // for reference types to use hashCode()
        hash = 31 * hash + when.hashCode(); // for primitive types, use hashCode()of wrapper type
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }
}