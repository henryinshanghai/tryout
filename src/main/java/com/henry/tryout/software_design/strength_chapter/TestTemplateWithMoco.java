package com.henry.tryout.software_design.strength_chapter;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Request;
import com.github.dreamhead.moco.resource.Content;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.running;

public class TestTemplateWithMoco {

    public void should_return_expected_response() {
        // 设置模拟服务器的信息
        // 设置服务器访问的端口
        HttpServer server = httpServer(123456);

        // 访问/foo这个 URI时，返回bar
        // 代码有点旧 跟现在的Moco依赖不兼容
        /*
        server.request(by(uri("/foo"))).response("bar");

        // 开始执行测试
        running(server, new Runnable() {
            // 使用 Apache HTTP库访问模拟服务器， 也可以使用真实的项目
            Content content = Request.Get("http://localhost:12306/foo")
                    .execute()
                    .returnContent();

            // 对结果进行断言
            assertThat(content.asString(), is("bar"));
        });

         */
    }
}
