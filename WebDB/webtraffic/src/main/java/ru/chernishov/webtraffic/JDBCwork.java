package ru.chernishov.webtraffic;

import javax.servlet.http.HttpServlet;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

//класс для работы с базой данных
public class JDBCwork extends HttpServlet{

    //регистрация драйвера
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String JDBC_URL = "";

    JDBCwork (String url){
        this.JDBC_URL=url;
    }

    //добавить бд
    public void createschema(String name) throws SQLException {
        String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS `"+name+"`";
        try {

            Connection connt = DriverManager.getConnection(JDBC_URL);
            Statement sttmnt = connt.createStatement();
            sttmnt.executeUpdate(CREATE_SCHEMA);

            sttmnt.close();
            connt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }


        //добавить таблицу
    public void createtable(String namedb, String nametb) throws SQLException {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +namedb+"."+nametb+ " (`id` INT NOT NULL AUTO_INCREMENT, `date` DATETIME NOT NULL, `abonent` VARCHAR(45) NOT NULL, `uplink` INT, `downlink` INT, PRIMARY KEY (`id`))";

            try {
                Connection connt = DriverManager.getConnection(JDBC_URL);
                Statement sttmnt = connt.createStatement();
                sttmnt.executeUpdate(CREATE_TABLE);
                sttmnt.close();
                connt.close();
            }
            catch (SQLException e) {e.printStackTrace(); }


    }


    //произвольный выбор из базы
    public int selectfromdb(String mess, String link) {

        int traf=0;
        ResultSet rs = null;

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL);
            PreparedStatement stmt = conn.prepareStatement(mess);
            rs = stmt.executeQuery();

            while (rs.next()){
                traf=traf+rs.getInt(link);
            }

            conn.close();
            stmt.close();
            rs.close();

        } catch (Exception e) {
        }
        return traf;
    }


      //список абонентов из базы
    public TreeSet<String> selectAbon(String nameDB, String nametb) {

        TreeSet<String> list= new TreeSet<String>();  //сортированный список уникальных имен
        ResultSet rs = null;

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL);
            PreparedStatement stmt = conn.prepareStatement("SELECT abonent FROM " +nameDB+"."+nametb );
            rs = stmt.executeQuery();

            while (rs.next()){
                list.add(rs.getString("abonent"));
            }

            conn.close();
            stmt.close();
            rs.close();

        } catch (Exception e) {
        }

     return list;
    }

}
