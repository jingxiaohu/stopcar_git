package com.park.gate.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import stopcar.test.action.BaseActionTest;

/**
 * 停车场包月车和免费车记录
 * Created by zzy on 2017/6/16.
 */
public class Read_ParkMonthFreeActionTest extends BaseActionTest{

    /**
     * 查询信息
     * 响应信息：
     {"data":{"area_code":"5100000","car_code":"川A2359","car_code_color":1,"car_color":1,"car_name":"李四","car_tel":"15611111111","car_type":1,"client_id":"1","ctime":1491549557891,"end_time_str":"2017-07-16 10:00:00","id":0,"local_loginname":"lisi","money":200,"note":"","pi_id":1,"pu_id":1,"start_time_str":"2017-06-16 10:00:00","state":0,"stime":1497606089665,"type":1,"utime":1497606089291},"errorcode":"","errormsg":"插入数据成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void readInfo() throws Exception{
        String url = BaseUrl + "query_parkmonthfree.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        Long pi_id = 1L;               //停车场ID
        String area_code = "5100000";           //地址编号
        String car_code = "川A2359";            //车牌号

        sign = getSignature(Constants.SYSTEM_KEY, dtype, pi_id, area_code);

        post.addParameter("pi_id", pi_id + "");
        post.addParameter("area_code", area_code);
        post.addParameter("car_code", car_code);

        post.addParameter("sign", sign);

        int xx = HC.executeMethod(post);
        System.out.println("*请求状态code=" + xx);
        try {
            String ds = post.getResponseBodyAsString();
            if (ds == null) {
                System.out.println("无数据返回");
            } else {
                System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("read_myinfo is error" + e.getMessage(), e);
        } finally {
            if (post != null) {
                post.releaseConnection();
                //释放链接
                HC.getHttpConnectionManager().closeIdleConnections(0);
            }
        }
    }

}
