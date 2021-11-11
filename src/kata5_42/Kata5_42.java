package kata5_42;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5_42 {

    public static void main(String[] args) {
        Kata5_42 kata5 = new Kata5_42();
        kata5.selectAll();
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
    
    
    
}
