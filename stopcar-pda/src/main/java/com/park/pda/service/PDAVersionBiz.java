
package com.park.pda.service;

import org.springframework.stereotype.Service;

import com.park.bean.Channel_version;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import com.park.v1.model.Version;

/**
 * 获取设备 Android IOS 升级对应的包URL
 * @author jingxiaohu
 *
 */
@Service
public class PDAVersionBiz extends BaseBiz{

//	@Resource(name="baseTransaction")
//	protected BaseTransaction baseTransaction;

	public void Return_PDA_VsersionUpgrade(ReturnDataNew returnData, int versioncode, String mac) {
		try {
			Channel_version channel_version = getMySelfService().queryUniqueT(
					"SELECT cv.* FROM channel_version cv JOIN pda_channel pc ON cv.pda_c_id=pc.pda_c_id JOIN pda_info pi ON pc.pda_c_id=pi.pda_c_id WHERE pi.mac=? AND cv.version_code>? AND pc.is_open=1 AND pc.is_delete=0 AND cv.is_open=1 AND cv.apk_url IS NOT NULL AND cv.apk_url<>'' ORDER BY version_code DESC LIMIT 1",
					Channel_version.class, mac, versioncode);
			Version versionmodel = new Version();
			if(channel_version == null){
				returnData.setReturnData(errorcode_success, null, versionmodel);
			}else{
				//否有更新  1 –是 0 –否
				versionmodel.setUpdate(1);
				versionmodel.setType("pda");
				versionmodel.setUrl(channel_version.apk_url);
				//是否强制更新
				versionmodel.setIs_forced(channel_version.is_force);
				//最新版本
				versionmodel.setVersion(channel_version.version_out_show);
				//最新版本号
				versionmodel.setVersioncode(channel_version.version_code);
				//更新内容
				versionmodel.setContent(channel_version.intro);
				//MD5校验
				versionmodel.setMd5(channel_version.md5);
				returnData.setReturnData(errorcode_success, null, versionmodel);
			}
		} catch (Exception e) {
			log.error("VersionBiz Return_PDA_VsersionUpgrade is error", e);
			returnData.setReturnData(errorcode_systerm, "system is error", null);
		}

	}
}
