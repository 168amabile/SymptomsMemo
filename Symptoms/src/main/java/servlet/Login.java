package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// リクエストパラメータの取得
	  request.setCharacterEncoding("UTF-8");
	  String id = request.getParameter("id");
	  String pass = request.getParameter("pass");

	  // 入力されたIDとPASSのログ出力
	  System.out.println("ID: " + id);
	  System.out.println("Password: " + pass);

	  // IDを使用してH2データベースから対応するNAMEを取得
	  String name = ""; // データベースから取得したNAMEを格納する変数
	  try {
	      // データベースからNAMEを取得する処理を記述
	      // 例：name = database.getNameById(id);
	  } catch (Exception e) {
	      // エラーが発生した場合の処理
	      e.printStackTrace();
	  }

	  // 取得したNAMEをログ出力
	  System.out.println("Name: " + name);

	  // Userインスタンス（ユーザー情報）の生成
	  User user = new User(id, name, pass);


    // ログイン処理
    LoginLogic loginLogic = new LoginLogic();
    boolean isLogin = loginLogic.execute(user);

    // ログイン成功時の処理
    if (isLogin) {
        // ユーザー情報をセッションスコープに保存
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", user);
        // ユーザーIDとユーザーNAMEをセッションスコープに保存
        session.setAttribute("userId", id);
        session.setAttribute("userName", name);
        
        // ログインしたユーザーの情報を取得してログに出力してみる
        User loggedInUser = (User) session.getAttribute("loginUser");
        if (loggedInUser != null) {
            System.out.println("ログインしたユーザーの情報: " + loggedInUser.getName());
        } else {
            System.out.println("ログインしたユーザーの情報が取得できませんでした。");
        }
    }
        // ログイン結果画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
        dispatcher.forward(request, response);
    }
  }

