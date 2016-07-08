var lastSel = "";



//lasy load images
var bLazy;


document.addEventListener("DOMContentLoaded", loadLazy, false);


function loadLazy() {
    bLazy = new Blazy({
        
         success: function (element) {
            setTimeout(function () {
               console.log("Element loaded: ", element.nodeName); 
            }, 200);
        }
    });
}

function torna() {
    document.fmain.where.value = "artzar";
    document.fmain.mx.value = window.innerWidth - 100;
    document.fmain.my.value = window.innerHeight - 100;

    document.fmain.what.value = "";//carrega inicial

    document.fmain.submit();
}
function selecciona(idName) {
    document.getElementById(idName).style.border = "solid 3px white";
    document.getElementById(idName).style.padding = "10px";
    if ((lastSel != "") && (lastSel != idName))
        document.getElementById(lastSel).style.border = "";
    lastSel = idName;
}
function edita(nom) {
    document.fmain.what.value = "selecciona";
    document.fmain.nom.value = nom;
    document.fmain.where.value = "listhorizons";
    document.fmain.submit();
}
function myLoad() {
}