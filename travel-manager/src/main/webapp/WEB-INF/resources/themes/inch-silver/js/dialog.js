//var IMGFOLDERPATH = 'http://att2.citysbs.com/hangzhou/2012/08/17/23/middle_235920_omdlkcuc_1ea19f76cd4b04e0aa7d05614d446a6a.jpg';//图片路径配置
//var CONTEXTPATH = '';//弹出框内页面路径配置
var isIE = navigator.userAgent.toLowerCase().indexOf("msie") != -1;
var isIE6 = navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1;
var isGecko = navigator.userAgent.toLowerCase().indexOf("gecko") != -1;
//var isQuirks = document.compatMode == "BackCompat";

function n(ele) {
  if (typeof(ele) == 'string'){
	ele = document.getElementById(ele)
	if(!ele){
		return null;
	}
  }
  return ele;
}

Array.prototype.remove = function(s){
  for(var i=0;i<this.length;i++){
    if(s == this[i]){
    	this.splice(i, 1);
    }
  }
}

var $E = {};
$E.$A = function(attr,ele) {
	ele = ele || this;
	ele = n(ele);
	return ele.getAttribute?ele.getAttribute(attr):null;
}
$E.getTopLevelWindow = function(){
	var pw = window;
	while(pw!=pw.parent){
		pw = pw.parent;
	}
	return pw;
}
$E.hide = function(ele) {
	ele = ele || this;
	ele = n(ele);
  ele.style.display = 'none';
}
$E.show = function(ele) {
	ele = ele || this;
	ele = n(ele);
  ele.style.display = '';
}
$E.visible = function(ele) {
	ele = ele || this;
	ele = n(ele);
	if(ele.style.display=="none"){
		return false;
	}
  return true;
}

function Dialog(strID){
		if(!strID){
			alert("错误的Dialog ID！");
			return;
		}
		this.ID = strID;
		this.Width = 400;
		this.Height = 300;
		this.Top = 0;
		this.Left = 0;
		this.ParentWindow = null;
		this.Window = null;

		this.Title = "";
		this.URL = null;
		this.innerHTML=null
		this.DialogArguments = {};
		this.WindowFlag = false;
		this.ShowButtonRow = true;
		this.Icon = null;
		this.bgdivID=null;
}

Dialog._Array = [];

Dialog.prototype.showWindow = function(){
	if(isIE){
		this.ParentWindow.showModalessDialog( this.URL, this.DialogArguments, "dialogWidth:" + this.Width + ";dialogHeight:" + this.Height + ";help:no;scroll:no;status:no") ;
	}
	if(isGecko){
		var sOption  = "location=no,menubar=no,status=no;toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=no";
		this.Window = this.ParentWindow.open( '', this.URL, sOption, true ) ;
		var w = this.Window;
		if ( !w ){
			alert( "发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!" ) ;
			return ;
		}
		w.moveTo( this.Left, this.Top ) ;
		w.resizeTo( this.Width, this.Height+30 ) ;
		w.focus() ;
		w.location.href = this.URL ;
		w.Parent = this.ParentWindow;
		w.dialogArguments = this.DialogArguments;
	}
}

Dialog.prototype.show = function(){
	//var pw = $E.getTopLevelWindow();
	//var doc = pw.document;
	var doc = window.document;
	var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sl = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var st = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);//考虑滚动的情况
	sw=Math.max(sw,cw);
	sh=Math.max(sh,ch);
