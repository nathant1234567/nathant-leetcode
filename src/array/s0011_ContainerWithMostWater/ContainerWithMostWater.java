package array.s0011_ContainerWithMostWater;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, maxArea = -1;

        for (int i = 0; i < height.length - 1; i++) {
            int newL = height[l];
            int newR = height[r];
            int newMaxArea = Math.abs((l - r) * Math.min(newL, newR));
            if (newMaxArea > maxArea) { maxArea = newMaxArea; }

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }

    public int maxAreaConvergence(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;

        while (l < r) {
            int width = r - l;
            int area = width * Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, area);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();
        System.out.println("Answer: " + solution.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println("Answer: " + solution.maxArea(new int[] {1,1}));
    }
}
