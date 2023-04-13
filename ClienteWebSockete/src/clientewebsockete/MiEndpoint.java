/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientewebsockete;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.Cancion;
import domain.Message;
import gui.Chat;
import gui.IChat;
import java.io.IOException;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

/**
 *
 * @author lv1013
 */
@ClientEndpoint
public class MiEndpoint {
    
    private IChat chat;
    private Gson gson;
    private Session sesion;
    private Message message;
    
    @OnOpen
    public void open(Session s) throws IOException{
        chat = new Chat(s);
        this.sesion = s;
    }
    

    @OnMessage
    public void onMessage(String receivedMessage) {
       
        System.out.println("Message: " + receivedMessage);
        
        JsonObject messageJson = new Gson().fromJson(receivedMessage, JsonObject.class);
        String from = messageJson.get("from").getAsString();
        String sendTo = messageJson.get("to").getAsString();
        String messageText = messageJson.get("message").getAsString();
        String command = messageJson.get("command").getAsString();
        
        Message message = new Message(from, sendTo, command, messageText);
        
        manageMessage(message);
        
//        String command = null;
//        
//        if(messageType.equalsIgnoreCase("msg")){
//            command = "add:msg";
//        }else if(messageType.equalsIgnoreCase("jsn")){
//            command = "add:obj";

//            Cancion cancion = gson.fromJson(messageText, Cancion.class);
//            messageText = gson.toJson(cancion);
//        }
        
//        if(sendTo.equalsIgnoreCase("All")){
//            message = new Message(sesion.getId() , command , sesion.getId() + ": " + messageText);
//            sendMessageAllClients(message, sesion);
//        }else{
//            message = new Message(sesion.getId() , command , "[Privado] " + sesion.getId() + ": " + messageText);
//            sendMessageSingleClient(message, getOnlineClient(sendTo));
//        }
        
        //String echoMsg = sesion.getId() + ": " + receivedMessage;
        
        // = new Message(SVR_TOKEN, "add:msg", echoMsg);
        
        //sendMessageAllClients(message, sesion);
    }

    public void manageMessage(Message message){
        String command = message.getCommand();
        String messageContent = message.getMessage();
        
        
        if(command.equalsIgnoreCase("add:uid")){
            chat.setUserID(messageContent);
        }else if(command.equalsIgnoreCase("add:usr")){
            chat.addOnlineUser(messageContent);
        }else if(command.equalsIgnoreCase("del:usr")){
            chat.removeOnlineUser(messageContent);
        }else if(command.equalsIgnoreCase("add:msg")){
            chat.printMessage(message);
        }else if(command.equalsIgnoreCase("add:obj")){
            chat.addObject(messageContent);
        }
    }
}
