import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s1 = "cat";
        String s2 = "abcxyba";
        String s3 = "a";
        String s4 = "";
        String s5 = "iamamai";
        assertFalse(palindrome.isPalindrome(s1));
        assertFalse(palindrome.isPalindrome(s2));
        assertTrue(palindrome.isPalindrome(s3));
        assertTrue(palindrome.isPalindrome(s4));
        assertTrue(palindrome.isPalindrome(s5));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator offbyone = new OffByOne();
        String s1 = "flake";
        assertTrue(palindrome.isPalindrome(s1, offbyone));
    }
}
