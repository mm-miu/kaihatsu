<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="content">
    <section>
        <h2 class="main-title">学生管理</h2>
        <div class="new">
            <a href="StudentCreate.action">新規登録</a>
        </div>
        
        <form method="get" class="form">
            <div class="row">
                <div class="col-4">
                    <label for="student-f1-select">入学年度</label>
                    <select id="student-f1-select" name="f1">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <!-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 -->
                            <option value="${year}" <c:if test="${year==f1}">selected></c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-4">
                    <label for="student-f2-select">クラス</label>
                    <select id="student-f2-select" name="f2">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <!-- 現在のnumと選択されていたf1が一致していた場合selectedを追記 -->
                            <option value="${num}" <c:if test="${num==f2}">selected></c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-2">
                    <label for="student-f3-check">在学中
                        <!-- パラメーターf3が存在している場合checkedを追加 -->
                        <input class="form-check-input" type="checkbox"
                        id="student-f3-check" name="f3" value="t"
                        <c:if test="${!empty f3}">checked</c:if> />
                    </label>
                </div>
                <div class="col-2">
                    <button>絞込み</button>
                </div>
                <div class="mt-2 text-warning">${errors.get("f1")}</div>
            </div>
        </form>

        <c:choose>
            <c:when test="${students.size()>0}">
                <div>検索結果：${students.size()}件</div>
                <table class="table">
                    <tr>
                        <th>入学年度</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th class="text-center">在学中</th>
                        <th></th>
                    </tr>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.entYear}</td>
                            <td>${student.no}</td>
                            <td>${student.name}</td>
                            <td>${student.classNum}</td>
                            <td class="text-center">
                                <!-- 在学フラグがたっている場合「〇」それ以外は「✕」を表示 -->
                                <c:choose>
                                    <c:when test="${student.isAttend()}">
                                        〇
                                    </c:when>
                                    <c:otherwise>
                                        ✕
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <div>学生情報が存在しませんでした。</div>
            </c:otherwise>
        </c:choose>
    </section>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content" value="${content}"></c:param>
</c:import>
