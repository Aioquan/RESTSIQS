package com.restsiqs.restsiqs.Utils;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * Created by devouty on 2015/10/23.
 */
public class HTTPJSONGetter {
    public static JSONObject get(String URL) {
        JSONObject jsonObject = null;
        try {
            HttpEntity entity;
            entity = null;
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet(URL);
//            Log.i("devouty", "test:" + request.getURI());
            HttpResponse response = httpclient.execute(request);
//                Log.i("devouty", "response:" + response.getStatusLine());
            entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            Log.i("devouty_connection", "connecting:" + response.getStatusLine() + "----------" + URL);
//            Log.i("devouty_connection", "test:" + result);
            jsonObject = (JSONObject) JSON.parse(result);
        }catch (IOException e)
        {
            Log.i("devouty_HTTPJSONGetter",e.getMessage());
        }
        return jsonObject;
    }
}
