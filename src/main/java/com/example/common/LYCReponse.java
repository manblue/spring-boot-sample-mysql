package com.example.common;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 统一响应
 *
 * @ClassName: LYSReponse.java
 * @Description: 统一响应
 *
 * @author tangzhi
 * @version V1.0
 * @Date 2015-11-30 下午7:05:48
 */
public class LYCReponse {
	
	
	private static String LIST_KEY = "list";
	
	private static String TOTAL_KEY = "total";
	private static String ip;

	private  String apiClient;
	
    /**
     * 错误代码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private Map<String, Object> data;

    public LYCReponse(int code, String msg, Map<String, Object> data) {
        super();
        this.apiClient = ip +":"+ServiceInfoUtil.getPort();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getApiClient() {
		return apiClient;
	}

	/**
     * 添加失败响应
     * @Description: 添加无数据响应
     * @param code		响应代码
     * @param message	响应结果
     * @return   
     * @return LYCReponse  
     * @throws
     * @author tangzhi
     * @date 2016-5-6
     */
    public static LYCReponse addFailedResponse(int code, String message) {
        return new LYCReponse(code, message, null);
    }

    /**
     * 添加成功响应
     * @Description: 添加成功响应
     * @param code		响应代码
     * @param message	响应结果
     * @return   
     * @return LYCReponse  
     * @throws
     * @author tangzhi
     * @date 2016-5-6
     */
    public static LYCReponse addSuccessResponse(String message,
                                                Map<String, Object> data) {
        return new LYCReponse(0, message, data);
    }

    /**
     * 添加列表响应
     * @Description: 添加列表响应
     * @param code		响应代码
     * @param message	响应结果
     * @return   
     * @return LYCReponse  
     * @throws
     * @author tangzhi
     * @date 2016-5-6
     */
    public static LYCReponse addListResponse(String message, int total,
                                             List<?> list) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(TOTAL_KEY, total);
        data.put(LIST_KEY, list);
        return new LYCReponse(0, message, data);
    }
    
    /**
     * 添加无分页列表响应
     * @Description: 添加无分页列表响应
     * @param code		响应代码
     * @param message	响应结果
     * @return   
     * @return LYCReponse  
     * @throws
     * @author tangzhi
     * @date 2016-5-6
     */
    public static LYCReponse addListResponse(String message, List<?> list) {
		Map<String, Object> data = new HashMap<String, Object>();
		int total = 0;
		if(null != list && list.size() != 0){
			total = list.size();
		}
		data.put(TOTAL_KEY, total);
		data.put(LIST_KEY, list);
		return new LYCReponse(0, message, data);
	}

    static {
        String regex = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";  
    	try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			Enumeration<NetworkInterface> e=NetworkInterface.getNetworkInterfaces();
	        while(e.hasMoreElements())
	        {
	        	NetworkInterface ni = e.nextElement();
	        	Enumeration<InetAddress> adds = ni.getInetAddresses();
	        	while (adds!=null&&adds.hasMoreElements()) {
					inetAddress = (InetAddress) adds.nextElement();
					if (!Pattern.matches(regex, inetAddress.getHostAddress())) {
						continue;
					}
					if (inetAddress.getHostAddress().equals("127.0.0.1")) {
						continue;
					}
					ip = inetAddress.getHostAddress();
					break;
	        	}
	        }
		} catch (UnknownHostException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
