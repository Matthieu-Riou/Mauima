package utils;

/**
 * A class to make pairs of objects
 *
 * @param <FIRST>  The type of the first element of the pair
 * @param <SECOND> The type of the second element of the pair
 */
public class Pair<FIRST, SECOND> implements Comparable<Pair<FIRST, SECOND>> {

    /**
     * The first element of the pair
     */
    private final FIRST first;

    /**
     * The second element of the pair
     */
    private final SECOND second;

    /**
     * Constructor of the class Pair
     * @param first The first element of the pair
     * @param second The second element of the pair
     */
    private Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    /**
     * A wrapper for the constructor of the class Pair
     * to make it easier to use.
     *
     * @param first The first element of the pair
     * @param second The second element of the pair
     * @param <FIRST> The type of the first element of the pair
     * @param <SECOND> The type of the second element of the pair
     * @return A Pair composed of first and second
     */
    public static <FIRST, SECOND> Pair<FIRST, SECOND> of(FIRST first,
                                                         SECOND second) {
        return new Pair<FIRST, SECOND>(first, second);
    }

    /**
     * Method to compare two objects
     * @param o1 An object to compare
     * @param o2 An object to compare
     * @return An integer less, equals or greater than zero depending on if o1 is less, equals or greater than o2
     */
    private static int compare(Object o1, Object o2) {
        return (o1 == null) ? ((o2 == null) ? 0 : -1) : o2 == null ? +1 : ((Comparable) o1).compareTo(o2);
    }

    /**
     * Compute the hash of an object
     * @param o The object to compute the hash
     * @return The hash of the pair
     */
    private static int hashcode(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    /**
     * Method to compare two pairs
     *
     * @param p The pair to compare with
     * @return An integer less, equals or greater than zero depending on if the calling pair is less, equals or greater than p
     */
    public int compareTo(Pair<FIRST, SECOND> p) {
        int cmp = compare(first, p.first);
        return cmp == 0 ? compare(second, p.second) : cmp;
    }

    /**
     * Compute the hash of a pair
     * @return The hash of the pair
     */
    public int hashCode() {
        return 31 * hashcode(first) + hashcode(second);
    }

    /**
     * A method to test equality between two objects
     * @param obj An object to compare with
     * @return True if both objects are equals, false otherwise
     */
    public boolean equals(Object obj) {
        return obj instanceof Pair && (this == obj || equal(first, ((Pair) obj).first) && equal(second, ((Pair) obj).second));
    }

    /**
     * A method to test equality between two objects
     * @param o1 An object to compare
     * @param o2 An object to compare
     * @return True if both objects are equals, false otherwise
     */
    private boolean equal(Object o1, Object o2) {
        return o1 == null ? o2 == null : (o1 == o2 || o1.equals(o2));
    }

    /**
     * Method to represent a pair as a string
     * @return The string representing the pair
     */
    public String toString() {
        return "(" + first + ", " + second + ')';
    }

    /**
     * A getter for the first element of the pair
     * @return The first element of the pair
     */
    public FIRST getFirst() {
        return first;
    }

    /**
     * A getter for the second element of the pair
     * @return The second element of the pair
     */
    public SECOND getSecond() {
        return second;
    }
}
