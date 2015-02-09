<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/header/tags.jsp"%>
<!doctype html>
<html>
<head>
<%@ include file="/jsp/header/header.jsp"%>
<title>new Student</title>
<script src="<c:url value='/js/stu-info/newStudent.js'/>"></script>
<style type="text/css">
.infobox {margin-left:20px; margin-right:20px; padding: 20px 10px;border: 1px solid #ccc;background-color: #fff;box-shadow: 0 1px 2px rgba(0, 0, 0, 0.11);font-size: 14px;zoom: 1;}
.infobox li {height:30px; padding:6px 0;overflow: hidden;zoom:1;}
.infobox input{font-size: 14px;vertical-align: middle;}
.infobox label{display: inline-block; width: 100px; text-align: left;vertical-align: middle;line-height: 30px;}

</style>
<script type="text/javascript">
	
</script>
</head>


<body>
	<div class="cf" id="hd">
		<p class="fl welcome"></p>
	</div>
	<div id="bd">
		<div class="col-main">
			<div class="m-top">add a new student</div>
			<div class="infobox">
				<ul>
					<li><label class="txt">name:</label> <input type="text"
						name="student.CName" class="ui-input vch-summary" />
					</li>
					<li><label class="txt">sex:</label>
						<div class="ui-combo-wrap" id="stu-sex">
							<input type="text" name="student.NSex" class="input-txt vch-mark"
								autocomplete="off"> <span class="trigger"></span>
						</div>
					</li>
					<li><label class="txt">englishlevel:</label> <input
						type="text" name="student.CEnglishlevel"
						class="ui-input vch-summary" />
					</li>
					<li><label class="txt">address:</label> <input type="text"
						name="student.CAddress" class="ui-input vch-summary" />
					</li>
					<li><label class="txt">timeZone:</label>
						<div class="ui-combo-wrap" id="stu-timeZone">
							<input type="text" name="student.NTimeZone"
								class="input-txt vch-mark" autocomplete="off"> <span
								class="trigger"></span>
						</div>
					</li>
					<li><label class="txt">aim:</label> <input type="text"
						name="student.CAddress" class="ui-input vch-summary" />
					</li>
				</ul>
					<a class="ui-btn ui-btn-sp" id="renewB">save and new</a>
					<a class="ui-btn m0" id="saveB">save</a>
			</div>
			<div class="col-extra"></div>
		</div>
	</div>
</body>
</html>
