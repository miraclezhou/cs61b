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
        if(checkUpper(x)) {
            x = Character.toLowerCase(x);
        }
        if(checkUpper(y)) {
            y = Character.toLowerCase(y);
        }
        int diff = Math.abs(x - y);
        return diff == offset;
    }

    private boolean checkUpper(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

}
