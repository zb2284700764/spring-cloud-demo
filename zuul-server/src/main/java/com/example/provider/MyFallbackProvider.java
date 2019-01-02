package com.example.provider;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * 实现 FallbackProvider 接口实现 Zuul 断路器
 */
//@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        //所有的路由都加熔断器,如果只对eureka-client 服务生效，此处可替换为“eureka-client”
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse(){

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                return null;
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "Ok";
            }

            @Override
            public void close() {

            }
        };
    }

}
