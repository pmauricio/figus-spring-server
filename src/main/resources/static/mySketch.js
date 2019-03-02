/// <reference path="./p5.global-mode.d.ts" />
var boxes = [];
 var colors = ['#fe4a49', '#2ab7ca', '#fed766', '#e6e6ea'/*, '#f4f4f8','#4a4e4d','#0e9aa7','#3da4ab','#f6cd61','#fe8a71'*/];
var lineColors = ['#3385c6'/*,'#ff6f69' ,'#3385c6','#ff6f69'*/];

let input, button, greeting;

//let c; 

function setup() {

	c = createCanvas(windowWidth, windowHeight);

	for (var i = 0; i < 20; i++) {

			// // Obtain the points for the fourth plot
			uniformStack = [];
			uniformCounter = 0;

			for (var j = 0;  j < 30; j++) {
				uniformStack[j] = 0;
			}

			for (j = 0; j < 20; j++) {
				index = Math.round(this.uniformStack.length * Math.random());

				if (index >= 0 && index < this.uniformStack.length) {
					this.uniformStack[index]++;
					this.uniformCounter++;
				}
			}

			points4 = [];

			for (j = 0; j < this.uniformStack.length; j++) {
				points4[j] = new GPoint(j , this.uniformStack[j] /*/ this.uniformCounter*/, "point " + j);
			}

// Setup for the fourth plot
			console.log((c));
			plot4 = new GPlot(this);
			
			plot4.setOuterDim(150, 150);
		//	plot4.setYLim(-0.005, 0.1);
			//plot4.getXAxis().getAxisLabel().setText("x variable");
			plot4.getXAxis().setLineColor('#2a4d69');
			// plot4.getYAxis().getAxisLabel().setText("Relative probability");
			// plot4.getTitle().setText("Uniform distribution (" + uniformCounter + " points)");
			plot4.getTitle().setTextAlignment(LEFT);
			plot4.getTitle().setRelativePos(0.1);
			plot4.setPoints(points4);
			plot4.startHistograms(GPlot.VERTICAL);
			
			plot4.setLineColor(color(colors[i % 4]));
			plot4.setPointColors(color(colors[i % 4]));
			plot4.getHistogram().setLineColors([color(lineColors[0])]);
			boxes.push(new Box(color(colors[i % colors.length]), random(40, 60), 'HUB_NAME_00' + int(random(100)),plot4));

		 ellipseMode(CENTER);

	}




};




function draw() {
	background(color('#afafaf'));
	for (var i = 0; i < boxes.length; i++) {
		boxes[i].show();
	}
}






function mouseDragged() {
	for (var i = 0; i < boxes.length; i++) {
		if (boxes[i].locked) {
			boxes[i].xpos = mouseX - boxes[i].xoffset;
			boxes[i].ypos = mouseY - boxes[i].yoffset;
		}
	}
}

function mouseReleased() {
	for (var i = 0; i < boxes.length; i++) {
		boxes[i].locked = false;
	}
}

var gaussianCounter, uniformStack, uniformCounter;

function Box(tempColor, tempSize, hub_name,plot4) {
	
	console.log(tempColor);
	this.c = tempColor
	this.xpos = random(width);
	this.ypos = random(height);
	this.boxsize = tempSize;
	this.boxover = false;
	this.locked = false;
	this.xoffset = 0;
	this.yoffset = 0;
	this.hub_name = hub_name;
	this.data = int(random(1000));
	rectMode(RADIUS);

	this.plot = plot4;
	this.plot.setPos(this.xpos-40, this.ypos+200);




	this.show = function () {
		this.plot.setPos(this.xpos-160, this.ypos-90);
		if (mouseX > this.xpos - this.boxsize && mouseX < this.xpos + this.boxsize &&
			mouseY > this.ypos - this.boxsize && mouseY < this.ypos + this.boxsize) {
			this.boxover = true;
			fill(255);

			if (mouseIsPressed && this.boxover == true) {
				stroke(color('#0392cf'));
				strokeWeight(3);
			} else {
				noStroke();
			}

		} else {
			this.boxover = false;
			noStroke();
			fill(this.c);
		}
		//	rect(this.xpos, this.ypos, this.boxsize, this.boxsize, 7);
		ellipse(this.xpos, this.ypos, this.boxsize, this.boxsize, 7);
		noStroke();
		textAlign(CENTER);
		fill(color('#2a4d69'));
		textSize(15);
		text(this.hub_name, this.xpos-50, this.ypos + this.boxsize + 5);

		textSize(20);
		text(this.data, this.xpos, this.ypos + 7);



		// Actions over the fourth plot (scrolling)
		if (this.plot.isOverBox(mouseX, mouseY)) {
			// Get the cursor relative position inside the inner plot area
			relativePos = this.plot.getRelativePlotPosAt(mouseX, mouseY);

			// Move the x axis
			if (relativePos[0] < 0.2) {
				this.plot.moveHorizontalAxesLim(2);
			} else if (relativePos[0] > 0.8) {
				this.plot.moveHorizontalAxesLim(-2);
			}

			// Move the y axis
			if (relativePos[1] < 0.2) {
				this.plot.moveVerticalAxesLim(2);
			} else if (relativePos[1] > 0.8) {
				this.plot.moveVerticalAxesLim(-2);
			}

			// Change the inner area bg color
			this.plot.setBoxBgColor(color(200, 100));
		} else {
			this.plot.setBoxBgColor(color(200, 50));
		}

		// Add one more point to the uniform stack
		//index = Math.round(uniformStack.length * Math.random());

		// if (index >= 0 && index < uniformStack.length) {
		// 	uniformStack[index]++;
		// 	uniformCounter++;

		// 	points4 = [];

		// 	for (i = 0; i < uniformStack.length; i++) {
		// 		points4[i] = new GPoint(i + 0.5 - uniformStack.length / 2, uniformStack[i] / uniformCounter, "point " + i);
		// 	}

		// 	plot4.setPoints(points4);
		// 	plot4.getTitle().setText("Uniform distribution (" + uniformCounter + " points)");
		// }
	
		// Draw the forth plot

		this.plot.beginDraw();
		 this.plot.getMainLayer().setLineColor('#2a4d69');
		//this.plot.drawBox();
		this.plot.drawXAxis();
		this.plot.drawYAxis();
		this.plot.drawTitle();
		this.plot.drawHistograms('#2a4d69');
		this.plot.endDraw();
	};
};



function mousePressed() {
	for (var i = 0; i < boxes.length; i++) {
		//checking to see if the mouse is over the box and turning it white if it is
		if (boxes[i].boxover == true) {
			boxes[i].locked = true;
			print("mouse is pressed")
		} else {

			boxes[i].locked = false;
			print("mouse isn't pressed")
		}
		boxes[i].xoffset = mouseX - boxes[i].xpos;
		boxes[i].yoffset = mouseY - boxes[i].ypos
		print(boxes[i].locked);
	}
	return false;
}