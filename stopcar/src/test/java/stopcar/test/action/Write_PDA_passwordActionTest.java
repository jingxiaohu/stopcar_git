
package stopcar.test.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户重置PDA密码
 * @author jingxiaohu
 *
 */
public class Write_PDA_passwordActionTest extends BaseActionTest {
	/**
	 * 用户重置PDA密码
	 * @throws Exception
	 * {"data":{"address_name":"天府三街地铁A出口","allow_expect_time":60,"allow_revoke_time":0,"area_code":"510112","camera_info":"罗技Pro C920","carport_space":89,"carport_total":100,"carport_yet":11,"copy_linkman_name":"张小强","copy_linkman_tel":"15882345447","ctime":1470296969000,"department":"四川乐库斯","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_money":5,"hlc_enter_num":1,"hlc_exit_num":1,"is_expect":0,"is_rent":1,"lat":30.547776,"linkman_name":"敬小虎","linkman_tel":"15882345446","lng":104.067138,"loginname":"9527000","mac":"A0-B2-3C-9B-FF,A0-B2-3C-9B-FF","money":0,"month_price":30000,"moth_car_num":0,"note":"","park_type":1,"password":"670b14728ad9902aecba32e22fa4f6bd","pi_id":1,"pi_name":"四川乐库斯","pi_phone":"028-85960236","rent_info":"准入时段 17:00-08:30，150元/月","time_car_num":0,"timeout_info":"首停3小时5元，之后每小时1元","utime":1473837476125},"errorcode":"","errormsg":"重置密码成功","errorno":"0"}
	 */
	@Test
	public void change_pass_pda() throws Exception{
		String url = BaseUrl+"change_pass_pda.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//电话号码
		String tel = "15882345446";
		//固定参数：1：注册 2：重置密码 3:重置绑定电话号码
		String vclass = "2";
		//用户验证码
		String verify_code="181905"; 
		String verify_list="0204371dd7dd771e00b131ee11b4ce4d";//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值 //md5(tel+code)
		String password=DigestUtils.md5Hex("000000");//123456用户密码 已经进行MD5后的值 
		String repassword=DigestUtils.md5Hex("000000");//123456确认密码
		String loginname="9527000";//露天停车场的分配的帐号
		String park_type="1";//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
		String area_code="510122";//area_code;//省市县编号 510122
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,tel,verify_code,verify_list,vclass,password,repassword);
		
		post.addParameter("tel", tel);
		post.addParameter("vclass", vclass);
		post.addParameter("verify_code", verify_code);
		post.addParameter("verify_list", verify_list);
		post.addParameter("password", password);
		post.addParameter("repassword", repassword);
		post.addParameter("park_type", park_type+"");
		post.addParameter("area_code", area_code);
		post.addParameter("loginname", loginname);
		
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
			log.error("change_pass_pda is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
	
	
}
