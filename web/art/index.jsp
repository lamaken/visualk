<%-- 
    Document   : art main html
    Created on : 03/06/2016, 14:10:54
    Author     : lamaken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--meta http-equiv="refresh" content="2"-->
        <title>JSP Page</title>
    </head>


    <script>
         function guid() {
                function _p8(s) {
                    var p = (Math.random().toString(16) + "000000000").substr(2, 8);
                    return s ? "-" + p.substr(0, 4) + "-" + p.substr(4, 4) : p;
                }
                return _p8() + _p8(true) + _p8(true) + _p8();
            }
            
        function start() {
            
        var count = 100;    
        var asaco;
            
            

            for (n = 0; n < count; n++) {
                
                var unique = guid();
                var mx = Math.random(100)*100+50;
                var my = Math.random(100)*100+50;
                
                 
                
                
                
                imatge = "<img  id=\"imatge\" src=\"/visualk/art/Main?rnd="+unique+"&my="+my+"&mx="+mx+"&cellw=10\"/>";
                document.getElementById("asaco").innerHTML += imatge;
            }
            
        }

    </script>

    <body onload="start();">
        <h1>visualk art!</h1>
    </body>

    <div id="asaco"></div>





</html>
