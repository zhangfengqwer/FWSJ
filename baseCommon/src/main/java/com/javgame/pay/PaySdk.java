package com.javgame.pay;

/**
 * @author zhangf
 * @date 2017/10/25
 */

public class PaySdk {
    private static PaySdk paySdk;

    public static PaySdk getInstance(){
        if(paySdk == null){
            paySdk = new PaySdk();
        }
        return paySdk;
    }


    public void pay(String paytype, String data, String callObj, String callFunc) {

    }
}
