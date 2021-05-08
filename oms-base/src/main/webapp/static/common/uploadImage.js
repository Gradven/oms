function previewFunc() {
	document.getElementById("upload_img").click();
}
// 值改变的话
document.getElementById("upload_img").onchange = function() {
	console.log(123);
	var sender = this;
	if (!sender.value.match(/.jpg|.gif|.png|.bmp/i)) {
		alert('图片格式无效！');
		return false;
	}
	var objPreviewImg = document.getElementById('preview_img');
	var objPreviewDiv = document.getElementById('preview_div');
	if (navigator.userAgent.indexOf("MSIE") > -1) {
		// IE浏览器的话
		try {
			objPreviewImg.src = createFileObj(this.files[0]);
		} catch (e) {
			// ie7中不兼容出错跳到这里
			this.select(); // 选择的时候
			top.parent.document.body.focus(); // 如果要嵌套到iframe中进行显示的话，需要加这个将焦点聚到iframe里面
			this.blur(); // 必须要加这个
			var src = document.selection.createRange().text; // IE
																// 11中改为如下:document.selection
																// ---》
																// window.getSelection
			document.selection.empty();
			objPreviewImg.style.display = "none"; // 隐藏img控件
			objPreviewDiv.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			objPreviewDiv.filters
					.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
		}
	} else {
		// chrome firfox都可以
		objPreviewImg.src = createFileObj(this.files[0]);
	}
}
function createFileObj(filePath) {
	var url = null;
	if (window.createObjectURL != undefined) {
		url = window.createObjectURL(filePath);
	} else if (window.URL != undefined) {
		url = window.URL.createObjectURL(filePath);
	} else if (window.webkitURL != undefined) {
		url = window.webkitURL.createObjectURL(filePath);
	} else {
	}
	return url;
}
