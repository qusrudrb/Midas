<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/sockjs.js" />"></script>
	<script src="<c:url value="/resources/js/stomp.js" />"></script>
	<script type="text/javascript">
		$(function(){
			connect();
			
			$("#send").on("click",function(){
				sendMessage();
			})
			
			document.onkeydown = function ( event ) {
			    if ( event.keyCode == 116  // F5
			        || event.ctrlKey == true && (event.keyCode == 82) // ctrl + r
			    ) {
			        //접속 강제 종료
			        disconnect();
			        // keyevent
			        event.cancelBubble = true; 
			        event.returnValue = false; 
			        setTimeout(function() {
			            window.location.reload();
			        }, 100);
			        return false;
			    }
			}
			
		})
	
		var stompClient = null;
		
		//채팅방 연결
		function connect() {
			// WebsocketMessageBrokerConfigurer의 registerStompEndpoints() 메소드에서 설정한 endpoint("/endpoint")를 파라미터로 전달
			var socket = new SockJS('/kr/endpoint');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				
				// 메세지 구독
				// WebsocketMessageBrokerConfigurer의 configureMessageBroker() 메소드에서 설정한 subscribe prefix("/subscribe")를 사용해야 함
				// 멀티 채팅방
				stompClient.subscribe('/subscribe/chat/${roomNum}', function(message){
					var data = JSON.parse(message.body);
					$("#chatroom").append(data.username+" 님 -> "+data.message+"<br />");
				});	
				
			});
		}
		
		//채팅 메세지 전달
		function sendMessage() {
			var str = $("#chatbox").val();
			str = str.replace(/ /gi, '&nbsp;')
			str = str.replace(/(?:\r\n|\r|\n)/g, '<br />');
			if(str.length > 0){
				// WebsocketMessageBrokerConfigurer의 configureMessageBroker() 메소드에서 설정한 send prefix("/")를 사용해야 함
				// 멀티 채팅방
				stompClient.send("/chat/${roomNum}", {}, JSON.stringify({
					message : str
				}));
				
			}
			
			$("#chatbox").val("");
		}
		
		// 채팅방 연결 끊기
		function disconnect() {
			stompClient.disconnect();
		}
	</script>
	<style type="text/css">
		#chatroom{
			width: 300px;
			height: 300px;
			border: 1px solid;
		}
	</style>
<title>일반 채팅 방</title>
</head>
<body>
	<input type="text" id="chatbox"><input type="button" id="send" value="전송"><br><br>
	<div id="chatroom">
	</div>
</body>
</html>