/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var levels_available;
var levels_complete;




var actual_level="1b";


function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1);
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


function addLevel(level) {
    oldLevel = getCookie(level);
    addCookie(level, "done");
}

function getNextLevel() {
    new_level = newGame(actual_level);
    setCookie("actual_level", new_level);
}

function isDonet(level){
    var levels=getCookie(level);
    return levels===null;
}

function newGame(level) {

    switch (level) {
        case "1b":
            level = "2";
            break;
        case "2":
            level = "3";
            break;
        case "3":
            level = "4b";
            break;
        case "4b":
            level = "5";
            break;
        case "5":
            level = "6";
            break;
        case "6":
            level = "7";
            break;
        case "7":
            level = "8";
            break;
        case "8":
            level = "9";
            break;
        case "9":
            level = "10";
            break;
        case "10":
            level = "11";
            break;
        case "11":
            level = "12";
            break;
        case "12":
            level = "1b";
            break;
    }
    actual_level=level;
    return level;
}