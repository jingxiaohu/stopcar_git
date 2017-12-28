package com.park.sign;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解需要签名验证的接口，{@code RequestMapping}注解的方法上
 * <p>
 * <p>
 * <pre class="code">
 * <p>
 * &#064;RestController
 * public class TestController {
 * <p>
 * &#064;ApiSign("dtype","ui_id")
 * &#064;RequestMapping(value = "/test", method = RequestMethod.POST)
 * public Object test() {
 * return "test";
 * }
 * <p>
 * }
 * </pre>
 *
 * @author Peter Wu
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ApiSign {
	/**
	 * @return 参与签名的参数名，为空表示所有非空字段
	 */
	String[] value();
}
