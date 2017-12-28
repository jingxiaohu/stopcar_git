package com.park.app.task;

import com.park.bean.Etc_userinfo;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.common.EtcPB;
import com.park.mvc.service.common.JpushPB;
import com.park.service.MySelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * etc签约时，支付一分钱进行安全验证，从银行端获取支付结果定时任务
 * Created by zzy on 2017/6/27.
 */
//@Component
public class EtcCheckSignTask {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MySelfService mySelfService;

//    @Autowired
//    private EtcBiz etcBiz;

    @Autowired
    private EtcPB etcPB;

    @Autowired
    private JpushPB jpushPB;

    //@Scheduled(cron = "0 */1 * * * ?")
    public void etcChekckSign(){
            log.info("EtcCheckSignTask ：开始");
            String title = "系统消息";
            try{
                List<Etc_userinfo> list = queryEtcUserInfos();
                log.info("未签约记录："+list.size());
                for(Etc_userinfo userinfo : list){
                    ReturnDataNew returnData = new ReturnDataNew();
                    //etcBiz.etc_checksign(returnData, 0, Long.parseLong(userinfo.getEu_nd()), userinfo.getUi_id());

                    etcPB.Query_ETC_BankSign(returnData,userinfo);

                    //String errorno = String.valueOf(returnData.getErrorno());//obj.getString("errorno");
                    if(1 == userinfo.getIs_sign()){  //是否签约成功（0：没有签约 1：签约成功 2：签约失败 3：解除签约）
                        String uiNd = userinfo.getUi_nd();
                        String message = "恭喜您，银行卡号 "+hideBankCard(userinfo.getBank_card_number())+" 签约成功！";
                        jpushPB.pushSystem(uiNd,message,title);
                        log.info("签约成功，推送用户编号："+uiNd);
                    }
                }
            }catch (Exception e){
                log.error("系统错误-->",e);
            }
    }


    /**
     *
     * @return
     */
    public List<Etc_userinfo> queryEtcUserInfos(){

        try{
            String sql = "SELECT * from etc_userinfo t WHERE t.is_del = 0 and t.verify_sign = 0 and t.bank_type = 0";
            return mySelfService.queryListT(sql,Etc_userinfo.class);
        }catch (Exception e){
            log.error("查询错误-->",e);
            return new ArrayList<Etc_userinfo>();
        }
    }

    /**
     * 银行卡号只显示前两位和后四位,其余*代替
     * @param str
     * @return
     */
    public String hideBankCard(String str) {
        if (null == str || "".equals(str)) {
            return null;
        }
        String regx = "(?<=\\d{4})\\d(?=\\d{4})";
        return str.replaceAll(regx, "*");
    }
}
