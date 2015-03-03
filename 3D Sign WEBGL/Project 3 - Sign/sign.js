// CS435, Project #3, Ethan Schatzline
// Use the buttons at the top to rotate the camera,
// or click and drag the canvas to rotate the camera
// Select a menu option and use the up and down button
// to increment or decrement the price of the gas type
// selected in the menu by 1 cent.


var canvas;
var gl;



var reg  = [1,9,7];
var supp  = [2,1,9];
var prem = [2,3,5];

var mousedown = false;

var numVertices  = 36;
var poleStart = 36;
var s=numVertices+404;


var current=0;

//r1
var start=[s,s+101,s+202,s+404,
        s+505,s+606,s+808,
        s+909,s+1010,s+1212,
        s+1313,s+1414,s+1616,
        s+1717,s+1818,s+2020,
        s+2121,s+2222,s+2424,
        s+2525,s+2626,s+2828,
        s+2929,s+3030,s+3232,
        s+3333,s+3434,s+3636,
        s+3737,s+3838,s+4040,
        s+4141,s+4242,s+4444,
        s+4545,s+4646,s+4848,
        s+4949,s+5050,s+5252,
        s+5353,s+5454,s+5656,
        s+5757,s+5858,s+6060,
        s+6161,s+6262
];
current = start[47];
for(var i = 3; i<=5000;i++){
    if(i%3==0){
    start.push(current+202);
    current+=202;
}
    else{
    start.push(current+101);
    current+=101;
}
}



var mouse = vec2(0,0);

var pointsArray =  [];
var colorsArray = [];



//rectangle sign
var vertices = [
        vec4( -0.5, -0.0,  0.03, 1.0 ),
        vec4( -0.5,  0.9,  0.03, 1.0 ),
        vec4( 0.5,  0.9,  0.03, 1.0 ),
        vec4( 0.5, -0.0,  0.03, 1.0 ),
        vec4( -0.5, -0.0, -0.03, 1.0 ),
        vec4( -0.5,  0.9, -0.03, 1.0 ),
        vec4( 0.5,  0.9, -0.03, 1.0 ),
        vec4( 0.5, -0.0, -0.03, 1.0 )
];

var pole=[
        vec4(-0.03,-1,0.03,1),
        vec4(-0.03,0,0.03,1),
        vec4(0.03,0,0.03,1),
        vec4(0.03,-1,0.03,1),
        vec4(-0.03,-1,-0.03,1),
        vec4(-0.03,0,-0.03,1),
        vec4(0.03,0,-0.03,1),
        vec4(0.03,-1,-0.03,1)
    ];

var vertexColors = [
        vec4( 0.0, 0.0, 0.0, 1.0 ),  // black
        vec4( 0.2, 0.2, 0.2, 1.0 ),  // light gray
        vec4( 0.1, 0.1, 0.1, 1.0 ),  // dark gray
        vec4( 0.4, 0.0, 0.0, 1.0 ),  // green
        vec4( 0, 0, 0, 1.0 ),  // light green
        vec4( 0.1, 0.1, 0.1, 1.0 ),  // dark green
        vec4( 1.0, 1.0, 1.0, 1.0 ),  // TEXT
        vec4( 0.0, 0.0, 0.0, 1.0 ),  // TEXT SIDE
        vec4(1,1,1,1)//white
    ];

var near = -5;
var far = 5;
var radius = 1.0;
var theta  = 0.0;
var phi    = 0.0;
var dr = 5.0 * Math.PI/180.0;

var left = -1.0;
var right = 1.0;
var ytop = 1.0;
var bottom = -1.0;

var regular = false;
var sup = false;
var premium = false;


var mvMatrix, pMatrix;
var modelView, projection;
var eye;

const at = vec3(0.0, 0.0, 0.0);
const up = vec3(0.0, 1.0, 0.0);

var elapsedTime = 0;
var frameCount = 0;
var lastTime = 0;

function drawScene() {

   // draw scene here

   var now = new Date().getTime();

   frameCount++;
   elapsedTime += (now - lastTime);

   lastTime = now;

   if(elapsedTime >= 1000) {
       fps = frameCount;
       frameCount = 0;
       elapsedTime -= 1000;

       document.getElementById("fps").innerHTML = "FPS: "+fps;
   }
}

lastTime = new Date().getTime();
setInterval(drawScene,33);



function inc(array){
    if(array[2]==9){
        if(array[1]==9){
            if(array[0]==9){
                return false;
            }
            else if(array[0]<9){
                array[0]++;
                array[1]=0;
                array[2]=0;
            }
        }
        else if(array[1]<9){
            array[1]++;
            array[2]=0;
        }
    }
    else if(array[2]<9){
        array[2]++;
    }
}
function decr(array){
    console.log("WHAT");
    if(array[2]==0){
        if(array[1]==0){
            if(array[0]==0){
                console.log("FALSE");
                return false;
            }
            else if(array[0]>0){
                console.log("Dec 0");
                array[0]--;
                array[1]=9;
                array[2]=9;
            }
        }
        else if(array[1]>0){
            console.log("dec 1");
            array[1]--;
            array[2]=9;
        }
    }
    else if(array[2]>0){
        console.log("dec 2");
        array[2]--;
    }
}


// quad uses first index to set color for face

function quad(a, b, c, d,e) {
     pointsArray.push(vertices[a]); 
     colorsArray.push(vertexColors[e]); 
     pointsArray.push(vertices[b]); 
     colorsArray.push(vertexColors[e]); 
     pointsArray.push(vertices[c]); 
     colorsArray.push(vertexColors[e]);    
     pointsArray.push(vertices[a]); 
     colorsArray.push(vertexColors[e]); 
     pointsArray.push(vertices[c]); 
     colorsArray.push(vertexColors[e]); 
     pointsArray.push(vertices[d]); 
     colorsArray.push(vertexColors[e]);   
}

