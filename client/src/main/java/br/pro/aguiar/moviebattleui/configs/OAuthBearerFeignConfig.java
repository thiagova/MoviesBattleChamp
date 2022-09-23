package br.pro.aguiar.moviebattleui.configs;

import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import br.pro.aguiar.moviebattleui.models.UserToken;
import feign.RequestInterceptor;

public class OAuthBearerFeignConfig { 


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            var headers = requestTemplate.headers();
            var tokens =  headers.get("token");
            var token = tokens.toArray()[0];
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }

}