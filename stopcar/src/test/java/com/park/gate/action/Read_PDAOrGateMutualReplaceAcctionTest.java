package com.park.gate.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import stopcar.test.action.BaseActionTest;

/**
 * PDA或者道闸出库时，相互代替，获取最新的一条未支付的订单
 * Created by zzy on 2017/6/19.
 */
public class Read_PDAOrGateMutualReplaceAcctionTest extends BaseActionTest {

    /**
     * 响应内容：
     * {"data":{"address_name":"天府立交(入口)","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510107","arrive_time":1497505224000,"cancel_state":0,"car_code":"沪F00001","charging":1,"charging_time":60,"ctime":1497505224000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":0,"gov_num":"000000000000001","id":60395,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":0,"lat":30.598148,"leave_time":1497505224000,"lng":104.076324,"magnetic_state":1,"money":0,"my_order":"2017061513402436687","note":"","open_time":1497505224000,"order_type":0,"other_order":"","park_type":0,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":36,"pi_name":"天府三街停车场","pp_state":0,"pp_versioncode":"","pu_id":0,"pu_nd":"","scan_time":1497505224000,"start_price":2,"start_time":120,"stime":1497505224000,"ui_id":0,"ui_nd":"","ui_tel":"","up_orderid":"","upc_id":0,"utime":1497505224000},"errorcode":"","errormsg":"操作成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void PDAOrGateMutualReplaceTest() throws Exception{
        String url = BaseUrl + "getLatestUnPayOrder.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        Long pi_id = 36L;               //停车场ID
        String area_code = "510107";           //地址编号
        String car_code = "沪F00001";            //车牌号

        sign = getSignature(Constants.SYSTEM_KEY, dtype, pi_id, area_code);

        post.addParameter("pi_id", pi_id + "");
        post.addParameter("area_code", area_code);
        post.addParameter("car_code", car_code);

        post.addParameter("sign", sign);

        int xx = HC.executeMethod(post);
        System.out.println("*请求状态code=" + xx);
        try {
            String result = post.getResponseBodyAsString();
            if (result == null) {
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