function drawDot(x,y){

for(var i = 0; i <= 100;i++) 
{ 
            var dot = vec4(
                  x+ (0.01 * Math.cos(i *  Math.PI*2 / 100)),
                y+ (0.005 * Math.sin(i * Math.PI*2 / 100)),0.033,1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[6]);
}


for(var i = 0; i <= 100;i++) 
{ 
            var dot = vec4(
                  x+ (0.01 * Math.cos(i *  Math.PI*2 / 100)),
                y+ (0.005 * Math.sin(i * Math.PI*2 / 100)),-0.033,1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[6]);
}

for(var i = 0; i <= 100;i++) 
{ 
    var dot = vec4(
                  x+ (0.01 * Math.cos(i *  Math.PI*2 / 100)),
               y+ (0.005 * Math.sin(i * Math.PI*2 / 100)),0.033,1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[7]);
            var dot = vec4(
                  x+ (0.01 * Math.cos(i *  Math.PI*2 / 100)),
                y+ (0.005 * Math.sin(i * Math.PI*2 / 100)),-0.033,1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[7]);
    }


}


function drawPole(y1,y2){

for(var i = 0; i <= 100;i++) 
{ 
            var dot = vec4(
                   (0.03 * Math.cos(i *  Math.PI*2 / 100)), y1,
                (0.03 * Math.sin(i * Math.PI*2 / 100)),1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[2]);
}


for(var i = 0; i <= 100;i++) 
{ 
            var dot = vec4(
                   (0.03 * Math.cos(i *  Math.PI*2 / 100)), y2,
              (0.03 * Math.sin(i * Math.PI*2 / 100)),1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[2]);
}

for(var i = 0; i <= 100;i++) 
{ 
    var dot = vec4(
                   (0.03 * Math.cos(i *  Math.PI*2 / 100)), y1,
                (0.03 * Math.sin(i * Math.PI*2 / 100)),1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[1]);
            var dot = vec4(
                   (0.03 * Math.cos(i *  Math.PI*2 / 100)), y2,
                (0.03 * Math.sin(i * Math.PI*2 / 100)),1.0
            );
            pointsArray.push(dot);
            colorsArray.push(vertexColors[1]);
    }


}

// Each face determines two triangles

function colorCube()
{
    quad( 1, 0, 3, 2,3 );
    quad( 2, 3, 7, 6,4 );
    quad( 3, 0, 4, 7,5 );
    quad( 6, 5, 1, 2,5 );
    quad( 4, 5, 6, 7,3 );
    quad( 5, 4, 0, 1,4 );
}


function Regular(){
    drawR(-0.43,0.82);
    drawE(-0.37,0.82);
    drawG(-0.315,0.82);
    drawU(-0.245,0.82);
    drawL(-0.19,0.82);
    drawA(-0.14,0.82);
    drawR(-0.08,0.82);

}
function Super(){
    drawS(-0.43,0.56);
    drawU(-0.37,0.56);
    drawP(-0.315,0.56);
    drawE(-0.26,0.56);
    drawR(-0.20,0.56);
}

function Premium(){
    drawP(-0.43,0.32);
    drawR(-0.37,0.32);
    drawE(-0.31,0.32);
    drawM(-0.25,0.32);
    drawI(-0.18,0.32);
    drawU(-0.13,0.32);
    drawM(-0.07,0.32);

}

function Numbers(){
    //888
    draw8(0.2,0.82);
    draw8(0.26,0.82);
    draw8(0.32,0.82);

    //888
    draw8(0.2,0.56);
    draw8(0.26,0.56);
    draw8(0.32,0.56);

    //888
    draw8(0.2,0.32);
    draw8(0.26,0.32);
    draw8(0.32,0.32);

    pointsArray = pointsArray.slice(0,111540);

    draw1(0.2,0.82);
    draw9(0.26,0.82);
    draw7(0.32,0.82);

    //219
    draw2(0.2,0.56);
    draw1(0.26,0.56);
    draw9(0.32,0.56);

    //235
    draw2(0.2,0.32);
    draw3(0.26,0.32);
    draw5(0.32,0.32);
}




function onWindowResize(event) {
        canvasWidth = window.innerWidth;
        canvasHeight = window.innerHeight;
        canvas.width = canvasWidth;
        canvas.height = canvasHeight;
      }


window.onload = function init() {
    canvas = document.getElementById( "gl-canvas" );
    onWindowResize();
    gl = WebGLUtils.setupWebGL( canvas );
    if ( !gl ) { alert( "WebGL isn't available" ); }

    window.addEventListener("contextmenu", function(e){
        e.preventDefault();
    });

    window.addEventListener("mousemove",function(){
        if(mousedown){
            var t = vec2(2*event.clientX/canvas.width-1, 
           2*(canvas.height-event.clientY)/canvas.height-1);
            phi+=2*(mouse[0]-(t[0]));
            theta+=2*(mouse[1]-(t[1]));
            mouse=t;
        }
    });

     canvas.addEventListener("mousedown",function(e){
        if(e.button===2){
            return false;
        }
        var t = vec2(2*event.clientX/canvas.width-1, 
           2*(canvas.height-event.clientY)/canvas.height-1);
        mouse = t;
        mousedown = true;
        e.preventDefault();
    });

     window.addEventListener("mouseup",function(e){
        mousedown=false;
     });


    gl.viewport( 0, 0, canvas.width, canvas.height );
    
    gl.clearColor( 0, 0, 0, 0 );
    
    gl.enable(gl.DEPTH_TEST);

    //
    //  Load shaders and initialize attribute buffers
    //
    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );
    
    colorCube();
    drawPole(0,-1);
    Regular();
    Super();
    Premium();
    Numbers();


    var cBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(colorsArray), gl.STATIC_DRAW );
    
    var vColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vColor);

    var boxBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, boxBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(pointsArray), gl.DYNAMIC_DRAW );
    
    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );
 
    modelView = gl.getUniformLocation( program, "modelView" );
    projection = gl.getUniformLocation( program, "projection" );

