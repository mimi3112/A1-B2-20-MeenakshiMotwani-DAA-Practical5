public class LongestCommonSubsequence {
    public static class LCSResult {
        int[][] cost;
        char[][] direction;

        public LCSResult(int[][] cost, char[][] direction) {
            this.cost = cost;
            this.direction = direction;
        }
    }

    public static LCSResult lcs(String A, String B) {
        int m = A.length();
        int n = B.length();

        int[][] cost = new int[m + 1][n + 1];
        char[][] direction = new char[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    cost[i][j] = 0;
                    direction[i][j] = 'H';
                } else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    cost[i][j] = cost[i - 1][j - 1] + 1;
                    direction[i][j] = 'D';
                } else {
                    if (cost[i - 1][j] >= cost[i][j - 1]) {
                        cost[i][j] = cost[i - 1][j];
                        direction[i][j] = 'U';
                    } else {
                        cost[i][j] = cost[i][j - 1];
                        direction[i][j] = 'S';
                    }
                }
            }
        }

        return new LCSResult(cost, direction);
    }

    public static void printLCS(String A, char[][] direction, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (direction[i][j] == 'D') {
            printLCS(A, direction, i - 1, j - 1);
            System.out.print(A.charAt(i - 1));
        } else if (direction[i][j] == 'U') {
            printLCS(A, direction, i - 1, j);
        } else {
            printLCS(A, direction, i, j - 1);
        }
    }

    public static void main(String[] args) {
        String X = "AGCCCTAAGGGCTACCTAGCTT";
        String Y = "GACAGCCTACAAGCGTTAGCTTG";

        int m = X.length();
        int n = Y.length();

        LCSResult result = lcs(X, Y);

        System.out.print("Longest Common Subsequence: ");
        printLCS(X, result.direction, m, n);

        System.out.println("\nLength of Longest Common Subsequence: " + result.cost[m][n]);
    }
}