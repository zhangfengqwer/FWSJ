package com.javgame.fksj;

import android.app.Activity;

import com.javgame.login.UserSdk;
import com.javgame.pay.IPay;
import com.javgame.pay.PayHelper;
import com.javgame.utility.LogUtil;
import com.javgame.utility.SignUtil;

import org.json.JSONException;
import org.json.JSONObject;

import static com.javgame.utility.Constants.TAG;

/**
 * @author zhangf
 * @date 2017/10/25
 */
public class Pay implements IPay {
    public static final String ORDER_URL_ALIPAY = "http://mpay.51v.cn/pay/trade_alipayv3.aspx";

    @Override
    public String getOrderKey() {
        return null;
    }

    private Activity getActivity(){
        return UserSdk.getInstance().getActivity();
    }

    @Override
    public void pay(String data) {
        LogUtil.d(TAG, data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            String uid = jsonObject.getString("uid");
            String itemid = jsonObject.getString("itemid");
            String chargecount = jsonObject.getString("chargecount");
            String amount = jsonObject.getString("amount");
            String expand = PayHelper.getOrderExpand(getActivity());
            String sign = "uid=" + uid + "&itemid=" + itemid + "&chargecount=" + chargecount + "&amount=" + amount + "&key=" + "alipay_javgame_@#$";
            String md5 = SignUtil.md5(sign);

            LogUtil.d(TAG, md5);
//
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .get()
//                    .url(ORDER_URL_ALIPAY)
//                    .header("uid", uid)
//                    .header("itemid", itemid)
//                    .header("chargecount", chargecount)
//                    .header("amount", amount)
//                    .header("gameid", 210+"")
//                    .header("sign", md5)
//                    .header("expand", expand)
//                    .build();
//
//            LogUtil.d(TAG, request.url().toString());
//            Call call = client.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    LogUtil.d(TAG, call.toString());
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    LogUtil.d(TAG, response.body().string());
//                }
//            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

