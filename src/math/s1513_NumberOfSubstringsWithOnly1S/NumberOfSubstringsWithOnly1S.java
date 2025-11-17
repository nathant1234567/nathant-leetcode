package math.s1513_NumberOfSubstringsWithOnly1S;

public class NumberOfSubstringsWithOnly1S {
    public int numSub(String s) {
        long result = 0;
        long count = 0;
        long mod = 1_000_000_007;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;
                result = (result + count) % mod;
            } else {
                count = 0;
            }
        }

        return (int) result;
    }

    public static void main(String[] args) {
        NumberOfSubstringsWithOnly1S solution = new NumberOfSubstringsWithOnly1S();
        System.out.println(solution.numSub("01101110"));
    }
}
