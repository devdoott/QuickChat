package com.buyhatke_intern.qauth;

import android.os.Build;

import org.telegram.api.TLAbsUpdates;
import org.telegram.api.TLConfig;
import org.telegram.api.auth.TLAuthorization;
import org.telegram.api.auth.TLCheckedPhone;
import org.telegram.api.auth.TLSentCode;
import org.telegram.api.engine.ApiCallback;
import org.telegram.api.engine.AppInfo;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.requests.TLRequestAuthCheckPhone;
import org.telegram.api.requests.TLRequestAuthSendCode;
import org.telegram.api.requests.TLRequestAuthSignUp;
import org.telegram.api.requests.TLRequestHelpGetConfig;

import java.io.IOException;

/**
 * Created by Devdoot on 26-03-2016.
 */
public class Teleapi {  private static TelegramApi api;
    private static Integer i;

private static MyApiStorage state;

   public static void     start(){
        state=new MyApiStorage();
 api       = new TelegramApi(state, new AppInfo(23564, Build.MODEL,Build.VERSION.RELEASE,BuildConfig.VERSION_NAME,"en"), new ApiCallback()
{
    // @Override
    public void onApiDies(TelegramApi api) {
        // When auth key or user authorization dies
    }

    @Override
    public void onAuthCancelled(TelegramApi telegramApi) {

    }

    @Override
    public void onUpdatesInvalidated(TelegramApi api) {
        // When api engine expects that update sequence might be broken
    }

    @Override
    public void onUpdate(TLAbsUpdates tlAbsUpdates) {

    }
});
        try {
            TLConfig config = api.doRpcCallNonAuth(new TLRequestHelpGetConfig());
            MyApiStorage.setConfig(config);
            state.updateSettings(config);

        }catch (IOException e){
            //System.out.println(e+"////////////////////////////////////////////////////////////////////////////////////////////////////");
            }

}
public static TLSentCode authsendcode(String mnumber){
try {


    TLSentCode sentCode = api.doRpcCallNonAuth(new TLRequestAuthSendCode(mnumber, 0, 23564, "72403e0500ec6bf9c2685f80ee670a4d", "en"));
    return sentCode;
}catch (IOException e){
    return null;
}}
    public static TLCheckedPhone authcheckphone(String mnumber){
        TLRequestAuthCheckPhone checkPhone = new TLRequestAuthCheckPhone(mnumber);
try{
        TLCheckedPhone checkedPhone = api.doRpcCallNonAuth(checkPhone);
  return checkedPhone;  }
catch (IOException e){return null;}}
    public static TLAuthorization signup(String mnumber,String mfname,String mlname,String phonehash,String mcode){
        TLRequestAuthCheckPhone checkPhone = new TLRequestAuthCheckPhone(mnumber);
        try{
            TLAuthorization signUp = api.doRpcCallNonAuth(new TLRequestAuthSignUp(mnumber, phonehash,mcode,mfname,mlname));
            return signUp;  }catch (IOException e){return null;}}

}
