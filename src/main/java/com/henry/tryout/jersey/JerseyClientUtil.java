package com.henry.tryout.jersey;



import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class JerseyClientUtil {

    private Client client;

    public JerseyClientUtil() {
    }

    // 静态内部类实现单例模式
    private static class Singleton {
        private static final JerseyClientUtil jerseyClientUtil =
        new JerseyClientUtil();
    }

    // 获取单例 JerseyClientUtil - 公开方法，用来获取实例
    public static JerseyClientUtil getJerseyClientUtil() {
        return Singleton.jerseyClientUtil;
    }

    // 初始化 client对象
    private void setDefaultClient() {
        this.client = ClientBuilder.newClient();
    }

    // 使用 clientConfig 来 初始化client
    private void setConfigClient(ClientConfig config) {
        this.client = ClientBuilder.newClient(config);
    }

    private void initClient(ClientConfig config) {
        if (config != null) {
            this.setConfigClient(config);
        } else {
            this.setDefaultClient();
        }
    }

    // 发起 http post 请求，并接受 服务端的响应信息
    /*
        url 请求的目的地
        form 请求中的form对象
        config jerseyClient实例的配置情况，可以为空
        tClass 服务端的响应所映射到的实体
        response 服务端的响应对象
     */
    public <T> T postInvoke(
            String url,
            Form form,
            ClientConfig config,
            Class<T> tClass
    ) {

        // 初始化 client对象
        this.initClient(config);

        // 发起 post请求 - 会得到响应实体
        Response serverResponse = client.target(url)
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));

        // 把服务端的响应 绑定到 自定义的实体上
//        serverResponse.readEntity(); // 没有 readEntity()这个方法
        // 暂时没啥章法, 强制转换一下
        T bindingObject = (T) serverResponse.getEntity();

        return bindingObject;
    }

    // 发起 get请求 - 会得到服务端的响应
    /*
        url - 请求的目的地
        param - 请求所携带的参数组成的map 这里允许的参数类型是什么？
        config - client实例的配置信息
        tClass - 服务端响应的所需要绑定到的自定义对象
        response - 服务端的响应对象
     */
    public <T> T getInvoke(
            String url,
            Map<String, Object> param,
            ClientConfig config,
            Class<T> tClass
    ) {
        this.initClient(config);

        WebTarget webTarget = client.target(url);

        // 为 GET请求添加 请求参数 - 这种是 key-value形式的请求参数，如果是一个 对象类型呢？
        for (String key : param.keySet()) {
            webTarget = webTarget.queryParam(key, param.get(key));
        }

        T bindingObject = webTarget.request()
                .get(tClass);

        return bindingObject;
    }
}