// buttons to change viewing parameters

    document.getElementById("Button1").onclick = function(){theta += dr; oldtheta=theta;};
    document.getElementById("Button2").onclick = function(){theta -= dr; oldtheta=theta;};
    document.getElementById("Button3").onclick = function(){phi += dr; oldphi=phi;};
    document.getElementById("Button4").onclick = function(){phi -= dr; oldphi=phi;};
       
    document.getElementById("Button5").onclick = function(){
        pointsArray = pointsArray.slice(0,111540);

        if(regular==true){
            inc(reg);
            switch(reg[0]){
                case 0: draw0(0.2,0.82); break;
                case 1: draw1(0.2,0.82); break;
                case 2: draw2(0.2,0.82); break;
                case 3: draw3(0.2,0.82); break;
                case 4: draw4(0.2,0.82); break;
                case 5: draw5(0.2,0.82); break;
                case 6: draw6(0.2,0.82); break;
                case 7: draw7(0.2,0.82); break;
                case 8: draw8(0.2,0.82); break;
                case 9: draw9(0.2,0.82); break;
            }
            switch(reg[1]){
                case 0: draw0(0.26,0.82); break;
                case 1: draw1(0.26,0.82); break;
                case 2: draw2(0.26,0.82); break;
                case 3: draw3(0.26,0.82); break;
                case 4: draw4(0.26,0.82); break;
                case 5: draw5(0.26,0.82); break;
                case 6: draw6(0.26,0.82); break;
                case 7: draw7(0.26,0.82); break;
                case 8: draw8(0.26,0.82); break;
                case 9: draw9(0.26,0.82); break;
            }
            switch(reg[2]){
                case 0: draw0(0.32,0.82); break;
                case 1: draw1(0.32,0.82); break;
                case 2: draw2(0.32,0.82); break;
                case 3: draw3(0.32,0.82); break;
                case 4: draw4(0.32,0.82); break;
                case 5: draw5(0.32,0.82); break;
                case 6: draw6(0.32,0.82); break;
                case 7: draw7(0.32,0.82); break;
                case 8: draw8(0.32,0.82); break;
                case 9: draw9(0.32,0.82); break;
            }

            switch(supp[0]){
                case 0: draw0(0.2,0.56); break;
                case 1: draw1(0.2,0.56); break;
                case 2: draw2(0.2,0.56); break;
                case 3: draw3(0.2,0.56); break;
                case 4: draw4(0.2,0.56); break;
                case 5: draw5(0.2,0.56); break;
                case 6: draw6(0.2,0.56); break;
                case 7: draw7(0.2,0.56); break;
                case 8: draw8(0.2,0.56); break;
                case 9: draw9(0.2,0.56); break;
            }
            switch(supp[1]){
                case 0: draw0(0.26,0.56); break;
                case 1: draw1(0.26,0.56); break;
                case 2: draw2(0.26,0.56); break;
                case 3: draw3(0.26,0.56); break;
                case 4: draw4(0.26,0.56); break;
                case 5: draw5(0.26,0.56); break;
                case 6: draw6(0.26,0.56); break;
                case 7: draw7(0.26,0.56); break;
                case 8: draw8(0.26,0.56); break;
                case 9: draw9(0.26,0.56); break;
            }
            switch(supp[2]){
                case 0: draw0(0.32,0.56); break;
                case 1: draw1(0.32,0.56); break;
                case 2: draw2(0.32,0.56); break;
                case 3: draw3(0.32,0.56); break;
                case 4: draw4(0.32,0.56); break;
                case 5: draw5(0.32,0.56); break;
                case 6: draw6(0.32,0.56); break;
                case 7: draw7(0.32,0.56); break;
                case 8: draw8(0.32,0.56); break;
                case 9: draw9(0.32,0.56); break;
            }

            switch(prem[0]){
                case 0: draw0(0.2,0.32); break;
                case 1: draw1(0.2,0.32); break;
                case 2: draw2(0.2,0.32); break;
                case 3: draw3(0.2,0.32); break;
                case 4: draw4(0.2,0.32); break;
                case 5: draw5(0.2,0.32); break;
                case 6: draw6(0.2,0.32); break;
                case 7: draw7(0.2,0.32); break;
                case 8: draw8(0.2,0.32); break;
                case 9: draw9(0.2,0.32); break;
            }
            switch(prem[1]){
                case 0: draw0(0.26,0.32); break;
                case 1: draw1(0.26,0.32); break;
                case 2: draw2(0.26,0.32); break;
                case 3: draw3(0.26,0.32); break;
                case 4: draw4(0.26,0.32); break;
                case 5: draw5(0.26,0.32); break;
                case 6: draw6(0.26,0.32); break;
                case 7: draw7(0.26,0.32); break;
                case 8: draw8(0.26,0.32); break;
                case 9: draw9(0.26,0.32); break;
            }
            switch(prem[2]){
                case 0: draw0(0.32,0.32); break;
                case 1: draw1(0.32,0.32); break;
                case 2: draw2(0.32,0.32); break;
                case 3: draw3(0.32,0.32); break;
                case 4: draw4(0.32,0.32); break;
                case 5: draw5(0.32,0.32); break;
                case 6: draw6(0.32,0.32); break;
                case 7: draw7(0.32,0.32); break;
                case 8: draw8(0.32,0.32); break;
                case 9: draw9(0.32,0.32); break;
            }

        }
        else if(sup==true){
            inc(supp);
            switch(reg[0]){
                case 0: draw0(0.2,0.82); break;
                case 1: draw1(0.2,0.82); break;
                case 2: draw2(0.2,0.82); break;
                case 3: draw3(0.2,0.82); break;
                case 4: draw4(0.2,0.82); break;
                case 5: draw5(0.2,0.82); break;
                case 6: draw6(0.2,0.82); break;
                case 7: draw7(0.2,0.82); break;
                case 8: draw8(0.2,0.82); break;
                case 9: draw9(0.2,0.82); break;
            }
            switch(reg[1]){
                case 0: draw0(0.26,0.82); break;
                case 1: draw1(0.26,0.82); break;
                case 2: draw2(0.26,0.82); break;
                case 3: draw3(0.26,0.82); break;
                case 4: draw4(0.26,0.82); break;
                case 5: draw5(0.26,0.82); break;
                case 6: draw6(0.26,0.82); break;
                case 7: draw7(0.26,0.82); break;
                case 8: draw8(0.26,0.82); break;
                case 9: draw9(0.26,0.82); break;
            }
            switch(reg[2]){
                case 0: draw0(0.32,0.82); break;
                case 1: draw1(0.32,0.82); break;
                case 2: draw2(0.32,0.82); break;
                case 3: draw3(0.32,0.82); break;
                case 4: draw4(0.32,0.82); break;
                case 5: draw5(0.32,0.82); break;
                case 6: draw6(0.32,0.82); break;
                case 7: draw7(0.32,0.82); break;
                case 8: draw8(0.32,0.82); break;
                case 9: draw9(0.32,0.82); break;
            }

            switch(supp[0]){
                case 0: draw0(0.2,0.56); break;
                case 1: draw1(0.2,0.56); break;
                case 2: draw2(0.2,0.56); break;
                case 3: draw3(0.2,0.56); break;
                case 4: draw4(0.2,0.56); break;
                case 5: draw5(0.2,0.56); break;
                case 6: draw6(0.2,0.56); break;
                case 7: draw7(0.2,0.56); break;
                case 8: draw8(0.2,0.56); break;
                case 9: draw9(0.2,0.56); break;
            }
            switch(supp[1]){
                case 0: draw0(0.26,0.56); break;
                case 1: draw1(0.26,0.56); break;
                case 2: draw2(0.26,0.56); break;
                case 3: draw3(0.26,0.56); break;
                case 4: draw4(0.26,0.56); break;
                case 5: draw5(0.26,0.56); break;
                case 6: draw6(0.26,0.56); break;
                case 7: draw7(0.26,0.56); break;
                case 8: draw8(0.26,0.56); break;
                case 9: draw9(0.26,0.56); break;
            }
            switch(supp[2]){
                case 0: draw0(0.32,0.56); break;
                case 1: draw1(0.32,0.56); break;
                case 2: draw2(0.32,0.56); break;
                case 3: draw3(0.32,0.56); break;
                case 4: draw4(0.32,0.56); break;
                case 5: draw5(0.32,0.56); break;
                case 6: draw6(0.32,0.56); break;
                case 7: draw7(0.32,0.56); break;
                case 8: draw8(0.32,0.56); break;
                case 9: draw9(0.32,0.56); break;
            }

            switch(prem[0]){
                case 0: draw0(0.2,0.32); break;
                case 1: draw1(0.2,0.32); break;
                case 2: draw2(0.2,0.32); break;
                case 3: draw3(0.2,0.32); break;
                case 4: draw4(0.2,0.32); break;
                case 5: draw5(0.2,0.32); break;
                case 6: draw6(0.2,0.32); break;
                case 7: draw7(0.2,0.32); break;
                case 8: draw8(0.2,0.32); break;
                case 9: draw9(0.2,0.32); break;
            }
            switch(prem[1]){
                case 0: draw0(0.26,0.32); break;
                case 1: draw1(0.26,0.32); break;
                case 2: draw2(0.26,0.32); break;
                case 3: draw3(0.26,0.32); break;
                case 4: draw4(0.26,0.32); break;
                case 5: draw5(0.26,0.32); break;
                case 6: draw6(0.26,0.32); break;
                case 7: draw7(0.26,0.32); break;
                case 8: draw8(0.26,0.32); break;
                case 9: draw9(0.26,0.32); break;
            }
            switch(prem[2]){
                case 0: draw0(0.32,0.32); break;
                case 1: draw1(0.32,0.32); break;
                case 2: draw2(0.32,0.32); break;
                case 3: draw3(0.32,0.32); break;
                case 4: draw4(0.32,0.32); break;
                case 5: draw5(0.32,0.32); break;
                case 6: draw6(0.32,0.32); break;
                case 7: draw7(0.32,0.32); break;
                case 8: draw8(0.32,0.32); break;
                case 9: draw9(0.32,0.32); break;
            }
            
        }
        else if(premium==true){
            inc(prem);

            switch(reg[0]){
                case 0: draw0(0.2,0.82); break;
                case 1: draw1(0.2,0.82); break;
                case 2: draw2(0.2,0.82); break;
                case 3: draw3(0.2,0.82); break;
                case 4: draw4(0.2,0.82); break;
                case 5: draw5(0.2,0.82); break;
                case 6: draw6(0.2,0.82); break;
                case 7: draw7(0.2,0.82); break;
                case 8: draw8(0.2,0.82); break;
                case 9: draw9(0.2,0.82); break;
            }
            switch(reg[1]){
                case 0: draw0(0.26,0.82); break;
                case 1: draw1(0.26,0.82); break;
                case 2: draw2(0.26,0.82); break;
                case 3: draw3(0.26,0.82); break;
                case 4: draw4(0.26,0.82); break;
                case 5: draw5(0.26,0.82); break;
                case 6: draw6(0.26,0.82); break;
                case 7: draw7(0.26,0.82); break;
                case 8: draw8(0.26,0.82); break;
                case 9: draw9(0.26,0.82); break;
            }
            switch(reg[2]){
                case 0: draw0(0.32,0.82); break;
                case 1: draw1(0.32,0.82); break;
                case 2: draw2(0.32,0.82); break;
                case 3: draw3(0.32,0.82); break;
                case 4: draw4(0.32,0.82); break;
                case 5: draw5(0.32,0.82); break;
                case 6: draw6(0.32,0.82); break;
                case 7: draw7(0.32,0.82); break;
                case 8: draw8(0.32,0.82); break;
                case 9: draw9(0.32,0.82); break;
            }

            switch(supp[0]){
                case 0: draw0(0.2,0.56); break;
                case 1: draw1(0.2,0.56); break;
                case 2: draw2(0.2,0.56); break;
                case 3: draw3(0.2,0.56); break;
                case 4: draw4(0.2,0.56); break;
                case 5: draw5(0.2,0.56); break;
                case 6: draw6(0.2,0.56); break;
                case 7: draw7(0.2,0.56); break;
                case 8: draw8(0.2,0.56); break;
                case 9: draw9(0.2,0.56); break;
            }
            switch(supp[1]){
                case 0: draw0(0.26,0.56); break;
                case 1: draw1(0.26,0.56); break;
                case 2: draw2(0.26,0.56); break;
                case 3: draw3(0.26,0.56); break;
                case 4: draw4(0.26,0.56); break;
                case 5: draw5(0.26,0.56); break;
                case 6: draw6(0.26,0.56); break;
                case 7: draw7(0.26,0.56); break;
                case 8: draw8(0.26,0.56); break;
                case 9: draw9(0.26,0.56); break;
            }
            switch(supp[2]){
                case 0: draw0(0.32,0.56); break;
                case 1: draw1(0.32,0.56); break;
                case 2: draw2(0.32,0.56); break;
                case 3: draw3(0.32,0.56); break;
                case 4: draw4(0.32,0.56); break;
                case 5: draw5(0.32,0.56); break;
                case 6: draw6(0.32,0.56); break;
                case 7: draw7(0.32,0.56); break;
                case 8: draw8(0.32,0.56); break;
                case 9: draw9(0.32,0.56); break;
            }

            switch(prem[0]){
                case 0: draw0(0.2,0.32); break;
                case 1: draw1(0.2,0.32); break;
                case 2: draw2(0.2,0.32); break;
                case 3: draw3(0.2,0.32); break;
                case 4: draw4(0.2,0.32); break;
                case 5: draw5(0.2,0.32); break;
                case 6: draw6(0.2,0.32); break;
                case 7: draw7(0.2,0.32); break;
                case 8: draw8(0.2,0.32); break;
                case 9: draw9(0.2,0.32); break;
            }
            switch(prem[1]){
                case 0: draw0(0.26,0.32); break;
                case 1: draw1(0.26,0.32); break;
                case 2: draw2(0.26,0.32); break;
                case 3: draw3(0.26,0.32); break;
                case 4: draw4(0.26,0.32); break;
                case 5: draw5(0.26,0.32); break;
                case 6: draw6(0.26,0.32); break;
                case 7: draw7(0.26,0.32); break;
                case 8: draw8(0.26,0.32); break;
                case 9: draw9(0.26,0.32); break;
            }
            switch(prem[2]){
                case 0: draw0(0.32,0.32); break;
                case 1: draw1(0.32,0.32); break;
                case 2: draw2(0.32,0.32); break;
                case 3: draw3(0.32,0.32); break;
                case 4: draw4(0.32,0.32); break;
                case 5: draw5(0.32,0.32); break;
                case 6: draw6(0.32,0.32); break;
                case 7: draw7(0.32,0.32); break;
                case 8: draw8(0.32,0.32); break;
                case 9: draw9(0.32,0.32); break;
            }
        }

        gl.bindBuffer( gl.ARRAY_BUFFER, boxBuffer );
        gl.bufferData( gl.ARRAY_BUFFER, flatten(pointsArray), gl.DYNAMIC_DRAW );
    };
    document.getElementById("Button6").onclick = function(){
        pointsArray = pointsArray.slice(0,111540);

        if(regular==true){
            decr(reg);
            switch(reg[0]){
                case 0: draw0(0.2,0.82); break;
                case 1: draw1(0.2,0.82); break;
                case 2: draw2(0.2,0.82); break;
                case 3: draw3(0.2,0.82); break;
                case 4: draw4(0.2,0.82); break;
                case 5: draw5(0.2,0.82); break;
                case 6: draw6(0.2,0.82); break;
                case 7: draw7(0.2,0.82); break;
                case 8: draw8(0.2,0.82); break;
                case 9: draw9(0.2,0.82); break;
            }
            switch(reg[1]){
                case 0: draw0(0.26,0.82); break;
                case 1: draw1(0.26,0.82); break;
                case 2: draw2(0.26,0.82); break;
                case 3: draw3(0.26,0.82); break;
                case 4: draw4(0.26,0.82); break;
                case 5: draw5(0.26,0.82); break;
                case 6: draw6(0.26,0.82); break;
                case 7: draw7(0.26,0.82); break;
                case 8: draw8(0.26,0.82); break;
                case 9: draw9(0.26,0.82); break;
            }
            switch(reg[2]){
                case 0: draw0(0.32,0.82); break;
                case 1: draw1(0.32,0.82); break;
                case 2: draw2(0.32,0.82); break;
                case 3: draw3(0.32,0.82); break;
                case 4: draw4(0.32,0.82); break;
                case 5: draw5(0.32,0.82); break;
                case 6: draw6(0.32,0.82); break;
                case 7: draw7(0.32,0.82); break;
                case 8: draw8(0.32,0.82); break;
                case 9: draw9(0.32,0.82); break;
            }

            switch(supp[0]){
                case 0: draw0(0.2,0.56); break;
                case 1: draw1(0.2,0.56); break;
                case 2: draw2(0.2,0.56); break;
                case 3: draw3(0.2,0.56); break;
                case 4: draw4(0.2,0.56); break;
                case 5: draw5(0.2,0.56); break;
                case 6: draw6(0.2,0.56); break;
                case 7: draw7(0.2,0.56); break;
                case 8: draw8(0.2,0.56); break;
                case 9: draw9(0.2,0.56); break;
            }
            switch(supp[1]){
                case 0: draw0(0.26,0.56); break;
                case 1: draw1(0.26,0.56); break;
                case 2: draw2(0.26,0.56); break;
                case 3: draw3(0.26,0.56); break;
                case 4: draw4(0.26,0.56); break;
                case 5: draw5(0.26,0.56); break;
                case 6: draw6(0.26,0.56); break;
                case 7: draw7(0.26,0.56); break;
                case 8: draw8(0.26,0.56); break;
                case 9: draw9(0.26,0.56); break;
            }
            switch(supp[2]){
                case 0: draw0(0.32,0.56); break;
                case 1: draw1(0.32,0.56); break;
                case 2: draw2(0.32,0.56); break;
                case 3: draw3(0.32,0.56); break;
                case 4: draw4(0.32,0.56); break;
                case 5: draw5(0.32,0.56); break;
                case 6: draw6(0.32,0.56); break;
                case 7: draw7(0.32,0.56); break;
                case 8: draw8(0.32,0.56); break;
                case 9: draw9(0.32,0.56); break;
            }

            switch(prem[0]){
                case 0: draw0(0.2,0.32); break;
                case 1: draw1(0.2,0.32); break;
                case 2: draw2(0.2,0.32); break;
                case 3: draw3(0.2,0.32); break;
                case 4: draw4(0.2,0.32); break;
                case 5: draw5(0.2,0.32); break;
                case 6: draw6(0.2,0.32); break;
                case 7: draw7(0.2,0.32); break;
                case 8: draw8(0.2,0.32); break;
                case 9: draw9(0.2,0.32); break;
            }
            switch(prem[1]){
                case 0: draw0(0.26,0.32); break;
                case 1: draw1(0.26,0.32); break;
                case 2: draw2(0.26,0.32); break;
                case 3: draw3(0.26,0.32); break;
                case 4: draw4(0.26,0.32); break;
                case 5: draw5(0.26,0.32); break;
                case 6: draw6(0.26,0.32); break;
                case 7: draw7(0.26,0.32); break;
                case 8: draw8(0.26,0.32); break;
                case 9: draw9(0.26,0.32); break;
            }
            switch(prem[2]){
                case 0: draw0(0.32,0.32); break;
                case 1: draw1(0.32,0.32); break;
                case 2: draw2(0.32,0.32); break;
                case 3: draw3(0.32,0.32); break;
                case 4: draw4(0.32,0.32); break;
                case 5: draw5(0.32,0.32); break;
                case 6: draw6(0.32,0.32); break;
                case 7: draw7(0.32,0.32); break;
                case 8: draw8(0.32,0.32); break;
                case 9: draw9(0.32,0.32); break;
            }

        }
        else if(sup==true){
            decr(supp);
            switch(reg[0]){
                case 0: draw0(0.2,0.82); break;
                case 1: draw1(0.2,0.82); break;
                case 2: draw2(0.2,0.82); break;
                case 3: draw3(0.2,0.82); break;
                case 4: draw4(0.2,0.82); break;
                case 5: draw5(0.2,0.82); break;
                case 6: draw6(0.2,0.82); break;
                case 7: draw7(0.2,0.82); break;
                case 8: draw8(0.2,0.82); break;
                case 9: draw9(0.2,0.82); break;
            }
            switch(reg[1]){
                case 0: draw0(0.26,0.82); break;
                case 1: draw1(0.26,0.82); break;
                case 2: draw2(0.26,0.82); break;
                case 3: draw3(0.26,0.82); break;
                case 4: draw4(0.26,0.82); break;
                case 5: draw5(0.26,0.82); break;
                case 6: draw6(0.26,0.82); break;
                case 7: draw7(0.26,0.82); break;
                case 8: draw8(0.26,0.82); break;
                case 9: draw9(0.26,0.82); break;
            }
            switch(reg[2]){
                case 0: draw0(0.32,0.82); break;
                case 1: draw1(0.32,0.82); break;
                case 2: draw2(0.32,0.82); break;
                case 3: draw3(0.32,0.82); break;
                case 4: draw4(0.32,0.82); break;
                case 5: draw5(0.32,0.82); break;
                case 6: draw6(0.32,0.82); break;
                case 7: draw7(0.32,0.82); break;
                case 8: draw8(0.32,0.82); break;
                case 9: draw9(0.32,0.82); break;
            }

            switch(supp[0]){
                case 0: draw0(0.2,0.56); break;
                case 1: draw1(0.2,0.56); break;
                case 2: draw2(0.2,0.56); break;
                case 3: draw3(0.2,0.56); break;
                case 4: draw4(0.2,0.56); break;
                case 5: draw5(0.2,0.56); break;
                case 6: draw6(0.2,0.56); break;
                case 7: draw7(0.2,0.56); break;
                case 8: draw8(0.2,0.56); break;
                case 9: draw9(0.2,0.56); break;
            }
            switch(supp[1]){
                case 0: draw0(0.26,0.56); break;
                case 1: draw1(0.26,0.56); break;
                case 2: draw2(0.26,0.56); break;
                case 3: draw3(0.26,0.56); break;
                case 4: draw4(0.26,0.56); break;
                case 5: draw5(0.26,0.56); break;
                case 6: draw6(0.26,0.56); break;
                case 7: draw7(0.26,0.56); break;
                case 8: draw8(0.26,0.56); break;
                case 9: draw9(0.26,0.56); break;
            }
            switch(supp[2]){
                case 0: draw0(0.32,0.56); break;
                case 1: draw1(0.32,0.56); break;
                case 2: draw2(0.32,0.56); break;
                case 3: draw3(0.32,0.56); break;
                case 4: draw4(0.32,0.56); break;
                case 5: draw5(0.32,0.56); break;
                case 6: draw6(0.32,0.56); break;
                case 7: draw7(0.32,0.56); break;
                case 8: draw8(0.32,0.56); break;
                case 9: draw9(0.32,0.56); break;
            }

            switch(prem[0]){
                case 0: draw0(0.2,0.32); break;
                case 1: draw1(0.2,0.32); break;
                case 2: draw2(0.2,0.32); break;
                case 3: draw3(0.2,0.32); break;
                case 4: draw4(0.2,0.32); break;
                case 5: draw5(0.2,0.32); break;
                case 6: draw6(0.2,0.32); break;
                case 7: draw7(0.2,0.32); break;
                case 8: draw8(0.2,0.32); break;
                case 9: draw9(0.2,0.32); break;
            }
            switch(prem[1]){
                case 0: draw0(0.26,0.32); break;
                case 1: draw1(0.26,0.32); break;
                case 2: draw2(0.26,0.32); break;
                case 3: draw3(0.26,0.32); break;
                case 4: draw4(0.26,0.32); break;
                case 5: draw5(0.26,0.32); break;
                case 6: draw6(0.26,0.32); break;
                case 7: draw7(0.26,0.32); break;
                case 8: draw8(0.26,0.32); break;
                case 9: draw9(0.26,0.32); break;
            }
            switch(prem[2]){
                case 0: draw0(0.32,0.32); break;
                case 1: draw1(0.32,0.32); break;
                case 2: draw2(0.32,0.32); break;
                case 3: draw3(0.32,0.32); break;
                case 4: draw4(0.32,0.32); break;
                case 5: draw5(0.32,0.32); break;
                case 6: draw6(0.32,0.32); break;
                case 7: draw7(0.32,0.32); break;
                case 8: draw8(0.32,0.32); break;
                case 9: draw9(0.32,0.32); break;
            }
            
        }
        else if(premium==true){
            decr(prem);

            switch(reg[0]){
                case 0: draw0(0.2,0.82); break;
                case 1: draw1(0.2,0.82); break;
                case 2: draw2(0.2,0.82); break;
                case 3: draw3(0.2,0.82); break;
                case 4: draw4(0.2,0.82); break;
                case 5: draw5(0.2,0.82); break;
                case 6: draw6(0.2,0.82); break;
                case 7: draw7(0.2,0.82); break;
                case 8: draw8(0.2,0.82); break;
                case 9: draw9(0.2,0.82); break;
            }
            switch(reg[1]){
                case 0: draw0(0.26,0.82); break;
                case 1: draw1(0.26,0.82); break;
                case 2: draw2(0.26,0.82); break;
                case 3: draw3(0.26,0.82); break;
                case 4: draw4(0.26,0.82); break;
                case 5: draw5(0.26,0.82); break;
                case 6: draw6(0.26,0.82); break;
                case 7: draw7(0.26,0.82); break;
                case 8: draw8(0.26,0.82); break;
                case 9: draw9(0.26,0.82); break;
            }
            switch(reg[2]){
                case 0: draw0(0.32,0.82); break;
                case 1: draw1(0.32,0.82); break;
                case 2: draw2(0.32,0.82); break;
                case 3: draw3(0.32,0.82); break;
                case 4: draw4(0.32,0.82); break;
                case 5: draw5(0.32,0.82); break;
                case 6: draw6(0.32,0.82); break;
                case 7: draw7(0.32,0.82); break;
                case 8: draw8(0.32,0.82); break;
                case 9: draw9(0.32,0.82); break;
            }

            switch(supp[0]){
                case 0: draw0(0.2,0.56); break;
                case 1: draw1(0.2,0.56); break;
                case 2: draw2(0.2,0.56); break;
                case 3: draw3(0.2,0.56); break;
                case 4: draw4(0.2,0.56); break;
                case 5: draw5(0.2,0.56); break;
                case 6: draw6(0.2,0.56); break;
                case 7: draw7(0.2,0.56); break;
                case 8: draw8(0.2,0.56); break;
                case 9: draw9(0.2,0.56); break;
            }
            switch(supp[1]){
                case 0: draw0(0.26,0.56); break;
                case 1: draw1(0.26,0.56); break;
                case 2: draw2(0.26,0.56); break;
                case 3: draw3(0.26,0.56); break;
                case 4: draw4(0.26,0.56); break;
                case 5: draw5(0.26,0.56); break;
                case 6: draw6(0.26,0.56); break;
                case 7: draw7(0.26,0.56); break;
                case 8: draw8(0.26,0.56); break;
                case 9: draw9(0.26,0.56); break;
            }
            switch(supp[2]){
                case 0: draw0(0.32,0.56); break;
                case 1: draw1(0.32,0.56); break;
                case 2: draw2(0.32,0.56); break;
                case 3: draw3(0.32,0.56); break;
                case 4: draw4(0.32,0.56); break;
                case 5: draw5(0.32,0.56); break;
                case 6: draw6(0.32,0.56); break;
                case 7: draw7(0.32,0.56); break;
                case 8: draw8(0.32,0.56); break;
                case 9: draw9(0.32,0.56); break;
            }

            switch(prem[0]){
                case 0: draw0(0.2,0.32); break;
                case 1: draw1(0.2,0.32); break;
                case 2: draw2(0.2,0.32); break;
                case 3: draw3(0.2,0.32); break;
                case 4: draw4(0.2,0.32); break;
                case 5: draw5(0.2,0.32); break;
                case 6: draw6(0.2,0.32); break;
                case 7: draw7(0.2,0.32); break;
                case 8: draw8(0.2,0.32); break;
                case 9: draw9(0.2,0.32); break;
            }
            switch(prem[1]){
                case 0: draw0(0.26,0.32); break;
                case 1: draw1(0.26,0.32); break;
                case 2: draw2(0.26,0.32); break;
                case 3: draw3(0.26,0.32); break;
                case 4: draw4(0.26,0.32); break;
                case 5: draw5(0.26,0.32); break;
                case 6: draw6(0.26,0.32); break;
                case 7: draw7(0.26,0.32); break;
                case 8: draw8(0.26,0.32); break;
                case 9: draw9(0.26,0.32); break;
            }
            switch(prem[2]){
                case 0: draw0(0.32,0.32); break;
                case 1: draw1(0.32,0.32); break;
                case 2: draw2(0.32,0.32); break;
                case 3: draw3(0.32,0.32); break;
                case 4: draw4(0.32,0.32); break;
                case 5: draw5(0.32,0.32); break;
                case 6: draw6(0.32,0.32); break;
                case 7: draw7(0.32,0.32); break;
                case 8: draw8(0.32,0.32); break;
                case 9: draw9(0.32,0.32); break;
            }
        }

        gl.bindBuffer( gl.ARRAY_BUFFER, boxBuffer );
        gl.bufferData( gl.ARRAY_BUFFER, flatten(pointsArray), gl.DYNAMIC_DRAW );
    };

    document.getElementById("Menu").onclick = function( event) {
        switch(event.srcElement.index) {
          case 0:
            regular=true;
            sup=false;
            premium=false;
            break;

         case 1:
            sup=true;
            regular=false;
            premium=false;
            break;

         case 2:
            premium=true;
            regular=false;
            sup=false;
            break;
       }
    };   
    render();

}


