<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="detail" value="/actions/person/detail.htm" />

<html>
<body>
    <h1>Personnes</h1>

    <ul>
        <c:forEach items="${persons}" var="p">
            <li><a href="${detail}?id=${p.login}">${p.login} : ${p.firstName} ${p.lastName}</a></li>
        </c:forEach>
    </ul>
</body>
</html>