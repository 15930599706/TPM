function showMask(config) {
	config = config || {
		info: ""
	};
	var mask = document.createElement("div");
	mask.id = "temp_mask";
	mask.style = "position:fixed;left:0;top:0;width:100%;height:100vh;background-color:rgba(0,0,0,0.5)";
	
	var center = document.createElement("div");
	center.style = "position:absolute;top:45%;left:45%;color:#fff;text-align:center;";
	
	var canvas = document.createElement("canvas");
	canvas.width = 100;
	canvas.height = 100;
	
	var text = document.createElement("div");
	text.innerHTML = config.info;
	
	center.appendChild(canvas);
	center.appendChild(text);
	mask.appendChild(center);
	document.getElementsByTagName("html")[0].appendChild(mask);
	
	var ctx=canvas.getContext('2d');
	
	var params = {
		startAngle: 0,
		endAngle: 0,
	};
	animate.push({
		target: params,
		setValue: function(value) {
			this.target.startAngle = value;
			this.target.endAngle = value + Math.PI;
			paint(ctx, this.target);
		},
		keyFrames: [
            { value: 0, duration: 0 },
            { value: Math.PI * 2, duration: 1000 }
		],
		loopType: "repeat",
		loopTimes: 0
	});
	
	function paint(ctx, params) {
		ctx.clearRect(0, 0, 100, 100);
		ctx.beginPath();
		ctx.strokeStyle = "#fff";
		ctx.lineWidth = 6;
	    ctx.arc(50,50,40,params.startAngle,params.endAngle,false);
	    ctx.stroke();
	}
}

function hiddenMask(callback) {
	var mask = document.getElementById("temp_mask");
	if(mask) {
		mask.parentNode.removeChild(mask);
		if(callback) callback();
	}
}