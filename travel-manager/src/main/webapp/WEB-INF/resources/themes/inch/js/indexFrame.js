var IE6 = false;
if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0") {
    //document.execCommand("BackgroundImageCache", false, true);
    IE6 = true;
}
  function switchSysBar(){
	 var slide = document.getElementById("slide");
	 var left_frame = document.getElementById("left_frame");
	 var right_frame = document.getElementById("right_frame");
    if (left_frame.style.display == "none"){
	  slide.style.backgroundImage = "url(/inch/themes/inch/images/barLeft.gif)";
      left_frame.style.display="block";
	  right_frame.style.marginLeft='210px';
	  if(IE6){
		  left_frame.style.marginRight = "-3px";
		  right_frame.style.marginLeft = "0";
	  }
	  slide.style.left = "210px"; 
	  //document.all("frmmd").style.width='2%';
    }
    else{
	  slide.style.backgroundImage = "url(/inch/themes/inch/images/barRight.gif)";
      left_frame.style.display="none"
	  right_frame.style.marginLeft='0';
	  slide.style.left = "0px"; 
    }
  }
  
