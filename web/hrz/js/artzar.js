

function doneDialog(name){
	document.fmain.option.value=name;
	document.fmain.what.value="guarda";
	document.fmain.submit();
}
function closeDialog(id_div){
	document.getElementById(id_div).style.visibility="hidden";
}

function guarda(id_div){//genera un horitzo al atzar
	
	document.getElementById(id_div).style.visibility="visible";
	document.formName.inputAnswer.focus();//input box
}
function carrega(){
	
	document.fmain.what.value="carrega";
	document.fmain.where.value="listhorizons";
	document.fmain.submit();
	
}

function refresca(){//genera un horitzo al atzar
	
	document.fmain.what.value="gen_atzar";
	updateHrz();
}

function colorsRnd(){//canvia colors a l'atzar
	
	document.fmain.what.value="colorsRnd";
	updateHrz();
}
function posicioRnd(){
	document.fmain.what.value="posicioRnd";
	updateHrz();
}

function canvasRnd(){
	document.fmain.what.value="canvasRnd";
	updateHrz();
}

function superRnd(){
	document.fmain.what.value="superRnd";
	updateHrz();
}

function hombraRnd(){
	document.fmain.what.value="hombraRnd";
	updateHrz();
}
function updateHrz(){

	//if(navId=="ok"){
		xmlhttpPost(SERVLET_HRZ_URL);
	//}else document.fmain.submit();
	
}


