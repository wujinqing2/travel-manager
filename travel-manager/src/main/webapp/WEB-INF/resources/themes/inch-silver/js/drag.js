// JavaScript Document
function Drag(o, e) {
	var e = window.event || e;
	var _x = e.offsetX || e.layerX;
	var _y = e.offsetY || e.layerY;
	o.style.filter = 'Alpha(opacity=100)';
	o.style.opacity = '1.0';
	document.onmousemove = function(e) {
		var e = window.event || e;
		o.style.left = e.clientX - _x + 'px';
		o.style.top = e.clientY - _y + 'px';
		o.style.cursor = "move";
	}
	document.onmouseup = function(e) {
		document.onmousemove = null;
		o.style.filter = o.style.opacity = '';
		o.style.cursor = "default";
	}
}