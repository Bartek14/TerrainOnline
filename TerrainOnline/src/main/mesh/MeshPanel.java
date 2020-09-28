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
   private float rquad = 0.0f;
   
   private final int scale =15;
   

	public MeshPanel() {
		MeshGenerator.meshInitialization();
	}
      
   @Override
   public void display( GLAutoDrawable drawable ) {

	     final GL2 gl = drawable.getGL().getGL2();
	     MeshGenerator.meshReinitialization(gl);
	     MeshGenerator.openGLSetUp(gl);
	      gl.glTranslatef( 20f, 190f, -320.5f );
	   
	      gl.glRotatef( rquad, 0.0f, 1.0f, 1.0f );
	      gl.glRotatef( -45, 1.0f, 0.0f, 0.0f );
	      gl.glTranslatef( -(Params.getWidth()*scale/2f), (Params.getLength()*scale/2f), -320.5f );
	      
	      for(int y=0; y>-Params.getLength()+1; y--){ 

	    	  gl.glBegin( GL2.GL_TRIANGLE_STRIP);
	    	  
				for (int x = 0; x < Params.getWidth(); x++) {
					gl.glVertex3f(x*scale, y*scale, MeshGenerator.height[x][-y]);
					gl.glVertex3f(x*scale, (y-1)*scale,MeshGenerator.height[x][-y+1]);
				}
				
				gl.glEnd();
				gl.glFlush();
				
	      }
	      gl.glBegin( GL2.GL_TRIANGLE_STRIP);
    	  
			for (int x = 0; x < Params.getWidth(); x++) {
				gl.glVertex3f(x*scale, (-Params.getLength()+1)*scale, MeshGenerator.height[x][Params.getLength()-1]);
				gl.glVertex3f(x*scale, -Params.getLength()*scale, MeshGenerator.height[x][Params.getLength()-1]);
			}
			
			gl.glEnd();
			gl.glFlush();
	      
	      rquad+=0.5f;
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