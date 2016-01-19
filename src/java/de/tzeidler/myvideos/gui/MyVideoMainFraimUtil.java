package de.tzeidler.myvideos.gui;

import de.tzeidler.myvideos.GENRES;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Toni Zeidler
 */
public class MyVideoMainFraimUtil {
    
    private static Connection connection = null;
    private static Statement stmt;
    private static ResultSet rs;
    DefaultTableModel tmodel = new DefaultTableModel();
    
    /**
     * 
     * @param connection
     * @return
     * @throws SQLException 
     */
    public static Connection getConnection(Connection connection)throws SQLException {
        // On ACER-Toni PC global database D:\\myvideo.db
        //String databaseDatei = "D:\\myvideo.db";
        // O
        String databaseFile = "/Users/Toni/Desktop/MyGitHub/MyVideo/src/java/de/tzeidler/myvideos/myvideos.sqlite";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return connection;
    }
    
    /**
     * 
     * @return 
     */
    public DefaultTableModel getListe(){
        try {
            connection = MyVideoMainFraimUtil.getConnection(connection);
            stmt = connection.createStatement();
            String query = "select * from myvideo";
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                tmodel.addColumn(rsmd.getColumnLabel(i));
            }
            while(rs.next()){
                Object[] fields = new Object[colCount];
                for (int i = 0; i < colCount; i++) {
                    fields[i] =rs.getObject(i+1);
                }
                tmodel.addRow(fields);
            }
        } catch (Exception e) {
        }
        return tmodel;
    }
    
    /**
     * Read the file and returns the byte array
     * 
     * @param imageFile
     * @return the bytes of the file
     */
    private byte[] readImageFile(String imageFile) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(imageFile);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
    
    /**
     * 
     * @param title
     * @param imagPath
     * @param genres
     * @param stars
     * @param description
     * @param videoPath 
     */
    public void insertVideo(String title, String imagPath, GENRES genres, int stars, String description, String videoPath){
        try {
            stmt =connection.createStatement();
            String query = "INSERT INTO myvideo (title,image,genres,stars,description,vpath ) "
                    + "VALUES ('"+title+"',readfile('"+imagPath+"'),'"+genres+"',"
                    + stars + ",'"+description+"','"+videoPath+"');";
            stmt.executeQuery(query);
            stmt.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Ist schon drin ! :-D");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR beim INSERT INTO");
        }
    }
    
    /**
     * 
     * @param id 
     */
    public void deleteVideo(int id){
        try {
            stmt =connection.createStatement();
            String query = "DELETE FROM myvideo WHERE id="+id+";";
            
            stmt.executeQuery(query);
            stmt.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Ist schon weg ! :-D");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR beim DELETE");
        }
    }
    
    /**
     * 
     * @param id
     * @param title
     * @param imagPath
     * @param genres
     * @param stars
     * @param description
     * @param videoPath 
     */
    public void updatetVideo(int id,String title, String imagPath, GENRES genres, int stars, String description, String videoPath){
        try {
            stmt =connection.createStatement();
            String query = "UPDATE myvideo SET title='"+title+"',"
                    + "image=readfile('"+imagPath+"'),genres='"+genres+"',"
                    + "stars='"+stars+"',description='"+description+"',vpath='"+videoPath+"'"
                    + "WHERE id="+id+";";
            stmt.executeQuery(query);
            stmt.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "UPDATE ist OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR beim UPDATE");
        }
    }
}
