package Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.ConnectException;

/**
 * Created by devouty on 2015/10/28.
 */
public class HTTPJSONHelper {
    public static JSONObject get(String URL) throws ConnectException {
        JSONObject jsonObject = null;
        try {
            HttpEntity entity;
            entity = null;
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet(URL);
            HttpResponse response = httpclient.execute(request);
            entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            System.out.println("connecting:" + response.getStatusLine() + "----------" + URL);
            jsonObject = (com.alibaba.fastjson.JSONObject) JSON.parse(result);
        } catch (ConnectException ce) {
            throw ce;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
