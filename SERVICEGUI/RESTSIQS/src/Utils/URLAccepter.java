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
        str = URLEncoder.encode(str,"GBK");
        return str;
    }
    public static String decrpt(String str) throws UnsupportedEncodingException
    {
        str = URLDecoder.decode(str,"GBK");
        return str;
    }
}
