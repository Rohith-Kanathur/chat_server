let socket = new WebSocket("ws://localhost:8081/ChatServer/chat");

socket.onmessage = function(event) {
	let str = event.data;
	const arr = str.split("~");
	
	if (arr.length == 2) {
		let message = arr[0];
		let sender = arr[1];
		
		let messageElem = document.createElement('div');
		messageElem.textContent = message;
		document.getElementById('incomingMessagesID').prepend(messageElem);
		
		document.getElementById("senderID").innerText = sender;
	}
	
}

socket.onopen = function(event) {
	let data = sessionStorage.getItem('userID');
	socket.send(data);
}

socket.onclose = function(event) {
	
}


function mySend() {
	let messageType = "SEND";
	let outgoingMessage = document.getElementById("messageID").value;
	let sender = sessionStorage.getItem('userID');
	let receiver = document.getElementById("receiverID").value;
	let result = messageType.concat("~", outgoingMessage, "~", sender, "~", receiver);
	socket.send(result);
	return false;
}