package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter {


    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public String filterType() {

        /*
        FilterType 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
            pre：路由之前
            routing：路由之时
            post： 路由之后
            error：发送错误调用
         */

        return "pre";
    }

    @Override
    public int filterOrder() {
        // 过滤器顺序

        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 是否要过滤，本文true,永远过滤。
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        // 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            logger.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        logger.info("ok");

        return null;
    }
}
