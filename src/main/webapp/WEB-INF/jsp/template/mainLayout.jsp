<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<title>Data Porter - ${param.title}</title>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

		<link rel="icon" href="<c:url value="/assets/img/favicon.png" />" type="image/png" />

		<jsp:include page="../frag/commonHeadCss.jsp" />

		<c:if test="${not empty param.cssIncludes}">
			<jsp:include page="${param.cssIncludes}" />
		</c:if>

		<jsp:include page="../frag/commonHeadJsIncludes.jsp" />

		<c:if test="${not empty param.headJsIncludes}">
			<jsp:include page="${param.headJsIncludes}" />
		</c:if>
	</head>

	<body>
		<div id="bodyContainer">
			<div id="topContainer">
				<jsp:include page="../frag/top.jsp" />
			</div>

			<div id="contentContainer">
				<div class="pageHeader">
					Data Porter - ${param.pageHeader}
				</div>
				<jsp:include page="${param.bodyContent}" />
			</div>

			<div id="bottomContainer">
				<jsp:include page="../frag/bottom.jsp" />
			</div>
		</div>

		<jsp:include page="../frag/commonBodyJsIncludes.jsp" />

		<c:if test="${not empty param.bodyJsIncludes}">
			<jsp:include page="${param.bodyJsIncludes}" />
		</c:if>

		<jsp:include page="../frag/commonBodyJsSetUp.jsp" />

		<c:if test="${not empty param.bodyJsSetUp}">
			<jsp:include page="${param.bodyJsSetUp}" />
		</c:if>
	</body>
</html>
