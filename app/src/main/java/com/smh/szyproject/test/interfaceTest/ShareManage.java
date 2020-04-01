package com.smh.szyproject.test.interfaceTest;

import android.content.Context;

import com.smh.szyproject.bean.ShareBean;
import com.smh.szyproject.bean.ShareItem;
import com.smh.szyproject.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * author : smh
 * date   : 2020/3/24 13:51
 * desc   :
 */
public class ShareManage {

    private static String PAGENAME = "com.tencent.mobileqq";
    private static String APPID = "wxcb89b8fc34e4e304";

//    private static boolean isFirst = true;

    private static final HashMap<String, ShareBean> shareMap = new HashMap<>();

    public static HashMap<String, ShareBean> getShareMap() {
        return shareMap;
    }

    private static final List<ShareBean> listKey = new ArrayList<>();

    public static ShareItem getItem(Context context) {
        ShareItem item = null;
        Iterator<String> iterator = shareMap.keySet().iterator();
        listKey.clear();
        while (iterator.hasNext()) {
            String packageName = iterator.next();
            boolean b = AppUtils.checkApkExist(context, packageName);
            if (b) {
                listKey.add(shareMap.get(packageName));
            }
        }
        if (listKey.size() == 0) {
            return null;
        } else {
            int position = RandomByWeight();
//            L.e("位置是：" + position + "，并且一共有:" + listKey.size() + "个");

            PAGENAME = listKey.get(position).getPackage_name();
//            L.e("222:"+PAGENAME);
            APPID = listKey.get(position).getApp_id();
        }
        return item;
    }


    private static int RandomByWeight() {
        int sum = 0;
        for (int i = 0; i < listKey.size(); i++) {
            sum += listKey.get(i).getWeight();
        }
        int number_rand = ((new Random().nextInt(sum) + 1));
        int sum_temp = 0;
        for (int i = 0; i < listKey.size(); i++) {
            sum_temp += listKey.get(i).getWeight();
            if (number_rand <= sum_temp) {
                return i;
            }
        }
        return -1;
    }


}
