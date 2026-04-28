<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">成績参照（科目別）</c:param>

<c:param name="content">

<h2>成績参照（科目別）</h2>

<!-- 上段：検索欄（test_list.jsp と同じ） -->
<section>
    <h4>科目情報</h4>
    <form method="get" action="TestListSubjectExecute.action">
        <div class="row">
            <div class="col-3">
                <label>入学年度
                    <select name="f1" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="col-3">
                <label>クラス
                    <select name="f2" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="col-4">
                <label>科目
                    <select name="f3" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="sub" items="${subject_set}">
                            <option value="${sub.cd}" <c:if test="${sub.cd==f3}">selected</c:if>>${sub.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="col-2 text-center">
                <button class="btn btn-secondary">検索</button>
            </div>
        </div>
    </form>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
</section>

<hr/>

<!-- 下段：検索結果一覧 -->
<section>
    <h4>検索結果：<c:out value="${subject.name}" /></h4>

    <c:if test="${empty tests}">
        <p>学生情報が存在しませんでした。</p>
    </c:if>

    <c:if test="${not empty tests}">
        <table class="table table-hover">
            <tr>
                <th>入学年度</th>
                <th>クラス</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>1回</th>
                <th>2回</th>
            </tr>

            <c:forEach var="row" items="${tests}">
                <tr>
                    <td><c:out value="${row.student.entYear}" /></td>
                    <td><c:out value="${row.classNum}" /></td>
                    <td><c:out value="${row.student.No}" /></td>
                    <td><c:out value="${row.student.Name}" /></td>
                    <!-- 1回目 -->
                     <td>
                        <c:choose>
                            <c:when test="${row.points[1] != null}">
                                <c:out value="${row.points[1]}" />
                            </c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>
                    
                    <!-- 2回目 -->
                     <td>
                        <c:choose>
                            <c:when test="${row.points[2] != null}">
                                <c:out value="${row.points[2]}" />
                            </c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</section>

</c:param>
</c:import>
