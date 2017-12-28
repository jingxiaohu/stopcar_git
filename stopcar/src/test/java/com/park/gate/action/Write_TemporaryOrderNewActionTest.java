package com.park.gate.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import stopcar.test.action.BaseActionTest;

/**
 * Created by zzy on 2017/6/12.
 */
public class Write_TemporaryOrderNewActionTest extends BaseActionTest {

    /**
     * 租赁订单转为临停订单
     * 	"{"data":{"address_name":"天府立交(入口)","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510107","arrive_time":1496994372529,"cancel_state":0,"car_code":"川A11111","charging":1,"charging_time":60,"ctime":1496994372529,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":2,"free_minute":0,"gov_num":"","id":0,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":0,"lat":30.598148,"leave_time":1497239623880,"lng":104.076324,"magnetic_state":0,"money":200,"my_order":"5101071000000360201706121153437s","note":"","open_time":1497239623880,"order_type":2,"other_order":"","park_type":0,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":36,"pi_name":"天府三街停车场","pp_state":0,"pp_versioncode":"","pu_id":0,"pu_nd":"","scan_time":1496994372529,"start_price":2,"start_time":120,"stime":1497239623880,"ui_id":51,"ui_nd":"2016121910525321","ui_tel":"18780169455","upc_id":0,"utime":1496994372529},"errorcode":"","errormsg":"生成订单成功","errorno":"0"}",
     * @throws Exception
     */
    @Test
    public void temporaryOrder() throws Exception{
        {
            String url = BaseUrl+"temporaryorder.php";
            PostMethod post  = new UTF8PostMethod(url);
            //设置公共参数
            setPublicParam(post);

            long pi_id = 36;//场地主键ID
            String car_code = "川A11111";//车牌号
            String area_code = "510107";//省市区区域代码
            Integer final_time = 2;//结算时计费时长（单位分钟）-- 当前时间-创建时间=起步时长+计费时长
            Integer car_type = 1;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
            Integer car_code_color = 1;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
            Integer money = 200;//缴费金额
            long create_time = 1496994372529L;//创建时间   13位毫秒数
            String order_type = "2";// 下单类型 0: 普通下单  1：预约下单 2：租赁临停订单   3:免费转临停   4：包月转临停
            String order_id = "945940340394939339";
            sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,final_time,money,order_id);

            post.addParameter("pi_id", pi_id+"");
            post.addParameter("car_code", car_code);
            post.addParameter("area_code", area_code);
            post.addParameter("final_time", final_time+"");
            post.addParameter("car_type", car_type+"");
            post.addParameter("car_code_color", car_code_color+"");
            post.addParameter("money", money+"");

            post.addParameter("create_time", create_time+"");
            post.addParameter("order_type", order_type+"");
            post.addParameter("order_id", order_id);

            post.addParameter("sign", sign);
            int xx = HC.executeMethod(post);
            System.out.println("*请求状态code="+xx);
            try {
                String ds = post.getResponseBodyAsString();
                if(ds == null){
                    System.out.println("无数据返回");
                }else{
                    System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("read_myinfo is error"+e.getMessage(), e);
            }finally{
                if(post!=null){
                    post.releaseConnection();
                    //释放链接
                    HC.getHttpConnectionManager().closeIdleConnections(0);
                }
            }
        }
    }

}
