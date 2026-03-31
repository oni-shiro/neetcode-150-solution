package SlidingWindow;
import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        // possible number of windows is n - k + 1
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            int leftBound = i - k + 1;

            // Remove indices that are out of the current window
            if(!deque.isEmpty() && deque.peekFirst() < leftBound) {
                deque.pollFirst();
            }

            // Remove elements smaller than the current element from the deque
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add current element at the back of the deque
            deque.offerLast(i);

            // storing the maximum for the current window at starting index of that window, and front of the deque is max
            if(i >= k - 1) {
                result[leftBound] = nums[deque.peekFirst()];
            }
        }

        return result;

    }
}
