public class OffByOne implements CharacterComparator {
    /** Returns true if characters are equal by the rules of the implementing class.
     *  All characters converted to lowercase to compare*/
    @Override
    public boolean equalChars(char x, char y) {
        if(checkUpper(x)) {
            x = Character.toLowerCase(x);
        }
        if(checkUpper(y)) {
            y = Character.toLowerCase(y);
        }
        int diff = Math.abs(x - y);
        return diff == 1;
    }

    private boolean checkUpper(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }
}
