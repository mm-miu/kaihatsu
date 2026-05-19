<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <div id="wrapper">
            <div id="main">
                <div id="breadcrumb">
                    <ol>
                        <li><a href="Menu.action">MENU</a></li>
                        <li><a href="TeacherList.action">ユーザー管理</a></li>
                        <li>ユーザー登録</li>
                    </ol>
                </div>
            </div>
        </div>

        <h2 class="main-title">ユーザー登録</h2>
        
            <div class="permission-wrapper">
                <h5 class="permission-title">権限</h5>
                    <div class="permission-tooltip">
                    <div><b>■ 権限1</b></div>
                    <div>・閲覧／変更：すべて可能</div>
                    <div>・新規登録：すべて可能</div><br>

                    <div><b>■ 権限2</b></div>
                    <div>・閲覧／変更：自身の学校のユーザーまで可能</div>
                    <div>・新規登録：ID・氏名のみ可能</div><br>

                    <div><b>■ 権限3</b></div>
                    <div>・閲覧／変更：自身のみ可能（学校コード・氏名は閲覧可）</div>
                    <div>・新規登録：不可</div>
                    </div>
            </div>

        <form action="TeacherCreateExecute.action">
            
            <div class="content-input">
                <c:if test="${not empty errors.not_save}">
                    <p style="color:red">${errors.not_save}</p>
                </c:if>
                <label for="id">ID</label>
                <input type="text" id="id" name="id" 
                    value="${id}" placeholder="半角でログイン名を入力してください" 
                    pattern="^[A-Za-z0-9]{1,10}$" title="半角英数字10文字以内で入力してください。"
                    maxlength="10" required>
                <br>
                <c:if test="${not empty errors.id}">
                    <p style="color:red">${errors.id}</p>
                </c:if>
                <c:if test="${not empty errors.id_count}">
                    <p style="color:red">${errors.id_count}</p>
                </c:if>
                
            </div>

            <div class="content-input">
                <label for="name">名前</label>
                <input type="text" id="name" name="name" 
                    value="${name}" placeholder="名前を入力してください" maxlength="10" required>
                <br>
            </div>

            

            <c:choose>
                <c:when test="${ authority=='1'}">
                    <div class="content-input">
                        <label for="school_cd">所属</label>
                        <select name="school" >
                            <c:forEach var="sList" items="${schoolList}">
                                <option value="${sList.cd}" label="${sList.name}">${sList.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>          
            
            <div class="button-2">
                <button name="end" type="submit">登録</button>
                <br>
            </div>
        </form>

        <a href="TeacherList.action">戻る</a>
    </c:param>

</c:import>

