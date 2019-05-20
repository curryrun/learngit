package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/5/8 下午10:01
 */
public class Dfs {

    //最后结果的全局变量，为什么不用返回值呢？下面有分析
    private boolean finalRes = false;

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() <= 0 || board == null || board.length <= 0 || board[0].length <= 0)
            return false;

        //由于我们是从word的第一个字符开始DFS遍历匹配，所以矩阵board中可能不止一个元素，
        //所以要遍历所有可能的word第一个元素word[0]，然后做DFS
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //每当找到一个word[0]，就开始做DFS遍历
                if (board[i][j] == word.charAt(0)) {
                    //设置标记数组，这个是必须的，在DFS算法中必须有一个flag数组
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    DFS(board, visited, word, 1, i, j);
                    //假如找到一个结果就直接返回，
                    if (finalRes == true)
                        return true;
                    //恢复原状态，但是因为visited数组的作用于就在这个if之中，所以这一句不写也可以
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private void DFS(char[][] board, boolean[][] visited, String word, int index, int x, int y) {
        if (index == word.length()) {
            finalRes = true;
            return;
        } else {
            //&& finalRes==false 这个条件必须添加，否者就会超时，我分析可能是因为一条路找到解，
            //然后就会影响到其他的解，毕竟我们使用的是全局变量，添加这个变量也起到剪枝的效果
            if (x - 1 >= 0 && visited[x - 1][y] == false
                    && board[x - 1][y] == word.charAt(index) && finalRes == false) {
                visited[x - 1][y] = true;
                DFS(board, visited, word, index + 1, x - 1, y);
                visited[x - 1][y] = false;
            }

            if (x + 1 < board.length && visited[x + 1][y] == false
                    && board[x + 1][y] == word.charAt(index) && finalRes == false) {
                visited[x + 1][y] = true;
                DFS(board, visited, word, index + 1, x + 1, y);
                visited[x + 1][y] = false;
            }

            if (y - 1 >= 0 && visited[x][y - 1] == false
                    && board[x][y - 1] == word.charAt(index) && finalRes == false) {
                visited[x][y - 1] = true;
                DFS(board, visited, word, index + 1, x, y - 1);
                visited[x][y - 1] = false;
            }

            if (y + 1 < board[0].length && visited[x][y + 1] == false
                    && board[x][y + 1] == word.charAt(index) && finalRes == false) {
                visited[x][y + 1] = true;
                DFS(board, visited, word, index + 1, x, y + 1);
                visited[x][y + 1] = false;
            }

        }

    }

}
