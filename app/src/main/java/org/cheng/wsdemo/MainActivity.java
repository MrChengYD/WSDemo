package org.cheng.wsdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.cheng.wsdemo.bean.WebSocketMessageBean;
import org.cheng.wsdemo.enums.MESSAGETYPE;
import org.cheng.wsdemo.service.WebSocketService;
import org.cheng.wsdemo.util.FakeDataUtil;
import org.cheng.wsdemo.util.NoticeUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Context mContext;
    EditText wsd_editText;
    Button wsd_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Intent intentService = new Intent(mContext, WebSocketService.class);
        startService(intentService);

        initView();

    }
    public void initView(){
        wsd_editText = findViewById(R.id.wsd_editText);
        wsd_button = findViewById(R.id.wsd_button);
        wsd_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(R.id.wsd_button == v.getId()){
            /**
             * 检查连接是否有效，如果有效，发送信息
             * **/
            if(WebSocketService.webSocketConnection.isConnected()){
                String sendText = wsd_editText.getText().toString();
                if(!"".equals(sendText)){
                    WebSocketMessageBean webSocketMessageBean = new WebSocketMessageBean();
                    webSocketMessageBean.setMessageType(MESSAGETYPE.USERCHAT);
                    webSocketMessageBean.setSendUserId(FakeDataUtil.SENDUSERID);
                    webSocketMessageBean.setMessage(sendText);

                    WebSocketService.webSocketConnection.sendTextMessage(JSON.toJSONString(webSocketMessageBean));
                }else{
                    Toast.makeText(mContext , NoticeUtil.NOT_ALLOWED_EMP , Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(mContext , NoticeUtil.NO_CONNECT , Toast.LENGTH_LONG).show();
            }

        }
    }
}
