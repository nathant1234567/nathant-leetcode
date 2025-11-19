package string.s0012_IntegerToRoman;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {

    public String intToRoman(int num) {
        int[] n = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] s = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int i = 0;
        StringBuilder str = new StringBuilder();

        while (num > 0){
            if (num >= n[i]){
                str.append(s[i]);
                num -= n[i];
            } else{
                i++;
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman solution = new IntegerToRoman();
        System.out.println(solution.intToRoman(3749));
    }
}
