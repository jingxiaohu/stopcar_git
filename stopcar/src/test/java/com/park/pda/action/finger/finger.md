### PDA ：： 指纹系统-------用户资料管理模块


[[_TOC_]]




#### 1->指纹系统--用户资料新增

|参数名称|值描述|是否可空|限制长度|参数类型|
|--------|-----|----|--------|-------|
| verify_code| 6位手机验证码 | 否| 6 |字符串|
| verify_list|手机号码ui_tel+随机码v_code的MD5| 否| 无 |字符串|
| vclass| 1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡  5：指纹数据采集验证 | 否| 无 |字符串|
| ui_tel|手机号码| 否| 无 |字符串|
| car_code|车牌号码| 否| 无 |字符串|
| name|用户真实姓名| 否| 无 |字符串|
| sfz_number|身份证号码| 否| 无 |字符串|
| sfz_img_url|用户身份证图片| 是| 无 |字符串|
| bank_card_number|用户银行卡卡号| 否| 无 |字符串|
| bank_type|银行类型（0：建行银行）| 是| 无 |整型|
| mac|采集数据提交设备MAC| 否| 无 |字符串|
| sign_ip|签约地IP| 是| 无 |整型|
| gather_id|采集数据提交设备基本信息表主键ID| 是| 无 |长整型|
| ctime|创建时间| 是| 无 |长整型|
| sign| MD5数字签名(dtype+ui_tel+sfz_number+bank_card_number)按参数的首字母升序顺序进行组装| 否| 无 |字符串|

#### 请求路径

