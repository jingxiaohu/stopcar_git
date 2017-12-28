package com.park.gate.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import stopcar.test.action.BaseActionTest;

/**
 * Created by zzy on 2017/6/16.
 */
public class Write_ParkMonthFreeActionTest extends BaseActionTest {
    /**
     * 添加操作
     * 响应数据：
     {"data":{"area_code":"5100000","car_code":"川A2359","car_code_color":1,"car_color":1,"car_name":"李四","car_tel":"15611111111","car_type":1,"client_id":"1","ctime":1491549557891,"end_time_str":"2017-07-16 10:00:00","id":0,"local_loginname":"lisi","money":200,"note":"","pi_id":1,"pu_id":1,"start_time_str":"2017-06-16 10:00:00","state":0,"stime":1497606089665,"type":1,"utime":1497606089291},"errorcode":"","errormsg":"插入数据成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void recordParkMonthFreeActionTest() throws Exception {
        String url = BaseUrl + "record_parkmonthfree.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);
        setParameter(post);
        sendMessage(post);
    }

    /**
     * 修改信息
     * 响应数据：
     * {"data":{"area_code":"5100000","car_code":"川A2359","car_code_color":1,"car_color":1,"car_name":"李四","car_tel":"15611111111","car_type":1,"ctime":1491549557891,"end_time_str":"2017-07-16 10:00:00","id":5,"local_loginname":"lisi","money":200,"note":"","pi_id":1,"pu_id":1,"start_time_str":"2017-06-16 10:00:00","state":0,"stime":1497598323000,"type":1,"utime":1497598404856},"errorcode":"","errormsg":"修改成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void updateParkMonthFreeActionTest() throws Exception {
        String url = BaseUrl + "update_parkmonthfree.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        setParameter(post);
        sendMessage(post);
    }

    /**
     * 修改状态
     * 响应数据：
     * {"data":"","errorcode":"","errormsg":"修改状态成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void updateStateParkMonthFreeActionTest() throws Exception{
        String url = BaseUrl + "update_state_parkmonthfree.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        //setParameter(post);
        Long pi_id = 1L;               //停车场ID
        String area_code = "5100000";           //地址编号
        String car_code = "川A2359";            //车牌号
        String client_id = "2";
        Integer state= 1;

        sign = getSignature(Constants.SYSTEM_KEY, dtype, pi_id, area_code);

        post.addParameter("pi_id", pi_id + "");
        post.addParameter("area_code", area_code);
        post.addParameter("car_code", car_code);
        post.addParameter("client_id", client_id);
        post.addParameter("state", state+"");

        post.addParameter("sign", sign);

        sendMessage(post);
    }

    /**
     * 删除信息
     * 响应数据：
     * {"data":"","errorcode":"","errormsg":"删除成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void delParkMonthFreeActionTest() throws Exception{
        String url = BaseUrl + "del_parkmonthfree.php";
        PostMethod post = new UTF8PostMethod(url);
        //设置公共参数
        setPublicParam(post);

        Long pi_id = 1L;               //停车场ID
        String area_code = "5100000";           //地址编号
        String car_code = "川A2359";            //车牌号
        String client_id = "2";

        sign = getSignature(Constants.SYSTEM_KEY, dtype, pi_id, area_code);

        post.addParameter("pi_id", pi_id + "");
        post.addParameter("area_code", area_code);
        post.addParameter("car_code", car_code);
        post.addParameter("client_id", client_id);

        post.addParameter("sign", sign);
        sendMessage(post);
    }

    /**
     * 设置请求参数
     * @param post
     * @return
     */
    public PostMethod setParameter(PostMethod post){
        Long pi_id = 1L;               //停车场ID
        String area_code = "5100000";           //地址编号
        String car_code = "川A2359";            //车牌号
        String client_id = "2";
        Long money = 200L;               //该次缴纳费用(单位 分)
        Integer car_type = 1;            //车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
        Integer car_code_color= 1;      //车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
        Integer car_color=1;           //车辆颜色 BLUE("蓝色", 1), YELLOW("黄色", 2), WHITE("白色", 3), BLACK("黑色", 4), GREEN("绿色", 5)
        String start_time_str="2017-06-16 10:00:00";      //开始包月时间(2017-03-22 10:00:00)
        String end_time_str="2017-07-16 10:00:00";        //包月到期时间(2017-03-22 10:00:00)
        Integer type=1;                //类型(0:包月车辆 1:免费车辆)
        Long ctime = 1491549557891L;               //创建时间
        Long utime = System.currentTimeMillis();               //更新时间
        String car_tel ="15611111111";             //车主电话
        String car_name = "王五";            //车主姓名
        String local_loginname = "lisi";     //本地管理人员的帐号


        sign = getSignature(Constants.SYSTEM_KEY, dtype, pi_id, area_code);

        //post.addParameter("pi_id", pi_id + "");
        post.addParameter("area_code", area_code);
        post.addParameter("car_code", car_code);
        post.addParameter("money", money + "");

        post.addParameter("car_type", car_type + "");
        post.addParameter("car_code_color", car_code_color + "");

        post.addParameter("car_color", car_color + "");
        post.addParameter("start_time_str", start_time_str + "");
        post.addParameter("end_time_str", end_time_str + "");

        post.addParameter("type", type+"");
        post.addParameter("ctime", ctime+"");
        post.addParameter("utime", utime+"");
        post.addParameter("car_tel", car_tel);
        post.addParameter("car_name", car_name);
        post.addParameter("local_loginname", local_loginname);
        post.addParameter("client_id", client_id);

        post.addParameter("sign", sign);
        return post;
    }

    /**
     * 发送消息
     * @param post
     * @throws Exception
     */
    public void sendMessage(PostMethod post) throws Exception{
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
