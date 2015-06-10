var oDropDown=null; 
var oDropDownBtn=null;
var aLi=[];

//var oImgInitSize={x:0, y:0};

var aTimer=[];
var aScale=[];

window.onload=function ()
{
	var oDiv=document.getElementById('topInput');
	var aDiv=oDiv.getElementsByTagName('div');
	//var oTmpContainer=document.getElementById('tmp_container');
	//var oTmpParent=null;
	
	//var oDivAll=document.getElementById()
	
	var oInput=oDiv.getElementsByTagName('input')[0];
	//var oImgTitle=oDiv.getElementsByTagName('img')[0];
	//var oImg=null;
	var i=0;
	
	oDropDown=oDiv.getElementsByTagName('ul')[0];
	
	aLi=oDropDown.getElementsByTagName('li');
	
	for(i=0;i<aDiv.length;i++)
	{
		switch(aDiv[i].className)
		{
			case 'link':
				oDropDownBtn=aDiv[i];
				break;
		}
	}
	
	oDropDownBtn.onmousedown=function (ev)
	{
		if(this.className=='active')
		{
			hideDropDown(ev);
			oDropDownBtn.className='hover';
		}
		else
		{
			showDropDown(ev);
		}
	};
	oDropDownBtn.onmouseover=function ()
	{
		if(oDropDownBtn.className!='active')
		{
			this.className='hover';
		}
	};
	oDropDownBtn.onmouseout=function ()
	{
		if(oDropDownBtn.className!='active')
		{
			this.className='link';
		}
	};
	document.body.onmousedown=hideDropDown;
	
	for(i=0;i<aLi.length;i++)
	{
		aLi[i].miaovIndex=i;
		aLi[i].onmouseover=doScale;
		aLi[i].onmousedown=function ()
		{
			oInput.value=this.innerHTML;
			for(j=0;j<aLi.length;j++){
				document.getElementById("topMenu_"+aLi[j].id).style.display='none';
			}
			document.getElementById("left_frameMenu").src="operate!menuLists.action?operate.id="+this.id
			document.getElementById("topMenu_"+this.id).style.display='block';
		};
		aTimer[i]=null;
		aScale[i]=10;
	}
	
	aScale[1]=16;
	aScale[2]=20;
	aScale[3]=16;
};

function showDropDown(ev)
{
	var oEvent=window.event || ev;
	oDropDown.style.display='block';
	oDropDownBtn.className='active';
	
	oEvent.cancelBubble=true;
}

function hideDropDown(ev)
{
	var oEvent=window.event || ev;
	
	oDropDown.style.display='none';
	oDropDownBtn.className='link';
	
	oEvent.cancelBubble=null;
}

function doScale()
{
	var iScale=0;
	var iPading=0;
	
	for(i=0;i<aLi.length;i++)
	{
		switch(Math.abs(i-this.miaovIndex))
		{
			case 1:
				iScale=16;
				iPading=6;
				break;
			case 0:
				iScale=22;
				iPading=10;
				break;
			default:
				iScale=10;
				iPading=4;
		}
		
		aLi[i].className='';
		//aLi[i].getElementsByTagName('span')[0].style.paddingTop=iPading+'px';
		
		setScale(i, iScale);
	}
	
	this.className='bg';
}

function setScale(index, iScale)
{
	if(aTimer[index])
	{
		clearInterval(aTimer[index]);
	}
	aTimer[index]=setInterval("setScaleInner("+index+", "+iScale+")", 30);
}

function setScaleInner(index, iTarget)
{
	var iScale=aScale[index];
	//var oImg=aLi[index].getElementsByTagName('img')[0];
	
	if(iScale==iTarget)
	{
		clearInterval(aTimer[index]);
		aTimer[index]=null;
	}
	else
	{
		if(iScale>iTarget)
		{
			iScale--;
		}
		else
		{
			iScale++;
		}
		//oImg.style.width=oImgInitSize.x*iScale/10+'px';
		//oImg.style.height=oImgInitSize.y*iScale/10+'px';
		
		aScale[index]=iScale;
	}
}