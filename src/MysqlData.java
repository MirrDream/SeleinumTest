import org.openqa.selenium.WebDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yujun on 2015/10/13.
 */
public class MysqlData {
    private String height;
    private String weight;
    private String bmi;
    private String bmiCategory;
    WebDriver driver;

    public static void main(String[] args){


    }

    public static Collection<String[]> testData(){
        List<String[]> records = new ArrayList<>();

        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //Class.forName("com.mysql.jdbc.Driver");
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.89.15:3306//coupon","coo8new","yhd,123");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM btoc_pro_promotion ORDER BY update_time DESC LIMIT 1");
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

        }catch (SQLException e){
            e.printStackTrace();
        }

    return records;
    }
}
