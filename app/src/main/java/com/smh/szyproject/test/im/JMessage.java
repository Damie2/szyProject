package com.smh.szyproject.test.im;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SoftHideKeyBoardUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.ChatRoomManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

/**
 * author : smh
 * date   : 2020/6/16 17:59
 * desc   : http://docs.jiguang.cn/jmessage/client/android_sdk/basic/
 */
public class JMessage extends BaseActivity implements View.OnClickListener {

    private String userName;
    private String pwd;
    @BindView(R.id.et_msg)
    EditText editText;
    @BindView(R.id.rv)
    ChatListView rv_chat;

    long roomID = 24158561;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jmessage_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        SoftHideKeyBoardUtil.assistActivity(this);
        userName = "13681200783";
        pwd = "123456";
    }

    @OnClick({R.id.tv_regiter, R.id.tv_login, R.id.tv_login_out, R.id.btn_send})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_regiter:
                L.e("注册");
                regiter();
                break;
            case R.id.tv_login:
                L.e("登录");
                login();
                break;
            case R.id.tv_login_out:
                L.e("登出");
                loginOut();
                break;
            case R.id.btn_send:
                send();
                break;
            default:
                break;
        }
    }

    //发送消息
    private void send() {
        L.e("发送");
        String text = editText.getText().toString().trim();
        if (!TextUtils.isEmpty(text)) {
            Conversation conv = JMessageClient.getChatRoomConversation(roomID);
            if (null == conv) {
                conv = Conversation.createChatRoomConversation(roomID);
            }
            final Message msg = conv.createSendTextMessage(text);//实际聊天室可以支持所有类型的消息发送，demo为了简便，仅仅实现了文本类型的消息发送，其他的消息类型参考消息发送相关文档
            msg.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    if (0 == responseCode) {
                        L.e("发送成功:"+ responseCode+","+ responseMessage+","+msg);
                    } else {
                        L.e("消息发送失败:"+ responseCode+","+ responseMessage+","+msg);
                    }
                    editText.setText("");
                }
            });
            JMessageClient.sendMessage(msg);
        }
    }

    private void regiter() {
        JMessageClient.register(userName, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                L.e("注册结果：" + i + "," + s);
            }
        });
    }

    private void login() {
        JMessageClient.login(userName, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                L.e("登录结果：" + i + "," + s);
                ChatRoomManager.enterChatRoom(roomID, new RequestCallback<Conversation>() {
                    @Override
                    public void gotResult(int i, String s, Conversation conversation) {
                        L.e("title:" + conversation.getTitle());
                        L.e("id:" + conversation.getId());
                    }
                });
            }
        });
    }

    //登出
    private void loginOut() {
        //离开聊天室
        ChatRoomManager.leaveChatRoom(roomID, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                //登出
                JMessageClient.logout();
            }
        });
    }
}
