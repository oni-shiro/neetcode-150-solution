public class MaxProductSubArray {
    /***
     * 
     * 
     * init thought = generate all sub arrays
     * check prod, o(n2) * n = n^3
     * 
     * optimal = as order of multipication matters as negativ or zero exists;
     * but we don't need to return the elements, so we product from left to right
     * and then right to left then return the max
     * while checking currProd, we reset it to 1 once 0 is hit
     * tc - O(N)
     * 
     * 
     */

    public int maxProduct(int[] nums) {
        return Math.max(maxProdLeft(nums, nums.length), maxProdRight(nums, nums.length));
    }

    private int maxProdLeft(int[] nums, int n) {
        int maxProd = Integer.MIN_VALUE;
        int currProd = 1;
        for (int i = 0; i < n; i++) {
            currProd *= nums[i];
            maxProd = Math.max(maxProd, currProd);
            if (currProd == 0) {
                currProd = 1;
            }
        }

        return maxProd;
    }

    private int maxProdRight(int[] nums, int n) {
        int maxProd = Integer.MIN_VALUE;
        int currProd = 1;
        for (int i = n - 1; i >= 0; i--) {
            currProd *= nums[i];
            maxProd = Math.max(maxProd, currProd);
            if (currProd == 0) {
                currProd = 1;
            }
        }

        return maxProd;
    }
}
