package com.javgame.utility;

import android.app.Activity;
import android.util.Log;

import com.javgame.Integration.IntegrationManager;
import com.javgame.alipay.AliPay;
import com.javgame.app.UnityPlayerActivity;

import static com.javgame.utility.Constants.TAG;

/**
 * @author zhangf
 * @date 2017/10/25
 */

public class UnityHelper {

    private static Activity getActivity() {
        return UnityPlayerActivity.realActivity;
    }

    /**
     * 登录
     * @param callObj unity3d中接收消息的gamaObject的名称
     * @param callFunc unity3d中接收消息的函数名称
     * @param data 数据格式:json
     */
    public static void login(final String callObj, final String callFunc, final String data) {
        Log.d(TAG, " login  callObj: " + callObj + " callFunc :" + callFunc);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                IntegrationManager.getInstance().login(getActivity(), callObj, callFunc, data);
            }
        });
    }

    /**
     * 支付方案统一入口
     *
     * @param paytype  支付类型
     * @param data
     * @param callObj
     * @param callFunc
     */
    public static void pay(final String callObj, final String callFunc, final String data) {
        LogUtil.d(TAG, " Pay " + data + " callObj " + callObj + " callFunc " + callFunc);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                IntegrationManager.getInstance().pay(data, callObj, callFunc);
            }
        });
    }

    /**
     * 微信分享
     */
    public static void wxShare(final String callObj, final String callFunc, final String data) {
        LogUtil.d(TAG, " wxShare: " + data + " callObj :" + callObj + " callFunc :" + callFunc);

    }
}
