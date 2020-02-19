package com.example.my_gateway;

import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class MyCodeingRouteConfig {

    // 对于请求符合链接的行为，直接返回200，病给出内容为body的字符串
    @Bean
    public RouterFunction<ServerResponse> testFunRouterFunction(){
        RouterFunction<ServerResponse> route_my = RouterFunctions.route(
                RequestPredicates.path("/testfun"),
                request -> ServerResponse.ok().body(BodyInserters.fromObject("Hello world : test fun")));

                return  route_my;

    }


    // 后台处理，将亲故转发到指定url上，获得返回结果后，在头部增加X-AnotherHeader，再返回给调用方
    @Bean
    public RouteLocator testRouteFunction_02(RouteLocatorBuilder builder, RewritePathGatewayFilterFactory throttle){
        return builder.routes()
                .route( r -> r.path("/baidu")
                .filters( f -> f.addResponseHeader("X-AnotherHeader","baz"))
                        .uri("http://baidu.com/")
                ).build();
        // http://baidu.com/baidu

    }

}
