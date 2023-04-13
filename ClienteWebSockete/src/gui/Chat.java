package gui;

import com.google.gson.Gson;
import domain.Cancion;
import domain.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

/**
 *
 * @author Luis Angel Marin
 */
public class Chat implements IChat{
    
    private GUIMensajes mensajes;
    private Session session;
    private Gson gson;
    private RemoteEndpoint.Basic basicRemote;
    
    
    public Chat(Session s){
        mensajes = new GUIMensajes(this);
        this.gson = new Gson();
        session = s;
        basicRemote = s.getBasicRemote();
    }
    
    @Override
    public void sendMessageAll(Message message){
        try {
            System.out.println(gson.toJson(message , Message.class));
            session.getBasicRemote().sendText(gson.toJson(message , Message.class));
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setUserID(String userID) {
        mensajes.setOnlineUserID(userID);
    }

    @Override
    public void addOnlineUser(String user) {
        mensajes.addOnlineUser(user);
        mensajes.addUserToSend(user);
    }

    @Override
    public void removeOnlineUser(String user) {
        mensajes.removeOnlineUser(user);
        mensajes.removeUserToSend(user);
    }

    @Override
    public void printMessage(Message message) {
        mensajes.addMessage(message.getMessage());
    }

    @Override
    public void addObject(String jsonSong) {
       Cancion cancion = gson.fromJson(jsonSong.substring(jsonSong.indexOf("{")), Cancion.class);
       mensajes.addCancion(cancion);
    }
    
}
