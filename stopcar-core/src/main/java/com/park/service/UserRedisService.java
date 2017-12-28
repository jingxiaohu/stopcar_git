package com.park.service;

import com.alibaba.fastjson.JSONObject;
import com.park.bean.User_info;
import com.park.jedis.pool.JedisUserInfoPool;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * 消息缓存服务
 *
 * @author tt
 */
@Service
public class UserRedisService {

  private static Logger logger = LoggerFactory.getLogger(UserRedisService.class);

  @Autowired
  private JedisUserInfoPool jedisUserInfoPool;

  /**
   * @param @param ii_loginname
   * @param @param imei
   * @param @param allow_count :允许的次数
   * @param @return 设定文件
   * @return String    返回类型
   * @Title: MakeUserCode
   * @Description: TODO(生成验证码)
   */
  public String MakeUserCode(String prefix, int allow_count, String ii_loginname, String imei) {
    if (RequestUtil.checkObjectBlank(ii_loginname)) {
      return null;
    }
    Jedis redis = null;
    try {
      String key = prefix + ii_loginname;
      redis = jedisUserInfoPool.getResource();
      if (redis.exists(key)) {
        //如果存在
        String reg_code_str = redis.get(key);
        if (RequestUtil.checkObjectBlank(reg_code_str)) {
          return null;
        }
        JSONObject obj = JSONObject.parseObject(reg_code_str);
        if (obj != null) {
          int count = obj.getIntValue("count");
          if (count > allow_count) {
            //每个账号最多允许获取allow_count次验证码
            return "error";
          } else {
            //生成新的验证码
            count++;
            obj.put("count", count);
            obj.put("code", RequestUtil.returnAuthCode());
            if (!RequestUtil.checkObjectBlank(imei)) {
              obj.put("imei", imei);
            }
            //更新redis
            redis.set(key, obj.toString());

            return obj.getString("code");
          }
        }
      } else {
        //第一次生成
        JSONObject obj = new JSONObject();
        obj.put("count", 1);
        obj.put("code", RequestUtil.returnAuthCode());
        obj.put("imei", imei == null ? "" : imei);
        //更新redis
        redis.set(key, obj.toString());
        return obj.getString("code");
      }
    } catch (Exception e) {
      logger.error("returnUserRegCode is error", e);
    } finally {
      if (redis != null) {
        //释放连接
        jedisUserInfoPool.returnResource(redis);
      }
    }
    return null;
  }


  /**
   * @param @param ii_loginname
   * @param @return 设定文件
   * @return JSONObject    返回类型
   * @Title: returnUserCode
   * @Description: TODO(获取验证码)
   */
  public JSONObject returnUserCode(String prefix, String ii_loginname) {
    if (RequestUtil.checkObjectBlank(ii_loginname)) {
      return null;
    }
    Jedis redis = null;
    try {
      String key = prefix + ii_loginname;
      redis = jedisUserInfoPool.getResource();
      if (redis.exists(key)) {
        //如果存在
        String reg_code_str = redis.get(key);
        if (RequestUtil.checkObjectBlank(reg_code_str)) {
          return null;
        }
        return JSONObject.parseObject(reg_code_str);
      }
    } catch (Exception e) {
      logger.error("returnUserCode is error", e);
    } finally {
      if (redis != null) {
        //释放连接
        jedisUserInfoPool.returnResource(redis);
      }
    }
    return null;
  }


  /**
   * @param @param prefix
   * @param @param suffix
   * @param @return 设定文件
   * @return JSONObject    返回类型
   * @Title: returnAppSdk_Info
   * @Description: TODO(获取APPSDK缓存信息)
   */
  public JSONObject returnAppSdk_Info(String prefix, String suffix) {
    if (RequestUtil.checkObjectBlank(suffix)) {
      return null;
    }
    Jedis redis = null;
    try {
      String key = prefix + suffix;
      redis = jedisUserInfoPool.getResource();
      if (redis.exists(key)) {
        //如果存在
        String str = redis.hget(key, "info");
        if (RequestUtil.checkObjectBlank(str)) {
          return null;
        }
        return JSONObject.parseObject(str);
      }
    } catch (Exception e) {
      logger.error("returnUserCode is error", e);
    } finally {
      if (redis != null) {
        //释放连接
        jedisUserInfoPool.returnResource(redis);
      }
    }
    return null;
  }


