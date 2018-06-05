package com.abt.clock_memo.util;

import android.widget.Toast;

import com.abt.clock_memo.ClockMemoApp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;

/**
 * @描述： @WeChatUtil
 * @作者： @黄卫旗
 * @创建时间： @05/06/2018
 */
public class WeChatUtil {

    public static final String APP_ID = "wx888888";
    public static final String APP_SECRET = "wx_app_secret";
    private static IWXAPI api;

    public static IWXAPI getApi() {
        return api;
    }

    /**
     * 注册应用到微信
     */
    public static void reqToWx() {
        api = WXAPIFactory.createWXAPI(ClockMemoApp.getAppContext(), APP_ID, true);
        api.registerApp(APP_ID);
    }

    /**
     * 分享到微信
     */
    public static void shareToWx() {
        //构建文本信息的分享对象（其它的有WXVideoObject,WXImageObject等），内容为hello
        WXTextObject textObject = new WXTextObject();
        textObject.text = "hello";

        //将textObject分装到WXMediaMessage里
        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = textObject;
        mediaMessage.description = "hello";

        //构建发送请求
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //设置发送场景为分享给微信好友
        req.scene = SendMessageToWX.Req.WXSceneSession;
        //设置该事务为唯一事务（因为时间只有一个）
        req.transaction = String.valueOf(System.currentTimeMillis());
        //将封装好的WXMediaMessage再封装给SendMessageToWX.Req
        req.message = mediaMessage;

        //通过IWXAPI发送请求
        api.sendReq(req);
    }

    /**
     * 登陆到微信
     */
    public static void loginToWx() {
        if (!api.isWXAppInstalled()) {
            Toast.makeText(ClockMemoApp.getAppContext(),
                    "请先安装微信", Toast.LENGTH_SHORT).show();
        } else {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "Moke";
            api.sendReq(req);
        }
    }

}
