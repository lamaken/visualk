<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="10">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Welcome to visualk v0.1</title>

        <meta name="Keywords"
              content="experiments, horizon maker, hrzmkr, mosaics, mosaic editor" />
        <meta name="Description"
              content="Let computer draw or paint for you." />
        <meta name="Author" content="lamaken@gmail.com" />


        <!-- google analytics-->
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-80760197-2', 'auto');
            ga('send', 'pageview');

        </script>
        <!-- google analytics-->

    </head>

    <body bgcolor="black"   style="color: white">
       
      <div style="font-size:55%;line-height:1.2;text-align:center">
        <h1><a href="https://github.com/lamaken/visualk.git">https://github.com/lamaken/visualk.git</a>
        <br/><br/>
        lamaken@gmail.com <br/>
        </h1>
        
    </div>
    

    <div style="background-color: black;font-family: monospace;text-align:center">
        <a href="art/editorSquared.html"><img id="squared"  src="/visualk/art/Squared?mx=150&my=150&cellw=23&d=1"/></a>
        <a href="art/editorRounded.html"><img id="roundedd"  src="/visualk/art/Rounded?mx=150&my=150&cellw=23&d=1"/></a>
        <a href="art/editorMixed.html"><img id="mixed"  src="/visualk/art/Mixed?mx=150&my=150&cellw=23&d=1"/></a>
        <a href="art/editorArab.html"><img id="arab"  src="/visualk/art/Arab?mx=75&my=75&cellw=9&d=1"/></a>
        <a href="art/liveMosaic.html"><img  src="/visualk/art/LiveMosaic?mx=75&my=75&cellw=9&d=1"/></a>
        <a href="tubs"><img  src="/visualk/tubs/img/logo.png"/></a>
        <a href="hrz"><img  src="/visualk/hrz/Hrz?option=firma"/></a>
        <!--a href="nine"><img src="/visualk/nine/img/logo.png"/></a-->
        <!--a href="ss"><img  src="/visualk/ss/img/logo.jpeg"/></a-->
        <!--a href="gallery"><img src="/visualk/gallery/img/logo.jpeg"/>gallery</a-->
    </div>

</body>
</html>
