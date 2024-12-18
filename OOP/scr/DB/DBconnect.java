package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconnect {
    // Cấu hình kết nối đến Microsoft SQL Server
    private static final String URL = "jdbc:sqlserver://KHUSE;databaseName=Library;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";  // Thay bằng tên người dùng thực tế
    private static final String PASSWORD = "cho123456";  // Thay bằng mật khẩu thực tế

    // Logger để ghi log thông báo
    private static final Logger LOGGER = Logger.getLogger(DBconnect.class.getName());

    public static Connection connect() {
        Connection conn = null;
        try {
            // Đăng ký driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Tạo kết nối
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.info("Connected to Microsoft SQL Server database!");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "SQL Server JDBC Driver not found.", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection failed: " + e.getMessage(), e);
        }
        return conn;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                LOGGER.info("Connection closed.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Failed to close the connection.", e);
            }
        }
    }
}