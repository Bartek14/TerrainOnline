package mouseInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseWheelListener, MouseListener {
	public static float cameraDistance;
	public static float horizontalCameraDrag;
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		cameraDistance-=(e.getWheelRotation()*100);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(2);
	}
	@Override
	public void mousePressed(MouseEvent e) {
	horizontalCameraDrag+=e.getX();
	System.out.println(e.getX());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println(3);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
