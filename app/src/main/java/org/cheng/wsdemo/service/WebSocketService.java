package org.cheng.wsdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.cheng.wsdemo.websocket.MyWebSocket;


import de.tavendo.autobahn.WebSocketConnection;

public class WebSocketService extends Service {


    public static WebSocketConnection webSocketConnection;
    public MyWebSocket myWebSocket;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        /** 建立连接 **/
        myWebSocket = new MyWebSocket();
        myWebSocket.run();
    }
}
