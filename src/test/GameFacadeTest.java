package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.GameBuilder;

import domain.BrickBreaker;
import domain.Chronometer;
import domain.DomainException;
import domain.GameFacadeImpl;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class GameFacadeTest{

	public Chronometer chronometer;
	public BrickBreaker game;
	public GameFacadeImpl facade;
	public GameBuilder gameBuilder;
	
	@Before
	public void setup() throws Exception{
		chronometer = mock(Chronometer.class);
		game = mock(BrickBreaker.class);
		gameBuilder = mock(GameBuilder.class);
		when(gameBuilder.createGame()).thenReturn(game);
		facade = new GameFacadeImpl(chronometer,game);
	}
	public void test_right_Game_is_asked_to_move_paddle_right_if_game_is_started() throws DomainException{

	}
	public void test_left_Game_is_asked_to_move_paddle_left_if_game_is_started(){
		
	}
	public void test_resume_Chronometer_and_game_are_resumed() throws DomainException{
		chronometer.resume();
		assertEquals(facade.getStatus(),"RESUMED");
		assertEquals(game.getStatus(),"RESUMED");
		assertEquals(chronometer.getStatus(), facade.getStatus());
	}
	public void test_getTime_Time_is_asked_from_chronometer(){
		assertEquals(facade.getTime(),chronometer.getTime());
	}
	public void test_start_Chronometer_and_game_are_started() throws DomainException{
		chronometer.start();
		assertEquals(facade.getStatus(),"STARTED");
		assertEquals(game.getStatus(),"STARTED");
		assertEquals(facade.getStatus(),chronometer.getStatus());
	}
	public void test_getRoundElements_All_round_elements_are_returned(){
		assertEquals(facade.getRoundElements(),game.getRoundElements());
	}
	public void test_getStatus_status_is_asked_from_chronometer(){
		assertEquals(facade.getStatus(),chronometer.getStatus());
	}
	public void test_launch_Game_is_launched() throws DomainException, CloneNotSupportedException{
		facade.launch();
		assertEquals(facade.getStatus(),"LAUNCHED");
		assertEquals(game.getStatus(),"LAUNCHED");
		assertEquals(game.isLaunched(),true);
	}
	public void test_getSquareElements_All_square_elements_are_returned(){
		assertEquals(facade.getSquareElements(),game.getSquareElements());
	}
	public void test_stop_Chronometer_and_game_are_stopped() throws DomainException{
		chronometer.stop();
		assertEquals(facade.getStatus(),"STOPPED");
		assertEquals(game.getStatus(),"STOPPED");
		assertEquals(facade.getStatus(),chronometer.getStatus());
	}
	public void test_getNumberOfLives_Number_of_lives_as_asked_from_game(){
		assertEquals(facade.getNumberOfLives(),game.getNumberOfLives());
	}
}
