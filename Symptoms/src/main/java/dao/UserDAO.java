package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public String getPasswordById(String id) throws SQLException {
        String password = null;
        String sql = "SELECT password FROM patients WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    password = rs.getString("password");
                }
            }
        }
        return password;
    }
    
    // IDとNAMEを取得するメソッドを追加
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM PATIENTS";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("ID"));
                user.setName(rs.getString("NAME"));
                users.add(user);
            }
        }
        return users;
    }

  

    public User getUserById(String userId) throws SQLException {
        User user = null;
        String sql = "SELECT ID, NAME FROM PATIENTS WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getString("ID"));
                    user.setName(rs.getString("NAME"));
                }
            }
        }
        return user; // tryブロックの外側にreturn文を移動
    }


// ユーザーIDとパスワードを使用してユーザーを検索するメソッド
public User getUserByIdAndPassword(String id, String password) throws SQLException {
    User user = null;
    String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, id);
        ps.setString(2, password);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("ID"));
                user.setName(rs.getString("NAME"));
                // 他のカラムを必要に応じて設定
            }
        }
    }
    return user;
}
}