//	alert("\n"+cw+"\n"+ch+"\n"+sw+"\n"+sh)

	if (!this.ParentWindow ){
	 	this.ParentWindow = window ;
	}
	this.DialogArguments._DialogInstance = this;
	this.DialogArguments.ID = this.ID;

	if(!this.Height){
		this.Height = this.Width/2;
	}

	if(this.Top==0){
		//this.Top = (ch - this.Height - 30) / 2 + st - 15;
		this.Top = (ch - this.Height) / 2 + st - 20;
	}
	if(this.Left==0){
		this.Left = (cw - this.Width) / 2 + sl - 5;
	}
	if(this.ShowButtonRow){//按钮行高36
		this.Top -= 18;
	}
	
	if(this.WindowFlag){
		this.showWindow();
		return;
	}
	var arr = [];
	arr.push("<div style=\"height:30px;width:"+this.Width+"px;cursor:move;background-color:#F6F6F6;\" id=\"_draghandle_"+this.ID+"\">");
	arr.push("<div style='line-height:30px;text-align:left;padding-left:10px;'>"+this.Title+"：</div>")
	arr.push("<div class='dialog_x_hover' onmouseover=\"this.className='dialog_x_visited'\" onmouseout=\"this.className='dialog_x_hover'\">")
	arr.push("<a title='关闭' onclick=\"Dialog.getInstance('"+this.ID+"').close();\"></a>");
	arr.push("</div></div>");
	arr.push("<div style='width:"+this.Width+"px;height:"+this.Height+"px;background-color:white'>");
	arr.push("<div id='_Covering_"+this.ID+"' style='position:absolute; height:100%; width:100%;display:none;'>&nbsp;</div>");
	if(this.innerHTML){
		arr.push(this.innerHTML);
	}else if(this.URL){
		arr.push("          <iframe src='");
		if(this.URL.substr(0,7)=="http://" || this.URL.substr(0,1)=="/"){
			arr.push(this.URL);
		}else{
			//arr.push(CONTEXTPATH+this.URL);
			arr.push(this.URL);
		}
		arr.push("' id='_DialogFrame_"+this.ID+"' allowTransparency='true'  width='100%' height='100%' frameborder='0' scrolling='auto' style=\"background-color: #transparent; border:none;\"></iframe>");
	}
	arr.push("</div>");
	arr.push("<div id='_ButtonRow_"+this.ID+"' style='background-color:#F6F6F6;width:"+this.Width+"px;height:36px;text-align:right;'>");
	arr.push("<input type='button' class='btn_bg' id='_ButtonOK_"+this.ID+"' value='确定'>")
	arr.push("<input type='button' class='btn_bg' id='_ButtonCancel_"+this.ID+"' onclick=\"Dialog.getInstance('"+this.ID+"').close();\" value='取消'></div>")

	var bgdiv = n("_DialogBGDiv");
	if(!bgdiv){
		bgdiv = document.createElement("div");
		bgdiv.id = "_DialogBGDiv";
		document.body.appendChild(bgdiv);
		$E.hide(bgdiv);
		if(isIE6){
			var bgIframeBox=document.createElement('<div style="position:relative;width:100%;height:100%;"></div>');
			var bgIframe=document.createElement('<iframe src="about:blank" style="filter:alpha(opacity=1);" width="100%" height="100%"></iframe>');
			var bgIframeMask=document.createElement('<div src="about:blank" style="position:absolute;background-color:#333;filter:alpha(opacity=1);width:100%;height:100%;"></div>');
			bgIframeBox.appendChild(bgIframeMask);
			bgIframeBox.appendChild(bgIframe);
			bgdiv.appendChild(bgIframeBox);
			var bgIframeDoc = bgIframe.contentWindow.document;
			bgIframeDoc.open();
			bgIframeDoc.write("<body style='background-color:#333' oncontextmenu='return false;'></body>") ;
			bgIframeDoc.close();
		}
	}
	var div = n("_DialogDiv_"+this.ID);
	if(!div){
		div = document.createElement("div");
		div.id = "_DialogDiv_"+this.ID;
		document.body.appendChild(div);
		$E.hide(div);
	}

	this.DialogDiv = div;
	div.innerHTML = arr.join('\n');

	n("_DialogDiv_"+this.ID).DialogInstance = this;
	if(this.URL)
		n("_DialogFrame_"+this.ID).DialogInstance = this;
	Drag.init(n("_draghandle_"+this.ID),n("_DialogDiv_"+this.ID));//注册拖拽方法
	if(!isIE){
		n("_DialogDiv_"+this.ID).dialogId=this.ID;
		n("_DialogDiv_"+this.ID).onDragStart = function(){n("_Covering_"+this.dialogId).style.display=""}
		n("_DialogDiv_"+this.ID).onDragEnd = function(){n("_Covering_"+this.dialogId).style.display="none"}
	}

	this.OKButton = n("_ButtonOK_"+this.ID);
	this.CancelButton = n("_ButtonCancel_"+this.ID);

	
	//显示按钮栏
	if(!this.ShowButtonRow){
		$E.hide("_ButtonRow_"+this.ID);
	}
	if(this.CancelEvent){
		this.CancelButton.onclick = this.CancelEvent;
	}
	if(this.OKEvent){
		this.OKButton.onclick = this.OKEvent;
	}
	
	if(!this.AlertFlag){
		//$E.show(bgdiv);
		this.bgdivID = "_DialogBGDiv";
	}else{
		bgdiv = n("_AlertBGDiv");
		if(!bgdiv){
			bgdiv = document.createElement("div");
			bgdiv.id = "_AlertBGDiv";
			$E.hide(bgdiv);
			document.body.appendChild(bgdiv);
			if(isIE6){
				var bgIframeBox=document.createElement('<div style="position:relative;width:100%;height:100%;"></div>');
				var bgIframe=document.createElement('<iframe src="about:blank" style="filter:alpha(opacity=1);" width="100%" height="100%"></iframe>');
				var bgIframeMask=document.createElement('<div src="about:blank" style="position:absolute;background-color:#333;filter:alpha(opacity=1);width:100%;height:100%;"></div>');
				bgIframeBox.appendChild(bgIframeMask);
				bgIframeBox.appendChild(bgIframe);
				bgdiv.appendChild(bgIframeBox);
				var bgIframeDoc = bgIframe.contentWindow.document;
				bgIframeDoc.open();
				bgIframeDoc.write("<body style='background-color:#333' oncontextmenu='return false;'></body>") ;
				bgIframeDoc.close();
			}
			bgdiv.style.cssText = "display:none;background-color:#333;position:absolute;left:0px;top:0px;opacity:0.4;filter:alpha(opacity=40);width:100%;height:" + sh + "px;z-index:991";
		}
		///$E.show(bgdiv);
		this.bgdivID = "_AlertBGDiv";
	}
	this.DialogDiv.style.cssText = "position:absolute;border:5px solid #0A5881;display:block;z-index:"+(this.AlertFlag?992:990)+";left:"+this.Left+"px;top:"+this.Top+"px";

	//判断当前窗口是否是对话框，如果是，则将其置在bgdiv之后
	if(!this.AlertFlag){
		var win = window;
		var flag = false;
		while(win!=win.parent){//需要考虑父窗口是弹出窗口中的一个iframe的情况
			if(win._DialogInstance){
				win._DialogInstance.DialogDiv.style.zIndex = 959;
				flag = true;
				break;
			}
			win = win.parent;
		}
		if(!flag){
			bgdiv.style.cssText = "display:none;background-color:#333;position:absolute;left:0px;top:0px;opacity:0.4;filter:alpha(opacity=40);width:100%;height:" + sh + "px;z-index:960";
			//$E.hide(bgdiv);
		}
		$E.show(bgdiv);
	}
	//this.OKButton.focus();
	//var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
	//pwbody.style.overflow="hidden";//禁止出现滚动条
	Dialog._Array.push(this.ID);//放入队列中，以便于ESC时正确关闭
}


