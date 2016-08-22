/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



function openDialog(id_div){//genera un horitzo al atzar
	document.getElementById(id_div).style.visibility="visible";
	document.formName.inputAnswer.focus();//input box
}

function doneDialog(name){
	document.fmain.option.value=name;
	document.fmain.what.value="guarda";
	document.fmain.submit();
}
function closeDialog(id_div){
	document.getElementById(id_div).style.visibility="hidden";
}

