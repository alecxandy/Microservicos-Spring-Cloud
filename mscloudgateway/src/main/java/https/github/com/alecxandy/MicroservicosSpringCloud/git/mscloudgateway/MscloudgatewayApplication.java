package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaServer
public class MscloudgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MscloudgatewayApplication.class, args);
    }

    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route(r -> r.path("/cliente/**").uri("lb://msclientes")).build();
    }

}
