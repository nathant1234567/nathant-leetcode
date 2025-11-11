package string.s0006_ZigzagConversion;

public class ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder res = new StringBuilder();

        for (int r = 0; r < numRows; r++) {
            int increment = 2 * (numRows - 1);
            for (int i = r; i < s.length(); i += increment) {
                res.append(s.charAt(i));

                if (r > 0 && r < numRows - 1 && i + increment - 2 * r < s.length()) {
                    res.append(s.charAt(i + increment - 2 * r));
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion solution = new ZigzagConversion();
        String string = "PAYPALISHIRING";
        int target = 4;
        String result = solution.convert(string, target);
        System.out.println(result);
    }
}
