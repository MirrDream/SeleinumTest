import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yujun on 2015/10/26.
 */
public class TestJDBC {
    public static void main(String[] args){
        test();
    }

    static void test(){
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;

        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection("jdbc:mysql://192.168.89.15/kubauser?" +
                    "user=coo8new&password=yhd,123");
        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        try{
            st = conn.createStatement();
            rs = st.executeQuery("select * from ecuser_account limit 1,100");
            while (rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +
                        "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6)
                + "\t" + rs.getString(7) + "\t" + rs.getString(8));
            }

            if(st.execute("select * from ecuser_account limit 1,100")){
                rs = st.getResultSet();
            }

        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if(rs != null){
                try{
                    rs.close();
                }catch (Exception e){}
                rs = null;
            }
            if(st != null){
                try{
                    st.close();
                }catch (Exception e){}
                st = null;
            }
        }
    }
}
