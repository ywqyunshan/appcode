package com.iigeo.appcode.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iigeo.appcode.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * SocketClient
 */
public class SocketActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.connectBtn)
    Button connectBtn;
    @BindView(R.id.sendBtn)
    Button sendBtn;
    @BindView(R.id.disconnectBtn)
    Button disconnectBtn;
    @BindView(R.id.textView)
    TextView textView;

    private Handler handler;
    private ExecutorService executorService;
    private Thread sendThread;
    private Thread conectThread;
    private String recevieMsg;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String host="192.168.0.104";
    private int port=8999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);
        initExecutor();
    }

    private void initExecutor() {
        executorService= Executors.newFixedThreadPool(20);
        handler=new MsgHandler(this);
    }

    @OnClick({R.id.sendBtn, R.id.disconnectBtn, R.id.connectBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sendBtn:
                Editable msg=editText.getText();
                if (msg!=null&&!msg.toString().isEmpty()){
                    executorService.execute(new SendThread(msg.toString()));
                }
                break;
            case R.id.disconnectBtn:
                executorService.execute(new SendThread("0"));
                break;
            case R.id.connectBtn:
                executorService.execute(new ConnectThread());
                break;
        }
    }

    //连接线程
    class ConnectThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                //创建socket
                Socket socket=new Socket(host,port);
                //获取客户端发送数据
                printWriter=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")),true);
                //获取服务返回数据
                bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (!Thread.currentThread().isInterrupted()){
                        if ((recevieMsg=bufferedReader.readLine())!=null){
                            Message message=handler.obtainMessage();
                            message.obj=recevieMsg;
                            handler.sendMessage(message);
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    //发送线程
    class SendThread extends Thread{
        private String msg;

        public SendThread(String name) {
            this.msg=name;
        }

        @Override
        public void run() {
            super.run();
            printWriter.println(msg);
        }
    }

    static class MsgHandler extends Handler{

        private WeakReference<SocketActivity> socketActivityWeakReference;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SocketActivity sActivity=socketActivityWeakReference.get();
            if (msg.obj!=null&&sActivity!=null)
                sActivity.textView.setText(msg.obj.toString());
        }

        public MsgHandler(SocketActivity socketActivity) {
            this.socketActivityWeakReference=new WeakReference<SocketActivity>(socketActivity);
        }
    }
}
