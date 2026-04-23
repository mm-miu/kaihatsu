<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績一覧</c:param>

    <c:param="content">
        <section class="container mt-4">

            <h2 class="h4 mb-3">成績一覧</h2>

            <!--検索フォーム-->
            <form action="TestList.action" method="get" class="row g-3 mb-4">
                <div class="col-auto">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="studentNo" class="form-control" value="${studentNo}">
                </div>
                <div class="col-auto align-self-end">
                    <button class="btn btn-primary">検索</button>
                </div>
            </form>

            <!--検索結果-->
            <c:if test="${not empty list}">
                <table class="table table-borderd">
                    <thead class="table-light">
                        <tr>
                            <th>科目コード</th>
                            <th>回数</th>
                            <th>得点</th>
                            <th>クラス番号</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="t" items="${list}">
                            <tr>
                                <td>${t.subjectCd}</td>
                                <td>${t.no}</td>
                                <td>${t.point}</td>
                                <td>${t.classNum}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <!--検索結果がなしの場合-->
            <c:if test="${empty list && not empty studentNo}">
                <p>検索結果はありません。</p>
            </c:if>
            
        </section>
    </c:param>
</c:import>