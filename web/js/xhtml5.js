
var navId;
var langId;


function getLang(){
	langId = navigator.browserLanguage;
        
}

function getAgent(){
	var agent = navigator.userAgent.toLowerCase(); 
		
	var isMoz = (agent.indexOf('mozilla')!=-1);
	var isIE = (agent.indexOf('msie')!=-1);
	var isSafari = (agent.indexOf('safari')!=-1);
	var isOpera = (agent.indexOf('opera')!=-1);
	var isChrome = (agent.indexOf('chrome')!=-1);
	
	if(isIE)navId="ie";
	else if(isMoz||isSafari||isOpera||isChrome){
		navId="ok";
	}else navId="unknown";
}

function init(){
	getAgent();
	getLang();
        
     
}

function redirect(){
	document.location="hrz/index.html";
}




   