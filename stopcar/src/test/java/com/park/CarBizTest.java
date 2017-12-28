package com.park;

import com.park.bean.User_park_coupon;
import com.park.dao.User_carcodeDao;
import com.park.mvc.service.common.ParkCouponPB;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author Peter Wu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/spring/spring.xml")
public class CarBizTest {

  @Value("${ebs.client_secret}")
  String client_secret;


  @Test
  public void client_secret() throws Exception {
    System.err.println(client_secret);
    Assert.assertEquals("UtMVrNfbkmy1y73QGjIREG8n1Il717rp",client_secret);
  }

  @Autowired
  User_carcodeDao user_carcodeDao;

  @Test
  public void clean() throws Exception {
    System.err.println("-------------------------------------");
    String sql = "select count(uc_id) from user_carcode where ui_id=?";
    int user_carcodecount = user_carcodeDao.getJdbc().getJdbcOperations()
        .queryForObject(sql, Integer.class,70);
    System.err.println(user_carcodecount);
    if (user_carcodecount >= 10) {
      System.err.println("超过数量 ");
    }
    System.err.println("-------------------------------------");
  }

  @Autowired
  private ParkCouponPB parkCouponPB;

  @Test
  public void getCoupon() throws Exception {
    User_park_coupon user_park_coupon = parkCouponPB.ReturnMaxAllCoupon_LZF(614);
    System.err.println(user_park_coupon.getUpc_id());
    user_park_coupon = parkCouponPB.ReturnZhandaoMaxAllCoupon_LZF(614,200);
    System.err.println(user_park_coupon.getUpc_id());

  }
}
