<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>visualk</title>
    </head>
    
    
    
    <script>
         function contrastImage(imageData, contrast) {

                var data = imageData.data;
                var factor = contrast/100;

                for (var i = 0; i < data.length; i += 4)
                {
                    data[i] = factor * (data[i]-128) ;
                    data[i + 1] = factor * (data[i + 1] - 128) + 128;
                    data[i + 2] = factor * (data[i + 2] - 128) + 128;
                }
                return imageData;
            }
            
        function init(){
            
        }
    </script>
        
  

    <body onload="init()"  bgcolor="black"   style="color: white">

        <div style="background-color: blue;font-family: sans-serif;font-size:120%;text-align:center">
            <h1>
                <h1>visualk@ main page</h1>
                
                <a href="art/editorSquared.html"><img id="squared" style="left:300px;top:100px" src="/visualk/art/Squared?mx=150&my=150&cellw=23"/></a>
                 <a href="art/editorRounded.html"><img id="roundedd" style="left:300px;top: 300px" src="/visualk/art/Rounded?mx=150&my=150&cellw=23"/></a>
                <a href="art/editorMixed.html"><img id="mixed" style="left:500px;top:200px" src="/visualk/art/Mixed?mx=150&my=150&cellw=23"/></a>
              
                <a href="hrz/index.html"><img style="left:150px;top: 150px" src="/visualk/hrz/Hrz?option=firma"/></br></a></br>
            </h1>

        </div>

    </body>
</html>