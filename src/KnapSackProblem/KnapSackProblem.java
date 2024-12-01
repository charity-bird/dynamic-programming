package KnapSackProblem;

public class KnapSackProblem {

    public static int fillKnapSack(int n, int W, int[] weights, int[] values) {
        int[][] M = new int[n+1][W+1];

        for (int w = 0; w <= W; w++) {
            M[0][w] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i] > w) {
                    M[i][w] = M[i-1][w];
                } else {
                    M[i][w] = Math.max(M[i-1][w], values[i] + M[i-1][w-weights[i]]);
                }
            }
        }
        return M[n][W];
    }
    public static void main(String[] args) {
        int n = 5;
        int W = 11;
        int[] w = {0, 1, 2, 5, 6, 7};
        int[] v = {0, 1, 6, 18, 22, 28};
        System.out.println(fillKnapSack(n,W,w,v));
    }

}
