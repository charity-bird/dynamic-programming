package MaximumContiguousSubarray;
public class MaximumContiguousSubarray {

    public static int maxSubArrayKadanesAlgorithm(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];
        for (Integer i : nums) {
            sum = Math.max(i + sum, i);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static int mcs(int[] a, int l, int r) {
        if (l == r) {
            return a[l];
        } else {
            int c = (l + r) / 2;
            int lmax = mcs(a, l, c);
            int rmax = mcs(a, c+1, r);
            int lhmax = Integer.MIN_VALUE;
            int lhsum = 0;
            for (int i = c; i >= l; i--) {
                lhsum = lhsum + a[i];
                if (lhsum > lhmax)
                    lhmax = lhsum;
            }
            int rhmax = Integer.MIN_VALUE;
            int rhsum = 0;
            for (int i = c+1; i <= r; i++) {
                rhsum = rhsum + a[i];
                if (rhsum > rhmax)
                    rhmax = rhsum;
            }
            int smax = lhmax + rhmax;
            return Math.max(Math.max(lmax, rmax), smax);
        }
    }

    public static int maxSubArrayBruteForce(int[] nums) {
        int[] subArraysSums = new int[numOfSubArrays(nums.length)];
        int maxSum = Integer.MIN_VALUE;
        int i = 0;
        int k = 1;
        int m = nums.length;
        while (m >= 1) {
            subArraysSums[i] = sumSubArray(nums, i, i+k);
            maxSum = Math.max(subArraysSums[i], maxSum);
            i++;
            if (i == m) {
                i = 0;
                m -= 1;
                k += 1;
            }
        }
        return maxSum;
    }

    public static int sumSubArray(int[] nums, int start, int end) {
        int sum = 0;
        while (start < end) {
            sum += nums[start++];
        }
        return sum;
    }

    public static int numOfSubArrays(int size) {
        int num = 0;
        for (int i = size; i > 0; i--)
            num += i;
        return num;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArrayBruteForce(nums));
        System.out.println(mcs(nums, 0, nums.length-1));
        System.out.println(maxSubArrayKadanesAlgorithm(nums));
    }
}
