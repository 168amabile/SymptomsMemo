<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User,model.Mutter,java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>症状メモ</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
 
    <script>
        function validateForm() {
            var jikan = document.getElementById("jikan").value;
            var syoujyou = document.querySelector('input[name="syoujyou"]:checked');
            var koudou = document.getElementById("koudou").value;

            if (jikan === "" || syoujyou === null || koudou === "") {
                Swal.fire({
                    text: 'すべての項目を入力してください。',
                    confirmButtonText: 'OK'
                });
                return false;
            }
        }

        function createCSVContent() {
            var mutterList = document.querySelectorAll(".example-text");
            console.log("mutterList:", mutterList); // mutterList の内容をコンソールに出力
            var csvContent = "\uFEFF時刻,症状,活動\n"; // BOMを含むUTF-8のエンコード
            for (var i = 0; i < mutterList.length; i++) {
                var mutterData = mutterList[i].textContent.trim().split(" / "); // テキストコンテンツを取得する
                console.log("mutterData:", mutterData); // mutterData の内容をコンソールに出力
                mutterData[0] = mutterData[0].replace("(*˘︶˘*).*♡", ""); // "(*˘︶˘*).*♡" を空文字列に置換する
                csvContent += mutterData.join(",") + "\n";
            }
            return csvContent;
        }


        function createAndDownloadCSV(loggedInUserId) {
            var csvContent = createCSVContent();
            var fileName = loggedInUserId + "_症状メモ一覧.csv"; // ファイル名を作成
            var blob = new Blob([csvContent], { type: "text/csv;charset=utf-8" }); // UTF-8でエンコード
            var url = window.URL.createObjectURL(blob);
            var a = document.createElement("a");
            a.href = url;
            a.download = fileName; // ファイル名を設定
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
        }
    </script>
</head>
<body>
<div class="wrapper">
    <h1>症状メモ</h1>
    
    <!-- ログインしたユーザーのIDとNAMEを表示 -->
    <% User loggedInUser = (User) request.getAttribute("loggedInUser"); %>
    <% if (loggedInUser != null) { %>
        <p><span style="display: none;">診察券番号：<%= loggedInUser.getId() %></span></p>
        <p class="custom-text"><%= loggedInUser.getName() %> さん</p>
    <% } %>
 
    <form action="Main" method="post" accept-charset="UTF-8" onsubmit="return validateForm()">
        <label for="jikan" class="label">🕙 何時 何分でしたか？ </label><br>
        <input type="datetime-local" id="jikan" name="jikan" class="datetime-local-input"><br><br>
        
        <label for="syoujyou" class="label">💫 どんな症状でしたか？ </label><br>
        <input type="radio" id="syoujyou1" name="syoujyou" value="めまい" >
        <label1 for="syoujyou1">めまい</label1>&nbsp;&nbsp;&nbsp;<!-- ここで追加のスペースを入れる -->
        <input type="radio" id="syoujyou2" name="syoujyou" value="動　悸">
        <label1 for="syoujyou2">動 悸</label1><!-- ここで追加のスペースを入れる --><br>
        <input type="radio" id="syoujyou4" name="syoujyou" value="息切れ">
        <label1 for="syoujyou4">息切れ</label1>&nbsp;&nbsp;&nbsp;
        <input type="radio" id="syoujyou3" name="syoujyou" value="胸　痛">
        <label1 for="syoujyou3">胸 痛</label1><!-- ここで追加のスペースを入れる -->
        <br>
        <br>
        
        <label for="koudou" class="label">☕ 何をしていましたか？ </label><br>
        <input type="text" id="koudou" name="koudou" class="wide-textbox"><br><br>
        <input type="submit" value="　📄　記　録　す　る　　" class="wide-button" >
        <br>
        <br>
        <div class="button-container"> </div>
        <br>
    </form>

    <%
        List<Mutter> mutterList = (List<Mutter>) request.getAttribute("mutterList");
        if (mutterList != null) {
            for (Mutter mutter : mutterList) {
    %>
                <p><span class="example-text">(*˘︶˘*).*♡ <%= mutter.getJikan() %> / <%= mutter.getSyoujyou() %> / <%= mutter.getKoudou() %></span></p>
    <%
            }
        }
    %>
    <br>
   		<input type="button" value="　📁　ファイル作成　　" onclick="createAndDownloadCSV('<%= loggedInUser.getId() %>')" class="wide-button">
 
</div> 
</body>
</html>
