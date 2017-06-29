<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users Manager: Searching results</title>

    <style>
        .my_button {
            width:150px;
            height: 30px;
        }

        .search {
            width:120px;
        }


        .tab th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #6699CC;
        }

        .tab td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color:#CCFFFF;
        }




    </style>

</head>
<body>
<br>
<a href="/users">Come Back</a>

<br><br>
<form action="/search">
    <table width="300">
        <tr><td>
            <input type="text" name="username" id="username" placeholder="type name here.."/>
        </td><td> <input class="search" type='submit' value='Search'/>
        </td></tr>
    </table>
</form>
<h3>Searching results</h3>
<c:if test="${!empty holder}">

    <table class="tab" cellspacing="0">
        <thead> <tr>
            <th width="50">ID</th>
            <th width="150">Name</th>
            <th width="50">Age</th>
            <th width="40">IsAdmin</th>
            <th width="180">CreatedDate</th>
            <th width="50">Edit</th>
            <th width="60">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${holder.pageList}">

            <tr>
                <td width="50">${u.id}</td>
                <td width="150">${u.name}</td>
                <td width="50">${u.age}</td>
                <td width="40">${u.admin}</td>
                <td width="180">${u.createdDate}</td>
                <td width="50"><a href="<c:url value='/edit/${u.id}'/>">edit</a></td>
                <td width="60"><a href="<c:url value='/remove/${u.id}'/>">delete</a></td>

            </tr>



        </c:forEach>
        </tbody>
    </table>

</c:if>


<br>


<div id="pagination">

    <c:url value="/search/" var="prev">
        <c:param name="page" value="${page-1}"/>
        <c:param name="username" value="${username}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/search/" var="url">
                    <c:param name="page" value="${i.index}"/>
                    <c:param name="username" value="${username}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/search/" var="next">
        <c:param name="page" value="${page + 1}"/>
        <c:param name="username" value="${username}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>























<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
































</body>
</html>
