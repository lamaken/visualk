
function xmlhttpPost(strURL,divId,data) {
    var xmlHttpReq = false;
    var self = this;
    // Mozilla/Safari
    if (window.XMLHttpRequest) {
        self.xmlHttpReq = new XMLHttpRequest();
    }
    // IE
    else if (window.ActiveXObject) {
        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    
    self.xmlHttpReq.open('POST', strURL, true);
    self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    self.xmlHttpReq.onreadystatechange = function() {
        if (self.xmlHttpReq.readyState == 4) {
            updatepage(self.xmlHttpReq.responseText);
        }
    }
    self.xmlHttpReq.send(getquerystring());
}

function getquerystring() {
    var params="";
    for(i=0; i<document.fmain.elements.length; i++)
    {
       var fieldName = document.fmain.elements[i].name;
       var fieldValue = document.fmain.elements[i].value;
       params += fieldName + '=' + fieldValue + '&';
    }
    params+="pino=1";
    return(params);
}

function updatepage(str){
	
	
	document.getElementById("pino").innerHTML = str;
	
	uniq = new Date();
	uniq = uniq.getTime();
	
	img=document.images["HrzCanvasImg"].src;
	index = img.lastIndexOf("&amp;");
	if(index > 0){
	img = img.substr(0, index);
	}
	document.images["HrzCanvasImg"].src = img+"&amp;uniq="+uniq;
	
}

