package main.mesh;

import java.awt.DisplayMode;
import java.util.Random;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

import main.parameters.Params;


public class MeshPanel extends GLJPanel implements GLEventListener {

   public static DisplayMode dm, dm_old;
   private GLU glu = new GLU();

   

	public MeshPanel() {
		MeshGenerator.meshInitialization();
	}
      
   @Override
   public void display( GLAutoDrawable drawable ) {

	     final GL2 gl = drawable.getGL().getGL2();
	     MeshGenerator.meshReinitialization(gl);
	     MeshGenerator.openGLSetUp(gl);
	     MeshGenerator.rotateAndTransformMesh(gl);
	     MeshGenerator.generateMesh(gl);
	      
	      
	    }
   
   @Override
   public void dispose( GLAutoDrawable drawable ) {
    
   }
   
   @Override
   public void init( GLAutoDrawable drawable ) {
	
   }
      
   @Override
   public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {
	 
	   GL2 gl = drawable.getGL().getGL2();
	      
	      if( height <= 0 )
	         height = 1;
				
	      final float h = ( float ) width / ( float ) height;
	      gl.glViewport( 0, 0, width, height );
	      gl.glMatrixMode( GL2.GL_PROJECTION );
	      gl.glLoadIdentity();
	  
	      glu.gluPerspective( 95.0f, h, 1.0, 5000.0 );
	      gl.glMatrixMode( GL2.GL_MODELVIEW );
	      gl.glLoadIdentity();
	      
   }
  

}