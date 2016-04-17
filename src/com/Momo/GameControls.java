package com.Momo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class   GameControls implements KeyListener {
	private Snake snake;
	// cite
		GameControls(Snake snake){
			this.snake = snake;
		}

	public void keyPressed(KeyEvent ev) {
		//keyPressed events are for catching events like function keys, enter, arrow keys
		//We want to listen for arrow keys to move snake
		//Has to id if user pressed arrow key, and if so, send info to Snake object

		//is game running? No? tell the game to draw grid, start, etc.

		//Get the component which generated this event
		//Hopefully, a DrawSnakeGamePanel object.

		DrawSnakeGamePanel panel = (DrawSnakeGamePanel) ev.getComponent();

		if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME) {

			if (ev.getKeyCode() == KeyEvent.VK_W) {
				SnakeGame.isWarp();
			}
			if (ev.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
				SnakeGame.challenge();
			}
			//The default game speed
			if (ev.getKeyCode() == KeyEvent.VK_1) {
				SnakeGame.setGameSpeed(SnakeGame.SPEED_1);
			}
			//Press 4 if you would like to increase the game speed to 3
			if (ev.getKeyCode() == KeyEvent.VK_2) {
				SnakeGame.setGameSpeed(SnakeGame.SPEED_2);
			}
			//Press 4 if you would like to increase the game speed to 4
			if (ev.getKeyCode() == KeyEvent.VK_3) {
				SnakeGame.setGameSpeed(SnakeGame.SPEED_3);
			}
			//Press 4 if you would like to increase the game speed to 4
			if (ev.getKeyCode() == KeyEvent.VK_4) {
				SnakeGame.setGameSpeed(SnakeGame.SPEED_4);
			}
			//Press five if you would want to increase your speed from the display option
			if (ev.getKeyCode() == KeyEvent.VK_5) {
				SnakeGame.setGameSpeed(SnakeGame.SPEED_5);

			}
			panel.repaint();
			//Press A to begin the game
			if (ev.getKeyCode() == KeyEvent.VK_A) {
				//if you press space, let the game starts
				SnakeGame.setGameStage(SnakeGame.DURING_GAME);
				SnakeGame.newGame();
				panel.repaint();
				return;
			}

		}

		if (SnakeGame.getGameStage() == SnakeGame.GAME_OVER){
			snake.reset();
			Score.resetScore();

			//If the above condition is true, reset score and start new game along with the timer
			SnakeGame.newGame();
			SnakeGame.setGameStage(SnakeGame.DURING_GAME);
			panel.repaint();
			return;
		}
		if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
			//snake moves down
			snake.snakeDown();
		}
		if (ev.getKeyCode() == KeyEvent.VK_UP) {
			//snake moves up
			snake.snakeUp();
		}
		if (ev.getKeyCode() == KeyEvent.VK_LEFT) {
			//snake turned left
			snake.snakeLeft();
		}
		if (ev.getKeyCode() == KeyEvent.VK_RIGHT) {
			//snake turn right
			snake.snakeRight();
		}
	}




	@Override
		public void keyReleased (KeyEvent ev){
			//We don't care about keyReleased events, but are required to implement this method anyway.
		}


		@Override
		public void keyTyped (KeyEvent ev){
			//keyTyped events are for user typing letters on the keyboard, anything that makes a character display on the screen
			char keyPressed = ev.getKeyChar();
			char q = 'q';
			if (keyPressed == q) {
				System.exit(0);    //quit if user presses the q key.
			}
		}

	}

