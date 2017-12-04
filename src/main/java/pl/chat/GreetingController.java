package pl.chat;

import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

//    private final ActiveUserService activeUserService;

//    public GreetingController(ActiveUserService activeUserService) {
//        this.activeUserService = activeUserService;
//    }

    @MessageMapping("/hello/{room}")
    @SendTo("/topic/{room}")
    public Greeting content(@DestinationVariable String room, String message) throws Exception {
        JSONObject js = new JSONObject(message);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("AUTH" + authentication);

        System.out.println(js.get("message"));

//        System.out.println(js.getJSONObject(1).get("test"));
//        System.out.print(SecurityContextHolder.getContext().getAuthentication());
        return new Greeting((String) js.get("message"));
//        return new Message()
    }


//    @MessageMapping("/hello2")
//    @SendTo("/topic/greetings")
//    public Greeting name(HelloMessage message) throws Exception {
//        System.out.println("Content" + message.getTest());
//        return new Greeting(  message.getTest());
    //  return new Message()
//
//    }
}
