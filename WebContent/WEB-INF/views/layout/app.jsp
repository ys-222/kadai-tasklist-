<!-- レイアウトファイル：共通のひな形（サイトデザインの大枠）-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTLのうちのコア機能を c という名前で利用できるようにする設定-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>タスクリスト</title>
    </head>
    <body>
        <div id="wrapper">
	        <div id="header">
	            <h1>タスクリスト アプリケーション</h1>
	        </div>
	        <div id ="content">
	           ${param.content}   <!-- 各ページのビューの内容が入る -->
	        </div>
	        <div id="footer">
	            by Yuki Shimoda
	        </div>
	    </div>
    </body>
</html>