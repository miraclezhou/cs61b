
public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }

        Deque<Character> charDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            charDeque.addLast(c);
        }
        return charDeque;
    }


    /**  Non-recursive methond. Return true if the given word is a Offbyone palindrome, and false otherwise. */
    /*
    public boolean isPalindrome(String word) {
        if(word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        String reverseWord = "";
        for (int i = 0; i < word.length(); i++) {
            reverseWord += d.removeLast();
        }
        return word.compareTo(reverseWord) == 0;
    }
    */

    /**  Return true if the given word is a Offbyone palindrome, and false otherwise. */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPali(d);
    }

    /** Recursive helper methond for isPalindrome methond. */
    private boolean isPali(Deque<Character> d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        char cFirst = d.removeFirst();
        char cLast = d.removeLast();
        if (cFirst != cLast) {
            return false;
        }
        return isPali(d);
    }

    /**  Return true if the given word is a Offbyone palindrome, and false otherwise. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPaliOffByOne(d, cc);
    }

    /** Recursive helper methond for isPalindrome(Offbyone) methond. */
    private boolean isPaliOffByOne(Deque<Character> d, CharacterComparator cc) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        char cFirst = d.removeFirst();
        char cLast = d.removeLast();
        if (!cc.equalChars(cFirst, cLast)) {
            return false;
        }
        return isPaliOffByOne(d, cc);
    }
}
