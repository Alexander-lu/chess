package cn.adalab.chess;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
public class Game extends GraphicsProgram  implements Backgroundmusic{
    /* 初始化音乐 */
    public Play gamestart;
    /* 生成随机数 */
    RandomGenerator randomGenerator = RandomGenerator.getInstance();
    /* 判断红棋还是黑棋先走 */
    boolean ifRedMove= true;
    /* 初始化棋盘 */
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
    /* CurrA和CurrB分别代表现在鼠标的Y和X */
    int CurrA;
    int CurrB;
    int AftA;
    int AftB;
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
    /**
     * 功能：移动棋子
     */
    public void playerMove(int a, int b, int c, int d) {
        chessBoard[c][d] = chessBoard[a][b];
        chessBoard[a][b] = 0;
        gamestart = new Play("src/music/move.mp3");
        gamestart.start();
        }
    /**
     * 功能：读取文件获取Actions
     */
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
    /**
     * 功能：读取文件跑游戏
     */
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
    /**
     * 功能：判断从(a,b)能否移动到（c,d）
     */
    public boolean isAbleToMove(int a, int b, int c, int d) {
        if (a == c & b == d) {
            return false;
        }
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
                if(c!=a&d!=b){return false;}
                //竖走
                //判断中间是否有棋子
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
                //一次只能走一格
                //不能走横线或竖线
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
                if (c >= 3) {return false;}
                if (d <= 2) {return false;}
                if (d >= 6) {return false;}
                //一次只能走一格
                //不能走斜线
                if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a + 1 && d == b) || (c == a - 1 && d == b)) {
                    return true;
                } else {
                    return false;
                }

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
                //竖走
                if(c!=a&d!=b){return false;}
                //不能走斜线
                //判断中间是否有棋子
                int paoFindOne = 0;
                //车往左走
                if (a == c & b > d) {
                    for (int i = b - 1; i > d; i--) {
                        if (chessBoard[a][i] > 0) {
                            paoFindOne++;
                        }
                    }
                }
                //车往右走
                if (a == c & b < d) {
                    for (int i = b + 1; i < d; i++) {
                        if (chessBoard[a][i] > 0) {
                            paoFindOne++;
                        }
                    }
                }
                //车往上走
                if (b == d & a > c) {
                    for (int i = a - 1; i > c; i--) {
                        if (chessBoard[i][b] > 0) {
                            paoFindOne++;
                        }
                    }
                }
                //车往下走
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
                //不能出九宫
                //小卒过河不回头
                if (c >= 5) {
                    //一次只能走一格
                    //不能走斜线
                    if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a + 1 && d == b)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                //不许向后退
                //只能往前走
                if (c < 5) {
                    //一次只能走一格
                    //不能走斜线
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
                //竖走
                if(c!=a&d!=b){return false;}
                //判断中间是否有棋子
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
                //一次只能走一格
                //不能走横线或竖线
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
                if (c <= 6) {return false;}
                if (d <= 2) {return false;}
                if (d >= 6) {return false;}
                //一次只能走一格
                //不能走斜线
                if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a + 1 && d == b) || (c == a - 1 && d == b)) {
                    return true;
                } else {
                    return false;
                }
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
                //竖走
                //不能走斜线
                if(c!=a&d!=b){return false;}
                //判断中间是否有棋子
                int paoFindTwo = 0;
                //车往左走
                if (a == c & b > d) {
                    for (int i = b - 1; i > d; i--) {
                        if (chessBoard[a][i] > 0) {
                            paoFindTwo++;
                        }
                    }
                }
                //车往右走
                if (a == c & b < d) {
                    for (int i = b + 1; i < d; i++) {
                        if (chessBoard[a][i] > 0) {
                            paoFindTwo++;
                        }
                    }
                }
                //车往上走
                if (b == d & a > c) {
                    for (int i = a - 1; i > c; i--) {
                        if (chessBoard[i][b] > 0) {
                            paoFindTwo++;
                        }
                    }
                }
                //车往下走
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
                //不能出九宫
                //小卒过河不回头
                if (c < 5) {
                    //一次只能走一格
                    //不能走斜线
                    if ((c == a && d == b + 1) || (c == a && d == b - 1) || (c == a - 1 && d == b)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                //不许向后退
                //只能往前走
                if (c >= 5) {
                    //一次只能走一格
                    //不能走斜线
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
    /**
     * 功能：得到某棋子的可移动路线图
     */
    private void getMoveRoute(int a, int b) {listMove.clear();
        //懒得分类挑，反正电脑计算快
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
    /**
     * 功能：检查是否将军
     */
    private boolean checkRed() {
        //全体循环，不知道将哪头的军
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[0].length; col++) {
                getMoveRoute(row, col);
                for (Map<String, Integer> map : listMove) {
                        if ((chessBoard[map.get("row")][map.get("col")]) == 5) {
                            return false;
                        }
                }
            }
        }
        listMove.clear();
        return true;
    }
    private boolean checkBlack() {
        //全体循环，不知道将哪头的军
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[0].length; col++) {
                getMoveRoute(row, col);
                for (Map<String, Integer> map : listMove) {
                        if ((chessBoard[map.get("row")][map.get("col")]) == 12) {
                            return false;
                    }

                }

            }
        }
        listMove.clear();
        return true;
    }
    private void addBackGroundImage(){
        ChessBoard = new GImage("images/MAIN.GIF");
        ChessBoard.setSize(600,670);
        add(ChessBoard);
    }
    private void addImage(){
        int width = chessBoard[0].length;    // 心形的宽度
        int height = chessBoard.length;      // 心形的高度
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
    private int tansformB(int X){
        if (X<= 70 & X >= 40 ) {
            return 0;
        }
        else if (X<= 132 & X >= 102 ) {
            return 1;
        }

        else if (X<= 193 & X >= 163 ) {
            return 2;
        }
        else if (X<= 255 & X >= 225 ) {
            return 3;
        }
        else if (X<= 316 & X >= 286 ) {
            return 4;
        }
        else if (X<= 377 & X >= 347 ) {
            return 5;
        }
        else if (X<= 439 & X >= 409 ) {
            return 6;
        }
        else if (X<= 500 & X >= 470 ) {
            return 7;
        }
        else if (X<= 562 & X >= 532 ) {
            return 8;
        }else {      return 10;}

    }
    private int tansformA(int Y){
        if (Y<= 68 & Y >= 38 ) {
            return 0;
        }
        else if (Y<= 130 & Y >= 100 ) {
            return 1;
        }

        else if (Y<= 192 & Y >= 162 ) {
            return 2;
        }
        else if (Y<= 254 & Y >= 224 ) {
            return 3;
        }
        else if (Y<= 319 & Y >= 289 ) {
            return 4;
        }
        else if (Y<= 381 & Y >= 351 ) {
            return 5;
        }
        else if (Y<= 442 & Y >= 412 ) {
            return 6;
        }
        else if (Y<= 506 & Y >= 476 ) {
            return 7;
        }
        else if (Y<= 566 & Y >= 536 ) {
            return 8;
        }
        else if (Y<= 626 & Y >= 596 ) {
            return 9;
        } else{return 10;}
    }
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
    /**
     * 功能：开始游戏
     */
    @Override
    public void run() {
        addBackGroundImage();
        addImage();
        nameLabel.setText("点击屏幕开始游戏");
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
                        if (checkRed()) {
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
                        if (checkBlack()) {
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
                }
            }
    }
    /**
     * 功能：读取，保存的JButton
     */
    @Override
    public void actionPerformed(ActionEvent event) {
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
        if ("save".equals(event.getActionCommand())) {
            saveAsFileWriter();
        }
        if ("read".equals(event.getActionCommand())) {
            readAsFileWriter();
        }
        if ("auto".equals(event.getActionCommand())) {
            readActions();
        }
    }
    /**
     * 功能：移动黑棋
     */
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
                    if (chessBoard[actionc][actiond] == 5) {
                        JOptionPane.showMessageDialog(null,"红方胜");
                        ifRedMove=false;
                    }
                    playerMove(actiona, actionb, actionc, actiond);
                    if (checkRed()) {
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
            }
    /**
     * 功能：移动黑棋
     */
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
                if (chessBoard[actionc][actiond] == 12) {
                    JOptionPane.showMessageDialog(null,"黑方胜");
                    ifRedMove=true;
                }
                playerMove(actiona, actionb, actionc, actiond);
                if (checkBlack()) {
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

    }
    /**
     * 功能：获取鼠标的坐标
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        CurrB =tansformB(e.getX());
        CurrA =tansformA(e.getY());
    }
    /**
     * 功能：将当前棋谱保存到D盘
     */
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
    /**
     * 功能：读取文件获取本地棋谱
     */
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
