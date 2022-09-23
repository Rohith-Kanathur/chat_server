let socket = new WebSocket("ws://localhost:8081/ChatServer/chat");

socket.onmessage = function(event) {
	let str = event.data;
	const arr = str.split("~");
	
	if (arr.length == 1) {			// Used to display the list of all online users.
	
		if ((arr[0].localeCompare("Remove List of All Online Users")) == 0)
			document.getElementById('onlineUsersListID').innerHTML = '';
		else {
			let messageElem = document.createElement('div');
			messageElem.textContent = arr[0];
			document.getElementById('onlineUsersListID').prepend(messageElem);
		}
	}
	else if (arr.length == 2) {		// Used to display the sent message and sender to the user.
		
		let message = arr[0];
		let sender = arr[1];
		
		let messageElem1 = document.createElement('div');
		messageElem1.textContent = message;
		document.getElementById('incomingMessagesID').prepend(messageElem1);
		
		let messageElem2 = document.createElement('div');
		messageElem2.textContent = sender;
		document.getElementById('senderID').prepend(messageElem2);
	}
	
}

socket.onopen = function(event) {
	let data = sessionStorage.getItem('userID');
	document.getElementById('welcomeMessageID').prepend("Welcome " + data);
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