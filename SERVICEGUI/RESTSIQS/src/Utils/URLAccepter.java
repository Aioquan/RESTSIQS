package Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by devouty on 2015/11/3.
 */
public class URLAccepter {
    public static String encrypt(String str) throws UnsupportedEncodingException
    {
//        str = URLEncoder.encode(str,"UTF-8");
        return URLEncoder.encode(str,"UTF-8");
    }
    public static String decrpt(String str) throws UnsupportedEncodingException
    {
//        str = URLDecoder.decode(str,"UTF-8");
        return URLDecoder.decode(str,"UTF-8");
    }
}
