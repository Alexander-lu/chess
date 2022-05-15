package cn.adalab.chess;
import acm.graphics.GImage;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
public class Game extends GraphicsProgram  implements Backgroundmusic{
    /** 初始化音乐 */
    public Play gamestart;
    /** 生成随机数 */
    RandomGenerator randomGenerator = RandomGenerator.getInstance();
    /** 判断红棋还是黑棋先走 */
    boolean ifRedMove= true;
    /** 判断将军有没有面对面 */
    boolean ifKingFace= false;
    /** 初始化棋盘 */
    GImage ChessBoard;
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
    /** CurrA和CurrB分别代表鼠标现在的Y和X */
    int CurrA;
    int CurrB;
    /** AftA和AftB分别代表 选择棋子的Y和X */
    int AftA;
    int AftB;
    /** AftC和AftD分别代表 棋子落点的Y和X */
    int AftC;
    int AftD;
    JButton save = new JButton("save");
    JButton read = new JButton("read");
    JButton auto = new JButton("auto");
    JLabel nameLabel = new JLabel("请点击棋子");
    JTextField TextField = new JTextField("请输入行为");
    /* 游戏界面的长和宽 */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 700;
    List<String> actions = new ArrayList<>();
    List<Map<String, Integer>> listMove = new ArrayList<Map<String, Integer>>();
    /** 功能：移动棋子 */
    public void playerMove(int a, int b, int c, int d) {
        chessBoard[c][d] = chessBoard[a][b];
        chessBoard[a][b] = 0;
        gamestart = new Play("src/music/move.mp3");
        gamestart.start();
        }
    /** 功能：读取文件获取Actions */
    private void readActions() {
        File readFileName = JFileChooserDemo.getFileName();
        Scanner scanner = null;
        try {
            scanner = new Scanner(readFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int nAction = scanner.nextInt();
        // 读取所有地点
        for (int i = 0; i < nAction; i++) {
            String inputBianHao = scanner.next();
            String inputAction = scanner.next();
            actions.add(inputAction);
        }
        gameMove();
    }
    /** 功能：读取Actions跑游戏 */
    public void gameMove() {
        for (int i = 0; i < actions.size(); i++) {
            if (ifRedMove) {
                realRedGameMove(actions.get(i));
                JOptionPane.showMessageDialog(null,i+". "+actions.get(i));
                if (ifRedMove) {
                    i++;
                    continue;
                } else {  clear();
                    addBackGroundImage();
                    addImage();}
            }else {
                realBlackGameMove(actions.get(i));
                JOptionPane.showMessageDialog(null,i+". "+actions.get(i));
                if (ifRedMove) {
                    clear();
                    addBackGroundImage();
                    addImage();
                }else {
                    i++;
                    continue;
               }
            }
        }}
    /** 功能：判断从(a,b)能否移动到（c,d）;如果将军面对面，将ifKingFace变为true */
    public boolean isAbleToMove(int a, int b, int c, int d) {
        //不能自己吃自己
        if (a == c & b == d) {
            return false;
        }
        //不能跑到不存在的点去
        if (a < 0 | a >= 10) {
            return false;
        }
        if (b < 0 | b >= 9) {
            return false;
        }
        if (c < 0 | c >= 10) {
            return false;
        }
        if (d < 0 | d >= 9) {
            return false;
        }
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
                if(c!=a&d!=b){return false;}
                //车往左走
                if (a == c & b > d) {
                    for (int i = b - 1; i > d; i--) {
                        if (chessBoard[a][i] > 0) {
                            return false;
                        }
                    }
                }
                //车往右走
                if (a == c & b < d) {
                    for (int i = b + 1; i < d; i++) {
                        if (chessBoard[a][i] > 0) {
                            return false;
                        }
                    }
                }
                //车往上走
                if (b == d & a > c) {
                    for (int i = a - 1; i > c; i--) {
                        if (chessBoard[i][b] > 0) {
                            return false;
                        }
                    }
                }
                //车往下走
                if (b == d & a < c) {
                    for (int i = a + 1; i < c; i++) {
                        if (chessBoard[i][b] > 0) {
                            return false;
                        }
                    }
                }
                return true;
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
                if ((c == a-1 && d == b - 2) || (c == a-2 && d == b-1) || (c == a + 1 && d == b-2) || (c == a +2 && d == b-1) || (c == a-1 && d == b + 2) || (c == a-2 && d == b+1) || (c == a + 1 && d == b+2) || (c == a +2 && d == b+1)) {
                    //是否堵马脚
                    if(c == a-1 && d == b - 2){
                        if(a>=0&&a<=9&&b-1>=0&&b-1<=9){if (chessBoard[a][b-1] > 0) {return false;}}
                    }
                    if(c == a-2 && d == b-1){
                        if(a-1>=0&&a-1<=9&&b>=0&&b<=9){if (chessBoard[a-1][b] > 0) {return false;}}
                    }
                    if(c == a + 1 && d == b-2){
                        if(a>=0&&a<=9&&b-1>=0&&b-1<=9){if (chessBoard[a][b-1] > 0) {return false;}}
                    }
                    if(c == a +2 && d == b-1){
                        if(a+1>=0&&a+1<=9&&b>=0&&b<=9){if (chessBoard[a+1][b] > 0) {return false;}}
                    }
                    if(c == a-1 && d == b + 2){
                        if(a>=0&&a<=9&&b+1>=0&&b+1<=9){if (chessBoard[a][b+1] > 0) {return false;}}
                    }
                    if(c == a-2 && d == b+1){
                        if(a-1>=0&&a-1<=9&&b>=0&&b<=9){if (chessBoard[a-1][b] > 0) {return false;}}
                    }
                    if(c == a + 1 && d == b+2){
                        if(a>=0&&a<=9&&b+1>=0&&b+1<=9){if (chessBoard[a][b+1] > 0) {return false;}}
                    }
                    if(c == a +2 && d == b+1){
                        if(a+1>=0&&a+1<=9&&b>=0&&b<=9){if (chessBoard[a+1][b] > 0) {return false;}}
                    }
                    return true;
                } else {
                    return false;
                }
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
                //只能往左上左下右上右下跳
                if ((c == a-2 && d == b - 2) || (c == a-2 && d == b+2) || (c == a + 2 && d == b-2) || (c == a +2 && d == b+2)) {
                    //是否过河
                    if(c>=5){return false;}
                    //是否堵象眼
                    if(c == a-2 && d == b - 2){
                        if (chessBoard[a-1][b-1] > 0) {return false;}
                    }
                    if(c == a-2 && d == b + 2){
                        if (chessBoard[a-1][b+1] > 0) {return false;}
                    }
                    if(c == a+2 && d == b - 2){
                        if (chessBoard[a+1][b-1] > 0) {return false;}
                    }
                    if(c == a+2 && d == b + 2){
                        if (chessBoard[a+1][b+1] > 0) {return false;}
                    }
                    return true;
                } else {
                    return false;
                }
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
                if (c >= 3) {return false;}
                if (d <= 2) {return false;}
                if (d >= 6) {return false;}
                //一次只能走一格 不能走横线或竖线
                if ((c == a-1 && d == b+1) || (c == a-1 && d == b-1) || (c == a+1 && d == b+1) || (c == a+1 && d == b-1)) {
                    return true;
                } else {
                    return false;
                }
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
                if (d <= 2) {return false;}
                if (d >= 6) {return false;}
                //如果将军面对面，将ifKingFace变为true
                if (c >= 3) {if(b == d & c > a + 1){
                        for (int i = a + 1; i < c; i++) {
                            if (chessBoard[i][b] > 0) {
                                return false;
                            }
                        }
                        if(chessBoard[c][d]==12){
                            ifKingFace=true;
                            return true;
                        }
                    }return false;}
                //一次只能走一格 不能走斜线
                if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a + 1 && d == b) || (c == a - 1 && d == b)) {
                    return true;
                }
                return false;
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
                //不能走斜线
                if(c!=a&d!=b){return false;}
                int paoFindOne = 0;
                //炮往左走
                if (a == c & b > d) {
                    for (int i = b - 1; i > d; i--) {
                        if (chessBoard[a][i] > 0) {
                            paoFindOne++;
                        }
                    }
                }
                //炮往右走
                if (a == c & b < d) {
                    for (int i = b + 1; i < d; i++) {
                        if (chessBoard[a][i] > 0) {
                            paoFindOne++;
                        }
                    }
                }
                //炮往上走
                if (b == d & a > c) {
                    for (int i = a - 1; i > c; i--) {
                        if (chessBoard[i][b] > 0) {
                            paoFindOne++;
                        }
                    }
                }
                //炮往下走
                if (b == d & a < c) {
                    for (int i = a + 1; i < c; i++) {
                        if (chessBoard[i][b] > 0) {
                            paoFindOne++;
                        }
                    }
                }
                if(paoFindOne==1&chessBoard[c][d]>0){return true;}
                if(paoFindOne==1&chessBoard[c][d]==0){return false;}
                if(paoFindOne==0&chessBoard[c][d]>0){return false;}
                if(paoFindOne==0&chessBoard[c][d]==0){return true;}
                if(paoFindOne>1){return false;}
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
                //小卒过河后只能往前，左，右走 一次只能走一格 不能走斜线
                if (c >= 5) {
                    if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a + 1 && d == b)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                //小卒未过河只能往前走 一次只能走一格 不能走斜线
                if (c < 5) {
                    if (c == a + 1 && d == b) {
                        return true;
                    } else {
                        return false;
                    }
                }
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
                //不能走斜线
                if(c!=a&d!=b){return false;}
                //车往左走
                if (a == c & b > d) {
                    for (int i = b - 1; i > d; i--) {
                        if (chessBoard[a][i] > 0) {
                            return false;
                        }
                    }
                }
                //车往右走
                if (a == c & b < d) {
                    for (int i = b + 1; i < d; i++) {
                        if (chessBoard[a][i] > 0) {
                            return false;
                        }
                    }
                }
                //车往上走
                if (b == d & a > c) {
                    for (int i = a - 1; i > c; i--) {
                        if (chessBoard[i][b] > 0) {
                            return false;
                        }
                    }
                }
                //车往下走
                if (b == d & a < c) {
                    for (int i = a + 1; i < c; i++) {
                        if (chessBoard[i][b] > 0) {
                            return false;
                        }
                    }
                }
                return true;
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
                if ((c == a-1 && d == b - 2) || (c == a-2 && d == b-1) || (c == a + 1 && d == b-2) || (c == a +2 && d == b-1) || (c == a-1 && d == b + 2) || (c == a-2 && d == b+1) || (c == a + 1 && d == b+2) || (c == a +2 && d == b+1)) {
                    //是否堵马脚
                    if(c == a-1 && d == b - 2){
                        if(a>=0&&a<=9&&b-1>=0&&b-1<=9){if (chessBoard[a][b-1] > 0) {return false;}}
                    }
                    if(c == a-2 && d == b-1){
                        if(a-1>=0&&a-1<=9&&b>=0&&b<=9){if (chessBoard[a-1][b] > 0) {return false;}}
                    }
                    if(c == a + 1 && d == b-2){
                        if(a>=0&&a<=9&&b-1>=0&&b-1<=9){if (chessBoard[a][b-1] > 0) {return false;}}
                    }
                    if(c == a +2 && d == b-1){
                        if(a+1>=0&&a+1<=9&&b>=0&&b<=9){if (chessBoard[a+1][b] > 0) {return false;}}
                    }
                    if(c == a-1 && d == b + 2){
                        if(a>=0&&a<=9&&b+1>=0&&b+1<=9){if (chessBoard[a][b+1] > 0) {return false;}}
                    }
                    if(c == a-2 && d == b+1){
                        if(a-1>=0&&a-1<=9&&b>=0&&b<=9){if (chessBoard[a-1][b] > 0) {return false;}}
                    }
                    if(c == a + 1 && d == b+2){
                        if(a>=0&&a<=9&&b+1>=0&&b+1<=9){if (chessBoard[a][b+1] > 0) {return false;}}
                    }
                    if(c == a +2 && d == b+1){
                        if(a+1>=0&&a+1<=9&&b>=0&&b<=9){if (chessBoard[a+1][b] > 0) {return false;}}
                    }
                    return true;
                } else {
                    return false;
                }
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
                if ((c == a-2 && d == b - 2) || (c == a-2 && d == b+2) || (c == a + 2 && d == b-2) || (c == a +2 && d == b+2)) {
                    //是否过河
                    if(c<=4){return false;}
                    //是否堵象眼
                    if(c == a-2 && d == b - 2){
                        if (chessBoard[a-1][b-1] > 0) {return false;}
                    }
                    if(c == a-2 && d == b + 2){
                        if (chessBoard[a-1][b+1] > 0) {return false;}
                    }
                    if(c == a+2 && d == b - 2){
                        if (chessBoard[a+1][b-1] > 0) {return false;}
                    }
                    if(c == a+2 && d == b + 2){
                        if (chessBoard[a+1][b+1] > 0) {return false;}
                    }
                    return true;
                } else {
                    return false;
                }
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
                if (c <= 6) {return false;}
                if (d <= 2) {return false;}
                if (d >= 6) {return false;}
                //一次只能走一格 不能走横线或竖线
                if ((c == a-1 && d == b+1) || (c == a-1 && d == b-1) || (c == a+1 && d == b+1) || (c == a+1 && d == b-1)) {
                    return true;
                } else {
                    return false;
                }
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
                if (d <= 2) {return false;}
                if (d >= 6) {return false;}
                //如果将军面对面，将ifKingFace变为true
                if (c <= 6) {
                    if (b == d & a-1 > c) {
                        for (int i = a - 1; i > c; i--) {
                            if (chessBoard[i][b] > 0) {
                                return false;
                            }
                        }
                        if(chessBoard[c][d]==5){
                            ifKingFace=true;
                            return true;
                        }
                    }
                    return false;
                }
                //一次只能走一格 不能走斜线
                if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a + 1 && d == b) || (c == a - 1 && d == b)) {
                    return true;
                }
                return false;
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
                //不能走斜线
                if(c!=a&d!=b){return false;}
                int paoFindTwo = 0;
                //炮往左走
                if (a == c & b > d) {
                    for (int i = b - 1; i > d; i--) {
                        if (chessBoard[a][i] > 0) {
                            paoFindTwo++;
                        }
                    }
                }
                //炮往右走
                if (a == c & b < d) {
                    for (int i = b + 1; i < d; i++) {
                        if (chessBoard[a][i] > 0) {
                            paoFindTwo++;
                        }
                    }
                }
                //炮往上走
                if (b == d & a > c) {
                    for (int i = a - 1; i > c; i--) {
                        if (chessBoard[i][b] > 0) {
                            paoFindTwo++;
                        }
                    }
                }
                //炮往下走
                if (b == d & a < c) {
                    for (int i = a + 1; i < c; i++) {
                        if (chessBoard[i][b] > 0) {
                            paoFindTwo++;
                        }
                    }
                }
                if(paoFindTwo==1&chessBoard[c][d]>0){return true;}
                if(paoFindTwo==1&chessBoard[c][d]==0){return false;}
                if(paoFindTwo==0&chessBoard[c][d]>0){return false;}
                if(paoFindTwo==0&chessBoard[c][d]==0){return true;}
                if(paoFindTwo>1){return false;}
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
                //小卒过河后只能往前，左，右走 一次只能走一格 不能走斜线
                if (c < 5) {
                    if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a - 1 && d == b)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                //小卒未过河只能往前走 一次只能走一格 不能走斜线
                if (c >= 5) {
                    if (c == a - 1 && d == b) {
                        return true;
                    } else {
                        return false;
                    }
                }
            default:
                break;
        }
        return true;
    }
    /** 功能：得到某棋子的所有可合法移动的坐标，并保存进Hashmap listMove里 */
    private void getMoveRoute(int a, int b) {listMove.clear();
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[0].length; col++) {
                if (isAbleToMove(a, b, row, col)) {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("row", row);
                    map.put("col", col);
                    listMove.add(map);
                }
            }
        }}
    /** 功能：检查是否将军 */
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
            }
        }
        listMove.clear();
        return true;
    }
    /** 功能：添加棋盘 */
    private void addBackGroundImage(){
        ChessBoard = new GImage("images/MAIN.GIF");
        ChessBoard.setSize(600,670);
        add(ChessBoard);
    }
    /** 功能：添加棋子 */
    private void addImage(){
        int width = chessBoard[0].length;
        int height = chessBoard.length;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if(chessBoard[row][col]>0) {
                    GImage chess = new GImage("images/" + chessBoard[row][col] + ".GIF");
                    chess.setSize(50, 50);
                    add(chess, tansformX(col), tansformY(row));
                }
            }
        }
    }
    /** 功能：将棋子的位置转化为坐标 */
    private int tansformX(int b){
        switch (b) {
            case 0:
                return 25;
            case 1:
                return 88;
            case 2:
                return 150;
            case 3:
                return 213;
            case 4:
                return 276;
            case 5:
                return 339;
            case 6:
                return 402;
            case 7:
                return 465;
            case 8:
                return 525;
            default :
            return 100;
        }
    }
    private int tansformY(int a){
        switch (a) {
            case 0:
                return 28;
            case 1:
                return 90;
            case 2:
                return 152;
            case 3:
                return 214;
            case 4:
                return 276;
            case 5:
                return 338;
            case 6:
                return 400;
            case 7:
                return 462;
            case 8:
                return 524;
            case 9:
                return 586;
            default :
                return 100;
        }
    }
    /** 功能：将棋子的坐标转化为位置*/
    private int tansformB(int X){
        if (X<= 73 & X >= 28 ) {
            return 0;
        }
        else if (X<= 135 & X >= 90 ) {
            return 1;
        }
        else if (X<= 195 & X >= 150 ) {
            return 2;
        }
        else if (X<= 260 & X >= 215 ) {
            return 3;
        }
        else if (X<= 322 & X >= 277 ) {
            return 4;
        }
        else if (X<= 385 & X >= 340 ) {
            return 5;
        }
        else if (X<= 447 & X >= 402 ) {
            return 6;
        }
        else if (X<= 513 & X >= 468 ) {
            return 7;
        }
        else if (X<= 573 & X >= 528 ) {
            return 8;
        }else {      return 10;}
    }
    private int tansformA(int Y){
        if (Y<= 75 & Y >= 30 ) {
            return 0;
        }
        else if (Y<= 137 & Y >= 92 ) {
            return 1;
        }
        else if (Y<= 198 & Y >= 153 ) {
            return 2;
        }
        else if (Y<= 260 & Y >= 215 ) {
            return 3;
        }
        else if (Y<= 321 & Y >= 279 ) {
            return 4;
        }
        else if (Y<= 385 & Y >= 340 ) {
            return 5;
        }
        else if (Y<= 445 & Y >= 401 ) {
            return 6;
        }
        else if (Y<= 510 & Y >= 465 ) {
            return 7;
        }
        else if (Y<= 570 & Y >= 525 ) {
            return 8;
        }
        else if (Y<= 634 & Y >= 589 ) {
            return 9;
        } else{return 10;}
    }
    /** 功能：初始化*/
    @Override
    public void init() {
        setTitle("中国象棋");
        this.setResizable(false);
        add(nameLabel, SOUTH);
        TextField.setEditable(true);
        TextField.setActionCommand("text");
        add(TextField, SOUTH);
        add(save,SOUTH);
        add(read,SOUTH);
        add(auto,SOUTH);
        TextField.addActionListener(this);
        save.addActionListener(this);
        read.addActionListener(this);
        auto.addActionListener(this);
        addMouseListeners();
        setVisible(true);
    }
    /** 功能：开始游戏 */
    @Override
    public void run() {
        addBackGroundImage();
        addImage();
        nameLabel.setText("点击屏幕开始游戏");
//        测试Y轴坐标
//        add(new GLine(0,30,500,30));
//        add(new GLine(0,75,500,75));
//        add(new GLine(0,92,500,92));
//        add(new GLine(0,137,500,137));
//        add(new GLine(0,153,500,153));
//        add(new GLine(0,198,500,198));
//        add(new GLine(0,215,500,215));
//        add(new GLine(0,260,500,260));
//        add(new GLine(0,279,500,279));
//        add(new GLine(0,321,500,321));
//        add(new GLine(0,340,500,340));
//        add(new GLine(0,385,500,385));
//        add(new GLine(0,401,500,401));
//        add(new GLine(0,445,500,445));
//        add(new GLine(0,465,500,465));
//        add(new GLine(0,510,500,510));
//        add(new GLine(0,525,500,525));
//        add(new GLine(0,570,500,570));
//        add(new GLine(0,589,500,589));
//        add(new GLine(0,634,500,634));
//        测试X轴坐标
//        add(new GLine(28,0,28,1000));
//        add(new GLine(73,0,73,1000));
//        add(new GLine(90,0,90,1000));
//        add(new GLine(135,0,135,1000));
//        add(new GLine(150,0,150,1000));
//        add(new GLine(195,0,195,1000));
//        add(new GLine(215,0,215,1000));
//        add(new GLine(260,0,260,1000));
//        add(new GLine(277,0,277,1000));
//        add(new GLine(322,0,322,1000));
//        add(new GLine(340,0,340,1000));
//        add(new GLine(385,0,385,1000));
//        add(new GLine(402,0,402,1000));
//        add(new GLine(447,0,447,1000));
//        add(new GLine(468,0,468,1000));
//        add(new GLine(513,0,513,1000));
//        add(new GLine(528,0,528,1000));
//        add(new GLine(573,0,573,1000));
        waitForClick();
            while (true) {
                if (ifRedMove) {
                    nameLabel.setText("请红方选子");
                    waitForClick();
                    AftA = CurrA;
                    AftB = CurrB;
                    while (AftA == 10 | AftB == 10) {
                        nameLabel.setText("未选中：请红方选子:错误选子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftA = CurrA;
                        AftB = CurrB;
                    }
                    while (chessBoard[AftA][AftB] == 0 | chessBoard[AftA][AftB] == 1 | chessBoard[AftA][AftB] == 2 | chessBoard[AftA][AftB] == 3 | chessBoard[AftA][AftB] == 4 | chessBoard[AftA][AftB] == 5 | chessBoard[AftA][AftB] == 6 | chessBoard[AftA][AftB] == 7) {
                        nameLabel.setText("未选中：请红方选子:错误选子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftA = CurrA;
                        AftB = CurrB;
                        while (AftA == 10 | AftB == 10) {
                            nameLabel.setText("未选中：请红方选子:错误选子");
                            gamestart = new Play("src/music/fail.mp3");
                            gamestart.start();
                            waitForClick();
                            AftA = CurrA;
                            AftB = CurrB;
                        }
                    }
                    nameLabel.setText("已选中:请红方落子:请落子");
                    gamestart = new Play("src/music/check.mp3");
                    gamestart.start();
                    waitForClick();
                    AftC = CurrA;
                    AftD = CurrB;
                    while (AftC == 10 | AftD == 10) {
                        nameLabel.setText("已选中:请红方落子:没选到棋子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftC = CurrA;
                        AftD = CurrB;
                    }
                    while (!isAbleToMove(AftA, AftB, AftC, AftD)) {
                        nameLabel.setText("已选中:请红方落子:错误落子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftC = CurrA;
                        AftD = CurrB;
                        while (AftC == 10 | AftD == 10) {
                            nameLabel.setText("已选中:请红方落子:没选到棋子");
                            gamestart = new Play("src/music/fail.mp3");
                            gamestart.start();
                            waitForClick();
                            AftC = CurrA;
                            AftD = CurrB;
                        }
                    }
                        if (chessBoard[AftC][AftD] == 5) {
                            JOptionPane.showMessageDialog(null, "红方胜");
                            nameLabel.setText("红方胜");
                            break;
                        } else{
                            playerMove(AftA, AftB, AftC, AftD);
                            nameLabel.setText("已选中:请红方落子:落子成功");
                        }
                        if (check()) {
                            ifRedMove = false;
                        } else {
                            gamestart = new Play("src/music/jiangjun.mp3");
                            gamestart.start();
                            JOptionPane.showMessageDialog(null, "将军");
                            ifRedMove = false;
                        }
                    clear();
                    addBackGroundImage();
                    addImage();
                    if(ifKingFace){
                        JOptionPane.showMessageDialog(null, "黑方胜");
                        nameLabel.setText("黑方胜");
                        break;
                    }
                } else {
                    nameLabel.setText("请黑方选子");
                    waitForClick();
                    AftA = CurrA;
                    AftB = CurrB;
                    while (AftA == 10 | AftB == 10) {
                        nameLabel.setText("未选中:请黑方选子:错误选子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftA = CurrA;
                        AftB = CurrB;
                    }
                    while (chessBoard[AftA][AftB] == 0 | chessBoard[AftA][AftB] == 8 | chessBoard[AftA][AftB] == 9 | chessBoard[AftA][AftB] == 10 | chessBoard[AftA][AftB] == 11 | chessBoard[AftA][AftB] == 12 | chessBoard[AftA][AftB] == 13 | chessBoard[AftA][AftB] == 14) {
                        nameLabel.setText("未选中:请黑方选子:错误选子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftA = CurrA;
                        AftB = CurrB;
                        while (AftA == 10 | AftB == 10) {
                            nameLabel.setText("未选中:请黑方选子:错误选子");
                            gamestart = new Play("src/music/fail.mp3");
                            gamestart.start();
                            waitForClick();
                            AftA = CurrA;
                            AftB = CurrB;
                        }
                    }
                    nameLabel.setText("已选中：请黑方落子:请落子");
                    gamestart = new Play("src/music/check.mp3");
                    gamestart.start();
                    waitForClick();
                    AftC = CurrA;
                    AftD = CurrB;
                    while (AftC == 10 | AftD == 10) {
                        nameLabel.setText("已选中：请黑方落子:没选到棋子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftC = CurrA;
                        AftD = CurrB;
                    }
                    while (!isAbleToMove(AftA, AftB, AftC, AftD)) {
                        nameLabel.setText("已选中：请黑方落子:错误落子");
                        gamestart = new Play("src/music/fail.mp3");
                        gamestart.start();
                        waitForClick();
                        AftC = CurrA;
                        AftD = CurrB;
                        while (AftC == 10 | AftD == 10) {
                            nameLabel.setText("已选中：请黑方落子:没选到棋子");
                            gamestart = new Play("src/music/fail.mp3");
                            gamestart.start();
                            waitForClick();
                            AftC = CurrA;
                            AftD = CurrB;
                        }
                    }
                        if (chessBoard[AftC][AftD] == 12) {
                            JOptionPane.showMessageDialog(null, "黑方胜");
                            nameLabel.setText("黑方胜");
                            break;
                        } else {
                            playerMove(AftA, AftB, AftC, AftD);
                            nameLabel.setText("已选中：请黑方落子:落子成功");}
                        if (check()) {
                            ifRedMove = true;
                        } else {
                            gamestart = new Play("src/music/jiangjun.mp3");
                            gamestart.start();
                            JOptionPane.showMessageDialog(null, "将军");
                            ifRedMove = true;
                        }
                    clear();
                    addBackGroundImage();
                    addImage();
                    if(ifKingFace){
                        JOptionPane.showMessageDialog(null, "红方胜");
                        nameLabel.setText("红方胜");
                        break;
                    }
                }
            }
    }
    /** 功能：对按钮进行反馈 */
    @Override
    public void actionPerformed(ActionEvent event) {
        /** 功能：输入文本移动棋子 */
        if ("text".equals(event.getActionCommand())) {
            if (ifRedMove) {
                realRedGameMove(TextField.getText());
                clear();
                addBackGroundImage();
                addImage();
            }else {
                realBlackGameMove(TextField.getText());
                clear();
                addBackGroundImage();
                addImage();
            }
        }
        /** 功能：保存当前棋谱到D盘根目录 */
        if ("save".equals(event.getActionCommand())) {
            saveAsFileWriter();
        }
        /** 功能：读取棋谱 */
        if ("read".equals(event.getActionCommand())) {
            readAsFileWriter();
        }
        /** 功能：读取Actions*/
        if ("auto".equals(event.getActionCommand())) {
            readActions();
        }
    }
    /** 功能：移动红棋 */
    private void realRedGameMove(String inputName) {
            int actiona = 0;
            int actionb = 0;
            int actionc = 0;
            int actiond = 0;
            int actionToCharArray1If = 0;
            char[] actionToCharArray = inputName.toCharArray();
            String actionToCharArray1 = new String(actionToCharArray, 0, 1);
            String actionToCharArray2 = new String(actionToCharArray, 1, 1);
            String actionToCharArray3 = new String(actionToCharArray, 2, 1);
            String actionToCharArray4 = new String(actionToCharArray, 3, 1);
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
                    } }else if (actionToCharArray1If == 9) {
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
                            } else if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona - 1;
                            } else{
                                actionc = 10;
                            }
                            break;
                        case "退":
                            if (Math.abs(actiond - actionb) == 1) {
                                actionc = actiona + 2;
                            } else if (Math.abs(actiond - actionb) == 2) {
                                actionc = actiona + 1;
                            } else{
                                actionc = 10;
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

                if (isAbleToMove(actiona, actionb, actionc, actiond)) {
                    playerMove(actiona, actionb, actionc, actiond);
                    if (chessBoard[actionc][actiond] == 5) {
                        JOptionPane.showMessageDialog(null,"红方胜");
                        ifRedMove=false;
                    }
                    if (check()) {
                        ifRedMove=false;
                    } else {
                        gamestart = new Play("src/music/jiangjun.mp3");
                        gamestart.start();
                        JOptionPane.showMessageDialog(null,"将军");
                        ifRedMove=false;
                    }
                } else {
                    gamestart = new Play("src/music/fail.mp3");
                    gamestart.start();
                    JOptionPane.showMessageDialog(null,"走法有误");
                    ifRedMove=true;
                }
        if(ifKingFace){
            JOptionPane.showMessageDialog(null, "黑方胜");
            nameLabel.setText("黑方胜");
        }
            }
    /** 功能：移动黑棋 */
    private void realBlackGameMove(String inputName) {
        int actiona = 0;
        int actionb = 0;
        int actionc = 0;
        int actiond = 0;
        int actionToCharArray1If = 0;
        char[] actionToCharArray = inputName.toCharArray();
        String actionToCharArray1 = new String(actionToCharArray, 0, 1);
        String actionToCharArray2 = new String(actionToCharArray, 1, 1);
        String actionToCharArray3 = new String(actionToCharArray, 2, 1);
        String actionToCharArray4 = new String(actionToCharArray, 3, 1);

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
            if (isAbleToMove(actiona, actionb, actionc, actiond)) {
                playerMove(actiona, actionb, actionc, actiond);
                if (chessBoard[actionc][actiond] == 12) {
                    JOptionPane.showMessageDialog(null,"黑方胜");
                    ifRedMove=true;
                }
                if (check()) {
                    ifRedMove=true;
                } else {
                    gamestart = new Play("src/music/jiangjun.mp3");
                    gamestart.start();
                    JOptionPane.showMessageDialog(null,"将军");
                    ifRedMove=true;
                }
            } else {
                gamestart = new Play("src/music/fail.mp3");
                gamestart.start();
                JOptionPane.showMessageDialog(null,"走法有误");
                ifRedMove=false;
            }
        if(ifKingFace){
            JOptionPane.showMessageDialog(null, "红方胜");
            nameLabel.setText("红方胜");
        }
    }
    /** 功能：获取鼠标的坐标 */
    @Override
    public void mouseMoved(MouseEvent e) {
        CurrB =tansformB(e.getX());
        CurrA =tansformA(e.getY());
    }
    /** 功能：将当前棋谱保存到D盘 */
    private void saveAsFileWriter() {
        int random = randomGenerator.nextInt(1, 999);
        String xml = "";
        int width = chessBoard[0].length;    // 心形的宽度
        int height = chessBoard.length;      // 心形的高度
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                xml+=chessBoard[row][col]+" ";
            }
        }
        if (ifRedMove) {
            xml+=0;
        } else {
            xml+=1;
        }
        try {
            File file=new File("D:\\"+random+"save.txt");
            file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        try {
            FileWriter write=new FileWriter("D:\\"+random+"save.txt");
            BufferedWriter bw=new BufferedWriter(write);
            bw.write(xml);
            bw.close();
            write.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /** 功能：读取文件获取本地棋谱 */
    private void readAsFileWriter() {
        File readFileName = JFileChooserDemo.getFileName();
        Scanner scanner = null;
        try {
            scanner = new Scanner(readFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int width = chessBoard[0].length;    // 心形的宽度
        int height = chessBoard.length;      // 心形的高度
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                chessBoard[row][col]=scanner.nextInt();

            }
        }
        if(scanner.nextInt()==0){
            ifRedMove=true;
        } else {ifRedMove=false;}
        clear();
        addBackGroundImage();
        addImage();
    }
}
