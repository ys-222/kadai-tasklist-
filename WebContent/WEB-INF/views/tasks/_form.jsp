<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<label>タイトル<br />
<!--リクエストスコープのtaskオブジェクトからデータを参照し、入力内容の初期値として表示-->
<input type="text" name="title" value="${task.title}" />
</label>
<br /><br />
<label>タスク<br />
<input type="text" name="content" value="${task.content}" />
</label>
<br /><br />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>