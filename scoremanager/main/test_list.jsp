<%-- test_list.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム - 成績参照</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <h2>成績参照</h2>

        <!-- 上段：科目情報で検索 -->
        <section>
            <h4>科目情報</h4>
            <form method="get" action="TestList.action">
                <div class="row">
                    <div class="col-3">
                        <label for="f1">入学年度
                            <select id="f1" name="f1" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>

                    <div class="col-3">
                        <label for="f2">クラス
                            <select id="f2" name="f2" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>

                    <div class="col-4">
                        <label for="f3">科目
                            <select id="f3" name="f3" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="subject" items="${subject_set}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>

                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" type="submit">検索</button>
                    </div>
                </div>
            </form>

            <!-- ★ 科目検索エラー表示 -->
            <c:if test="${not empty error}">
                <p style="color:red">${error}</p>
            </c:if>
        </section>

        <hr/>

        <!-- 下段：学生情報で検索 -->
        <section>
            <h4>学生情報</h4>
            <form method="get" action="TestList.action">
                <div class="row">
                    <div class="col-4">
                        <label for="studentNo">学生番号
                            <input type="text" id="studentNo" name="studentNo" class="form-control"
                                   value="${studentNo}" placeholder="学生番号を入力してください" />
                        </label>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" type="submit">検索</button>
                    </div>
                </div>
            </form>

            <!-- ★ 学生検索エラー表示 -->
            <c:if test="${not empty error_student}">
                <p style="color:red">${error_student}</p>
            </c:if>
        </section>

    </c:param>
</c:import>
