<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/header/tags.jsp"%>
<!doctype html>
<html>
<head>
<%@ include file="/jsp/header/header.jsp"%>
<title>ELDBindex</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/index.css'/>">
</head>

<body>
	<header>
		<section id="global-nav"></section>
	</header>
	<div id="container" class="cf">
		<div id="col-side">
			<ul id="nav">
				<li class="item item-courses"><a
	d				href='<c:url value="/queryStutent.action"/>' rel="pageTab"
					class="course main-nav" tabid="courses" data-requiredright="91">courses</a>
				</li>
				<li class="item item-information"><a
					href='<c:url value="/jsp/stu-info/newStudent.jsp"/>' rel="pageTab"
					class="information main-nav" tabid="information"
					data-requiredright="151">information</a></li>
				<li class="item item-data-sync"><a
					href="http://www.google.com.hk" rel="pageTab"
					class="data-sync main-nav" tabid="data-sync"
					data-requiredright="151">data-sync</a></li>
				<li class="item item-setting"><a
					href="http://www.google.com.hk" rel="pageTab"
					class="setting main-nav" tabid="setting" data-requiredright="151">setting</a>
				</li>
			</ul>
		</div>
		<div id="col-main">
			<div id="main-hd" class="cf"></div>
			<div id="main-bd">
				<div id="page-tab" class="page-tab"></div>
			</div>
		</div>
		<!-- 注意引入js的时机呐.....看看js的装载过程吧  by z -->
		<script src="<c:url value='/js/index.js'/>"></script>
	</div>

</body>
</html>
