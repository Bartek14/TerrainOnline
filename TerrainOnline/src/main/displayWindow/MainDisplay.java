package main.displayWindow;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import main.mesh.MeshPanel;
import main.parameters.Params;
import mouseInput.MouseInput;

public class MainDisplay extends JFrame {
	
	final public static int HEIGHT = 700;
	final public static int WIDTH = 1000;
	
	public MainDisplay() {
	
	}
		public static void main( String[] args ) {

		      final GLProfile profile = GLProfile.get(GLProfile.GL2);
		      GLCapabilities capabilities = new GLCapabilities(profile);
		      final GLCanvas glcanvas = new GLCanvas(capabilities);
		     
		      Params params = new Params();
		      ConfigPanel conf = new ConfigPanel(params);
		      
		       
		      final JFrame frame = new JFrame ("mesh");
		      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		      frame.setLayout(new BorderLayout());
		      frame.setJMenuBar(conf.menuBar);	
		      frame.getContentPane().add(glcanvas);
		      frame.setSize(WIDTH,HEIGHT);
		      frame.add(glcanvas, BorderLayout.CENTER);
		      frame.add(conf,BorderLayout.EAST);
		      frame.setVisible(true);

		      MeshPanel mesh = new MeshPanel();
		      MouseInput mouse = new MouseInput();
		      glcanvas.addGLEventListener(mesh);
		      glcanvas.addMouseWheelListener(mouse);
		      glcanvas.addMouseListener(mouse);
		      
		      FPSAnimator FPSAnimator = new FPSAnimator(glcanvas,30);
		      FPSAnimator.start();
		   }
		   

}