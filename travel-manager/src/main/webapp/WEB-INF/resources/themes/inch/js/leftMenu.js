// JavaScript Document
n4 = (document.layers)?1:0
e4 = (document.all)?1:0;
// --- 获取ClassName
document.getElementsByClassName = function(cl) {
var retnode = [];
var myclass = new RegExp('\\b'+cl+'\\b');
var elem = document.body.getElementsByTagName('*');
for (var j = 0; j < elem.length; j++) {
var classes = elem[j].className;
if (myclass.test(classes)) retnode.push(elem[j]);
}
return retnode;
}
// --- 隐藏所有
function HideAll() {
var items = document.getElementsByClassName("optiton");
for (var j=0; j<items.length; j++) {
items[j].style.display = "none";
}
}

function showsubmenu(sid)
{
	var whichEl = document.getElementById("tt" + sid);
	if (whichEl.style.display=='block')
		{
		document.getElementById("tt" + sid).style.display="none";
		//eval("t" + sid + ".src=\"plus.gif\";");
       document.getElementById("t" + sid).innerHTML="＋";

		}
	else
		{
		document.getElementById("tt" + sid).style.display="block";
        document.getElementById("t" + sid).innerHTML="－";
	}
}