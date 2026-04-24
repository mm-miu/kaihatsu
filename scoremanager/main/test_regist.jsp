<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html;
    charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2>成績管理</h2>
        <form method="get">
            <div class="col-4">
                <label class="form-label" for="f1">入学年度
                    <select class="form-select" id="f1" name="f1">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <!-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 -->
                            <option value="${year}" <c:if test="${year==f1}">selected></c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="col-4">
                <label class="form-label" for="f2">クラス
                    <select class="form-select" id="f2" name="f2">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <!-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 -->
                            <option value="${num}" <c:if test="${num==f2}">selected></c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="col-4">
                <label class="form-label" for="f3">科目
                    <select class="form-select" id="f3" name="f3">
                        <option value="0">--------</option>
                        <c:forEach var="subject" items="${subject_set}">
                            <!-- 現在のsubjectと選択されていたf3が一致していた場合selectedを追記 -->
                            <option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected></c:if>>${subject.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="col-4">
                <label class="form-label" for="f4">回数
                    <select class="form-select" id="f4" name="f4">
                        <option value="0">--------</option>
                        <c:forEach var="count" items="${count_set}">
                            <!-- 現在のcountと選択されていたf4が一致していた場合selectedを追記 -->
                            <option value="${count}" <c:if test="${count==f4}">selected></c:if>>${count}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="col-2 text-center">
                <button class="btn btn-secondary" id="filter-botton">検索</button>
            </div>
        </form>

        <c:choose>
            <c:when test="${tests.size()>0}">
                <div>科目：${subject} (${count}回)</div>
                <table class="table table-hover">
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>点数</th>
                    </tr>
                    <c:forEach var="test" items="${tests}">
                        <tr>
                            <td>${test.student.entYear}</td>
                            <td>${test.classNum}</td>
                            <td>${test.studentName}</td>
                            <td>${test.point}</td>
                            <td><input type="text" value="${test.no}"></td>
                            <td class="text-center">
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
        </section>
    </c:param>

</c:import>