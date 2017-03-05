package com.cndll.shequ.integrate.webview;

import android.util.Log;

import com.cndll.shequ.RXbus.RxBus;
import com.hyphenate.easeui.model.UserLodingInFo;
import com.cndll.shequ.eventtype.JSEvent;
import com.cndll.shequ.eventtype.LoginIM;
import com.cndll.shequ.util.ObjectSaveUtils;
import com.hyphenate.chat.EMClient;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

/**
 * 通过代码注册5+扩展插件示例类
 **/
public class WebViewMode_FeatureImpl implements IFeature {

    @Override
    public String execute(final IWebview pWebViewImpl, String pActionName,
                          String[] pJsArgs)
    {
        switch (pActionName) {
            case "exit":
                if (EMClient.getInstance().isLoggedInBefore())
                    EMClient.getInstance().logout(true);
                break;
            case "login":
                UserLodingInFo.getInstance().setIcon(pJsArgs[3]).setId(pJsArgs[0]).setNick(pJsArgs[2]).setMobile(pJsArgs[1]);
                ObjectSaveUtils.saveObject(pWebViewImpl.getContext(), "USERINFO", UserLodingInFo.getInstance());
                RxBus.getDefault().post(new LoginIM().setIcon(pJsArgs[3]).setId(pJsArgs[0]).setNick(pJsArgs[2]).setMobile(pJsArgs[1]));
                break;
            case "hideNativeView":
                RxBus.getDefault().post(new JSEvent().setEventType("hideNativeView"));
                Log.d("MYWAY", "hideNativeView: ");
                break;
            case "showNativeView":
                RxBus.getDefault().post(new JSEvent().setEventType("showNativeView"));

                Log.d("MYWAY", "showativeiew: ");
                break;
        }

        return null;
    }

    @Override
    public void init(AbsMgr pFeatureMgr, String pFeatureName) {
        //初始化Feature
    }

    @Override
    public void dispose(String pAppid) {

    }

}