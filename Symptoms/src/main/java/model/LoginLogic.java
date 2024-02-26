package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class LoginLogic {
    public boolean execute(User user) {
        boolean isLogin = false;
        String inputId = user.getId(); // ユーザーのIDを取得
        String inputPassword = user.getPass(); // ユーザーのパスワードを取得

     // 入力されたIDとパスワードをログに出力
        System.out.println("入力されたID: " + inputId);
        System.out.println("入力されたパスワード: " + inputPassword);
        
        
        try (Connection con = DBUtil.getConnection()) {
            // データベースへの接続を取得

            // SQLクエリを準備
            String sql = "SELECT * FROM PATIENTS WHERE ID = ? AND PASSWORD = ?";
            PreparedStatement pstmt = con.prepareStatement(sql); // SQL文をプリコンパイルしてPreparedStatementを作成
            pstmt.setString(1, inputId); // プリペアドステートメントの1番目のパラメータにユーザーIDをセット
            pstmt.setString(2, inputPassword); // プリペアドステートメントの2番目のパラメータにパスワードをセット

            // クエリを実行し、結果を取得
            ResultSet rs = pstmt.executeQuery(); // SQLクエリを実行して結果をResultSetに格納

            // 結果セットが空でなければログイン成功
            if (rs.next()) { // ResultSetから次の行が存在すればtrueを返す
                isLogin = true; // ログイン成功
                String userName = rs.getString("NAME"); // NAMEを取得
                user.setName(userName); // 取得したNAMEをUserオブジェクトに設定
            }
        } catch (SQLException e) { // SQLの例外をキャッチ
            e.printStackTrace(); // エラーを標準エラー出力に出力
        }
        return isLogin; // ログイン成功の真偽値を返す
    }
}
