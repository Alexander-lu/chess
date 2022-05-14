package cn.adalab.chess;

import acm.graphics.GImage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface Backgroundmusic {
    /**音乐库，复制到对应的场景并调用方法即可
     举例：
     Play gamestart = new Play("res/mp3/gamestart.mp3");
     播放    gamestart.start();
     暂停    gamestart.stop();
     切换歌曲：changeMusic("res/mp3/Pokemon-fight.mp3");
     * */
    class Play implements Runnable {

        private Player player = null;
        private Thread thread = null;
        private String file;

        public Play(String files) {
            try {
                file=files;
                player = new Player(new FileInputStream(file));
            } catch (JavaLayerException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                player.play();
                while (player.isComplete()) {
                    player = new Player(new FileInputStream(file));
                    player.play();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void start() {
            thread = new Thread(this, "Player thread");
            thread.start();
        }

        public void stop() {
            player.close();
            thread = null;
        }

        public Player getPlayer() {
            return player;
        }

    }
}


