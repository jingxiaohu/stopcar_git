package com.park.jpush.demo;

import cn.jpush.api.push.PushResult;
import com.alibaba.fastjson.JSON;
import com.park.constants.Constants;
import com.park.jpush.PushUtil;
import com.park.jpush.bean.JPushMessageBean;
import java.util.Date;
import org.junit.Test;

/**
 * @author Peter Wu
 */
public class PushTest {

  @Test
  public void temp() throws Exception {
  }

  @Test
  public void push() throws Exception {
    JPushMessageBean jPushMessageBean = new JPushMessageBean();
    jPushMessageBean.setType(0);
    jPushMessageBean.setMessage("您充值" + 1 + "吾泊币已经到账，可以在电子钱包明细处查询。");
    jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
    jPushMessageBean.setTitle("充值到账");
    jPushMessageBean.setDate(new Date());
    String message = JSON.toJSONString(jPushMessageBean);
    String alert = "alert";
    PushResult pushResult = PushUtil
        .SendPush(JSON.toJSONString(jPushMessageBean), jPushMessageBean.getMessage(),
            "123");

    System.err.println(pushResult.isResultOK());
  }
}
