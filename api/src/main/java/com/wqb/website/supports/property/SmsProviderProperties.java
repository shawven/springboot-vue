package com.wqb.website.supports.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Shoven
 * @since 2019-05-17 16:13
 */
@ConfigurationProperties(prefix = "app.sms-provider")
public class SmsProviderProperties {

    private Provider lc = new Provider();

    public Provider getLc() {
        return lc;
    }

    public void setLc(Provider lc) {
        this.lc = lc;
    }

    public class Provider {

        private String appId;

        private String secret;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}
