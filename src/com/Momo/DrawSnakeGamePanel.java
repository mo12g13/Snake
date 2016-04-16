package com.Momo;

import javafx.scene.layout.Background;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JPanel;

import static java.awt.Font.*;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 * @author Momo
 *
 */
public class DrawSnakeGamePanel extends JPanel {
	
	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint
	
	private Snake snake;
	private Kibble kibble;
	private Score score;
	private ChallengeLevel challenge;
	//The Draw snake constructor that inherits from the JPanel to draw the components on the  Jframe using JPanel
	DrawSnakeGamePanel(GameComponentManager components){
		this.snake = components.getSnake();//Get the snake and display the snake on the JPanel
		this.kibble = components.getKibble();//display the kibble on the Jpanel
		this.score = components.getScore();//display the score on the Jpanel
		this.setBackground(Color.CYAN);//Change the background color of the JPanel to Cyan


	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);




        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();

        
        switch (gameStage) {
			case SnakeGame.BEFORE_GAME: {
				g.setColor(Color.BLUE);//Display the instructions color  to yellow

				displayGameMenu(g);
				displayInstructions(g);

				break;
			}

			case SnakeGame.DURING_GAME: {

				displayGame(g);


				break;
			}
			case SnakeGame.GAME_OVER: {
				g.setColor(Color.RED);//Display the gameover text as read.
				g.setFont(new Font ("Serif", Font.BOLD, 45));
				displayGameOver(g);
				break;
			}
			case SnakeGame.GAME_WON: {
				g.setColor(Color.RED);
				displayGameWon(g);
				break;
        	}
        }
    }

	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!



		g.clearRect(100,100,350,350);
		g.setFont(new Font("Monospaced", Font.BOLD, 30));//Set the snake fond to Monopaced and bold with font size of 30
		g.drawString("YOU WON SNAKE!!!", 150, 150);

		
	}
	private void displayGameOver(Graphics g) {
		Font myFond = new Font(Font.SERIF, Font.BOLD, 23);//Set new Font for After game display


		g.clearRect(100,100,350,350);
		g.setFont(myFond);

		g.drawString("GAME OVER", 150, 150);
		g.setColor(Color.blue);

		
		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		
		g.drawString("SCORE = " + textScore, 150, 250);
		
		g.drawString("HIGH SCORE = " + textHighScore, 150, 300);
		g.drawString(newHighScore, 150, 400);
		
		g.drawString("press a key to play again", 150, 350);
		g.drawString("Press q to quit the game",150,400);		
    			
	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);
	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;
		
		g.clearRect(0, 0, maxX, maxY);




		g.setColor(Color.RED);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){			
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){			
			g.drawLine(x, 0, x, maxY);
		}
	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in green
		g.setColor(Color.GREEN);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillRect(x+1, y+1, SnakeGame.squareSize-2, SnakeGame.squareSize-2);
		
	}


	private void displaySnake(Graphics g) {


		LinkedList<Point> coordinates = snake.segmentsToDraw();
		
		//Draw head in grey
		g.setColor(Color.LIGHT_GRAY);
		Point head = coordinates.pop();
		g.fillRect((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);

		
		//Draw rest of snake in black
		g.setColor(Color.BLACK);
		for (Point p : coordinates) {
			g.fillRect((int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}
	}
	//A method that display the game menu
	private void displayGameMenu(Graphics g) {

		g.setFont(new Font("Time New Roman", Font.BOLD, 16));

		g.drawString("Game Options", 100, 50);


		g.drawString("Press W to turn on Warp Walls", 17, 75);

		String warpWallsEnabled = SnakeGame.wardInUse() ? "ON" : "OFF";
		g.drawString(warpWallsEnabled, 300, 75);

		g.drawString("Press O for challenge", 17, 100);
		String obstaclesEnabled = SnakeGame.  challengInUse() ? "ON" : "OFF";
		g.drawString(obstaclesEnabled, 300, 100);
		g.setFont (new Font("Serif", Font.BOLD, 20));
		g.drawString("Enter 1-5 to change game speed",15, 125);
		long speed = SnakeGame.getGameSpeed();
		String speedSetting;
		if (speed == SnakeGame.SPEED_1) {
			speedSetting = "1 -Slowest";
		} else if (speed == SnakeGame.SPEED_2) {
			speedSetting = "2 - Slow";
		} else if (speed == SnakeGame.SPEED_3) {
			speedSetting = "3 - Medium";
		} else if (speed == SnakeGame.SPEED_4) {
			speedSetting = "4 - Fast";
		} else if (speed == SnakeGame.SPEED_5) {
			speedSetting = "5 - Really Fast";
		} else {
			//this shouldn't happen
			System.err.println("invalid speed in panel");
			speedSetting = "";
		}
		g.drawString(speedSetting, 300, 125);
	}



	private void displayInstructions(Graphics g) {
		g.setFont(new Font("SanSerif", Font.BOLD, 23));//Change the fond of display instructions to bold and fond of sanserif with size of 34
        g.drawString("Press \"A\" key to begin!",17,200);
        g.drawString("Press q to quit the game",17,300);
    	}


}

