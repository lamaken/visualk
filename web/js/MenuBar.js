/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//17.juliol.2013


var MenuBar;

var NUM_PARAMS=6;

function cMenuItem(id_item,id,caption,onclick,parent,show){
    this.caption=caption;
    this.onclick=onclick;
    this.id=id;
    this.id_item=id_item;
    this.parent=parent;
    this.show=show;
    this.num++;
}

function cMenu() {
    this.allItems = new Array;
    this.num=0;
   
   
}
/*
 * return(this.label.toHtml()+
                ","+this.id_item+
                ","+this.onclick_item+
                ","+this.id+
                ","+this.parent+
                ","+this.show+",");
 **/

cMenu.prototype.load= function load(src,ch,iditem) {
    var result = new Array();
    var k=0;
    document.getElementById("submenu").style.visibility="hidden";
    result = src.split(ch);
    var size=(result.length-1)/NUM_PARAMS;
    for(var n = 0;n<size;n++){
        var caption=result[n*NUM_PARAMS];
        var id_item=result[(n*NUM_PARAMS)+1];
        var onclick=result[(n*NUM_PARAMS)+2];
        var id=result[(n*NUM_PARAMS)+3];
        var parent=result[(n*NUM_PARAMS)+4];
        var show=result[(n*NUM_PARAMS)+5]; //que no tingui oest    
        // alert("id_item="+id_item+" iditem="+iditem+" id="+id+" parent="+parent);
        if(id==iditem){
            mainparent = id_item;
        }
        if(parent!=""){
            document.getElementById("menuitem_"+id).style.padding="0px";
            document.getElementById("menuitem_"+id).style.background="red";
            document.getElementById("menuitem_"+id).style.visibility="hidden";
        }    
        this.allItems[k++]=new cMenuItem(id_item,id,caption,onclick,parent,show);
        
    }
    var popup="";
    var z;
    var existe=0;
    for(z=0;z<k;z++){
        if(this.allItems[z].parent==mainparent){
            document.getElementById("menuitem_"+this.allItems[z].id).style.padding="10px";
            document.getElementById("menuitem_"+this.allItems[z].id).style.background="red";
            document.getElementById("menuitem_"+this.allItems[z].id).style.visibility="visible";
            existe=1;
        }
    }
    if(existe){
    document.getElementById("submenu").style.visibility="visible";}
    
//showMenu(mainparent);
};

cMenu.prototype.showOne=function showOne(id){
   
    };

cMenu.prototype.hideAll=function hideAll(){
    for(var n = 0;n<this.num;n++){
        if(this.allItems[n].id_o!="")//tots els que tinguin oest els ocultem
            this.allItems[n].show=false;
    }
};

cMenu.prototype.update=function update(){
    for(var n = 0;n<this.num;n++){
        
        }
};

cMenu.prototype.over=function over(who){
    for(var n = 0;n<this.num;n++){
        if(this.allitems[n].parent==who){
            this.showOne(this.allitems[n].id);
        }
    }
};

function Load(menuid,iditem){
    // alert("raw_"+menuid);
    var src=document.getElementById("raw_"+menuid).innerHTML;
    //alert(src);
    MenuBar=new cMenu();
    MenuBar.load(src,",",iditem);
// alert("Intentem carregar:"+iditem);
}

function update(){
    alert(MenuBar.length);
    for(var n = 0;n<MenuBar.length;n++){
        if(MenuBar.allitems[n].show) getElementById(MenuBar.allitems[n].id).setAttribute("visible", false);
        else  getElementById(MenuBar.allitems[n].id).setAttribute("visible", false);
    }
}
function showAll(){
    
}
function over_MenuBar(iditem,menuid){ 
    Load(menuid,iditem);   
    MenuBar.over(iditem);
    ShowAll();
}

  