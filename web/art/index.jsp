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
        <title>visualk art</title>
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

            var countx = 10;
            var county = 5;

            var x = 0;
            var y = 0;


            for (x = 0; x < countx; x += 1) {
                for (y = 0; y < county; y += 1) {
                    var unique=countx;

                    if (x % 2 == 0) {
                        unique = guid();
                    }
                    var mx = 100;//Math.floor(Math.random(100) * 100);
                    var my = 100;//Math.floor(Math.random(100) * 100);
                    var left = (x * mx + mx);
                    var top = (y * my + my);


                    imatge = "<img  style=\"position:absolute;left:" + left + "px;top:" + top + "px;\" id=\"imatge\" src=\"/visualk/art/Main?rnd=" + unique + "&my=" + my + "&mx=" + mx + "&cellw=11\"/>";
                    document.getElementById("asaco").innerHTML += imatge;
                }
            }
        }

    </script>
<body bgcolor="black" style="color: white"  onload="start();">
<center><h1>visualk art!</h1>
</center>   </body>

    <div id="asaco"></div>





</html>
