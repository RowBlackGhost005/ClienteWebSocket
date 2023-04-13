package gui;

import domain.Message;

/**
 *
 * @author Luis Angel Marin
 */
public interface IChat {
    
    public void sendMessageAll(Message message);
    
    public void setUserID(String userID);
    
    public void addOnlineUser(String user);
    
    public void removeOnlineUser(String user);
    
    public void printMessage(Message message);
    
    public void addObject(String jsonSong);
}
