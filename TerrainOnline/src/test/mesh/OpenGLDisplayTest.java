package test.mesh;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import main.displayWindow.ConfigPanel;
import main.mesh.MeshGenerator;

class OpenGLDisplayTest {
	GLAutoDrawable drawable = null;
	
	@BeforeEach
	public void setUp() {
		
		//MeshGeneraotr openGLDisplay = new MeshGeneraotr(drawable);
		// final GL2 gl = drawable.getGL().getGL2();
	}
	@Test
	void GLAutoDrawableIsNotNulltest() {
		//assertNull(drawable);
	}
	
	@Test
	void reinitiateDisplayTest() {
		//MeshGeneraotr.reinitiateDisplay();
		//assertFalse(ConfigPanel.getGenerating());
	}

}
