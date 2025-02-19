// Time Complexity : O (n!)
// Space Complexity : O (n^2)
// Did this code successfully run on Leetcode : Yes

// Your code here along with comments explaining your approach
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    char[][] grid;
    List<List<String>> result;
    int n;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        grid = new char[n][n];
        for(char[] line: grid) {
            Arrays.fill(line, '.');
        }
        result = new ArrayList<>();
        backtrack(0);
        return result;
    }
    void backtrack(int row) {
        //since we are placing 1 Queen per row,
        //if we got to n rows - we placed n Queens
        if(row == n) {
            //convert grid to expected output
            List<String> config = new ArrayList<>();
            for(char[] line: grid) {
                config.add(String.valueOf(line));
            }
            result.add(config);
            return;
        }
        for(int j = 0; j < n; j++) {
            if(isSafe(row, j)) {
                //place queen
                grid[row][j] = 'Q';
                //check subsequent rows
                backtrack(row + 1);
                //remove queen - backtrack
                grid[row][j] = '.';
            }
        }
    }
    /**
     * Checks if it is safe to place the queen at (x, y)
     * (i.e. it is not under attack by any other queens)
     */
    boolean isSafe(int x, int y) {
        int i, j;
        // check column up till cell
        for(i = 0; i < x; i++) {
            if(grid[i][y] == 'Q') return false;
        }
        // check main diagonal up till cell
        for(i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if(grid[i][j] == 'Q') return false;
        }
        // check secondary diagonal up till cell
        for(i = x, j = y; i >= 0 && j < n; i--, j++) {
            if(grid[i][j] == 'Q') return false;
        }
        return true;
    }
}
