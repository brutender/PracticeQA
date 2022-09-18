package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "km-jm-kam-am1";

    public Connection connection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection open");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
