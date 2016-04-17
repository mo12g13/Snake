package com.Momo;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Momo Johnson on 4/16/2016.
 */
public class ChallengeLevel extends JPanel {
    private boolean challenge[][];
    private int challengeOverCome;
    public ChallengeLevel() {
        this.challenge = new boolean[SnakeGame.ySquares][SnakeGame.ySquares];
    }

    public void challengeEstablish(Snake s, Kibble k, int count) {
        Random rng = new Random();
        int i = 0;
        while (i < count) {
            // find an unoccupied cell to place the challenge
            int x = rng.nextInt(SnakeGame.xSquares);
            int y = rng.nextInt(SnakeGame.ySquares);
            if (!s.isSnakeSegment(x, y) && !k.iskibbleFound(x, y) && !challenge[y][x]) {
                challenge[y][x] = true;
               challengeOverCome++;
                i++;
            }
        }
    }

    public LinkedList<Point> obstaclesToDraw() {
        LinkedList<Point> points = new LinkedList<Point>();
        for (int i = 0; i < challengeOverCome; i++) {
            for (int y = 0; y < SnakeGame.ySquares; y++) {
                for (int x = 0; x < SnakeGame.xSquares; x++) {
                    if (challenge[y][x]) {
                        Point p = new Point(x * SnakeGame.squareSize, y * SnakeGame.squareSize);
                        points.add(p);
                    }
                }
            }
        }
        return points;
    }

    public void reset() {
        challenge = new boolean[SnakeGame.ySquares][SnakeGame.xSquares];
    }

    public boolean isChallengePosition(int x, int y) {
        return challenge[y][x];
    }

}
