package view;

public class MovementThread implements Runnable {

	private ViewFrame viewFrame;
	private int key;
	private Boolean canApplyMovement;
	private Boolean hasAppliedMovement;

	public Boolean getHasAppliedMovement() {
		return hasAppliedMovement;
	}

	public void setHasAppliedMovement(Boolean hasAppliedMovement) {
		this.hasAppliedMovement = hasAppliedMovement;
	}

	public Boolean getCanApplyMovement() {
		return canApplyMovement;
	}

	public void setCanApplyMovement(Boolean canApplyMovement) {
		this.canApplyMovement = canApplyMovement;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}

	public MovementThread(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		this.key = -1;
		this.canApplyMovement = false;
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.setHasAppliedMovement(false);
				Thread.sleep(150);
				if(this.canApplyMovement) {
					this.viewFrame.applyOrderPerform(this.getKey());
					this.setHasAppliedMovement(true);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