[http://223.85.163.38:88/stopcar/v1/finger_userinfo_add.php](http://223.85.163.38:88/stopcar/v1/finger_userinfo_add.php)

###### 返回结果
```json
{
    "data": {
        "lzf_info": {
            "act_type": 1,
            "car_order_id": "",
            "ctime": 1500866107568,
            "escape_orderids": "",
            "etime": 1500866107568,
            "id": 1261,
            "ip": "192.168.1.1",
            "money": 1,
            "note": "",
            "order_id": "2017072411150779196",
            "referer": "",
            "return_url": "",
            "state": 0,
            "subject": "吾泊充值",
            "system_type": 1,
            "tel": "13688052700",
            "transaction_id": "",
            "type": 5,
            "ui_id": 397,
            "ui_nd": "2017051711552394829",
            "utime": 1500866107568,
            "version_code": 1
        },
        "finger_userinfo": {
            "bank_card_number": "6217111111111111231",
            "bank_type": 0,
            "car_code": "川A11111",
            "ctime": 1500863643008,
            "discard_time": 1500866107605,
            "finger_veno": "",
            "fingerprint": "",
            "fu_id": 2,
            "fu_nd": "20170724111507g0zJP4hc2PsX0YzdBj",
            "gather_id": 123,
            "is_default": 0,
            "is_del": 0,
            "is_sign": 0,
            "mac": "abc-12912883893-abddffd",
            "name": "张三",
            "note": "初始化数据",
            "orderid": "2017072411150779196",
            "sfz_img_url": "",
            "sfz_number": "421182197504264466",
            "sign_ip": "192.168.1.1",
            "signtime": 1500866107605,
            "state": 1,
            "ui_id": 397,
            "ui_nd": "2017051711552394829",
            "ui_tel": "13688052700",
            "utime": 1500866107605,
            "verify_sign": 0
        }
    },
    "errorcode": "",
    "errormsg": "成功",
    "errorno": "0"
}
```
###### 返回字段描述：
|名称|值描述|限制长度|参数类型|
|--------|----|--------|-------|
| order_id| 我的订单（发往第三方的订单号）| 无 |字符串|
| fu_id| 纹系统-用户资料管理主键id| 无 |整型|
| fu_nd| 唯一标识符| 无 |字符串|
| ui_tel| 用户手机号码| 无 |字符串|


#### 2->支付一分钱结果查询并签约

|参数名称|值描述|是否可空|限制长度|参数类型|
|--------|-----|----|--------|-------|
| fu_id| 指纹系统-用户资料管理主键id | 否| 无 |长整型|
| fu_nd| 唯一标识符 | 否| 无 |字符串|
| sign| MD5数字签名(dtype+fu_id)按参数的首字母升序顺序进行组装| 否| 无 |字符串|

#### 请求路径

[http://223.85.163.38:88/stopcar/v1/finger_userinfo_check_sign.php](http://223.85.163.38:88/stopcar/v1/finger_userinfo_check_sign.php)

###### 返回结果
```json
{
    "data": "",
    "errorcode": "",
    "errormsg": "查询成功",
    "errorno": "0"
}
```
    
#### 3->用户指纹信息查询

|参数名称|值描述|是否可空|限制长度|参数类型|
|--------|-----|----|--------|-------|
| ui_tel| 用户手机号码| 否| 无 |字符串|
| verify_code| 6位手机验证码 | 否| 6 |字符串|
| verify_list| 手机号码ui_tel+随机码v_code的MD5 | 否| 无 |字符串|
| vclass| 1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡  5：指纹数据采集验证 | 否| 无 |字符串|
| sign| MD5数字签名(dtype+ui_tel)按参数的首字母升序顺序进行组装| 否| 无 |字符串|

#### 请求路径

[http://223.85.163.38:88/stopcar/v1/finger_query.php](http://223.85.163.38:88/stopcar/v1/finger_query.php)

###### 返回结果
```json
{
    "data": [
        {
            "bank_card_number": "6217111111111111231",
            "bank_type": 0,
            "car_code": "川A11111",
            "ctime": 1500863643000,
            "discard_time": 1500864440000,
            "finger_veno": "",
            "fingerprint": "",
            "fu_id": 1,
            "fu_nd": "20170724104720A1EoHVS799yazwuGR8",
            "gather_id": 123,
            "is_default": 0,
            "is_del": 0,
            "is_sign": 1,
            "mac": "abc-12912883893-abddffd",
            "name": "张三",
            "note": "初始化数据",
            "orderid": "2017072410472043489",
            "sfz_img_url": "",
            "sfz_number": "421182197504264466",
            "sign_ip": "192.168.1.1",
            "signtime": 1500864440000,
            "state": 1,
            "ui_id": 397,
            "ui_nd": "2017051711552394829",
            "ui_tel": "13688052700",
            "utime": 1500864440000,
            "verify_sign": 1
        }
    ],
    "errorcode": "",
    "errormsg": "查询成功",
    "errorno": "0"
}
```
######返回字段描述
|名称|值描述|限制长度|参数类型|
|--------|----|--------|-------|
fu_id|主键ID',|无|长整型
fu_nd|唯一标识符',|无|字符串
ui_tel|用户手机号码',|无|字符串
ui_id|平台用户ID',|无|
ui_nd|平台用户唯一标识符',|无|
name|用户真实姓名',|无|
sfz_number|用户真实身份证号码',|无|
sfz_img_url|用户身份证图片',|无|
fingerprint|用户指纹特征信息|无|
bank_card_number|用户银行卡卡号',|无|
bank_type|银行类型（0：建行银行）',|无|
orderid|签约验证支付订单号(user_pay表订单号)',|无|
verify_sign|是否签约支付验证成功0：未验证1：成功2：失败',|无|
state|有效状态(0：无效1：有效)',|无|
is_del|是否删除0:没有1：删除',|无|
ctime|创建时间',|无|
utime|修改时间',|无|
mac|采集数据提交设备MAC',|无|
gather_id|采集数据提交设备基本信息表主键ID',|无|
is_sign|是否签约成功（0：没有签约1：签约成功2：签约失败3：解除签约）',|无|
sign_ip|签约地IP',|无|
signtime|签约时间',|无|
discard_time|解除签约时间',|无|
is_default|是否是默认ETC支付卡0:不是1：是',|无|整型
note|备注',|无|字符串
car_code|车牌号',|无|字符串
finger_veno||无|字符串



#### 4->用户资料解除签约

|参数名称|值描述|是否可空|限制长度|参数类型|
|--------|-----|----|--------|-------|
| fu_id| 指纹系统-用户资料管理主键id| 否| 无 |长整型|
| sign| MD5数字签名(dtype+fu_id)按参数的首字母升序顺序进行组装| 否| 无 |字符串|

#### 请求路径

[http://223.85.163.38:88/stopcar/v1/finger_removesigned.php](http://223.85.163.38:88/stopcar/v1/finger_removesigned.php)

###### 返回结果
```json
{
    "data": "",
    "errorcode": "",
    "errormsg": "指纹系统--用户没有签约成功，无需解约",
    "errorno": "1002"
}
```

#### 5->用户指纹信息修改

|参数名称|值描述|是否可空|限制长度|参数类型|
|--------|-----|----|--------|-------|
| fu_id| 指纹系统-用户资料管理主键id | 否| 无 |字符串|
| car_code| 车牌号码 | 是| 无 |字符串|
| name| 用户真实姓名 | 是| 无 |字符串|
| sfz_number| 用户真实身份证号码 | 是| 无 |字符串|
| sfz_img_url| 用户身份证图片 | 是| 无 |字符串|
| fingerprint| 用户指纹特征信息 | 否| 无 |字符串|
| bank_type| 银行类型（0：建行银行） | 是| 无 |字符串|
| bank_card_number| 用户银行卡卡号 | 是| 无 |字符串|
| mac| 采集数据提交设备MAC | 是| 无 |字符串|
| gather_id| 采集数据提交设备基本信息表主键ID | 是| 无 |字符串|
| sign_ip| 签约地IP | 是| 无 |字符串|
| sign| MD5数字签名(dtype+fu_id)按参数的首字母升序顺序进行组装| 否| 无 |字符串|

#### 请求路径

[http://223.85.163.38:88/stopcar/v1/finger_query.php](http://223.85.163.38:88/stopcar/v1/finger_query.php)

###### 返回结果
```json
{
    "data": "",
    "errorcode": "",
    "errormsg": "修改信息成功",
    "errorno": "0"
}
