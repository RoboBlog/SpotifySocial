import React from 'react';
import $ from 'jquery';
import SockJS from 'sockjs-client';
// import Stomp from 'stompjs'

class ChatComponent extends React.Component {


  render() {
    let stompClient = null;
    let room = 'maciekandkarol';

    function connect() {
      let socket = new SockJS('http://localhost:8080/testchat');
      // stompClient = Stomp.overWS(socket);
      stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        let link = '/topic/' + room;
        stompClient.subscribe(link, function (message) {
          // showGreeting(JSON.parse(message.body).content);
          showMessage(JSON.parse(message.body).content);
        });
      });
    }




    function sendName() {

      stompClient.send("/app/hello/"+room, [], JSON.stringify(
        {"message": $("#messageInput").val()} ));//do kogo
    }
// function  sendTest() {
//     stompClient.send("/app/hello2", {}, JSON.stringify({'test': $("#test").val()}));
// }

    function showGreeting(message) {
      $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }

//if null
    function showMessage(message) {
      // let fromNow = moment(message.time).format('HH:mm:ss');
      console.log(message)
      let $message = $(`<li class="clearfix">
        <div class="message-data 'align-left'">
        <span class="message-data-name">maciek</span>
        <!--<span class="message-data-time">${fromNow}</span>-->
    </div>
    <div class="message my-message">
       ${message}
    </div>
    </li>`);


      let $messages = $('#messages');
      $messages.append($message);
      $messages.scrollTop($messages.prop("scrollHeight"));

    }

    $(function () {
      $("form").on('submit', function (e) {
        e.preventDefault();
      });
      $( "#connect" ).click(function() { connect(); });
      $( "#disconnect" ).click(function() { disconnect(); });
      $( "#send" ).click(function() { sendName(); });
    });





    function setConnected(connected) {
      $("#connect").prop("disabled", connected);
      $("#disconnect").prop("disabled", !connected);
      if (connected) {
        $("#conversation").show();
      }
      else {
        $("#conversation").hide();
      }
      $("#greetings").html("");
    }



    function disconnect() {
      if (stompClient !== null) {
        stompClient.disconnect();
      }
      setConnected(false);
      console.log("Disconnected");
    }

    connect();
    return (
        <div id="container">
          <div id="chat">
            <ul id="messages">
            </ul>
            <form>
              <div className="chat-message clearfix">
                <input name="message-to-send" id="messageInput" placeholder="Type your message" />
                <button type="submit" id="send">Send</button>
              </div>
            </form>
          </div>
        </div>
    );
  }
}

export default ChatComponent;
