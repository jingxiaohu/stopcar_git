package com.park

import cn.bestwu.gradle.apidoc.support.MDGenerator
import cn.bestwu.gradle.dbscript.DbScriptExtension
import cn.bestwu.gradle.dbscript.support.DbScriptGenerator
import cn.bestwu.gradle.dbscript.support.DbScripts
import com.park.sign.ApiDoc
import com.park.sign.ApiSign
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import org.apache.commons.io.FileUtils
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.servlet.http.HttpServletRequest
import java.lang.reflect.Method

import static com.park.interceptor.SpringMVCInterceptor.RESPONSE_DATA

/**
 * 生成文档
 *
 * @author Peter Wu
 */
@Component
@Aspect
class ApiDocAspect {

    @Autowired(required = false)
    private HttpServletRequest request

    @AfterReturning(value = "@annotation(com.park.sign.ApiDoc)", returning = "result")
    void doc(JoinPoint jp, Object result) throws IOException {
        Method method = ((MethodSignature) jp.getSignature()).getMethod()
        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class)
        String url = ""
        String resource = ""
        String name = methodAnnotation.name()
        Class<?> declaringClass = method.getDeclaringClass()
        RequestMapping classRequestMapping = declaringClass
                .getAnnotation(RequestMapping.class)
        if (classRequestMapping != null) {
            String[] value = classRequestMapping.value()
            url = value.length > 0 ? value[0] : ""
            resource = classRequestMapping.name()
        }
        String[] value = methodAnnotation.value()
        url += value.length > 0 ? value[0] : ""
        String httpMethod = new String('')
        RequestMethod[] requestMethods = methodAnnotation.method()
        for (int i = 0; i < requestMethods.length; i++) {
            httpMethod += requestMethods[i]
            if (i < requestMethods.length - 1) {
                httpMethod += ","
            }
        }
        if (httpMethod == '')
            httpMethod = request.getMethod()

        DbScriptExtension.url("jdbc:mysql://223.85.163.38:5068/stopcar?useUnicode=true&characterEncoding=utf-8&useSSL=false")
        DbScriptExtension.setPassword("Jingxiaohu123456")
        DbScriptExtension.javaName = { s, capitalize ->
            capitalize ? s : s[0].toLowerCase() + s[1..-1]
        }

        def slurper = new JsonSlurper()
        List fields = (List) slurper.parseText(
                FileUtils.readFileToString(new ClassPathResource("field.json").getFile(), "utf-8"))

        ApiDoc apiDoc = method.getAnnotation(ApiDoc.class)

        def signFields = apiDoc.signs()
        if (signFields.length == 0) {
            ApiSign apiSign = method.getAnnotation(ApiSign.class)
            signFields = apiSign.value()
        }
        if (signFields.length == 0) {
            throw new RuntimeException('未设置签名字段')
        }
        def signf = '', i = 0
        signFields.each {
            signf += it
            i++
            if (i < signFields.length)
                signf += '+'
        }
        def signField = new LazyMap()
        signField.put('desc', "MD5数字签名($signf)按参数的首字母升序顺序进行组装")
        signField.put('name', 'sign')
        signField.put('nullable', false)
        signField.put('type', 'String')
        signField.put('value', '')
        fields.add(signField)

        url += '.php'

        def api = new LazyMap()
        api.put('method', httpMethod)
        api.put('name', name)
        api.put('params', request.getParameterMap())
        api.put('resource', resource)
        api.put('results', slurper.parseText(request.getAttribute(RESPONSE_DATA)))
        api.put('url', url)
        def apis = [api]
        def tree = new LazyMap()
        def child = new LazyMap()
        child.put('leaf', true)
        child.put('text', name)
        tree.put('children', [child])
        tree.put('expanded', true)
        tree.put('text', resource)

        def tempfields = []
        DbScriptGenerator.generate(apiDoc.tableNames().toList(), [DbScripts.fieldScript]).each {
            tempfields.addAll(it)
        }
        def requires = apiDoc.requires()
        tempfields.each {
            it.nullable = !requires.contains(it.name)
        }
        tempfields.addAll(fields)

        def output = new File("src/test/resources/doc")
        output.deleteDir()
        MDGenerator.strict = false
        MDGenerator
                .generate(null, tree, apis, tempfields, output, "http://223.85.163.38:88/stopcar", false,
                "utf-8")
    }

}
