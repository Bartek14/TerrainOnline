package main.displayWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import main.databaseManagement.DatabaseEntity;
import main.mesh.MeshGenerator;
import main.mesh.MeshPanel;
import main.parameters.Params;

public class ConfigurationListener implements ActionListener {

	ConfigPanel confPanel;
	public ConfigurationListener(ConfigPanel configPanel) {
		this.confPanel = configPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String optionPicked = e.getActionCommand();

		switch (optionPicked) {
		case "LENGHT":
		case "WIDTH":
		case "MAX":
		case "MIN":
		case "ROUGHNESS":
		case "SEED":
		case "RELATION":
		case "GENERATE": {
			ConfigPanel.setGenerating(true);
			
			 String lengthString = confPanel.lengthTextField().getText(); 
			  try {
				  if(Integer.parseInt(lengthString)!=Params.getLength())
			  Params.setLength(Math.abs(Integer.parseInt(lengthString))+1);
			  } 
			  catch (NumberFormatException ex) {
				  System.out.println("Intercepted exception: " + ex.getClass().getName());
				  System.out.println("Change the 'length' string format.");
				  System.out.println("Previous value preserved. Current length value: " + Params.getLength());
				  ex.printStackTrace();
				  String previousValue = Integer.toString(Params.getLength());
				  confPanel.lengthTextField().setText(previousValue);
			  }
			  
				String widthString = confPanel.widthTextField().getText();
				try {
					if(Integer.parseInt(widthString)!=Params.getWidth())
					 Params.setWidth(Math.abs(Integer.parseInt(widthString))+1);
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'width' string format.");
					System.out.println("Previous value preserved. Current width value: " + Params.getWidth());
					ex.printStackTrace();
					String previousValue = Integer.toString(Params.getWidth());
					confPanel.widthTextField().setText(previousValue);
				}
			  
				String maxHeightString = confPanel.maxHeightTextField().getText();
				try {
					Params.setMaxHeight(Float.parseFloat(maxHeightString));
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'max height' string format.");
					System.out.println("Previous value preserved. Current max height value: " + Params.getMaxHeight());
					ex.printStackTrace();
					String previousValue = Float.toString(Params.getMaxHeight());
					confPanel.maxHeightTextField().setText(previousValue);
				}
				
				String minHeightString = confPanel.minHeightTextField().getText();
				try {
					Params.setMinHeight(Float.parseFloat(minHeightString));
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'min height' string format.");
					System.out.println("Previous value preserved. Current min height value: " + Params.getMinHeight());
					ex.printStackTrace();
					String previousValue = Float.toString(Params.getMinHeight());
					confPanel.minHeightTextField().setText(previousValue);
				}
				
				String roughnessString = confPanel.roughnessTextField().getText();
				try {
					Params.setRoughness(Float.parseFloat(roughnessString));
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'roughness' string format.");
					System.out.println("Previous value preserved. Current roughness value: " + Params.getRoughness());
					ex.printStackTrace();
					String previousValue = Float.toString(Params.getRoughness());
					confPanel.roughnessTextField().setText(previousValue);
				}
				
				String seedString = confPanel.seedTextField().getText();
				try {
					Params.setSeed(Integer.parseInt(seedString));
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'seed' string format.");
					System.out.println("Previous value preserved. Current seed value: " + Params.getSeed());
					ex.printStackTrace();
					String previousValue = Long.toString(Params.getSeed());
					confPanel.seedTextField().setText(previousValue);
				}
				
				String relativnessString = confPanel.relativnessTextField().getText();
				try {
					Params.setRelativness(Float.parseFloat(relativnessString));
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'height relativness of the neighbour points' string format.");
					System.out.println("Previous value preserved. Current height relativness of the neighbour points value: " + Params.getRelativness());
					ex.printStackTrace();
					String previousValue = Float.toString(Params.getRelativness());
					confPanel.relativnessTextField().setText(previousValue);
				}
				
			break;
			
			

		}
		case "LINEAR": {
			Params.setIsLinear(true);
		break;	
		}
		case "TRIGONOMETRIC": {
			Params.setIsLinear(false);
		break;	
		}
		case "DB_SAVE":{
			try {
				
				DatabaseEntity.saveCoordsAndSpecs();
			} catch (SQLException e1) {
				System.out.println("error at db_save");
				e1.printStackTrace();
			}
			break;
		}
		case "DB_LOAD":{
			
			try {
				
				DatabaseEntity.loadCoordsAndSpecs();
			} catch (SQLException e1) {
				System.out.println("error at db_load");
				e1.printStackTrace();
			}
			break;
		}
		case "SAVE": {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Choose file");
			int result = chooser.showDialog(null, "Choose");
			
			if(JFileChooser.APPROVE_OPTION == result) {
				System.out.println("File chosen: " + chooser.getSelectedFile().toPath());
				FileWriter writer = null;
				BufferedWriter bufferedWriter = null;
				String lengthString = confPanel.lengthTextField().getText();
				
				try {
					writer = new FileWriter(chooser.getSelectedFile());
					bufferedWriter = new BufferedWriter(writer);
					  bufferedWriter.write(lengthString);
					  bufferedWriter.newLine();
				} 
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'length' string format.");
					ex.printStackTrace();
				} 
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				
			String widthString = confPanel.widthTextField().getText();
				try {
					  bufferedWriter.write(widthString);
					  bufferedWriter.newLine();
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'width' string format.");
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
			String maxHeightString = confPanel.maxHeightTextField().getText();
				try {
					  bufferedWriter.write(maxHeightString);
					  bufferedWriter.newLine();
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'max height' string format.");
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
			String minHeightString = confPanel.minHeightTextField().getText();
				try {
					  bufferedWriter.write(minHeightString);
					  bufferedWriter.newLine();
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'min height' string format.");
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
			String roughnessString = confPanel.roughnessTextField().getText();
				try {
					  bufferedWriter.write(roughnessString);
					  bufferedWriter.newLine();
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'roughness' string format.");
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
			String seedString = confPanel.seedTextField().getText();
				try {
					  bufferedWriter.write(seedString);
					  bufferedWriter.newLine();
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'seed' string format.");
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
			String relativnessString = confPanel.relativnessTextField().getText();
				try {
					  bufferedWriter.write(relativnessString);
					  bufferedWriter.newLine();
				}
				catch (NumberFormatException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					System.out.println("Change the 'height relativness of the neighbour points' string format.");
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try {
					
					for (int x = 0; x<Params.getWidth()-1; x++) {
						for (int y = 0; y<Params.getLength()-1; y++) {
							String str = Float.toString(MeshGenerator.height[x][y]);
							bufferedWriter.write(str);
							bufferedWriter.newLine();
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}	

					try {
						bufferedWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
			}
			else 
				System.out.println("No file has been chosen");
			break;
		}
		case "OPEN": {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Choose file");
			int result = chooser.showDialog(null, "Choose");
			
			if(JFileChooser.APPROVE_OPTION == result) {
				System.out.println("File chosen: " + chooser.getSelectedFile());
				FileReader reader = null;
				BufferedReader bufferedReader = null;
				
				try 
				{
					reader = new FileReader(chooser.getSelectedFile());
					bufferedReader = new BufferedReader(reader);
					  String lengthString = bufferedReader.readLine();
					  Params.setLength(Integer.parseInt(lengthString));
					  confPanel.lengthTextField().setText(lengthString);
				} 
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try 
				{
					 String widthString = bufferedReader.readLine();
					  Params.setWidth(Integer.parseInt(widthString));
					  confPanel.widthTextField().setText(widthString);
				}
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try 
				{
					 String maxHeightString = bufferedReader.readLine();
					  Params.setMaxHeight(Integer.parseInt(maxHeightString));
					  confPanel.maxHeightTextField().setText(maxHeightString);
				}
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try 
				{
					 String minHeightString = bufferedReader.readLine();
					  Params.setMinHeight(Integer.parseInt(minHeightString));
					  confPanel.minHeightTextField().setText(minHeightString);
				}
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try 
				{
					 String roughnessString = bufferedReader.readLine();
					  Params.setRoughness(Float.parseFloat(roughnessString));
					  confPanel.roughnessTextField().setText(roughnessString);
				}
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try 
				{
					 String seedString = bufferedReader.readLine();
					  Params.setSeed(Integer.parseInt(seedString));
					  confPanel.seedTextField().setText(seedString);
				}
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try 
				{
					 String relativnessString = bufferedReader.readLine();
					  Params.setRelativness(Float.parseFloat(relativnessString));
					  confPanel.relativnessTextField().setText(relativnessString);
				}
				catch (IOException ex) {
					System.out.println("Intercepted exception: " + ex.getClass().getName());
					ex.printStackTrace();
				}
				try {
					MeshGenerator.height= new float[Params.getWidth()][Params.getLength()];
					for (int x = 0; x<Params.getWidth()-1; x++) {
						for (int y = 0; y<Params.getLength()-1; y++) {
							 String str = bufferedReader.readLine();
							MeshGenerator.height[x][y] = Float.parseFloat(str);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				

				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
			else 
				System.out.println("No file has been chosen");
			break;
		}
		case "NEW": {
			  try {				
				  Params.setLength(0);
				  Params.setWidth(0);
				  Params.setMaxHeight(0);
				  Params.setMinHeight(0);
				  Params.setRoughness(0);
				  Params.setSeed(0);
				  Params.setRelativness(0);
			  } 
			  catch (Exception ex) {
					  ex.printStackTrace();
			  }
			  
			  confPanel.lengthTextField().setText("0");
			  confPanel.widthTextField().setText("0");
			  confPanel.maxHeightTextField().setText("0");
			  confPanel.minHeightTextField().setText("0");
			  confPanel.roughnessTextField().setText("0");
			  confPanel.seedTextField().setText("0");
			  confPanel.relativnessTextField().setText("0");
			  break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + optionPicked);
		}

	}

}