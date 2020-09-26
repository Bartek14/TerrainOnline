package main.displayWindow;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class FPSAnimatorThread extends Thread {
	
	private GLCanvas glCanvas;
	private int frames;
	public FPSAnimatorThread(GLCanvas glCanvas, int frames) {
		this.glCanvas = glCanvas;
		this.frames = frames;
	}
	
	public void run() {
		final FPSAnimator animator = new FPSAnimator(glCanvas, frames,true); 
	      animator.start();
	}
	

}
