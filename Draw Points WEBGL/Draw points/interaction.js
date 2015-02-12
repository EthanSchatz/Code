/*CS435, Project #2, Ethan Schatzline
	Draw dots based on what key you press. 
	Left click while holding:
	'R' to draw a Red dot,
	'Y' to draw a Yellow dot, 
	'G' to draw a Green dot, 
	'B' to draw a Blue dot,
	'C' to draw a Cyan dot,
	'M' to draw a Maroon dot. 
	Left click and drag a dot to move it.
	Right click a dot to delete it.
*/

var canvas;
var gl;

var maxNumTriangles = 200;  
var maxNumVertices  = 3 * maxNumTriangles;
var index = 0;
//which color to draw
var colorNum = 7;

//vertex array
var points=[];
//color array
var colormem=[];

var mousedown=false;
var mouse = 0;
var circle = -1;



var colors = [
    vec4( 1.0, 1.0, 1.0, 1.0 ),  // white
    vec4( 1.0, 0.0, 0.0, 1.0 ),  // red
    vec4( 1.0, 1.0, 0.0, 1.0 ),  // yellow
    vec4( 0.0, 1.0, 0.0, 1.0 ),  // green
    vec4( 0.0, 0.0, 1.0, 1.0 ),  // blue
    vec4( 1.0, 0.0, 1.0, 1.0 ),  // magenta
    vec4( 0.0, 1.0, 1.0, 1.0 ),	//cyan
    vec4( 0.5, 0.5, 0.5, 1.0 )   //grey
];

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


    
    
    //left click
    canvas.addEventListener("click", function(e){
    	if(e.button==0){
        gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer );
        var t = vec2(2*event.clientX/canvas.width-1, 
           2*(canvas.height-event.clientY)/canvas.height-1);
        
        if(colorNum<7){
        points.push([t[0],t[1]]);
        //update vertex buffer
        gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.DYNAMIC_DRAW );

        gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
        colormem.push(vec4(colors[colorNum]));
       //update color buffer
        gl.bufferData( gl.ARRAY_BUFFER, flatten(colormem), gl.DYNAMIC_DRAW );
        index++;
   	 }
    }
    } );

    //right click
    canvas.addEventListener("contextmenu", function(e){
    	if (e.button === 2) {
    		//disable context menu from popping up
      		e.preventDefault();
  	   }
         var t = vec2(2*event.clientX/canvas.width-1, 
           2*(canvas.height-event.clientY)/canvas.height-1);
         for(var i=points.length-1;i>=0;i--){
         	if(isInside(t[0],t[1],points[i][0],points[i][1])){
         		points.splice(i,1);
         		colormem.splice(i,1);
         		index--;
         		break;
         	}
         	
         }
         	
      	//update vertex buffer
    	gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer);
    	gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.DYNAMIC_DRAW );

    	//update color buffer
    	gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    	gl.bufferData( gl.ARRAY_BUFFER, flatten(colormem), gl.DYNAMIC_DRAW );
    	
    }, false);


    //mouse drag
    canvas.addEventListener("mousemove",function(){
    	if(mousedown){
    		//new mouse location
    		var t = vec2(2*event.clientX/canvas.width-1, 
            2*(canvas.height-event.clientY)/canvas.height-1);
    		if(circle!=-1){
    		//move circle with mouse without circle jumping to mouse
    		points[circle][0]+=t[0]-mouse[0];
    		points[circle][1]+=t[1]-mouse[1];
    		//mouse now equal to current mouse location
    		mouse = t;
    	}
            
         //update vertex buffer
    	gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer);
    	gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.DYNAMIC_DRAW );

    	//update color buffer
    	gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    	gl.bufferData( gl.ARRAY_BUFFER, flatten(colormem), gl.DYNAMIC_DRAW );
    	}
    });

    //adds a dot to the end of the vertex array/color array 
    //so that it is drawn on top
    function addLast(i){
    	var v = points[i];
    	var c = colormem[i];
    	points.splice(i,1);
    	colormem.splice(i,1);
    	points.push(v);
    	colormem.push(c);
    	//update vertex buffer
    	gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer);
    	gl.bufferData( gl.ARRAY_BUFFER, flatten(points), gl.DYNAMIC_DRAW );

    	//update color buffer
    	gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    	gl.bufferData( gl.ARRAY_BUFFER, flatten(colormem), gl.DYNAMIC_DRAW );
    }

    function colorLast(i){
    	var c = colormem[i];
    	colormem.splice(i,1);
    	colormem.push(c);

    	gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    	gl.bufferData( gl.ARRAY_BUFFER, flatten(colormem), gl.DYNAMIC_DRAW );

    }



    //keydown: turn on dot drawing and set color
    window.onkeydown = function(event){
    	//white
    	if(event.keyCode== 87) colorNum=0;
    	//red
    	if (event.keyCode==82) colorNum=1;
    	//yellow
    	if (event.keyCode==89) colorNum=2;
    	//green
    	if (event.keyCode==71) colorNum=3;
    	//blue
    	if (event.keyCode==66) colorNum=4;
    	//magenta
    	if (event.keyCode==77) colorNum=5;
    	//cyan
    	if (event.keyCode==67) colorNum=6;
    	//loop
    	if(event.keyCode==49)  colorLast(0);
    	
    }

    
    //keyup: turn off dot drawing
    window.onkeyup = function(event){
    	colorNum=7;
    }

    //gets mouse location and enables dot dragging
    //by selecting current dot with isInside method
    canvas.addEventListener("mousedown",function(e){
    	if(e.button===2){
    		return false;
    	}
    	if(colorNum==7){
    	var t = vec2(2*event.clientX/canvas.width-1, 
           2*(canvas.height-event.clientY)/canvas.height-1);
    	mouse = t;
    	mousedown=true;
    	for(var i=points.length-1;i>=0;i--){
         		if(isInside(t[0],t[1],points[i][0],points[i][1])){
         			circle=points.length-1;
         			addLast(i);
         			break;
         		}
         	}
         }
    });

    //mouseup: disables dot dragging and deselects current dot
    canvas.addEventListener("mouseup",function(){
    	mousedown=false;
    	circle=-1;
    });


    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 0.5, 0.5, 0.5, 1.0 );


    //
    //  Load shaders and initialize attribute buffers
    //
    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );
    
    
    var vBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer);
    gl.bufferData( gl.ARRAY_BUFFER, 8*maxNumVertices, gl.DYNAMIC_DRAW );
    
    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);
    
    var cBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer );
    gl.bufferData( gl.ARRAY_BUFFER, 16*maxNumVertices, gl.DYNAMIC_DRAW );
    
    var vColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vColor );

    
    render();

}

//determines if the current mouse position is
//inside the current dot
//100 = pointSize
//pointSize/width or height gives WebGL coords
function isInside(x,y, pointx, pointy){

	if (diff(x,pointx)<=(100/canvas.width) && diff(y,pointy)<=(100/canvas.height))
		return true;

	return false;
}

//finds the difference between 2 numbers
// (mouse location vs. dot location)
function diff(a,b){
	if(a>b){
		return a-b;
	}
	else if(b>a){
		return b-a;
	}
	else return 0;
}


function render() {
    gl.clear( gl.COLOR_BUFFER_BIT );
    gl.drawArrays( gl.POINTS, 0, points.length);


    //constantly render the frame
    window.requestAnimFrame(render);

}