var render = function() {

       

        gl.clear( gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
            
        eye = vec3(radius*Math.sin(phi), radius*Math.sin(theta), 
             radius*Math.cos(phi));

        mvMatrix = lookAt(eye, at , up); 
        pMatrix = ortho(left, right, bottom, ytop, near, far);
        
        gl.uniformMatrix4fv( modelView, false, flatten(mvMatrix) );
        gl.uniformMatrix4fv( projection, false, flatten(pMatrix) );


        //draw Sign
        gl.drawArrays( gl.TRIANGLES, 0, numVertices );
        
        //draw Cylinder Pole
        gl.drawArrays(gl.TRIANGLE_FAN,poleStart,100);
        gl.drawArrays(gl.TRIANGLE_FAN,poleStart+101,100);
        gl.drawArrays(gl.TRIANGLE_STRIP,poleStart+202,202);

        //Text
        for(var i = 0; i<=1210;i+=3){
        gl.drawArrays(gl.TRIANGLE_FAN,start[i],100);
        gl.drawArrays(gl.TRIANGLE_FAN,start[i+1],100);
        gl.drawArrays(gl.TRIANGLE_STRIP,start[i+2],202);
        }
        



        requestAnimFrame(render);
    }

function drawR(x,y){
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x,i);
    }
    for(var i=x+0.01;i<x+(0.03);i=i+0.01){
        drawDot(i,y);
        drawDot(i,y-0.06);
    }
    for(var i = y-0.02; i>(y-0.02)-(0.02*2);i=i-0.02){
        drawDot(x+0.03,i);
    }
    for(var i = y-0.08; i>(y-0.13);i=i-0.02){
        drawDot(x+0.03,i);
    }
}

