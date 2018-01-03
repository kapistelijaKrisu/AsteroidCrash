package animations;

public abstract class VisualEffect {
	protected final int TIME;
	protected int cycleTime;	

public VisualEffect(int cycleTime) {
	TIME = cycleTime;
	this.cycleTime = cycleTime;

	}

public abstract void update();

public int getCycleProgress() {
	return cycleTime;
}

public void setCycleTime(int lifeLeft) {
	if (TIME >= lifeLeft) {
	this.cycleTime = lifeLeft;
	} else {
		this.cycleTime = TIME;
	}
}

}