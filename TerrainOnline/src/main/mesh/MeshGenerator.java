package main.mesh;

import java.util.Random;

import com.jogamp.opengl.GL2;

import main.displayWindow.ConfigPanel;
import main.parameters.Params;
import mouseInput.MouseInput;

public class MeshGenerator {
	public static float[][] height;	
	private static Random heightRandom = new Random();
	private static final int scale =15;
	private static float rotationStep=0f;
	private static float cameraDistance=-320f;
	
	 public MeshGenerator() {}
	 
	 public static void meshReinitialization(GL2 gl) {
			if(ConfigPanel.getGenerating()) {
		   		gl.glFlush();
		   		meshInitialization();
		   		ConfigPanel.setGenerating(false);
		   	}	
		}
		
	public static void meshInitialization() {
			 height= new float[Params.getWidth()][Params.getLength()];
			for (int x = 0; x < Params.getWidth(); x++) {
				for (int y = 0; y < Params.getLength(); y++) {
					height[x][y]=finalHeight(x/Params.getSmoothness(), y/Params.getSmoothness());
				}
			}
	   }
		
	 public static void openGLSetUp(GL2 gl) {
		 gl.glEnable(GL2.GL_CULL_FACE);
	      gl.glCullFace(GL2.GL_BACK);
	      gl.glFrontFace(GL2.GL_CCW); 
	      gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_LINE);
	      gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );
	      gl.glMatrixMode(GL2.GL_MODELVIEW);
	      gl.glLoadIdentity();
	 }
	 public static void generateMesh(GL2 gl) {
	      for(int y=0; y>-Params.getLength()+1; y--){ 
	    	  
	    	  gl.glBegin( GL2.GL_TRIANGLE_STRIP);
				for (int x = 0; x < Params.getWidth(); x++) {
					gl.glVertex3f(x*scale, y*scale, MeshGenerator.height[x][-y]);
					gl.glVertex3f(x*scale, (y-1)*scale,MeshGenerator.height[x][-y+1]);
				}
				gl.glEnd();
				gl.glFlush();
	      }
	 }
	 public static void rotateAndTranslateMesh(GL2 gl) {
		 
		  gl.glRotatef( rotationStep, 0.0f, 1.0f, 1.0f );
	      gl.glRotatef( -45, 1.0f, 0.0f, 0.0f );
	      gl.glTranslatef( 20f, 190f, MouseInput.cameraDistance);
	      gl.glTranslatef( -(Params.getWidth()*scale/2f)-MouseInput.horizontalCameraDrag, (Params.getLength()*scale/2f), -320.5f );
	      rotationStep+=0.5f;
	      
	 }
	
	 private static float finalHeight(float x, float y) {
		   float height=0;
		   
		   for(int i=0; i<Params.getOctaves(); i++) {
			     
			   float frequency = (float) (Math.pow(2,i)/((float) Math.pow(2, Params.getOctaves()-i)));
			   height+=interpolatedHeight(x*frequency, y*frequency)*(Params.getMaxHeight()-Params.getMinHeight())*(float)(Math.pow(Params.getRoughness(), i));
			   
		   }
		   return height;
	   }
	 private static float interpolatedHeight(float x, float y) {

		   float downLeft = randomHeight((int) x, (int) y);
		   float upLeft = randomHeight((int) x, ((int) y)+1);
		   float upRight = randomHeight(((int) x)+1, ((int) y)+1);
		   float downRight = randomHeight(((int) x)+1, (int) y);
		   
		   float bottomInterpolation = cosineFading(downLeft, downRight,  x-((int) x));
		   float topInterpolation = cosineFading(upLeft, upRight,  x-((int) x));
		   
		   float interpolation;
		   
		   if(Params.getLinear()) {
			   interpolation=linearFading(bottomInterpolation, topInterpolation,  y-((int) y));
		   }
		   else {
			   interpolation=cosineFading(bottomInterpolation, topInterpolation,  y-((int) y));
		   }
		   
		   return interpolation;
	   }
	 private static float randomHeight(int x, int y) {
	  	   
		   float height=0;
		   float tempRelativness=Params.getRelativness();
		   height+=((randomValue(x+1,y+1)+randomValue(x+1,y-1)+randomValue(x-1,y+1)+randomValue(x-1,y-1))/tempRelativness);
		   tempRelativness/=2;
		   height+=((randomValue(x,y+1)+randomValue(x,y-1)+randomValue(x-1,y)+randomValue(x+1,y))/tempRelativness);
		   tempRelativness/=2;
		   height+=(randomValue(x,y)/tempRelativness);
				   
		   return height;
		   }
	 private static float randomValue(int x, int y) {
		   heightRandom.setSeed(x*12345+y*4321+Params.getSeed()*54321);
		   return heightRandom.nextFloat()*2-1;
		   
	    }
	   private static float cosineFading(float startInterpolation, float endInterpolation, float factor ) {
		   factor = (float)(1f-Math.cos(factor*Math.PI))/2f;
		   return startInterpolation*(1f-factor) +endInterpolation * factor;
		   
	   }
	   private static float linearFading(float startInterpolation, float endInterpolation, float factor ) {
		   return startInterpolation*(1f-factor) +endInterpolation * factor;
		   
	   }

	 
}