function drawE(x,y){
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.06){
        drawDot(x+0.01,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.06){
        drawDot(x+0.02,i);
    }
    for(var i = y; i>y-(0.14);i=i-0.12){
        drawDot(x+0.03,i);
    }
}

function drawG(x,y){
    for(var i = y-0.02; i>y-(0.02*6);i=i-0.02){
        drawDot(x,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.12){
        drawDot(x+0.01,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.06){
        drawDot(x+0.03,i);
    }
    for(var i = y-0.02; i>y-(0.02*7);i=i-0.02){
        if(i!=y-0.04)
        drawDot(x+0.04,i);
    }
}

function drawU(x,y){
    for(var i = y; i>y-(0.02*6);i=i-0.02){
        drawDot(x,i);
    }
    drawDot(x+0.01,y-(0.02*6));
    drawDot(x+0.02,y-(0.02*6));

    for(var i = y; i>y-(0.02*6);i=i-0.02){
        drawDot(x+0.03,i);
    }
}

function drawL(x,y){
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x,i);
    }
    drawDot(x+0.01,y-(0.02*6));
    drawDot(x+0.02,y-(0.02*6));
    drawDot(x+0.03,y-(0.02*6));
}

function drawA(x,y){
    for(var i = y-0.02; i>y-(0.02*7);i=i-0.02){
        drawDot(x,i);
    }
    for(var i=x+0.01;i<x+(0.03);i=i+0.01){
        drawDot(i,y);
        drawDot(i,y-0.06);
    }
    for(var i = y-0.02; i>y-(0.02*7);i=i-0.02){
        drawDot(x+0.03,i);
    }
}

