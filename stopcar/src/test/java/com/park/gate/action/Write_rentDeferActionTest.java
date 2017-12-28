package com.park.gate.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import stopcar.test.action.BaseActionTest;

/**
 * Created by zzy on 2017/7/3.
 */
public class Write_rentDeferActionTest extends BaseActionTest {

    /**
     * 新增数据
     * @throws Exception
     *
     * {"data":{"area_code":"510107","car_code":"川A11111","ctime":1496994372529,"defer_state":2,"endtime":1496994372929,"father_order_id":"20170703998181293831","flag":1,"is_del":0,"is_expire":0,"money":100,"month_num":10,"mq_state":0,"note":"","pay_source":0,"pay_state":1,"permit_time":"8：00-23：00","pi_id":36,"pi_name":"天府三街停车场","pu_id":0,"pu_nd":"","rd_id":7,"rent_order_id":"20170703998181293834","rent_type":1,"son_order_id":"","starttime":1496994372529,"stime":1499053410092,"ui_id":51,"ui_nd":"2016121910525321","unit_price":10,"up_orderid":"","utime":1496994372529},"errorcode":"","errormsg":"道闸租赁续约新增成功","errorno":"0"}
     */
    @Test
    public void rentDeferAdd() throws Exception {
        String url = BaseUrl + "rentdeferadd.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        Long pi_id = 36L;   //停车场主键id
        String area_code = "510107";    //区域代码
        Integer money = 100;   //租赁续期金额（单位 分）
        Integer unit_price = 10;   //租赁每个月单价（单位分）
        Integer month_num = 10;   //续约的月份数
        Long starttime = 1496994372529L;   //开始时间
        Long endtime = 1496994372929L;   //结束时间
        String car_code = "川A12348";    //车牌号
        String permit_time = "8：00-23：00";    //允许时间段（8：00-23：00）
        Integer pay_source = 0;   //支付类型 0：现金 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付  6:ETC快捷支付
        Integer rent_type = 1;    //租赁类型（0：普通时间段  1：早半天  2：晚半天  3：全天）
        Long ctime = 1496994372529L;   //客户端创建时间
        String client_order_id = "201707131357000001";   //客户端租赁订单号
        String client_rule_id = "201707131357100000";   //客户端规则id
        sign = getSignature(Constants.SYSTEM_KEY, dtype, client_order_id, pi_id, area_code);

        post.addParameter("pi_id", pi_id + "");
        post.addParameter("area_code", area_code);
        post.addParameter("money", money + "");
        post.addParameter("unit_price", unit_price + "");
        post.addParameter("month_num", month_num + "");
        post.addParameter("starttime", starttime + "");
        post.addParameter("money", money + "");
        post.addParameter("endtime", endtime + "");
        post.addParameter("car_code", car_code + "");
        post.addParameter("permit_time", permit_time);
        post.addParameter("pay_source", pay_source + "");
        post.addParameter("rent_type", rent_type + "");
        post.addParameter("ctime", ctime + "");
        post.addParameter("client_order_id", client_order_id);
        post.addParameter("client_rule_id", client_rule_id);
        post.addParameter("sign", sign);

        int xx = HC.executeMethod(post);
        System.out.println("*请求状态code=" + xx);
        try {
            String ds = post.getResponseBodyAsString();
            if (ds == null) {
                System.out.println("返回为空");
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




    /**
     * 修改到期状态
     * @throws Exception
     *
     * 响应数据：
     * {"data":"","errorcode":"","errormsg":"操作成功","errorno":"0"}
     */
    @Test
    public void rentDeferUpdateStatue() throws Exception {

        String url = BaseUrl + "updateisexpire.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        Long pi_id = 36L;//停车场主键id
        String area_code = "510107";//区域代码
        String car_code = "川A12348";   //车牌号
        int expire_state = 1;   //是否已经到期（0：未到期 1：已经到期）

        post.addParameter("area_code", area_code);
        post.addParameter("pi_id", pi_id+"");
        post.addParameter("car_code", car_code);
        post.addParameter("expire_state", expire_state+"");

        sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,area_code);

        post.addParameter("sign", sign);
        int xx = HC.executeMethod(post);
        System.out.println("*请求状态code=" + xx);
        try {
            String ds = post.getResponseBodyAsString();
            if (null == ds) {
                System.out.println("返回数据为空");
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

    /**
     * 租赁续约确认
     * 响应数据：
     * {"data":"","errorcode":"","errormsg":"道闸租赁续租确认成功","errorno":"0"}
     */
    @Test
    public void rentDeferSure() throws Exception{
        String url = BaseUrl + "rentdefersure.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        Long pi_id = 36L; //停车场主键id
        String area_code = "510107";  //区域代码
        String car_code = "川A12348";    //车牌号
        String rent_order_id = "510107200000036420170717132606OY";   //租赁订单号
        String client_order_id = "201707131357000001";   //客户端的租赁订单号
        Long endtime=1496994372929L;//租赁的到期时间

        post.addParameter("area_code", area_code);
        post.addParameter("pi_id", pi_id+"");
        post.addParameter("car_code", car_code);
        post.addParameter("rent_order_id", rent_order_id);
        post.addParameter("client_order_id", client_order_id+"");
        post.addParameter("endtime", endtime+"");

        sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,area_code);
        post.addParameter("sign",sign);

        int xx = HC.executeMethod(post);
        System.out.println("*请求状态code=" + xx);
        try {
            String ds = post.getResponseBodyAsString();
            if (null == ds) {
                System.out.println("返回数据为空");
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
