/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientewebsockete;

import gui.GUIMensajes;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author lv1013
 */
public class ClienteWebSockete {
    private static Object waitLock = new Object();

        
    public static void main(String[] args) {
        WebSocketContainer container=null;//
        Session session=null;
        try {                        
            //Tyrus is plugged via ServiceLoader API. See notes above
            container = ContainerProvider.getWebSocketContainer();
            //WS1 is the context-root of my web.app
            //ratesrv is the  path given in the ServerEndPoint annotation on server implementation
            session=container.connectToServer(MiEndpoint.class, 
                    URI.create("ws://localhost:8080/MensajeroWebSocket/websocketendpoint"));            
//            RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
//            String msj=null;
//            Scanner sc = new Scanner(System.in);
//            
//            do{
//                msj = sc.nextLine();
//                basicRemote.sendText(msj);
//            }while(!msj.equals("ya me voy"));            

        } catch (DeploymentException | IOException ex) {
            ex.printStackTrace();
        }
//        finally{
//            if(session!=null){
//                try {
//                    session.close();
//                } catch (IOException e) {     
//                    e.printStackTrace();
//                }
//            }     
//        }
    }
}
