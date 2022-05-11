package cn.adalab.chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class Game {
    List<Map<String,Integer>> listMove = new ArrayList<Map<String,Integer>>();
    List<String> actions = new ArrayList<>();
    int[][] chessBoard = {
            {1, 2, 3, 4, 5, 4, 3, 2, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 0, 6, 0},
            {7, 0, 7, 0, 7, 0, 7, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {14, 0, 14, 0, 14, 0, 14, 0, 14},
            {0, 13, 0, 0, 0, 0, 0, 13, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {8, 9, 10, 11, 12, 11, 10, 9, 8},
    };

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Game a = new Game();
        a.loadActions(sc);
        a.gameMove();
        }

    public void printChessBoard() {
        int width = chessBoard[0].length;    // 心形的宽度
        int height = chessBoard.length;      // 心形的高度
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (chessBoard[row][col] == 0) {
                    System.out.print("口\t");
                } else if (chessBoard[row][col] == 1) {
                    System.out.print("车\t");
                } else if (chessBoard[row][col] == 2) {
                    System.out.print("马\t");
                } else if (chessBoard[row][col] == 3) {
                    // 如果是0,打印成空格
                    System.out.print("象\t");
                } else if (chessBoard[row][col] == 4) {
                    // 如果是0,打印成空格
                    System.out.print("士\t");
                } else if (chessBoard[row][col] == 5) {
                    // 如果是0,打印成空格
                    System.out.print("将\t");
                } else if (chessBoard[row][col] == 6) {
                    // 如果是0,打印成空格
                    System.out.print("砲\t");
                } else if (chessBoard[row][col] == 7) {
                    // 如果是0,打印成空格
                    System.out.print("卒\t");
                } else if (chessBoard[row][col] == 8) {
                    // 如果是0,打印成空格
                    System.out.print("俥\t");
                } else if (chessBoard[row][col] == 9) {
                    // 如果是0,打印成空格
                    System.out.print("傌\t");
                } else if (chessBoard[row][col] == 10) {
                    // 如果是0,打印成空格
                    System.out.print("相\t");
                } else if (chessBoard[row][col] == 11) {
                    // 如果是0,打印成空格
                    System.out.print("仕\t");
                } else if (chessBoard[row][col] == 12) {
                    // 如果是0,打印成空格
                    System.out.print("帅\t");
                } else if (chessBoard[row][col] == 13) {
                    // 如果是0,打印成空格
                    System.out.print("炮\t");
                } else if (chessBoard[row][col] == 14) {
                    // 如果是0,打印成空格
                    System.out.print("兵\t");
                }
            }
            System.out.println("");
        }
    }
    /**
     * 功能：红方移动棋子
     */
    public void playerRedMove(int a, int b, int c, int d) {
        switch (chessBoard[a][b]) {
            case 8:
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 8) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 9) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 10) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 11) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 12) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 13) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 14) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 6) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 5) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 4) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 3) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 2) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    chessBoard[c][d] = 8;
                    chessBoard[a][b] = 0;
                }
                break;
            case 9:
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 8) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 9) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 10) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 11) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 12) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 13) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 14) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 6) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 5) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 4) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 3) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 2) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    chessBoard[c][d] = 9;
                    chessBoard[a][b] = 0;
                }
                break;
            case 10:
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 8) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 9) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 10) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 11) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 12) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 13) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 14) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 6) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 5) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 4) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 3) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 2) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    chessBoard[c][d] = 10;
                    chessBoard[a][b] = 0;
                }
                break;
            case 11:
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 8) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 9) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 10) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 11) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 12) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 13) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 14) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 6) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 5) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 4) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 3) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 2) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    chessBoard[c][d] = 11;
                    chessBoard[a][b] = 0;
                }
                break;
            case 12:
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 8) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 9) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 10) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 11) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 12) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 13) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 14) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 6) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 5) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 4) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 3) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 2) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    chessBoard[c][d] = 12;
                    chessBoard[a][b] = 0;
                }
                break;
            case 13:
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 8) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 9) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 10) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 11) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 12) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 13) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 14) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 6) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 5) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 4) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 3) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 2) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    chessBoard[c][d] = 13;
                    chessBoard[a][b] = 0;
                }
                break;
            case 14:
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 8) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 9) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 10) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 11) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 12) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 13) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 14) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 6) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 5) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 4) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 3) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 2) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    chessBoard[c][d] = 14;
                    chessBoard[a][b] = 0;
                }
                break;
            default:
                break;
        }

    }
    /**
     * 功能：黑方移动棋子
     */
    public void playerBlackMove(int a, int b, int c, int d) {
        int count=0;
        switch (chessBoard[a][b]) {
            case 1:
                count = 1;
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 2) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 3) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 4) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 5) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 6) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 8) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 9) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 10) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 11) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 12) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 13) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 14) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                }
                break;
            case 2:
                count = 2;
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 2) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 3) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 4) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 5) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 6) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 8) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 9) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 10) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 11) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 12) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 13) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 14) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                }
                break;
            case 3:
                count = 3;
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 2) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 3) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 4) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 5) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 6) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 8) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 9) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 10) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 11) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 12) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 13) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 14) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                }
                break;
            case 4:
                count = 4;
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 2) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 3) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 4) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 5) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 6) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 8) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 9) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 10) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 11) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 12) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 13) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 14) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                }
                break;
            case 5:
                count = 5;
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 2) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 3) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 4) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 5) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 6) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 8) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 9) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 10) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 11) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 12) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 13) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 14) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                }
                break;
            case 6:
                count = 6;
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 2) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 3) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 4) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 5) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 6) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 8) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 9) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 10) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 11) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 12) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 13) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 14) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                }
                break;
            case 7:
                count = 7;
                if (chessBoard[c][d] == 0) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 1) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 2) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 3) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 4) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 5) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 6) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 7) {
                    System.out.println("错误");
                } else if (chessBoard[c][d] == 8) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 9) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 10) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                    System.out.println("win");
                } else if (chessBoard[c][d] == 11) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 12) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 13) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                } else if (chessBoard[c][d] == 14) {
                    chessBoard[c][d] = count;
                    chessBoard[a][b] = 0;
                }
                break;
            default:
                break;
        }
    }
    /**
     * 功能：读取文件获取Actions
     */
    public boolean loadActions(Scanner scanner) {
        try {
            int nAction = scanner.nextInt();
            // 读取所有地点
            for (int i = 0; i < nAction; i++) {
                String inputBianHao = scanner.next();
                String inputAction = scanner.next();
                actions.add(inputAction);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * 功能：读取文件跑游戏
     */
    public void gameMove(){
        boolean ifNext = true;
        for (int i = 0; i < actions.size(); i++) {
            int actiona = 0;
            int actionb = 0;
            int actionc = 0;
            int actiond = 0;
            int actionToCharArray1If = 0;
            char[] actionToCharArray = actions.get(i).toCharArray();
            String actionToCharArray1 = new String(actionToCharArray, 0, 1);
            String actionToCharArray2 = new String(actionToCharArray, 1, 1);
            String actionToCharArray3 = new String(actionToCharArray, 2, 1);
            String actionToCharArray4 = new String(actionToCharArray, 3, 1);
            if (ifNext) {
                switch (actionToCharArray2) {
                    case "一":
                        actionb = 8;
                        break;
                    case "二":
                        actionb = 7;
                        break;
                    case "三":
                        actionb = 6;
                        break;
                    case "四":
                        actionb = 5;
                        break;
                    case "五":
                        actionb = 4;
                        break;
                    case "六":
                        actionb = 3;
                        break;
                    case "七":
                        actionb = 2;
                        break;
                    case "八":
                        actionb = 1;
                        break;
                    case "九":
                        actionb = 0;
                        break;
                    default:
                        break;
                }
                switch (actionToCharArray1) {
                    case "炮":
                        actionToCharArray1If = 13;
                        break;
                    case "马":
                        actionToCharArray1If = 9;
                        break;
                    case "车":
                        actionToCharArray1If = 8;
                        break;
                    case "相":
                        actionToCharArray1If = 10;
                        break;
                    case "兵":
                        actionToCharArray1If = 14;
                        break;
                    case "仕":
                        actionToCharArray1If = 11;
                        break;
                    case "帅":
                        actionToCharArray1If = 12;
                        break;
                    default:
                        break;
                }
                for (int paoi = 0; paoi < chessBoard.length; paoi++) {
                    if (chessBoard[paoi][actionb] == actionToCharArray1If) {
                        actiona = paoi;
                        break;
                    }
                }
                if (actionToCharArray1If == 8) {
                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona - 2;
                                break;
                            case "三":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            case "二":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "三":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "四":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "五":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "六":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "七":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "八":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "九":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "三":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    }
                } else if (actionToCharArray1If == 9) {
                    switch (actionToCharArray4) {
                        case "一":
                            actiond = 8;
                            break;
                        case "二":
                            actiond = 7;
                            break;
                        case "三":
                            actiond = 6;
                            break;
                        case "四":
                            actiond = 5;
                            break;
                        case "五":
                            actiond = 4;
                            break;
                        case "六":
                            actiond = 3;
                            break;
                        case "七":
                            actiond = 2;
                            break;
                        case "八":
                            actiond = 1;
                            break;
                        case "九":
                            actiond = 0;
                            break;
                        default:
                            break;
                    }
                    switch (actionToCharArray3) {
                        case "进":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona - 2;
                                if(chessBoard[actiona-1][actionb]!=0){
                                    System.out.println((i+1)+". error");
                                    i++;
                                    System.out.println((i+1)+". skip");
                                    continue;}
                            } else if (Math.abs(actiond - actionb) == 2) {
                                if(chessBoard[actiona][actionb-1]!=0){
                                    System.out.println((i+1)+". error");
                                    i++;
                                    System.out.println((i+1)+". skip");
                                    continue;}
                                if(chessBoard[actiona][actionb+1]!=0){
                                    System.out.println((i+1)+". error");
                                    i++;
                                    System.out.println((i+1)+". skip");
                                    continue;}
                                actionc = actiona - 1;
                            }
                            break;
                        case "退":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona + 2;
                                if(chessBoard[actiona+1][actionb]!=0){
                                    System.out.println((i+1)+". error");
                                    i++;
                                    System.out.println((i+1)+". skip");
                                    continue;}
                            } else if (Math.abs(actiond - actionb) == 2) {
                                if(chessBoard[actiona][actionb-1]!=0){
                                    System.out.println((i+1)+". error");
                                    i++;
                                    System.out.println((i+1)+". skip");
                                    continue;}
                                if(chessBoard[actiona][actionb+1]!=0){
                                    System.out.println((i+1)+". error");
                                    i++;
                                    System.out.println((i+1)+". skip");
                                    continue;}
                                actionc = actiona + 1;
                            }
                            break;
                        default:
                            break;
                    }
                } else if (actionToCharArray1If == 10) {
                    switch (actionToCharArray4) {
                        case "一":
                            actiond = 8;
                            break;
                        case "二":
                            actiond = 7;
                            break;
                        case "三":
                            actiond = 6;
                            break;
                        case "四":
                            actiond = 5;
                            break;
                        case "五":
                            actiond = 4;
                            break;
                        case "六":
                            actiond = 3;
                            break;
                        case "七":
                            actiond = 2;
                            break;
                        case "八":
                            actiond = 1;
                            break;
                        case "九":
                            actiond = 0;
                            break;
                        default:
                            break;
                    }
                    switch (actionToCharArray3) {
                        case "进":
                            if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona - 2;
                            }
                            break;
                        case "退":
                            if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona + 2;
                            }
                            break;
                        default:
                            break;
                    }
                } else if (actionToCharArray1If == 11) {
                    switch (actionToCharArray4) {
                        case "一":
                            actiond = 8;
                            break;
                        case "二":
                            actiond = 7;
                            break;
                        case "三":
                            actiond = 6;
                            break;
                        case "四":
                            actiond = 5;
                            break;
                        case "五":
                            actiond = 4;
                            break;
                        case "六":
                            actiond = 3;
                            break;
                        case "七":
                            actiond = 2;
                            break;
                        case "八":
                            actiond = 1;
                            break;
                        case "九":
                            actiond = 0;
                            break;
                        default:
                            break;
                    }
                    switch (actionToCharArray3) {
                        case "进":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona - 1;
                            }
                            break;
                        case "退":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona + 1;
                            }
                            break;
                        default:
                            break;
                    }
                } else if (actionToCharArray1If == 12) {
                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona - 2;
                            case "三":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            case "二":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "三":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "四":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "五":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "六":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "七":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "八":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "九":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "三":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    }
                } else if (actionToCharArray1If == 13) {
                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona - 2;
                                break;
                            case "三":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            case "二":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "三":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "四":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "五":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "六":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "七":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "八":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "九":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "三":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    }
                } else if (actionToCharArray1If == 14) {

                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona - 2;
                                break;
                            case "三":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            case "二":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "三":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "四":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "五":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "六":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "七":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "八":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "九":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "一":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "二":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "三":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "四":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "五":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "六":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "七":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "八":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "九":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    }
                }
                if (chessBoard[actionc][actiond] == 5) {
                    System.out.println((i+1)+". 红方胜");
                    break;
                }
                if(isAbleToMove(actiona, actionb, actionc, actiond)){
                    if(check()){
                    playerRedMove(actiona, actionb, actionc, actiond);
                    System.out.println((i+1)+". ok");
                    ifNext = false;}
                    else{
                        System.out.println((i+1)+". 将军");
                        continue;
                    }
                }else {
                    System.out.println((i+1)+". error");
                    i++;
                    System.out.println((i+1)+". skip");
                    continue;
                }
            }
            else{
                switch (actionToCharArray2) {
                    case "1":
                        actionb = 0;
                        break;
                    case "2":
                        actionb = 1;
                        break;
                    case "3":
                        actionb = 2;
                        break;
                    case "4":
                        actionb = 3;
                        break;
                    case "5":
                        actionb = 4;
                        break;
                    case "6":
                        actionb = 5;
                        break;
                    case "7":
                        actionb = 6;
                        break;
                    case "8":
                        actionb = 7;
                        break;
                    case "9":
                        actionb = 8;
                        break;
                    default:
                        break;
                }
                switch (actionToCharArray1) {
                    case "炮":
                        actionToCharArray1If = 6;
                        break;
                    case "马":
                        actionToCharArray1If = 2;
                        break;
                    case "车":
                        actionToCharArray1If = 1;
                        break;
                    case "象":
                        actionToCharArray1If = 3;
                        break;
                    case "卒":
                        actionToCharArray1If = 7;
                        break;
                    case "士":
                        actionToCharArray1If = 4;
                        break;
                    case "将":
                        actionToCharArray1If = 5;
                        break;
                    default:
                        break;
                }
                for (int paoi = 0; paoi < chessBoard.length; paoi++) {
                    if (chessBoard[paoi][actionb] == actionToCharArray1If) {
                        actiona = paoi;
                        break;
                    }
                }
                if (actionToCharArray1If == 1) {
                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            case "2":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "3":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "4":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "5":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "6":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "7":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "8":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "9":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona - 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    }
                } else if (actionToCharArray1If == 2) {
                    switch (actionToCharArray4) {
                        case "1":
                            actiond = 0;
                            break;
                        case "2":
                            actiond = 1;
                            break;
                        case "3":
                            actiond = 2;
                            break;
                        case "4":
                            actiond = 3;
                            break;
                        case "5":
                            actiond = 4;
                            break;
                        case "6":
                            actiond = 5;
                            break;
                        case "7":
                            actiond = 6;
                            break;
                        case "8":
                            actiond = 7;
                            break;
                        case "9":
                            actiond = 8;
                            break;
                        default:
                            break;
                    }
                    switch (actionToCharArray3) {
                        case "进":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona + 2;
                            } else if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona + 1;
                            }
                            break;
                        case "退":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona - 2;
                            } else if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona - 1;
                            }
                            break;
                        default:
                            break;
                    }
                } else if (actionToCharArray1If == 3) {
                    switch (actionToCharArray4) {
                        case "1":
                            actiond = 0;
                            break;
                        case "2":
                            actiond = 1;
                            break;
                        case "3":
                            actiond = 2;
                            break;
                        case "4":
                            actiond = 3;
                            break;
                        case "5":
                            actiond = 4;
                            break;
                        case "6":
                            actiond = 5;
                            break;
                        case "7":
                            actiond = 6;
                            break;
                        case "8":
                            actiond = 7;
                            break;
                        case "9":
                            actiond = 8;
                            break;
                        default:
                            break;
                    }
                    switch (actionToCharArray3) {
                        case "进":
                            if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona + 2;
                            }
                            break;
                        case "退":
                            if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona - 2;
                            }
                            break;
                        default:
                            break;
                    }
                } else if (actionToCharArray1If == 4) {
                    switch (actionToCharArray4) {
                        case "1":
                            actiond = 0;
                            break;
                        case "2":
                            actiond = 1;
                            break;
                        case "3":
                            actiond = 2;
                            break;
                        case "4":
                            actiond = 3;
                            break;
                        case "5":
                            actiond = 4;
                            break;
                        case "6":
                            actiond = 5;
                            break;
                        case "7":
                            actiond = 6;
                            break;
                        case "8":
                            actiond = 7;
                            break;
                        case "9":
                            actiond = 8;
                            break;
                        default:
                            break;
                    }
                    switch (actionToCharArray3) {
                        case "进":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona + 1;
                            }
                            break;
                        case "退":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona - 1;
                            }
                            break;
                        default:
                            break;
                    }
                } else if (actionToCharArray1If == 5) {
                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            case "2":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "3":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "4":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "5":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "6":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "7":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "8":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "9":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona - 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    }
                } else if (actionToCharArray1If == 6) {
                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            case "2":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "3":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "4":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "5":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "6":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "7":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "8":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "9":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona - 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    }
                } else if (actionToCharArray1If == 7) {
                    if (actionToCharArray3.equals("进")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona + 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona + 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona + 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona + 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona + 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona + 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona + 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona + 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona + 9;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("平")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = 0;
                                actionc = actiona;
                                break;
                            case "2":
                                actiond = 1;
                                actionc = actiona;
                                break;
                            case "3":
                                actiond = 2;
                                actionc = actiona;
                                break;
                            case "4":
                                actiond = 3;
                                actionc = actiona;
                                break;
                            case "5":
                                actiond = 4;
                                actionc = actiona;
                                break;
                            case "6":
                                actiond = 5;
                                actionc = actiona;
                                break;
                            case "7":
                                actiond = 6;
                                actionc = actiona;
                                break;
                            case "8":
                                actiond = 7;
                                actionc = actiona;
                                break;
                            case "9":
                                actiond = 8;
                                actionc = actiona;
                                break;
                            default:
                                break;
                        }
                    } else if (actionToCharArray3.equals("退")) {
                        switch (actionToCharArray4) {
                            case "1":
                                actiond = actionb;
                                actionc = actiona - 1;
                                break;
                            case "2":
                                actiond = actionb;
                                actionc = actiona - 2;
                                break;
                            case "3":
                                actiond = actionb;
                                actionc = actiona - 3;
                                break;
                            case "4":
                                actiond = actionb;
                                actionc = actiona - 4;
                                break;
                            case "5":
                                actiond = actionb;
                                actionc = actiona - 5;
                                break;
                            case "6":
                                actiond = actionb;
                                actionc = actiona - 6;
                                break;
                            case "7":
                                actiond = actionb;
                                actionc = actiona - 7;
                                break;
                            case "8":
                                actiond = actionb;
                                actionc = actiona - 8;
                                break;
                            case "9":
                                actiond = actionb;
                                actionc = actiona - 9;
                                break;
                            default:
                                break;
                        }
                    }
                }
                if (chessBoard[actionc][actiond] == 5) {
                    System.out.println((i+1)+". 黑方胜");
                    break;
                }
                if(isAbleToMove(actiona, actionb, actionc, actiond)){
                    if(check()){
                        playerBlackMove(actiona, actionb, actionc, actiond);
                        System.out.println((i+1)+". ok");
                        ifNext = true;}
                    else{
                        System.out.println((i+1)+". 将军");
                    }
                }else {
                    System.out.println((i+1)+". error");
                    i++;
                    System.out.println((i+1)+". skip");
                }
            }

        }
    }
    /**
     * 功能：判断从(a,b)能否移动到（c,d）
     */
    public boolean isAbleToMove(int a, int b, int c, int d){
        if(a<0 | c>=chessBoard[0].length){
            return false;
        }
        if(b<0 | c>=chessBoard.length){
            return false;
        }
        if(c<0 | c>=chessBoard[0].length){
            return false;
        }
        if(d<0 | c>=chessBoard.length){
            return false;
        }
        //不能吃自己伙的棋子
        switch (chessBoard[a][b]) {
            case 0:
                return false;
            case 1:
                if (chessBoard[c][d] == 1) {
                    return false;
                } else if (chessBoard[c][d] == 2) {
                    return false;
                } else if (chessBoard[c][d] == 3) {
                    return false;
                } else if (chessBoard[c][d] == 4) {
                    return false;
                } else if (chessBoard[c][d] == 5) {
                    return false;
                } else if (chessBoard[c][d] == 6) {
                    return false;
                } else if (chessBoard[c][d] == 7) {
                    return false;
                }
                //不能走斜线

                //竖走

                //判断中间是否有棋子

                //横走

                //判断中间是否有棋子
            case 2:
                if (chessBoard[c][d] == 1) {
                    return false;
                } else if (chessBoard[c][d] == 2) {
                    return false;
                } else if (chessBoard[c][d] == 3) {
                    return false;
                } else if (chessBoard[c][d] == 4) {
                    return false;
                } else if (chessBoard[c][d] == 5) {
                    return false;
                } else if (chessBoard[c][d] == 6) {
                    return false;
                } else if (chessBoard[c][d] == 7) {
                    return false;
                }
                //必须走日字格

                //向上跳


                //向下跳

                //向左跳

                //向右跳
            case 3:
                if (chessBoard[c][d] == 1) {
                    return false;
                } else if (chessBoard[c][d] == 2) {
                    return false;
                } else if (chessBoard[c][d] == 3) {
                    return false;
                } else if (chessBoard[c][d] == 4) {
                    return false;
                } else if (chessBoard[c][d] == 5) {
                    return false;
                } else if (chessBoard[c][d] == 6) {
                    return false;
                } else if (chessBoard[c][d] == 7) {
                    return false;
                }
                //不能越界

                //不能走横线或竖线

                //一次只能走二格

                //是否堵象眼
            case 4:
                if (chessBoard[c][d] == 1) {
                    return false;
                } else if (chessBoard[c][d] == 2) {
                    return false;
                } else if (chessBoard[c][d] == 3) {
                    return false;
                } else if (chessBoard[c][d] == 4) {
                    return false;
                } else if (chessBoard[c][d] == 5) {
                    return false;
                } else if (chessBoard[c][d] == 6) {
                    return false;
                } else if (chessBoard[c][d] == 7) {
                    return false;
                }
                //不能出九宫

                //一次只能走一格

                //不能走横线或竖线
            case 5:
                if (chessBoard[c][d] == 1) {
                    return false;
                } else if (chessBoard[c][d] == 2) {
                    return false;
                } else if (chessBoard[c][d] == 3) {
                    return false;
                } else if (chessBoard[c][d] == 4) {
                    return false;
                } else if (chessBoard[c][d] == 5) {
                    return false;
                } else if (chessBoard[c][d] == 6) {
                    return false;
                } else if (chessBoard[c][d] == 7) {
                    return false;
                }
                //不能出九宫

                //一次只能走一格

                //不能走斜线

                //将帅不能露脸
                //目标棋子是将帅并且在同一列上
                //判断中间是否有棋子
//                int count = 0;
//                int min = Math.min(oldRow,_newRow);
//                int max = Math.max(oldRow,_newRow);
//                for(int row=min+1;row<max;row++)
//                {
//                    if(this.gamePanel.chessBoradState[row][_newColumn] != -1){count++;}
//                }
//                if(count == 0){return false;}
            case 6:
                if (chessBoard[c][d] == 1) {
                    return false;
                } else if (chessBoard[c][d] == 2) {
                    return false;
                } else if (chessBoard[c][d] == 3) {
                    return false;
                } else if (chessBoard[c][d] == 4) {
                    return false;
                } else if (chessBoard[c][d] == 5) {
                    return false;
                } else if (chessBoard[c][d] == 6) {
                    return false;
                } else if (chessBoard[c][d] == 7) {
                    return false;
                }
                //炮

                //不能走斜线

                //竖走

                //判断中间是否有棋子

                //横走

                //判断中间是否有棋子

                //开始判断是否可以移动或吃棋子
            case 7:
                if (chessBoard[c][d] == 1) {
                    return false;
                } else if (chessBoard[c][d] == 2) {
                    return false;
                } else if (chessBoard[c][d] == 3) {
                    return false;
                } else if (chessBoard[c][d] == 4) {
                    return false;
                } else if (chessBoard[c][d] == 5) {
                    return false;
                } else if (chessBoard[c][d] == 6) {
                    return false;
                } else if (chessBoard[c][d] == 7) {
                    return false;
                }
                //卒兵

                //不能走斜线

                //一次只能走一格

                //小卒过河不回头

                //过河了

                //下方
                //过河了
                //不许向后退
                //只能往前走
            case 8:
                if (chessBoard[c][d] == 8) {
                    return false;
                } else if (chessBoard[c][d] == 9) {
                    return false;
                } else if (chessBoard[c][d] == 10) {
                    return false;
                } else if (chessBoard[c][d] == 11) {
                    return false;
                } else if (chessBoard[c][d] == 12) {
                    return false;
                } else if (chessBoard[c][d] == 13) {
                    return false;
                } else if (chessBoard[c][d] == 14) {
                    return false;
                }
            case 9:
                if (chessBoard[c][d] == 8) {
                    return false;
                } else if (chessBoard[c][d] == 9) {
                    return false;
                } else if (chessBoard[c][d] == 10) {
                    return false;
                } else if (chessBoard[c][d] == 11) {
                    return false;
                } else if (chessBoard[c][d] == 12) {
                    return false;
                } else if (chessBoard[c][d] == 13) {
                    return false;
                } else if (chessBoard[c][d] == 14) {
                    return false;
                }
                //必须走日字格

                //向上跳


                //向下跳

                //向左跳

                //向右跳
            case 10:
                if (chessBoard[c][d] == 8) {
                    return false;
                } else if (chessBoard[c][d] == 9) {
                    return false;
                } else if (chessBoard[c][d] == 10) {
                    return false;
                } else if (chessBoard[c][d] == 11) {
                    return false;
                } else if (chessBoard[c][d] == 12) {
                    return false;
                } else if (chessBoard[c][d] == 13) {
                    return false;
                } else if (chessBoard[c][d] == 14) {
                    return false;
                }
                //不能越界

                //不能走横线或竖线

                //一次只能走二格

                //是否堵象眼
            case 11:
                if (chessBoard[c][d] == 8) {
                    return false;
                } else if (chessBoard[c][d] == 9) {
                    return false;
                } else if (chessBoard[c][d] == 10) {
                    return false;
                } else if (chessBoard[c][d] == 11) {
                    return false;
                } else if (chessBoard[c][d] == 12) {
                    return false;
                } else if (chessBoard[c][d] == 13) {
                    return false;
                } else if (chessBoard[c][d] == 14) {
                    return false;
                }
                //不能出九宫

                //一次只能走一格

                //不能走横线或竖线
            case 12:
                if (chessBoard[c][d] == 8) {
                    return false;
                } else if (chessBoard[c][d] == 9) {
                    return false;
                } else if (chessBoard[c][d] == 10) {
                    return false;
                } else if (chessBoard[c][d] == 11) {
                    return false;
                } else if (chessBoard[c][d] == 12) {
                    return false;
                } else if (chessBoard[c][d] == 13) {
                    return false;
                } else if (chessBoard[c][d] == 14) {
                    return false;
                }
                //不能出九宫

                //一次只能走一格

                //不能走斜线

                //将帅不能露脸
                //目标棋子是将帅并且在同一列上
                //判断中间是否有棋子
//                int count = 0;
//                int min = Math.min(oldRow,_newRow);
//                int max = Math.max(oldRow,_newRow);
//                for(int row=min+1;row<max;row++)
//                {
//                    if(this.gamePanel.chessBoradState[row][_newColumn] != -1){count++;}
//                }
//                if(count == 0){return false;}
            case 13:
                if (chessBoard[c][d] == 8) {
                    return false;
                } else if (chessBoard[c][d] == 9) {
                    return false;
                } else if (chessBoard[c][d] == 10) {
                    return false;
                } else if (chessBoard[c][d] == 11) {
                    return false;
                } else if (chessBoard[c][d] == 12) {
                    return false;
                } else if (chessBoard[c][d] == 13) {
                    return false;
                } else if (chessBoard[c][d] == 14) {
                    return false;
                }
            case 14:
                if (chessBoard[c][d] == 8) {
                    return false;
                } else if (chessBoard[c][d] == 9) {
                    return false;
                } else if (chessBoard[c][d] == 10) {
                    return false;
                } else if (chessBoard[c][d] == 11) {
                    return false;
                } else if (chessBoard[c][d] == 12) {
                    return false;
                } else if (chessBoard[c][d] == 13) {
                    return false;
                } else if (chessBoard[c][d] == 14) {
                    return false;
                }
            default:
                break;
        }
        return true;
    }
    /**
     * 功能：得到某棋子的可移动路线图
     */
    private void getMoveRoute(int a, int b) {
        listMove.clear();
        //懒得分类挑，反正电脑计算快
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[0].length; col++) {
                if (isAbleToMove(a,b,row,col)) {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("row", row);
                    map.put("col", col);
                    listMove.add(map);
                }
            }
        }
    }
    /**
     * 功能：检查是否将军
     */
    private boolean check() {
        //全体循环，不知道将哪头的军
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[0].length; col++) {
                getMoveRoute(row, col);
                for (Map<String, Integer> map : listMove) {
                    if ((chessBoard[map.get("row")][map.get("col")]) == 5 | (chessBoard[map.get("row")][map.get("col")]) == 12) {
                        return false;
                    }
                }
                listMove.clear();
            }
        }
        return true;
    }
}
