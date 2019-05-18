package com.wqb.website.supports.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Shoven
 * @since 2019-05-17 11:18
 */
@ConfigurationProperties(prefix = "app.cloud-account")
public class CloudAccountProperties {

    private String buyCloudAccountUrl;

    private String checkTriedUrl;

    private String checkPaidUrl;

    private String triedLoginUrl;

    private String paidLoginUrl;

    public String getBuyCloudAccountUrl() {
        return buyCloudAccountUrl;
    }

    public void setBuyCloudAccountUrl(String buyCloudAccountUrl) {
        this.buyCloudAccountUrl = buyCloudAccountUrl;
    }

    public String getCheckTriedUrl() {
        return checkTriedUrl;
    }

    public void setCheckTriedUrl(String checkTriedUrl) {
        this.checkTriedUrl = checkTriedUrl;
    }

    public String getCheckPaidUrl() {
        return checkPaidUrl;
    }

    public void setCheckPaidUrl(String checkPaidUrl) {
        this.checkPaidUrl = checkPaidUrl;
    }

    public String getTriedLoginUrl() {
        return triedLoginUrl;
    }

    public void setTriedLoginUrl(String triedLoginUrl) {
        this.triedLoginUrl = triedLoginUrl;
    }

    public String getPaidLoginUrl() {
        return paidLoginUrl;
    }

    public void setPaidLoginUrl(String paidLoginUrl) {
        this.paidLoginUrl = paidLoginUrl;
    }
}
