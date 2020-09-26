package main.mesh;

import com.jogamp.opengl.GLAutoDrawable;

public class OpenGLDisplay {
	
	 private GLAutoDrawable drawable;

	public OpenGLDisplay(GLAutoDrawable drawable) {
		
		this.drawable = drawable;
	}

	public GLAutoDrawable getDrawable() {
		return drawable;
	}

	public void setDrawable(GLAutoDrawable drawable) {
		this.drawable = drawable;
	}
	 
}
