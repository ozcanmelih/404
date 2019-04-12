alert("OBSS Sınav Platformuna Hoş  Geldiniz");

var jiraId = 1;
var candidateId = 2;

function startStream() {	

	initCurrentCandidate(jiraId);
	
	const vidElm = document.createElement("video");
	vidElm.id= "currentVid";
	vidElm.autoplay = true;
	document.body.append(vidElm);
	
	const constraints = {
		  video:{width: {exact: 640}, height: {exact: 480}}
		};

	vidElm.load();

	navigator.mediaDevices.getUserMedia(constraints).
	  then((stream) => {vidElm.srcObject = stream});
	  
	
	$("body").bind("copy", function(e){
		// access the clipboard using the api
		console.log(e);
		var copiedData = window.getSelection().toString(); 
		//e.originalEvent.clipboardData.getData('text'); //not valid for cpy
		var postURL = "http://localhost:8080/events/COPY/" + candidateId;
		console.log("sneding copy data");
		sendCopyPasteEvent(postURL, copiedData);
	} );
	
	$("body").bind("paste", function(e){
		// access the clipboard using the api
		var pastedData = e.originalEvent.clipboardData.getData('text');
		var postURL = "http://localhost:8080/events/PASTE/" + candidateId;
		console.log("sneding paste data");
		sendCopyPasteEvent(postURL, pastedData);
	} );
	
	console.log("starting automation ....");
	triggerConstantSS(0);
}

function triggerConstantSS(count){
	setTimeout(function(){
		console.log("automated SS send:" + count);
		sendCurrentImageToCheckService();
		triggerConstantSS(count + 1);
		
	}, 10000);
}

function sendCopyPasteEvent(postURL, eventStr) {
	$.post(postURL, {'eventStr': eventStr})
	.done(function(resp) {
		console.log("copy/paste sent:" + resp);
	})
	;
}

function getScreenshot(){

	var videoElement = document.getElementById("currentVid");
	var canvas = document.createElement('canvas');
	canvas.width = 640;
	canvas.height = 480;
	var ctx = canvas.getContext('2d');
	ctx.drawImage(videoElement, 0, 0, canvas.width, canvas.height);

	//convert to desired file format
	var dataURI = canvas.toDataURL('image/jpeg'); // can also use 'image/png'
	var imgElement = document.getElementById("captured");
	imgElement.src = dataURI;
	
	return dataURI;
}


function initCurrentCandidate(jiraId) {
	var postURL = "http://localhost:8080/setup-candidate"
	$.post(postURL, {'jiraId':jiraId})
	.done(function(resp) {
		candidateId = resp.id;
	})
	.fail(function() {
		alert("Aday bilgisi olustururken hata olustu");
	})
	;
}

function sendCurrentImageToCheckService(){
	var postURL = "http://localhost:8080/face/" + candidateId;
	
	
	$.ajax({
		type: "POST",
		url: postURL,
		data: {imageBase64: getScreenshot()}
		
	})
	.done(function(resp){
		console.log(resp);
	})
	;
}