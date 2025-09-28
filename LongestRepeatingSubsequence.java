public class LongestRepeatingSubsequence {
    public static class LRSResult {
        int[][] dp;
        char[][] direction;

        public LRSResult(int[][] dp, char[][] direction) {
            this.dp = dp;
            this.direction = direction;
        }
    }

    public static LRSResult lrs(String S) {
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];
        char[][] direction = new char[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    direction[i][j] = 'H';
                } else if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    direction[i][j] = 'D';
                } else {
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        direction[i][j] = 'U';
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        direction[i][j] = 'S';
                    }
                }
            }
        }
        return new LRSResult(dp, direction);
    }

    public static void printLRS(String S, char[][] direction, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (direction[i][j] == 'D') {
            printLRS(S, direction, i - 1, j - 1);
            System.out.print(S.charAt(i - 1));
        } else if (direction[i][j] == 'U') {
            printLRS(S, direction, i - 1, j);
        } else {
            printLRS(S, direction, i, j - 1);
        }
    }

    public static void main(String[] args) {
        String S = "AABCBDC";

        LRSResult result = lrs(S);
        int n = S.length();

        System.out.print("LRS: ");
        printLRS(S, result.direction, n, n);
    }
}
