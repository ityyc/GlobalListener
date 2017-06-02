package com.tuba.yuanyc.globallistener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import listener.Listener;

/**
 * 测试
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        MyServer myServer = new MyServer();
        switch (view.getId()) {
            case R.id.button1:
                myServer.setModeEnum(ModeEnum.MELERLY);
                myServer.setGeneralListener(new Listener.GeneralListener() {
                    @Override
                    public void onEvent() {
                        Toast.makeText(MainActivity.this, "收到单纯的使用监听器的回调通知", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button2:
                myServer.setModeEnum(ModeEnum.GENERAL);
                myServer.setWeakGeneralListener(new Listener.GeneralListener() {
                    @Override
                    public void onEvent() {
                        Toast.makeText(MainActivity.this, "收到一般使用监听器的回调通知", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button3:
                myServer.setModeEnum(ModeEnum.SIMPLE);
                myServer.setWeakSimpleListener(new Listener.SimpleListener<SimpleEnum>() {
                    @Override
                    public void onEvent(SimpleEnum event) {
                        Toast.makeText(MainActivity.this, "event的值:" + event + "    简单使用监听器", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button4:
                myServer.setModeEnum(ModeEnum.CUSTOM);
                myServer.setWeakCustomListener(new Listener.CustomListener<MyEventInfo>() {
                    @Override
                    public void onEvent(MyEventInfo eventInfo) {
                        CustomEnum customEnum = eventInfo.getCustomEnum();
                        String data = eventInfo.getData();
                        Toast.makeText(MainActivity.this, "customEnum的值:" + customEnum + "  data的值:" + data + "  自定义使用", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
        myServer.execute();
    }
}
