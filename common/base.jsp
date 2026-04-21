<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>

<head>
  <meta charset="UTF-8">
  <title>${param.title }</title>
</head>

<body>
  <header>
    <h1>${param.title}</h1>
    <c:if test="${!empty user}">
      <span>
        ${user.getName()}様
      </span>
      <a href="/kaihatsu/scoremanager/main/Logout.action">ログアウト</a>
    </c:if>
  </header>

  <c:if test="${!empty user}">
    <div>
      <nav>
        <ul>
          <a href="/kaihatsu/scoremanager/main/Menu.action">メニュー</a><br>
          <a href="/kaihatsu/scoremanager/main/StudentList.action">学生管理</a><br>
          <label>成績管理</label>
          <li><a href="/kaihatsu/scoremanager/main/TestRegist.action">成績登録</a></li>
          <li><a href="/kaihatsu/scoremanager/main/Test_List.action">成績参照</a></li><br>
          <a href="/kaihatsu/scoremanager/main/SubjectList.action">科目管理</a>
        </ul>
        
      </nav>
    </div>
  </c:if>
  ${param.content }

  <footer>
    <p>© 2023 TIC</p>
    <p>大原学園</p>
  </footer>
  
</body>

</html>