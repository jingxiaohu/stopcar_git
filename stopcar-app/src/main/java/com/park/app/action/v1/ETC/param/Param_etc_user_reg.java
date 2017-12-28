package com.park.app.action.v1.ETC.param;

import org.springframework.web.multipart.MultipartFile;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 用户ETC注册
 * @author jingxiaohu
 *
 */
public class Param_etc_user_reg extends BaseParam {
	
	/********************接收参数区*************************/
	@Deprecated
	public String tel;//用户手机号码
	public String name;//ETC用户真实姓名
	public String sfz_number;//ETC用户真实身份证号码
	public String bank_card_number;//ETC用户银行卡卡号 
	public Integer bank_type;//银行类型（0：建行银行）
	
	@Deprecated
	//用户身份证图片文件 
	public  MultipartFile cardimg;
	@Deprecated
    //提交过来的file的名字
	public String cardimgFileName;
	@Deprecated
    //提交过来的file的MIME类型
	public String cardimgContentType;
	
	//ETC用户绑定银行卡表主键ID
	public Long eu_id;
	
	
/*	@Deprecated
	public String verify_code;//用户验证码
	@Deprecated
	public String verify_list;//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值
	@Deprecated
	public String vclass;//固定参数：1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡
*/	
	
	
	
	
	/*************************龙支付相关参数*********************************/
	public int pay_type=5;//支付类型 1:支付宝  2：微信  3：建行银联  4：钱包 5:龙支付 
	public int pay_price=1;//充值金额 单位 分
	public int version=1;//当前版本编号
	public String subject="建行签约";//商品名称
	public int system_type=1;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web   4:PDA
	public long t;//时间戳ms
	public String token;//令牌
	//收银台页面上，商品展示的超链接，必填
	public String show_url ="";
	// 页面跳转同步通知页面路径
	public String return_url = "";
	public int type=1;//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
	/************************get set 方法区****************************/

	public String getTel() {
		return tel;
	}
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}
	public Long getEu_id() {
		return eu_id;
	}
	public void setEu_id(Long eu_id) {
		this.eu_id = eu_id;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSfz_number() {
		return sfz_number;
	}
	public void setSfz_number(String sfz_number) {
		this.sfz_number = sfz_number;
	}
	public String getBank_card_number() {
		return bank_card_number;
	}
	public void setBank_card_number(String bank_card_number) {
		this.bank_card_number = bank_card_number;
	}
	public MultipartFile getCardimg() {
		return cardimg;
	}
	public void setCardimg(MultipartFile cardimg) {
		this.cardimg = cardimg;
	}
	public String getCardimgFileName() {
		return cardimgFileName;
	}
	public void setCardimgFileName(String cardimgFileName) {
		this.cardimgFileName = cardimgFileName;
	}
	public String getCardimgContentType() {
		return cardimgContentType;
	}
	public void setCardimgContentType(String cardimgContentType) {
		this.cardimgContentType = cardimgContentType;
	}
	public Integer getBank_type() {
		return bank_type;
	}
	public void setBank_type(Integer bank_type) {
		this.bank_type = bank_type;
	}
	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getSystem_type() {
		return system_type;
	}
	public void setSystem_type(int system_type) {
		this.system_type = system_type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
