package util;

import java.sql.*;


public class JDBC {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/library?&useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "123456";

    public Connection getCon() throws Exception {
        Connection con = null;
        try {
            
            Class.forName(driver);
            
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return con;
    }
    public void closeCon(Connection con)throws Exception
    {
        if(con!=null)
        {
            con.close();
        }
    }

    public static void main(String[] args)
    {
        JDBC jdbc=new JDBC();
        try {
            jdbc.getCon();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}