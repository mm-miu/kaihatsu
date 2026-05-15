<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="content">
    <section>
        <h2 class="main-title">ユーザー管理</h2>
        <c:choose>
        <c:when test="${ authority=='1' || authority=='2'}">
        <div class="new">
            <a href="TeacherCreate.action">新規登録</a>
        </div>
        </c:when>
        <c:otherwise></c:otherwise>
        </c:choose>
        <form method="get" class="form">
            <div class="row">
                <div class="col-4">
                    <label for="school-select">学校コード</label>
                    <select  id="school-select" name="f1" >
                        <option value="0"selected disabled>--------</option>
                        <option value="upcd" label="昇順">昇順</option>
                        <option value="downcd" label="降順">降順</option>
                    </select>
                </div>
                <div class="col-4">
                    <label for="name-select">名前</label>
                    <select  id="name-select" name="f1" >
                        <option value="0"selected disabled>--------</option>
                        <option value="upname" label="昇順">昇順</option>
                        <option value="downname" label="降順">降順</option>
                    </select>
                </div>
            </div>
            
        </form>

        <div>ログイン中のユーザー</div>      
        <c:choose>
            <c:when test="${AllList.size()>0}">
                <table class="table">
                    <tr>
                        <th>学校コード</th>
                       
                        <th>氏名</th>
                        <th>id</th>
                        <th></th>
                        
                    </tr>
                <c:forEach var="T_My" items="${MyList}">
                    <tr>
                        
                    
                        <td>${T_My.school.getName()}</td>  
                        <td>${T_My.name}</td>
                        <td>${T_My.id}</td>
                        
                            
                        <td><a href="TeacherUpdate.action?id=${T_My.id}">変更</a></td>
                        
                    </tr>
                    </c:forEach>
                </table>

                <br><hr><br>

                <div>ユーザー一覧：${AllList.size()}件</div>
                <c:choose>
                <c:when test="${ authority=='1' || authority=='2'}">
                <table class="table">
                    <tr>
                        <th>学校コード</th>
                        <th>氏名</th>
                        <th>id</th>
                        <th></th>
                        
                    </tr>
                        <c:forEach var="T_all" items="${AllList}">
                    <tr>
                        <td>${T_all.school.getName()}</td>
                        <td>${T_all.name}</td>
                        <td>${T_all.id}</td>
                        
                            
                        <td><a href="TeacherUpdate.action?id=${T_all.id}">変更</a></td>
                        
                    </tr>
                    </c:forEach>
                </table>
                </c:when>
                <c:otherwise>
                <table class="table">
                    <tr>
                        <th>学校コード</th>
                        
                        <th>氏名</th>
                    </tr>
                    <c:forEach var="T_ALL" items="${AllList}">
                        <tr>
                            <td>${T_ALL.school.getName()}</td>
                            <td>${T_ALL.name}</td>
                        </tr>
                    </c:forEach>
                </table>
                </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <div>ユーザー情報が存在しませんでした。</div>
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