function drawS(x,y){
    for(var i = y-0.02; i>y-(0.02*7);i=i-0.02){
        if(i>=y-0.04 || i<y-0.12){
            drawDot(x,i);
        }
    }
    for(var i = y; i>y-(0.02*7);i=i-0.06){
        drawDot(x+0.01,i);
        drawDot(x+0.02,i);
    }
    drawDot(x+0.03,y);
    for(var i = y-0.08; i>y-(0.02*6);i=i-0.02){
        drawDot(x+0.03,i);
    }
}

function drawP(x,y){
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x,i);
    }
    for(var i=x+0.01;i<x+(0.03);i=i+0.01){
        drawDot(i,y);
        drawDot(i,y-0.06);
    }
    for(var i = y-0.02; i>(y-0.02)-(0.02*2);i=i-0.02){
        drawDot(x+0.03,i);
    }
}

function drawM(x,y){
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x,i);
    }
    drawDot(x+0.01,y-0.02);
    drawDot(x+0.02,y-0.04);
    drawDot(x+0.03,y-0.02);
    for(var i = y; i>y-(0.14);i=i-0.02){
        drawDot(x+0.04,i);
    }

}

function drawI(x,y){
    for(var i = y; i>y-(0.02*7);i=i-0.12){
        drawDot(x,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x+0.01,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.12){
        drawDot(x+0.02,i);
    }
}
function draw0(x,y){
    for(var i = y-0.02; i>y-(0.02*6);i=i-0.02){
        drawDot(x,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.12){
        drawDot(x+0.01,i);
        drawDot(x+0.02,i);
    }
    for(var i = y-0.02; i>y-(0.02*6);i=i-0.02){
        drawDot(x+0.03,i);
    }
}
function draw1(x,y){
    for(var i = y-0.02; i>y-(0.02*7);i=i-0.10){
        drawDot(x+0.01,i);
    }
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x+0.02,i);
    }
    drawDot(x+0.03,y-0.12);
}
function draw2(x,y){
    drawDot(x,y-0.02); drawDot(x,y-0.10); drawDot(x,y-0.12);
    drawDot(x+0.01,y); drawDot(x+0.01,y-0.08); drawDot(x+0.01,y-0.12);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.06); drawDot(x+0.02,y-0.12);
    drawDot(x+0.03,y-0.02); drawDot(x+0.03,y-0.04); drawDot(x+0.03,y-0.12);
}
function draw3(x,y){
    drawDot(x,y-0.02); drawDot(x,y-0.1);
    drawDot(x+0.01,y); drawDot(x+0.01,y-0.12);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.06); drawDot(x+0.02,y-0.12);
    drawDot(x+0.03,y-0.02); drawDot(x+0.03,y-0.04); drawDot(x+0.03,y-0.08);drawDot(x+0.03,y-0.1);
}
function draw4(x,y){
    drawDot(x,y-0.04); drawDot(x,y-0.06);
    drawDot(x+0.01,y-0.02); drawDot(x+0.01,y-0.06);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.06);
    for(var i = y; i>y-(0.02*7);i=i-0.02){
        drawDot(x+0.03,i);
    }
}
function draw5(x,y){
    drawDot(x,y); drawDot(x,y-0.02); drawDot(x,y-0.04); drawDot(x,y-0.1);
    drawDot(x+0.01,y); drawDot(x+0.01,y-0.04); drawDot(x+0.01,y-0.12);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.04); drawDot(x+0.02,y-0.12);
    drawDot(x+0.03,y); drawDot(x+0.03,y-0.06); drawDot(x+0.03,y-0.08); drawDot(x+0.03,y-0.1);
}
function draw6(x,y){
    for(var i = y-0.02; i>y-(0.02*6);i=i-0.02){
        drawDot(x,i);
    }
    drawDot(x+0.01,y); drawDot(x+0.01,y-0.04); drawDot(x+0.01,y-0.12);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.04); drawDot(x+0.02,y-0.12);
    drawDot(x+0.03,y-0.06); drawDot(x+0.03,y-0.08); drawDot(x+0.03,y-0.1);
}
function draw7(x,y){
    drawDot(x,y); drawDot(x,y-0.1); drawDot(x,y-0.12);
    drawDot(x+0.01,y);drawDot(x+0.01,y-0.08);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.06);
    drawDot(x+0.03,y); drawDot(x+0.03,y-0.02); drawDot(x+0.03,y-0.04);
}
function draw8(x,y){
    drawDot(x,y-0.02); drawDot(x,y-0.04); drawDot(x,y-0.08); drawDot(x,y-0.1);
    drawDot(x+0.01,y); drawDot(x+0.01,y-0.06); drawDot(x+0.01,y-0.12);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.06); drawDot(x+0.02,y-0.12);
    drawDot(x+0.03,y-0.02); drawDot(x+0.03,y-0.04); drawDot(x+0.03,y-0.08); drawDot(x+0.03,y-0.1);
}
function draw9(x,y){
    drawDot(x,y-0.02); drawDot(x,y-0.04); drawDot(x,y-0.06);
    drawDot(x+0.01,y); drawDot(x+0.01,y-0.08); drawDot(x+0.01,y-0.12);
    drawDot(x+0.02,y); drawDot(x+0.02,y-0.08); drawDot(x+0.02,y-0.12);
    for(var i = y-0.02; i>y-(0.02*6);i=i-0.02){
        drawDot(x+0.03,i);
    }
}