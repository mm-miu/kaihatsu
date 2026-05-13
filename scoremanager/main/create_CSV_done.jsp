<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">

        <h2 class="main-title">${title}CSV書き込み</h2>
        <textarea id="copytxt" rows="10" cols="50" readonly>${csvData}</textarea><br>
        <button type="button" onclick="copyText('copytxt')">コピー</button><br><br>

        <a href="MenuCSV.action">戻る</a>

    </c:param>

</c:import>

<script>
function copyText(elementId) {

    const element = document.getElementById(elementId);

    navigator.clipboard.writeText(element.value)
        .then(() => {
            alert("コピーしました");
        })
        .catch(err => {
            console.error("コピー失敗: ", err);
        });
}
</script>