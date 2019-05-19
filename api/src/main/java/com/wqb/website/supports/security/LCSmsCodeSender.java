//
//package com.wqb.website.supports.security;
//
//import com.alibaba.fastjson.JSONObject;
//import com.wqb.security.core.validate.code.ValidateCode;
//import com.wqb.security.core.validate.code.sms.SmsCodeSender;
//import com.wqb.website.commons.message.MessageTemplate;
//import com.wqb.website.commons.message.SmsMessage;
//import com.wqb.website.supports.util.HttpClientUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//
///**
// * 蓝创短信发送器
// * @author WQB
// */
//@Component
//public class LCSmsCodeSender implements SmsCodeSender {
//
//	private Logger logger = LoggerFactory.getLogger(getClass());
//
//    private static final String SMS_SINGLE_REQUEST_SERVER_URL = "http://vsms.253.com/msg/send/json";
//
//	/**
//     * (non-Javadoc)
//	 * @see SmsCodeSender#send
//	 */
//	@Override
//	public void send(String mobile, ValidateCode validateCode) {
//
//	    // 过期时间（秒）
//        int seconds = validateCode.getExpireIn() / 60;
//        // 短信消息
//        String message = new SmsMessage(MessageTemplate.SMS_CODE, validateCode.getCode(), seconds).toString();
//
//        // 下发短信请求体
//        SmsSendRequest smsSendRequest = new SmsSendRequest("N2080702", "i97EbGlRs", message, mobile);
//        try {
//            String rsp = HttpClientUtils.executor()
//                    .setUrl(SMS_SINGLE_REQUEST_SERVER_URL)
//                    .setPayload(JSONObject.toJSONString(smsSendRequest))
//                    .jsonPost();
//            // 下发短信响应
//            SmsSendResponse smsSendResponse = JSONObject.parseObject(rsp, SmsSendResponse.class);
//
//            if (smsSendResponse != null && "0".equals(smsSendResponse.getCode())) {
//                logger.info("给{}发送短信：{}", mobile, message);
//            } else {
//                logger.info("给{}发送短信失败：没有返回值", mobile);
//            }
//        } catch (IOException e) {
//            logger.error("给{}发送短信失败：{}", mobile, e.getMessage());
//        }
//    }
//
//    public static class SmsSendRequest {
//        /**
//         * 用户账号，必填
//         */
//        private String account;
//        /**
//         * 用户密码，必填
//         */
//        private String password;
//        /**
//         * 短信内容。长度不能超过536个字符，必填
//         */
//        private String msg;
//        /**
//         * 机号码。多个手机号码使用英文逗号分隔，必填
//         */
//        private String phone;
//
//
//        /**
//         * 定时发送短信时间。格式为yyyyMMddHHmm，值小于或等于当前时间则立即发送，默认立即发送，选填
//         */
//        private String sendtime;
//        /**
//         * 是否需要状态报告（默认false），选填
//         */
//        private String report;
//        /**
//         * 下发短信号码扩展码，纯数字，建议1-3位，选填
//         */
//        private String extend;
//        /**
//         * 该条短信在您业务系统内的ID，如订单号或者短信发送记录流水号，选填
//         */
//        private String uid;
//
//        public SmsSendRequest() {
//
//        }
//        public SmsSendRequest(String account, String password, String msg, String phone) {
//            super();
//            this.account = account;
//            this.password = password;
//            this.msg = msg;
//            this.phone = phone;
//        }
//        public SmsSendRequest(String account, String password, String msg, String phone, String sendtime) {
//            super();
//            this.account = account;
//            this.password = password;
//            this.msg = msg;
//            this.phone = phone;
//            this.sendtime=sendtime;
//        }
//        public String getAccount() {
//            return account;
//        }
//        public void setAccount(String account) {
//            this.account = account;
//        }
//        public String getPassword() {
//            return password;
//        }
//        public void setPassword(String password) {
//            this.password = password;
//        }
//        public String getMsg() {
//            return msg;
//        }
//        public void setMsg(String msg) {
//            this.msg = msg;
//        }
//        public String getPhone() {
//            return phone;
//        }
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//        public String getSendtime() {
//            return sendtime;
//        }
//        public void setSendtime(String sendtime) {
//            this.sendtime = sendtime;
//        }
//        public String getReport() {
//            return report;
//        }
//        public void setReport(String report) {
//            this.report = report;
//        }
//        public String getExtend() {
//            return extend;
//        }
//        public void setExtend(String extend) {
//            this.extend = extend;
//        }
//        public String getUid() {
//            return uid;
//        }
//        public void setUid(String uid) {
//            this.uid = uid;
//        }
//
//        @Override
//        public String toString() {
//            return "SmsSendRequest{" +
//                    "account='" + account + '\'' +
//                    ", password='" + password + '\'' +
//                    ", msg='" + msg + '\'' +
//                    ", phone='" + phone + '\'' +
//                    ", sendtime='" + sendtime + '\'' +
//                    ", report='" + report + '\'' +
//                    ", extend='" + extend + '\'' +
//                    ", uid='" + uid + '\'' +
//                    '}';
//        }
//    }
//
//    public static class SmsSendResponse {
//        /**
//         * 响应时间
//         */
//        private String time;
//        /**
//         * 消息id
//         */
//        private String msgId;
//        /**
//         * 状态码说明（成功返回空）
//         */
//        private String errorMsg;
//        /**
//         * 状态码（详细参考提交响应状态码）
//         */
//        private String code;
//        public String getTime() {
//            return time;
//        }
//        public void setTime(String time) {
//            this.time = time;
//        }
//        public String getMsgId() {
//            return msgId;
//        }
//        public void setMsgId(String msgId) {
//            this.msgId = msgId;
//        }
//        public String getErrorMsg() {
//            return errorMsg;
//        }
//        public void setErrorMsg(String errorMsg) {
//            this.errorMsg = errorMsg;
//        }
//        public String getCode() {
//            return code;
//        }
//        public void setCode(String code) {
//            this.code = code;
//        }
//        @Override
//        public String toString() {
//            return "SmsSingleResponse [time=" + time + ", msgId=" + msgId + ", errorMsg=" + errorMsg + ", code=" + code
//                    + "]";
//        }
//    }
//}
