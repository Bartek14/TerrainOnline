package main.databaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import main.mesh.MeshPanel;
import main.parameters.Params;

public class DatabaseEntity {
	static Connection conn = null;
	static Statement saveStatement = null;
	static Connection loadConn = null;
	static Statement loadStatement = null;
	static ResultSet resultSet = null;
	static ResultSetMetaData metaData = null;
	
 private static void establishConnection() throws SQLException {
		
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/terrain", "sa","");
			     saveStatement = conn.createStatement();
			     conn.createStatement().executeUpdate("DROP TABLE IF EXISTS `terrainCoords`;");
			     conn.createStatement().executeUpdate("CREATE TABLE `terrainCoords` ("+
							  "`Id` int(6) unsigned NOT NULL auto_increment,"+
							  "`x` int default NULL,"+
							  "`y` int default NULL,"+
							  "`height` float default NULL,"+
							  "PRIMARY KEY  (`Id`)"+
							") ;");
				
 			} catch (SQLException e) {
				
 				System.out.println("dupa");
 				e.printStackTrace();
 			}	
		try {
			conn.createStatement().executeUpdate("DROP TABLE IF EXISTS `terrainSpecs`;");
			conn.createStatement().executeUpdate("CREATE TABLE `terrainSpecs` ("+
							 // "`Id` int(6) unsigned NOT NULL auto_increment,"+
							  "`length` int default NULL,"+
							  "`width` int default NULL,"+
							  "`max_height` float default NULL,"+
							  "`min_height` float default NULL,"+
							  "`roughness` float default NULL,"+
							  "`seed` long default NULL,"+
							  "`relativness` float default NULL"+
							//  "PRIMARY KEY  (`Id`)"+
							") ;");
				
 			} catch (SQLException e) {
				
 				System.out.println("dupa");
 				e.printStackTrace();
 			}	
		
	}
 public static void saveCoordsAndSpecs() throws SQLException {
	 try {
		establishConnection();
		
		for (int x = 0; x<Params.getWidth()-1; x++) {
			for (int y = 0; y<Params.getLength()-1; y++) {
				conn.createStatement().executeUpdate(DatabaseEntity.insertQueryToTerrainCoords(x,y,MeshPanel.height[x][y])); 
			}
		}
		conn.createStatement().executeUpdate(DatabaseEntity.insertQueryToTerrainSpecs());
	 } finally {
		if(conn!=null)
			conn.close();
	}

 }
  
 private static String insertQueryToTerrainCoords(int x, int y, float height ) {
	 String query="";
	 query="INSERT INTO `terrainCoords`(`x`,`y`,`height`) VALUES (" +x+","+y+","+MeshPanel.height[x][y]+");";
	 return query;
 }
 private static String insertQueryToTerrainSpecs() {
	 String query="";
	 
	 query="INSERT INTO `terrainSpecs`(`length`,`width`,`max_height`,`min_height`,`roughness`,`seed`,`relativness`) VALUES ("+
			 Params.getLength()+","
			 +Params.getWidth()+","
			 +Params.getMaxHeight()+","
			 +Params.getMinHeight()+","
			 +Params.getRoughness()+","
			 +Params.getSeed()+","
			 +Params.getRelativness()+");";
	 
	 return query;
 }

 public static void loadCoordsAndSpecs() throws SQLException {
	 conn = DriverManager.getConnection("jdbc:h2:~/terrain", "sa","");
	 Statement specsStatement =conn.createStatement();
	 specsStatement.execute("SELECT * FROM terrainSpecs");
	 resultSet=specsStatement.getResultSet();
	 metaData = resultSet.getMetaData();
	 //resultSet.next();//dump the ID
	 
	if(resultSet.next()){
	 	Params.setLength(resultSet.getInt("length"));
	}
	if(resultSet.next()){
	 	Params.setWidth(resultSet.getInt("width"));
	}
	if(resultSet.next()){
	 	Params.setMaxHeight(resultSet.getFloat("max_height"));
	}
	if(resultSet.next()){
	 	Params.setMinHeight(resultSet.getFloat("minHeight"));
	}
	if(resultSet.next()){
	 	Params.setRoughness(resultSet.getFloat("roughness"));
	}
	if(resultSet.next()){
	 	Params.setSeed(resultSet.getInt("seed"));
	}
	if(resultSet.next()){
	 	Params.setRelativness(resultSet.getFloat("relativness"));
	}
	 	
	 Statement coordsStatement = conn.createStatement();
	 coordsStatement.execute("SELECT * FROM terrainCoords");
	 resultSet=coordsStatement.getResultSet();
	 metaData = resultSet.getMetaData();
	 
	 MeshPanel.height = new float[Params.getLength()][Params.getWidth()];
	 
	 while(resultSet.next()) {
			MeshPanel.height[resultSet.getInt("x")][resultSet.getInt("y")]=
					resultSet.getFloat("height");
				 
	 }
	 
 }

}
