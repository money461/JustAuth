package me.zhyd.oauth.sdk;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.9.6
 */
public class ThirdPartSdkTest {

    @Test
    public void huawei() {
        String code = "CF1IvwdXw18r6LTfoRSgs+LrdP/DuO1VJJmAD0up2grQrSs3gcuyrt1O+jjWp7/TFiBy9IlPepNs/PUggcLe8cgjesqj1+DGXXojJsjEqsokFCCU0eJVt1F02zLDWH1+bq40HSlljXDaTvCBNrqWJJnIZhRetoV9pocrWPLZpYrx/h0iaC9T0GjMRVEXC//LnTAlTjg7";
        HttpResponse response = HttpRequest.post("https://oauth-login.cloud.huawei.com/oauth2/v2/token")
            .form("grant_type", "authorization_code")
            .form("code", code)
            .form("client_id", "100xxxxx")
            .form("client_secret", "22aea400bef603xxxxxbfb80d")
            .form("redirect_uri", "http://localhost:8443/huawei/login")
            .execute();
        System.out.println(response.body());

        // {"access_token":"accessToken","expires_in":3600,"refresh_token":"refreshToken","scope":"https:\/\/www.huawei.com\/auth\/account\/base.profile","token_type":"Bearer"}

        //
        HttpResponse response2 = HttpRequest.post("https://api.vmall.com/rest.php")
            .form("nsp_ts", System.currentTimeMillis())
            .form("access_token", JSONObject.parseObject(response.body()).getString("access_token"))
            .form("nsp_fmt", "JS")
//            .form("nsp_cb", "")
            .form("nsp_svc", "OpenUP.User.getInfo")
            .execute();
        System.out.println(response2.body());
        // 华为性别 0是男，女是1
        // {"gender":1,"headPictureURL":"https://upfile-drcn.platform.hicloud.com/FileServer/image/b.0260086000226601572.20190415065228.iBKdTsqaNkdPXSz4N7pIRWAgeu45ec3k.1000.9A5467309F9284B267ECA33B59D3D7DA4A71BC732D3BB24EC6B880A73DEE9BAB.jpg","languageCode":"zh-CN","userID":"260086000226601572","userName":"151****2326","userState":1,"userValidStatus":1}
    }
}