// Time Complexity : O (m*n*3^L)
// Space Complexity : O (m*n)
// Did this code successfully run on Leetcode : Yes

// Your code here along with comments explaining your approach
public class WordSearch {
    //Pair class to store board index
    static class Pair {
        int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    char[][] board;
    String word;

    int m, n;
    Pair[] neighbourDiffs = {new Pair(-1, 0), new Pair(0, -1), new Pair(0, 1), new Pair(1, 0)};

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        m = board.length;
        n = board[0].length;
        int i, j;

        for(i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                Pair index = new Pair(i, j);
                // do dfs from every start position
                if (dfs(index, 0)) return true;
            }
        }
        return false;
    }

    boolean dfs(Pair index, int wordIndex) {
        //return true if index at wordLength
        if (wordIndex == word.length()) return true;

        // if invalid board index or letter does not match return false
        if (!checkValid(index) || board[index.i][index.j] != word.charAt(wordIndex)) return false;


        // mark letter as visited
        board[index.i][index.j] = '*';
        // explore all neighbours
        for(Pair nDiff: neighbourDiffs) {
            Pair neighbour = new Pair(index.i + nDiff.i, index.j + nDiff.j);
            //if found, return true
            if(dfs(neighbour, wordIndex + 1)) return true;
        }
        // mark unvisited for next search
        board[index.i][index.j] = word.charAt(wordIndex);
        return false;
    }

    boolean checkValid(Pair index) {
        return index.i >= 0 && index.i < m && index.j >= 0 && index.j < n;
    }
}