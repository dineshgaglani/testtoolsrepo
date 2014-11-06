package com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.exotel;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.TrustEverythingSSLContext;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 11/5/14.
 */
public class ExotelOperations {

    private static final String EXOTEL_ENDPOINT_THAT_WILL_MAKE_CALL = "";


    public static void callToPhone(String phoneNumToCall, String callerNumber) throws Exception {

        SSLSocketFactory sf = new SSLSocketFactory(TrustEverythingSSLContext.getTrustEverythingSslContext(), SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme httpsScheme = new Scheme("https", sf, 443);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(httpsScheme);
        HttpParams params = new BasicHttpParams();
        ClientConnectionManager cm = new SingleClientConnManager(params, schemeRegistry);
        DefaultHttpClient client = new DefaultHttpClient(cm, params);
        //Replace "<Exotel SID>" and "<Exotel Token>" with your SID and Token
        client.getCredentialsProvider().setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials("<Exotel SID>", "<Exotel Token>")
        );
        HttpPost post = new HttpPost("https://twilix.exotel.in/v1/Accounts/<Exotel SID>/Calls/connect");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        /*
        *Replace the text enclosed in < > with your desired values
        *The options for CallType are "trans" for transactional call and "promo" for promotional call
        */
        nameValuePairs.add(new BasicNameValuePair("From", "<Your-Customer's-Number>"));
        nameValuePairs.add(new BasicNameValuePair("To", "<Your-Exotel-Landline-or-Mobile>"));
        nameValuePairs.add(new BasicNameValuePair("CallerId", "<Your-Exotel-virtual-number>"));
        nameValuePairs.add(new BasicNameValuePair("Url", "http://my.exotel.in/exoml/start/<flow_id>"));
        nameValuePairs.add(new BasicNameValuePair("CallType", "trans"));
        /* Optional params
        nameValuePairs.add(new BasicNameValuePair("TimeLimit", "<time-in-seconds>"));
        nameValuePairs.add(new BasicNameValuePair("TimeOut", "<time-in-seconds>"));
        nameValuePairs.add(new BasicNameValuePair("StatusCallback", "<http//: your company URL>"));
        */
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = client.execute(post, responseHandler);
        System.out.println(response);
    }
}

