package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // データベース接続情報
    private static final String URL = "jdbc:h2:~/Sysmptoms"; // H2データベースの場所と名前
    private static final String USER = "sa"; // デフォルトのユーザー名は"sa"
    private static final String PASSWORD = ""; // デフォルトのパスワードは空白

    // データベースへの接続を取得するメソッド
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
}