  /**
   * 检查是否存在某KEY
   *
   * @param @param prefix
   * @param @param suffix
   * @param @return 设定文件
   * @return boolean    返回类型
   * @Title: isExistKey
   * @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public boolean isExistKey(String prefix, String suffix) {
    Jedis redis = null;
    try {
      String key = prefix + suffix;
      redis = jedisUserInfoPool.getResource();
      return redis.exists(key);
    } catch (Exception e) {
      logger.error("isExistKey is error", e);
    } finally {
      if (redis != null) {
        //释放连接
        jedisUserInfoPool.returnResource(redis);
      }
    }
    return false;
  }

  /**
   * 更新或者设置APPSDK缓存区域数据
   *
   * @param @param prefix
   * @param @param suffix
   * @param @param value
   * @param @return 设定文件
   * @return boolean    返回类型
   * @Title: updateAppSdkKeyData
   * @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public boolean updateAppSdkKeyData(String prefix, String suffix, String value) {
    Jedis redis = null;
    try {
      String key = prefix + suffix;
      redis = jedisUserInfoPool.getResource();
      if (redis.exists(key)) {
        //更新
        redis.hset(key, "info", value);
      } else {
        //保存
        redis.hset(key, "info", value);
      }
    } catch (Exception e) {
      logger.error("isExistKey is error", e);
    } finally {
      if (redis != null) {
        //释放连接
        jedisUserInfoPool.returnResource(redis);
      }
    }
    return false;
  }


  /**
   * @param @param prefix
   * @param @param suffix
   * @param @return 设定文件
   * @return JSONObject    返回类型
   * @Title: returnGameSdk_Info
   * @Description: TODO(获取GAMESDK缓存信息)
   */
  public JSONObject returnGameSdk_Info(String prefix, String suffix) {
    if (RequestUtil.checkObjectBlank(suffix)) {
      return null;
    }
    Jedis redis = null;
    try {
      String key = prefix + suffix;
      redis = jedisUserInfoPool.getResource();
      if (redis.exists(key)) {
        //如果存在
        String str = redis.hget(key, "info");
        if (RequestUtil.checkObjectBlank(str)) {
          return null;
        }
        return JSONObject.parseObject(str);
      }
    } catch (Exception e) {
      logger.error("returnGameSdk_Info is error", e);
    } finally {
      if (redis != null) {
        //释放连接
        jedisUserInfoPool.returnResource(redis);
      }
    }
    return null;
  }

  /**
   * 更新或者设置GameSDK缓存区域数据
   *
   * @param @param prefix
   * @param @param suffix
   * @param @param value
   * @param @return 设定文件
   * @return boolean    返回类型
   * @Title: updateGameSdkKeyData
   * @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public boolean updateGameSdkKeyData(String prefix, String suffix, String value) {
    Jedis redis = null;
    try {
      String key = prefix + suffix;
      redis = jedisUserInfoPool.getResource();
      if (redis.exists(key)) {
        //更新
        redis.hset(key, "info", value);
      } else {
        //保存
        redis.hset(key, "info", value);
      }
    } catch (Exception e) {
      logger.error("updateGameSdkKeyData is error", e);
    } finally {
      if (redis != null) {
        //释放连接
        jedisUserInfoPool.returnResource(redis);
      }
    }
    return false;
  }

  /**********************下面是以前的方法**************************/

  /**
   * 根据用户编号检查用户是否存在
   *
   * @return true表示存在
   */
  public boolean checkUserExist(String ui_id) {
    boolean flag = false;
    Jedis redis = null;
    try {
      String key = "ut_" + ui_id;
      redis = jedisUserInfoPool.getResource();
      flag = redis.exists(key);
    } catch (Exception e) {
      logger.error("checkUserExist", e);
    } finally {
      jedisUserInfoPool.returnResource(redis);
    }
    return flag;
  }

  /**
   * 更新用户的基本信息
   */
  public void updateUserInfo(User_info uInfo) {
    Jedis redis = null;
    try {
      String key = "ut_" + uInfo.getUi_id();
      redis = jedisUserInfoPool.getResource();
      if (redis.exists(key)) {
        redis.hset(key, "info", JSONObject.toJSONString(uInfo));
      }
    } catch (Exception e) {
      logger.error("checkUserExist", e);
    } finally {
      jedisUserInfoPool.returnResource(redis);
    }
  }

  public String getUserNickName(long ui_id) {
    boolean flag = false;
    Jedis redis = null;
    try {
      String key = "ut_" + ui_id;
      redis = jedisUserInfoPool.getResource();
      flag = redis.exists(key);
      if (flag) {//存在该用户
        String info = redis.hget(key, "info");
        if (info != null && info.length() > 6) {
          JSONObject obj = JSONObject.parseObject(info);
          if (obj != null && !obj.isEmpty()) {
            return obj.getString("ui_nickname");
          }
        }
      }
    } catch (Exception e) {
      logger.error("checkUserExist", e);
    } finally {
      jedisUserInfoPool.returnResource(redis);
    }
    return null;
  }

  /**
   * 获取用户属性
   */
  public String getUserInfo(long ui_id, String col_name) {
    boolean flag = false;
    Jedis redis = null;
    try {
      String key = "ut_" + ui_id;
      redis = jedisUserInfoPool.getResource();
      flag = redis.exists(key);
      if (flag) {//存在该用户
        String info = redis.hget(key, "info");
        if (info != null && info.length() > 6) {
          JSONObject obj = JSONObject.parseObject(info);
          if (obj != null && !obj.isEmpty()) {
            return obj.getString(col_name);
          }
        }
      }
    } catch (Exception e) {
      logger.error("checkUserExist", e);
    } finally {
      jedisUserInfoPool.returnResource(redis);
    }
    return null;
  }

}
