package ElementarySymbolTables.TeachingCode;

//Main Point: make is final and equal compilations

//typically unsafe to use equals() with inheritance(would violate symmetry)
public final class Date implements Comparable<Date> {
    private final int month;
    private final int day;
    private final int year;

    // ... I did the below 2 (for error purposes)
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int compareTo(Date that) {
        return 0; // ?
    }

    // must be Object. Why? Experts still debate.
    public boolean equals(Object y) {
        // complications!
        if (y == this) // optimize for true object equality
            return true;
        if (y == null) // check for null
            return false;
        if (y.getClass() != this.getClass())
            return false; // objects must be in the same class(religion: getClass() vs. instanceof)

        Date that = (Date) y;
        if (this.day != that.day)
            return false;
        if (this.month != that.month)
            return false;
        if (this.year != that.year)
            return false;
        return true;
    }
}