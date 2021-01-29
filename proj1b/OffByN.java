public class OffByN implements CharacterComparator {
    private int offset;

    /** Constructor. */
    public OffByN(int N) {
        offset = N;
    }

    /** Returns true if characters are equal by the rules of the implementing class.
     *  All characters converted to lowercase to compare. */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(Character.toLowerCase(x) - Character.toLowerCase(y)) == offset;
    }

}
