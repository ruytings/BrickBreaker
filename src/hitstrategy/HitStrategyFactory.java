package hitstrategy;

public class HitStrategyFactory {

	public HitStrategy create(int lives) {
		switch(lives) {
		case 3: return new BrickDeleter();
		case 2: return new ColorChanger();
		case 1: return new BrickCutter();	
		default: return new BrickDeleter();
		}
	}
}
