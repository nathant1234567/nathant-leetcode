package math.s0009_PalindromeNumber;

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        // instant: negative numbers and numbers ending in 0 (except 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        // Revert half of the number and compare it with the other half
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by reversedHalf/10
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public static void main(String[] args) {
        System.out.println("Running PalindromeNumber...");
    }
}

