package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class SymptomsDAO {

    // データベース接続情報
    private final String JDBC_URL = "jdbc:h2:~/Sysmptoms"; // 接続URL
    private final String DB_USER = "sa"; // ユーザー名
    private final String DB_PASS = ""; // パスワード

    // コネクションオブジェクトを取得するメソッド
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }
    
    // メソッド：SYMPTOMSテーブルに記録内容を登録する
    public void insertSymptom(String jikan, String syoujyou, String koudou, String userId) throws SQLException {
        try (Connection conn = getConnection()) {
            // SQL文の準備
            String sql = "INSERT INTO SYMPTOMS (JIKAN, SYOUJYOU, KOUDOU, USER_ID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // パラメータの設定
                pstmt.setString(1, jikan);
                pstmt.setString(2, syoujyou);
                pstmt.setString(3, koudou);
                pstmt.setString(4, userId);
                
                // SQL文の実行
                pstmt.executeUpdate();
            }
        }
    }
    
    // メソッド：SYMPTOMSテーブルから特定のユーザーの症状メモを取得する
    public List<Mutter> getSymptomsByUserId(String loggedInUserId) throws SQLException {
        List<Mutter> mutterList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            // SQL文の準備
            String sql = "SELECT * FROM SYMPTOMS WHERE USER_ID = ? ORDER BY JIKAN DESC";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // パラメータの設定
                pstmt.setString(1, loggedInUserId);
                // SQL文の実行
                try (ResultSet rs = pstmt.executeQuery()) {
                    // 結果をリストに追加
                    while (rs.next()) {
                        Mutter mutter = new Mutter();
                        mutter.setJikan(rs.getString("JIKAN"));
                        mutter.setSyoujyou(rs.getString("SYOUJYOU"));
                        mutter.setKoudou(rs.getString("KOUDOU"));
                        mutterList.add(mutter);
                    }
                }
            }
        }
        return mutterList;
    }
}
