package com.living.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jialin.chat.Message;
import com.jialin.chat.MessageAdapter;
import com.jialin.chat.MessageInputToolBox;
import com.jialin.chat.OnOperationListener;
import com.jialin.chat.Option;
import com.living.R;
import com.living.util.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 图灵机器人聊天界面
 *
 * @author Tobin
 */
public class TuLingChatActivity extends AppCompatActivity implements View.OnClickListener {

    private MessageInputToolBox box;
    private ListView listView;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuling);
        initView();

        initMessageInputToolBox();
        initListView();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.messageListview);

        //create Data
        Message message = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Hi", false, true, new Date(System.currentTimeMillis()));
        Message message1 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Hello World", true, true, new Date(System.currentTimeMillis()));
        Message message2 = new Message(Message.MSG_TYPE_PHOTO, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "em_cate_duck", false, true, new Date(System.currentTimeMillis()));
        Message message3 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Haha", true, true, new Date(System.currentTimeMillis()));
        Message message4 = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "big3", false, true, new Date(System.currentTimeMillis()));
        Message message5 = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "big2", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));
        Message message6 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_FAIL, "Tom", "avatar", "Jerry", "avatar", "test send fail", true, false, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));
        Message message7 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SENDING, "Tom", "avatar", "Jerry", "avatar", "test sending", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));

        List<Message> messages = new ArrayList<>();
        messages.add(message);
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message5);
        messages.add(message6);
        messages.add(message7);

        adapter = new MessageAdapter(this, messages);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                box.hide();
                return false;
            }
        });

    }

    /**
     * init MessageInputToolBox
     */
    private void initMessageInputToolBox() {
        box = (MessageInputToolBox) findViewById(R.id.messageInputToolBox);
        box.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {
                LogUtil.e("tobin initMessageInputToolBox send content: " + content);
                Message message = new Message(0, 1, "Tom", "avatar", "Jerry", "avatar", content, true, true, new Date());
                adapter.getData().add(message);
                listView.setSelection(listView.getBottom());
                //Just demo
                createReplayMsg(message);
            }

            @Override
            public void selectedFace(String content) {
                LogUtil.e("tobin initMessageInputToolBox selectedFace content: " + content);
                Message message = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "Tomcat", "avatar", "Jerry", "avatar", content, true, true, new Date());
                adapter.getData().add(message);
                listView.setSelection(listView.getBottom());
                //Just demo
                createReplayMsg(message);
            }

            @Override
            public void selectedFuncation(int index) {
                LogUtil.e("tobin initMessageInputToolBox selectedFuncation index: " + index);
                switch (index) {
                    case 0:
                        //do some thing
                        break;
                    case 1:
                        //do some thing
                        break;
                    default:
                        break;
                }
                Toast.makeText(TuLingChatActivity.this, "Do some thing here, index :" + index, Toast.LENGTH_LONG).show();
            }
        });

        ArrayList<String> faceNameList = new ArrayList<>();
        for (int x = 1; x <= 10; x++) {
            faceNameList.add("big" + x);
            faceNameList.add("big" + (11 - x));
        }

        Map<Integer, ArrayList<String>> faceData = new HashMap<>();
//        faceData.put(R.drawable.em_cate_duck, faceNameList);
//        faceData.put(R.drawable.em_cate_rib, faceNameList);
        faceData.put(R.drawable.em_cate_duck, faceNameList);
        box.setFaceData(faceData);

        List<Option> functionData = new ArrayList<>();
        Option takePhotoOption = new Option(this, "Take", R.drawable.take_photo);
        Option galleryOption = new Option(this, "Gallery", R.drawable.gallery);
        functionData.add(galleryOption);
        functionData.add(takePhotoOption);
        box.setFunctionData(functionData);
    }

    private void createReplayMsg(Message message) {

        final Message reMessage = new Message(message.getType(), 1, "Tom", "avatar", "Jerry", "avatar", message.getType() == 0 ? "Re:" + message.getContent() : message.getContent(), false, true, new Date());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * (new Random().nextInt(3) + 1));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.getData().add(reMessage);
                            listView.setSelection(listView.getBottom());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText("聊天");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            default:
                break;
        }
    }
}
