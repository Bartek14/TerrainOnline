package test.mesh;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jogamp.opengl.GLAutoDrawable;

import main.mesh.OpenGLDisplay;

class OpenGLDisplayTest {
	GLAutoDrawable drawable = null;
	
	@BeforeEach
	public void setUp() {
		
		OpenGLDisplay openGLDisplay = new OpenGLDisplay(drawable);
	}
	@Test
	void GLAutoDrawableIsNotNulltest() {
		assertNull(drawable);
	}

}
