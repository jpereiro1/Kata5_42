package kata5_42;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Kata5_42 {

    public static void main(String[] args) {
        Kata5_42 kata5 = new Kata5_42();
        kata5.selectAll();
        
        kata5.createTableEmail();
        
        kata5.insertMails();
    }

    private void selectAll() {
        String sql = "SELECT * FROM PEOPLE";
        
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                System.out.println(rs.getInt("Id")+ "\t" +
                                   rs.getString("Name")+ "\t" +
                                   rs.getString("Apellidos")+ "\t" +
                                   rs.getString("Departamento"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:KATA5.db";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        }catch(SQLException e){
            System.out.println("No se ha podido conectar con la base de datos"
                    + e.getMessage());
        }
        return conn;
    }

    private void createTableEmail() {
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL( "
                + "Id integer PRIMARY KEY AUTOINCREMENT,"
                + "Mail text NOT NULL);";
        
        try{
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }catch(SQLException e){
            System.out.println("La tabla no se ha podido crear");
        }
    }

    private void insertMails() {
        String sql = "INSERT INTO EMAIL(Mail) VALUES(?)";
        
        try{
            MailListReader mailReader = new MailListReader();
            ArrayList<String> emails = mailReader.read("email.txt");
            
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            Iterator<String> it = emails.iterator();
            while(it.hasNext()){
                pstmt.setString(1,it.next());
                pstmt.executeUpdate();
            }
            
        }catch(FileNotFoundException e){
            System.out.println("Error al insertar los emails a la BD");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    
    
    
}
