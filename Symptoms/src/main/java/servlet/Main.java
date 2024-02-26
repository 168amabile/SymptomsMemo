package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SymptomsDAO;
import dao.UserDAO;
import model.Mutter;
import model.User;
import util.DBUtil;

@WebServlet(name = "Main", urlPatterns = { "/Main" })
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection con = DBUtil.getConnection()) {
            // ログインユーザーの情報をセッションから取得
            HttpSession session = request.getSession();
            User loggedInUser = (User) session.getAttribute("loginUser");
            String loggedInUserId = loggedInUser.getId(); // ログイン中のユーザーIDを取得
            String loggedInUserName = loggedInUser.getName(); // ログイン中のユーザー氏名を取得

            // 症状メモを取得
            SymptomsDAO symptomsDAO = new SymptomsDAO();
            List<Mutter> mutterList = symptomsDAO.getSymptomsByUserId(loggedInUserId);

            // IDとNAMEを取得
            UserDAO userDAO = new UserDAO(con);
            List<User> userList = userDAO.getAllUsers();

            // リクエスト属性にセットする
            request.setAttribute("loggedInUser", loggedInUser); // 修正：セッションに設定する際のキーを "loggedInUser" から "loginUser" に修正
            request.setAttribute("mutterList", mutterList);
            request.setAttribute("userList", userList);

            // JSPにフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを追加
        }
    }






    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // フォームから入力された内容を取得
        String jikan = request.getParameter("jikan");

        // 「T」を空白に置き換える
        String cleanedDateTime = jikan.replace("T", " ");

        String syoujyou = request.getParameter("syoujyou");
        String koudou = request.getParameter("koudou");

        // ユーザーIDを取得する処理
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loginUser");
        String userId = loggedInUser.getId();

        // H2データベースに症状メモを登録
        SymptomsDAO symptomsDAO = new SymptomsDAO();
        try {
            symptomsDAO.insertSymptom(jikan, syoujyou, koudou, userId);
        } catch (SQLException e) {
            e.printStackTrace();
            // 登録に失敗した場合はエラーメッセージを設定
            request.setAttribute("errorMsg", "症状メモの登録に失敗しました。");
        }

        // GETリクエストを実行して症状メモ画面を表示
        response.sendRedirect(request.getContextPath() + "/Main");
    }

    // 症状メモをSYMPTOMSテーブルに挿入するメソッド
    @SuppressWarnings("unused")
    private void insertSymptom(String jikan, String syoujyou, String koudou, String userId) throws SQLException {
        // SQL文を準備
        String sql = "INSERT INTO SYMPTOMS (JIKAN, SYOUJYOU, KOUDOU, USER_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, jikan);
            pstmt.setString(2, syoujyou);
            pstmt.setString(3, koudou);
            pstmt.setString(4, userId);

            // クエリを実行
            pstmt.executeUpdate();
        }
    }
}
