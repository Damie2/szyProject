package com.smh.szyproject.test.fragment.RYfriendAdd;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.FragmentHelp;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/8 11:27
 * desc   :
 */
public class FriendAddActivity extends BaseActivity implements View.OnClickListener {
    private FragmentHelp help;
    private ApprenticeFragment apprenticeFragment;  //邀请好友
    private InviteFragment inviteFragment;          //我的徒弟

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_invite_apprentices;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        help = new FragmentHelp(getSupportFragmentManager());
        apprenticeFragment = new ApprenticeFragment();
        inviteFragment = new InviteFragment();
        help.add(apprenticeFragment, R.id.rl_rv, "apprentice");
        help.setCurrentFragment("apprentice", apprenticeFragment);
    }

    @OnClick({R.id.tv_invite_friend, R.id.tv_my_apprentice})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_invite_friend:
                help.add(apprenticeFragment, R.id.rl_rv, "apprentice");
                break;
            case R.id.tv_my_apprentice:
                help.add(inviteFragment, R.id.rl_rv, "invite");
                break;
            default:
                break;
        }
    }
}
