package com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Created by dgaglani on 11/6/14.
 */
public class TrustEverythingSSLContext {

    public static SSLContext getTrustEverythingSslContext() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        // set up a TrustManager that trusts everything
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers =============");
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
                System.out.println("checkClientTrusted =============");
            }

            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
                System.out.println("checkServerTrusted =============");
            }

            public boolean isClientTrusted(X509Certificate[] arg0) {
                return false;
            }

            public boolean isServerTrusted(X509Certificate[] arg0) {
                return false;
            }
        }}, new SecureRandom());
        return sslContext;
    }

}
