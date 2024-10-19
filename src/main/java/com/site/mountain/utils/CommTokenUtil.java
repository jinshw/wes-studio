package com.site.mountain.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


/**
 * 生成token util
 *
 * @author maql
 */
public abstract class CommTokenUtil {
    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        return request;
    }

    /**
     * 转换特殊字符
     *
     * @param filedValue 字段值
     * @return
     */
    public static String sqlEncode(String filedValue, boolean likeQuery) {
        String tempValue = filedValue;
        tempValue = tempValue.replace("'", "''");
        tempValue = tempValue.replace("{", "{}");
        if (likeQuery) {
            /* tempValue = tempValue.replace("[","[[]%"); */
            tempValue = tempValue.replace("\\", "\\\\");
            tempValue = tempValue.replace("_", "\\_");
            tempValue = tempValue.replace("＿", "'\\_'");
            tempValue = tempValue.replace("%", "\\%");
            tempValue = tempValue.replace("{", " ");
        }

        return tempValue;
    }

    /**
     * 小数转换成百分数
     * @param inNumber
     * @param fractionDigits
     * @return
     */
	/*public static String percentTrans(double inNumber, int fractionDigits) {
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(fractionDigits);
		return nt.format(inNumber);
	}*/

    /**
     * 生成14位字符token
     *
     * @return
     */
    public static String createToken() {
        UUID.randomUUID();
        String str = UUID.randomUUID().toString().replace("-", "").substring(0, 14);
        return str;
    }

    /**
     * 生成32位字符token
     *
     * @return
     */
    public static String createMaxToken() {
        UUID.randomUUID();
        String str = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        return str;
    }
}
