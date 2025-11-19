package array.s0717_OneBitAnd2BitCharacters;

public class OneBitAnd2BitCharacters {

    public boolean isOneBitCharacter(int[] bits) {
        int length = bits.length;
        if (bits[length-2] == 1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        OneBitAnd2BitCharacters solution = new OneBitAnd2BitCharacters();
        System.out.println(solution.isOneBitCharacter(new int[] {1,0,0}));
        System.out.println(solution.isOneBitCharacter(new int[] {1,1,1,0}));
    }
}