Dialog.prototype.close = function(){
	if(this.WindowFlag){
		this.ParentWindow.$D = null;
		this.ParentWindow.$DW = null;
		this.Window.opener = null;
		this.Window.close();
		this.Window = null;
	}else{
		//如果上级窗口是对话框，则将其置于bgdiv前
		var pw = $E.getTopLevelWindow();
		var doc=pw.document;
		var win = window;
		var flag = false;
		while(win!=win.parent){
			if(win._DialogInstance){
				flag = true;
				win._DialogInstance.DialogDiv.style.zIndex = 960;
				break;
			}
			win = win.parent;
		}
		if(this.AlertFlag){
			$E.hide(n("_AlertBGDiv"));
		}
		if(!flag&&!this.AlertFlag){//此处是为处理弹出窗口被关闭后iframe立即被重定向时背景层不消失的问题
			eval('window._OpacityFunc = function(){$E.hide(window.n("_DialogBGDiv"));}');
			_OpacityFunc();
		}
		this.DialogDiv.innerHTML = "";
		$E.hide(n("_DialogDiv_"+this.ID));
		//var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
		//pwbody.style.overflow="auto";//还原滚动条
		Dialog._Array.remove(this.ID);
	}
}

Dialog.close = function(evt){
	window.Args._DialogInstance.close();
}

