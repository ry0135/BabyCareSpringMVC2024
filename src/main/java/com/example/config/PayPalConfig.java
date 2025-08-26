package com.example.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PayPalConfig {

    private String clientId = "AQyrHvnKLOlr7shxLSxNz2ZVH4Cjq1H7yPQ5ZOc8oGr_FKwsReTcclGdeeYct9gE5L5s2xwjjYOuzfwG"; // lấy ở PayPal Developer
    private String clientSecret = "EPMRiGSvPAWCpicQa6O-uKfNBa6HMEIZtRlYd1jIc-pE3yX8sn6X-WRq_S1IsBu2owqjLJx1fWHQ5f3s";
    private String mode = "sandbox"; // hoặc "live"

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }
}