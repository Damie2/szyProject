package com.smh.szyproject.test.recycleView;

import android.content.Context;

import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.other.utils.L;

import java.util.List;

/**
 * author : smh
 * date   : 2020/4/27 11:14
 * desc   :
 */
public class headFootAdapter extends CommonAdapter<HeadAndFootBean> {

    List<HeadAndFootBean> datas;

    public headFootAdapter(List<HeadAndFootBean> datas, Context context) {
        super(datas, context);
        this.context = context;
        this.datas = datas;
    }

    /**
     * 第一步，从服务器拿过来的数据来判断类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getType() == 0) {//如果从前面拿到的type是0
            return 0;
        } else if (getItem(position).getType() == 1) {//如果从前面拿到的type是1
            return 1;
        } else if (getItem(position).getType() == 2) {//如果从前面拿到的type是2
            return 2;
        }
        return -1;
    }


    /**
     * 第二步，根据第一步返回的类型使用不同的布局xml
     */
    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:
                return R.layout.test_activity_header;
            case 1:
                return R.layout.test_activity_footer;
            case 2:
                return R.layout.test_zm_contact_item;
        }
        return R.layout.test_zm_contact_item;
    }


    /**
     * 第三步，根据第一步返回的类型来用不同的控件
     */
    @Override
    protected void showItemContent(ViewHolder holder, int position, HeadAndFootBean zmContact) {
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                TextView tvName1 = holder.findViewById(R.id.header);
                tvName1.setText("我是头");
                break;
            case 1:
                TextView footer = holder.findViewById(R.id.footer);
                footer.setText("我是尾巴");
                break;
            case 2:
                TextView tvName = holder.findViewById(R.id.tv_name);
                TextView tvPhone = holder.findViewById(R.id.tv_phone);
                TextView tvAddress = holder.findViewById(R.id.tv_address);
                tvName.setText(zmContact.getName());
                tvPhone.setText(zmContact.getPhone());
                tvAddress.setText(zmContact.getAddress());
                break;
        }
    }
}
