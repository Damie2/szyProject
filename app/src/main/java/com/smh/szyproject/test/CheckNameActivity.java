package com.smh.szyproject.test;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.utilCode.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/4/20 14:17
 * desc   :
 */
public class CheckNameActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;


    String ua = "http://182.254.52.193/om.tc.qq.com/Ai7_U-9vw3ZWcuZRBH_TuEjMyPAf3aIUv7w7USKsmbQU/uwMROfz2r5zBIaQXGdGnC2dfhzknrthYD-Z_acttsiZdV6SQ/e0707xlz8ex.m701.mp4?vkey=400322C1C5C11FC829354B593A07108A4FA2947F51CC28AA7F7F4A413819F2398FCF8F341A8E9389031074922872B9D4668EBF8EAE5A52605A13F86736639E4D9820B62CD683607906487752DBD9870B4A49DE9A72ED2336019D0D76B0946C02FA94011AEAFC86E60D215315D74355EDD9BE691A05EFC007F0EEDDF3875F5FBD&br=29&platform=2&fmt=auto&level=0&sdtfrom=v3010&guid=3e4a23811561b283398fa4a798b486d9";

    @Override
    public int getLayoutId() {
        return R.layout.activity_name;
    }

    @Override
    public void init(Bundle savedInstanceState) {


//        if (ua.startsWith("http://182.254.52.193")) {
//            ua.replace("http://182.254.52.193", " http://10.2.9.130/182.254.52.193");
//        }
        getDate();
    }

    private void getDate() {
        tv_name.post(new Runnable() {
            @Override
            public void run() {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence text = clipData.getItemAt(0).getText();
                    parseDate(text.toString());
                }
            }
        });
    }


    private void parseDate(String msg) {
        String[] data = msg.split("：");
        if (data == null) {
            return;
        }
        msg = data[1].replace("体温正常", "").replace("北京", "").replace("河南", "").replace(" ", "");
        String description = Pattern.compile("[\\d]").matcher(msg).replaceAll("");
        String[] newData = description.split("\\.");
        if (newData == null) {
            return;
        }
        if (newData.length < 21) {
            getLake(newData);
        } else {
            checkRepeat(newData);
        }
    }

    private void getLake(String[] newData) {
        Set<String> set = new HashSet<>();
        set.add("江煜霖");
        set.add("江婧宇");
        set.add("康佳浩");
        set.add("蔡佳俊");
        set.add("郭珈菲");
        set.add("曹昊霖");
        set.add("王小艺");
        set.add("管正成");
        set.add("郑皓月");
        set.add("李妙伊");
        set.add("杨子玉");
        set.add("于宗圣");
        set.add("段思铭");
        set.add("刘阔");
        set.add("王译可");
        set.add("黄冰欣");
        set.add("王铭辰");
        set.add("杜英姿");
        set.add("陈奕如");
        set.add("张老师");

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < newData.length; i++) {
            if (!TextUtils.isEmpty(newData[i])) {
                L.e("list放入:" + newData[i]);
                map.put(newData[i], 1);
            }
        }

        for (String info : set) {
                if(map.get(info)==null){
                    tv_name.setText(info);
                    break;
                }
        }
    }

    private void checkRepeat(String[] newData) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < newData.length; i++) {
            list.add(newData[i]);
            set.add(newData[i]);
        }
        Collection collection = CollectionUtils.disjunction(list, set);
        if (!collection.isEmpty()) {
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                tv_name.setText("重复名是：" + object);
            }
        } else {
            tv_name.setText("没有找到重复值");
        }
    }
}
