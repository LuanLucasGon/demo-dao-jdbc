import db.DB;
import db.DbException;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main2 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = DB.getConnection();
//            ps = conn.prepareStatement("INSERT INTO seller "
//                    +"(Name, Email, BirthDate, BaseSalary, DepartmentId)"
//                    +"VALUES "
//                    +"(?, ?, ?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS
//            );
//            ps.setString(1, "Carl Jhonson");
//            ps.setString(2, "Carl@gmail.com");
//            ps.setDate(3, new java.sql.Date(sdf.parse("22/04/1998").getTime()));
//            ps.setDouble(4, 4000.0);
//            ps.setInt(5,4);

            ps = conn.prepareStatement("INSERT INTO department (Name) VALUES ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = "+id);
                }
            }else{
                System.out.println("Done ! Rows affected: " + rowsAffected);
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
//        catch (ParseException e){
//            e.printStackTrace();
//        }
        finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }
    }
}