Dialog.getInstance = function(id){
	var pw = $E.getTopLevelWindow()
	var f = n("_DialogDiv_"+id);
	if(!f){
		return null;
	}
	return f.DialogInstance;
}

Dialog.AlertNo = 0;

Dialog.alert = function(msg,func,w,h){
	var pw = $E.getTopLevelWindow()
	//var diag = new Dialog("_DialogAlert"+Dialog.AlertNo++);
	var diag = new Dialog("_DialogAlert0");
	diag.ShowButtonRow = true;
	diag.ParentWindow = pw;
	diag.Width = w?w:200;
	diag.Height = h?h:100;
	diag.Title = "系统提示";
	diag.URL = false;
	diag.AlertFlag = true;
	diag.CancelEvent = function(){
		diag.close();
		if(func){
			func();
		}
	};
		
	var arr = [];
	//arr.push("<div align='center' style='height:100%'>"+msg+"</div>");
	arr.push("<table height='100%' width='100%' border='0' align='center' cellpadding='10' cellspacing='0'>")
	arr.push("<tr><td>"+msg+"</td></tr></table>")
	diag.innerHTML = arr.join('');
	diag.show();
	$E.show(n("_AlertBGDiv"));
	$E.hide(n("_ButtonOK_"+diag.ID));
	diag.CancelButton.value = "确 定";
	diag.CancelButton.focus();
	n("_ButtonRow_"+diag.ID).style.textAlign = "center";
}

Dialog.confirm = function(msg,func1,func2,w,h){
	var pw = $E.getTopLevelWindow()
	//var diag = new Dialog("_DialogAlert"+Dialog.AlertNo++);
	var diag = new Dialog("_DialogConfirm0");
	diag.ShowButtonRow = true;
	diag.Width = w?w:300;
	diag.Height = h?h:120;
	diag.Title = "信息确认";
	diag.URL = false;
	diag.AlertFlag = true;
	diag.CancelEvent = function(){
		diag.close();
		if(func2){
			func2();
		}
	};
	diag.OKEvent = function(){
		diag.close();
		if(func1){
			func1();
		}
	};
	
	var arr = [];
	arr.push("<table height='100%' width='100%' border='0' align='center' cellpadding='10' cellspacing='0'>");
	arr.push("<tr><td align='center'>"+msg+"</td></tr></table>");
	diag.innerHTML = arr.join('');
	diag.show();
	$E.show(n("_AlertBGDiv"));
	diag.CancelButton.focus();
	n("_ButtonRow_"+diag.ID).style.textAlign = "center";
}


Dialog.box = function(title,html,w,h,func1,func2){
	var pw = $E.getTopLevelWindow()
	//var diag = new Dialog("_DialogAlert"+Dialog.AlertNo++);
	var diag = new Dialog("_DialogBox0");
	diag.Width = w?w:300;
	diag.Height = h?h:120;
	diag.Title = title;
	diag.URL = false;
	diag.AlertFlag = true;
	diag.CancelEvent = function(){
		if(func2){
			func2();
		}
		diag.close();
	};
	diag.OKEvent = function(){
		if(func1){
			func1();
		}
		diag.close();
	};
	diag.innerHTML = html;
	diag.show();
	$E.show(n("_AlertBGDiv"));
	diag.CancelButton.focus();
}

