

function init1(){
    var balls = document.getElementById("balls");
    
    
    
    
    for (var n=0;n<1300;n++)
    {    
        var ball = document.createElement("img");
        ball.className="ball";
        var name = "rnd_"+999*Math.random();
        ball.src="http://alkasoft.org/visualk/art/Arab?mx=10&my=10&cellw=3&rnd="+name;//Arab
        
        balls.appendChild(ball);
    }
}




function init(){
    var balls = document.getElementById("balls");
    
    
    var str="<!--IMATEGES A SACO -->"
    
    for (var n=0;n<100;n++)
    {    
     var name = "rnd_"+999*Math.random();   
     str += "<img class=ball src='http://alkasoft.org/visualk/art/graph/LiveMosaic?mx=100&my=100&cellw=35&rnd="+name+"'/>";//Arab
    }
    balls.innerHTML=str;
}
