package com.park.task;

import com.park.mvc.service.common.ActivityPB2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Peter Wu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/spring/spring.xml")
public class ActivityPB2Test {

  @Autowired
  ActivityPB2 activityPB2;

  @Test
  public void test() throws Exception {
    activityPB2.doEvent();
  }
}
