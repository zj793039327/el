<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%@ include file="/component/jquery/jquery-include.jsp"%>
<%@ include file="/component/ligerui/ligerui-include.jsp"%>

<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<script>
/**
 * define sys consts
 */
 Sys.DEFAULT_PLUGIN_PATH = "";
 
function Sys() {
    this._pluginPath = Sys.DEFAULT_PLUGIN_PATH;
}
Sys.prototype.getContextPath = function(){
    return  "<%=request.getContextPath()%>";
};
Sys.prototype.getCompletePath = function() {
    return "<%=basePath%>";
	};
	sys = new Sys();
</script>

<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/common.css'/>">
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/ui.css'/>">
<script src="<c:url value='/js/common.js'/>"></script>
