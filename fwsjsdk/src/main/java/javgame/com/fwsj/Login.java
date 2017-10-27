package javgame.com.fwsj;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.javgame.Integration.IActivityListener;
import com.javgame.login.IUser;
import com.javgame.login.UserSdk;
import com.javgame.utility.CommonUtils;
import com.javgame.utility.LogUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.javgame.utility.Constants.TAG;

/**
 * @author zhangf
 * @date 2017/10/25
 */

public class Login implements IUser, IActivityListener {
    private String AppId = "101436232";
    private UserInfo mInfo;
    private Tencent mTencent;
    private String token;
    private String expires;
    private String openId;
    private String nickname;
    private String figureurl;

    private Activity getActivity() {
        return UserSdk.getInstance().getActivity();
    }

    @Override
    public void login(String data) {

        if ("weixin".equals(data)) {
            LogUtil.d(TAG, "点击wexin");
        } else if ("qq".equals(data)) {
            LogUtil.d(TAG, "点击qq");
            doQQLogin();
        }
    }

    private void doQQLogin() {
        mTencent = Tencent.createInstance(AppId, getActivity());
        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {
            mTencent.setAccessToken(token, expires);
            mTencent.setOpenId(openId);
            LogUtil.d(TAG, "设置openid");
        }
        mTencent.login(getActivity(), "all", loginListener);
    }

    class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                CommonUtils.showToast(getActivity(), "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                CommonUtils.showToast(getActivity(), "登录失败");
                return;
            }
            CommonUtils.showToast(getActivity(), "登录成功");
            doComplete(jsonResponse);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            LogUtil.e(TAG, "error:" + e.errorDetail);
        }

        @Override
        public void onCancel() {
            CommonUtils.showToast(getActivity(), "取消登录");
        }
    }

    public IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d(TAG, "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            Log.d(TAG, values.toString());
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };

    private void handlerResult() {
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", AppId);
        map.put("openid", openId);
        map.put("nickname", nickname);
        map.put("platform", 101 + "");
        map.put("figureurl", figureurl);
        UserSdk.getInstance().loginResult(new JSONObject(map).toString());
    }

    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            token = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
            expires = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
            openId = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    private void updateUserInfo() {
        mInfo = new UserInfo(getActivity(), mTencent.getQQToken());
        mInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject jsonObject = (JSONObject) o;
                try {
                    nickname = jsonObject.getString("nickname");
                    figureurl = jsonObject.getString("figureurl_qq_2");
                    handlerResult();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, o.toString());
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }
    }


    @Override
    public void logout() {

    }

    @Override
    public void setLoginResponse(String data) {

    }
}
