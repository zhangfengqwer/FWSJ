package com.javgame.pay;

import android.app.Activity;

import com.javgame.Integration.ComponentFactory;
import com.javgame.Integration.IActivityListener;
import com.javgame.utility.LogUtil;

import static com.javgame.utility.Constants.TAG;

/**
 * @author zhangf
 * @date 2017/10/25
 */

public class PaySdk {

    private static PaySdk paySdk;
    private Activity mContext;

    /**
     * unity回调对象
     */
    public String objCallBack;
    /**
     * unity回调方法
     */
    public String funCallBack;

    private IPay mPay;

    public static PaySdk getInstance(){
        if(paySdk == null){
            paySdk = new PaySdk();
        }
        return paySdk;
    }

    public void Init(Activity act){
        mContext = act;
        Object obj = getPayObject();
        if(obj != null){
            if (obj instanceof IPay) {
                mPay = (IPay) obj;
                LogUtil.i(TAG, "IPay create");
            }

//            if(obj instanceof IThirdMethod){
//                thirdListener = (IThirdMethod) obj;
//                LogUtil.i(TAG, "IThirdMethod create");
//            }
        }
    }

    public Object getPayObject(){
        return ComponentFactory.getInstance().getPay();
    }
    public void pay(String data, String callObj, String callFunc) {
        if (mPay != null) {
            objCallBack = callObj;
            funCallBack = callFunc;
            mPay.pay(data);
        }
    }
}
