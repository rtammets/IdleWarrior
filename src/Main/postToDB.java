package Main;
import java.sql.*;

/**
 * Created by R on 09.09.2017.
 */
public class postToDB {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://192.168.0.26:3306/nexnet","root","Passw0rd");
            String sql = "INSERT INTO `items`(`index`, `slot`, `name`, `att`, `str`, `def`, `life`) VALUES (?,?,?,?,?,?,?)";
            // String sql = "INSERT INTO `nex_users`(`UID`, `attr_id`, `attr`)"+"VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2,"name");
            preparedStatement.setInt(3,2);
            preparedStatement.setInt(4,2);
            preparedStatement.setInt(5,2);
            preparedStatement.setInt(6,2);
            preparedStatement.setInt(7,2);

            //preparedStatement.setInt(1,44);
            //preparedStatement.setInt(1,2);
            //preparedStatement.setInt(2,2);
            //preparedStatement.setString(3,"TEReprepa");
            preparedStatement.executeUpdate();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM nex_users;");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(4));
            con.close();
        }catch(Exception e){ System.out.println(e);}

    }


}
