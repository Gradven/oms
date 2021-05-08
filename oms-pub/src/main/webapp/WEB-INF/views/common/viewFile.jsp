<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>附件查看</title>
</head>
<body>
	<c:choose>
		<c:when test="${fileType == 'image'}">
			<img src="${encryptedUrl}" style="max-width:1024;min-width:200"/>
		</c:when>
		<c:when test="${fileType == 'video'}">
			<video src="${encryptedUrl}" controls="controls" autoplay="autoplay" style="max-width:1024;min-width:200">
				您的浏览器不支持在线观看视频，请<a href="${encryptedUrl}">点击此处</a>直接下载。
			</video>
		</c:when>
		<c:when test="${fileType == 'pdf'}">
			<embed width="100%" height="100%" src="${encryptedUrl}"></embed> 
		</c:when>
		<c:otherwise>
			不支持在线预览的文件格式，请<a href="${encryptedUrl}">点击此处</a>直接下载。
		</c:otherwise>
	</c:choose>
</body>
</html>