package com.park.service;

import com.park.exception.QzException;
import com.park.service.etc.etcapi.ETCResponse;
import com.park.service.etc.etcapi.StrikeBalRequest;
import com.park.service.etc.etcapi.ToRegisterRequest;
import com.park.service.etc.etcapi.ToRopeRequest;
import com.park.util.MoneyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ETC辅助类
 *
 * @author Peter Wu
 */
@Component
public class ETCHelper {

  private Logger log = LoggerFactory.getLogger(ETCHelper.class);
  /**
   * 银企直联接口主机地址
   */
  @Value("${etc.domain}")
  private String domain;

  /**
   * etc单笔支付限额 200元,单位 分
   */
  private long exceedSingleAmt = 20000L;

  private RestTemplate restTemplate = new RestTemplate();
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
  private SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");


  public ETCHelper() {
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(new GsonHttpMessageConverter() {
      @Override
      protected boolean canRead(MediaType mediaType) {
        return true;
      }

      @Override
      public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;
      }
    });
    restTemplate.setMessageConverters(messageConverters);
  }

  /**
   * 签约/解约
   *
   * @param func 功能号 标识该请求为签约还是解约，0为签约，1为解约
   * @param serial 第三方流水	String(16)	第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
   * @param userNo 在系统存在的用户的唯一标识 长度 32
   * @param userName 用户姓名 长度 20
   * @param idNo 用户身份证号 长度 18
   * @param bankCard 建设银行卡卡号 长度 19
   * @return 响应
   */
  public ETCResponse toregister(String serial, int func, String userNo, String userName,
      String idNo,
      String bankCard) throws QzException {
    try {
      Date now = new Date();
      ToRegisterRequest params = new ToRegisterRequest();
      params.setTransDate(getTransDate(now));
      params.setTransTime(timeFormat.format(now));
      params.setSerial(serial);
      params.setUserNo(userNo);
      params.setUserName(userName);
      params.setIdNo(idNo);
      params.setBankCard(bankCard);
      params.setFunc(String.valueOf(func));

      return restTemplate.postForObject(expandUrl("/etcapi/toregister"), params, ETCResponse.class);
    } catch (Exception e) {
      if (func == 0) {
        throw new QzException("ETC签约失败", e);
      } else {
        throw new QzException("ETC解约失败", e);
      }
    }
  }

  /**
   * 消费/交易
   *
   * @param serial 第三方流水	String(16)	第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
   * @param userNo 在系统存在的用户的唯一标识 长度 32
   * @param userName 用户姓名 长度 20
   * @param idNo 用户身份证号 长度 18
   * @param bankCard 建设银行卡卡号 长度 19
   * @param ropAmt 圈存金额 单位分
   * @return 响应
   */
  public ETCResponse torope(String serial, String userNo, String userName, String idNo,
      String bankCard, long ropAmt) throws QzException {
    try {

      //by zzy 增加单笔限额200元限制  2017-06-29
      if(checkIsExceedSingleAmt(ropAmt)){
        throw new QzException("ETC消费/交易失败，超过单笔限额200元");
      }

      Date now = new Date();
      ToRopeRequest params = new ToRopeRequest();
      params.setTransDate(getTransDate(now));
      params.setTransTime(timeFormat.format(now));
      params.setSerial(serial);
      params.setUserNo(userNo);
      params.setUserName(userName);
      params.setIdNo(idNo);
      params.setBankCard(bankCard);
      params.setRopAmt(MoneyUtil.toYun(ropAmt).toString());

      return restTemplate.postForObject(expandUrl("/etcapi/torope"), params, ETCResponse.class);
    } catch (Exception e) {
      throw new QzException("ETC消费/交易失败", e);
    }
  }

  /**
   * 冲正
   *
   * @param serial 第三方流水	String(16)	第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
   * @param oldTxdate 原交易日期
   * @param oldTransBankId 原交易第三方流水号
   * @param oldRopAmt 原圈存额度 单位分
   * @return 响应
   */
  public ETCResponse strikebal(String serial, String oldTxdate, String oldTransBankId,
      long oldRopAmt) throws QzException {
    try {
      Date now = new Date();
      StrikeBalRequest params = new StrikeBalRequest();
      params.setTransDate(getTransDate(now));
      params.setTransTime(timeFormat.format(now));
      params.setSerial(serial);
      params.setOldTxdate(oldTxdate);
      params.setOldTransBankId(oldTransBankId);
      params.setOldRopAmt(MoneyUtil.toYun(oldRopAmt).toString());

      return restTemplate.postForObject(expandUrl("/etcapi/strikebal"), params, ETCResponse.class);
    } catch (Exception e) {
      throw new QzException("ETC冲正失败", e);
    }
  }

  /**
   * 交易日期
   *
   * @param date 时间
   * @return 交易日期
   */
  public String getTransDate(Date date) {
    return dateFormat.format(date);
  }

  /**
   * 补全URL
   *
   * @param apiPath api path
   * @return 完整URL
   */
  private String expandUrl(String apiPath) {
    if (log.isDebugEnabled()) {
      log.debug("接口：{}", domain + apiPath);
    }
    return domain + apiPath;
  }


  /**
   * 检查支付金额是否超过单笔限额200元
   * @param amt 消费金额 单位分
   * @return
   */
  private boolean checkIsExceedSingleAmt(long amt){
    if(amt > exceedSingleAmt){
      return true;
    }else{
      return false;
    }
  }

}