Dialog.setPosition=function(){
	var DialogArr=Dialog._Array;
	if(DialogArr==null||DialogArr.length==0)return;
	for(i=0;i<DialogArr.length;i++){
		n("_DialogDiv_"+DialogArr[i]).DialogInstance.setPosition();
	}
}
Dialog.prototype.setPosition=function(){
	//var pw = $E.getTopLevelWindow();
	//var doc = pw.document;
	var doc = window.document;
	var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sl = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var st = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	sw=Math.max(sw,cw);
	sh=Math.max(sh,ch);
	//this.Top = (ch - this.Height - 30) / 2 + st - 8;//有8像素的透明背景
	this.Top = (ch - this.Height) / 2 + st - 20;
	this.Left = (cw - this.Width) / 2 + sl - 5;
	if(this.ShowButtonRow){//按钮行高36
		this.Top -= 18;
	}
	this.DialogDiv.style.top=this.Top+"px";
	this.DialogDiv.style.left=this.Left+"px";
	//pw.n(this.bgdivID).style.width= sw + "px";
	n(this.bgdivID).style.height= sh + "px";
}

var Drag={
    "obj":null,
	"init":function(handle, dragBody, e){
		if (e == null) {
			handle.onmousedown=Drag.start;
		}
		handle.root = dragBody;

		if(isNaN(parseInt(handle.root.style.left)))handle.root.style.left="0px";
		if(isNaN(parseInt(handle.root.style.top)))handle.root.style.top="0px";
		handle.root.onDragStart=new Function();
		handle.root.onDragEnd=new Function();
		handle.root.onDrag=new Function();
		if (e !=null) {
			var handle=Drag.obj=handle;
			e=Drag.fixe(e);
			var top=parseInt(handle.root.style.top);
			var left=parseInt(handle.root.style.left);
			handle.root.onDragStart(left,top,e.pageX,e.pageY);
			handle.lastMouseX=e.pageX;
			handle.lastMouseY=e.pageY;
			document.onmousemove=Drag.drag;
			document.onmouseup=Drag.end;
		}
	},
	"start":function(e){
		var handle=Drag.obj=this;
		e=Drag.fixEvent(e);
		var top=parseInt(handle.root.style.top);
		var left=parseInt(handle.root.style.left);
		handle.root.onDragStart(left,top,e.pageX,e.pageY);
		handle.lastMouseX=e.pageX;
		handle.lastMouseY=e.pageY;
		document.onmousemove=Drag.drag;
		document.onmouseup=Drag.end;
		return false;
	},
	"drag":function(e){
		e=Drag.fixEvent(e);
							
		var handle=Drag.obj;
		var mouseY=e.pageY;
		var mouseX=e.pageX;
		var top=parseInt(handle.root.style.top);
		var left=parseInt(handle.root.style.left);
		
		if(isIE){Drag.obj.setCapture();}else{e.preventDefault();};//作用是将所有鼠标事件捕获到handle对象，对于firefox，以用preventDefault来取消事件的默认动作：

		var currentLeft,currentTop;
		currentLeft=left+mouseX-handle.lastMouseX;
		currentTop=top+(mouseY-handle.lastMouseY);
		handle.root.style.left=currentLeft +"px";
		handle.root.style.top=currentTop+"px";
		handle.lastMouseX=mouseX;
		handle.lastMouseY=mouseY;
		handle.root.onDrag(currentLeft,currentTop,e.pageX,e.pageY);
		return false;
	},
	"end":function(){
		if(isIE){Drag.obj.releaseCapture();};//取消所有鼠标事件捕获到handle对象
		document.onmousemove=null;
		document.onmouseup=null;
		Drag.obj.root.onDragEnd(parseInt(Drag.obj.root.style.left),parseInt(Drag.obj.root.style.top));
		Drag.obj=null;
	},
	"fixEvent":function(e){//格式化事件参数对象
		var sl = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
		var st = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		if(typeof e=="undefined")e=window.event;
		if(typeof e.layerX=="undefined")e.layerX=e.offsetX;
		if(typeof e.layerY=="undefined")e.layerY=e.offsetY;
		if(typeof e.pageX == "undefined")e.pageX = e.clientX + sl - document.body.clientLeft;
		if(typeof e.pageY == "undefined")e.pageY = e.clientY + st - document.body.clientTop;
		return e;
	}
};

if(isIE){
	window.attachEvent('onresize',Dialog.setPosition);
}else{
	window.addEventListener('resize',Dialog.setPosition,false);
}