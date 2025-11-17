package array.s1437_CheckIfAll1SAreAtLeastLengthKPlacesAway;

public class CheckIfAll1SAreAtLeastLengthKPlacesAway {

    public boolean kLengthApart(int[] nums, int k) {
        int count = k;
        for (int i : nums) {
            if (i == 1) {
                if (count < k) {
                    return false;
                }
                count = 0;
            } else {
                count++;
            }
        }
        return true;
    }

    public boolean twoPointerkLengthApart(int[] nums, int k) {
        int prev = -1; // index of last seen 1

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (prev != -1 && (i - prev - 1) < k) {
                    return false;
                }
                prev = i; // update last seen 1 index
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CheckIfAll1SAreAtLeastLengthKPlacesAway solution = new CheckIfAll1SAreAtLeastLengthKPlacesAway();
        System.out.println(solution.kLengthApart(new int[] {1,0,0,0,1,0,0,1}, 2));
        System.out.println(solution.twoPointerkLengthApart(new int[] {1,0,0,1,0,1}, 2));
    }
}


