package org.visitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



public class WebServiceNetwork {
    private Context context;
    private ConnectivityManager connectivityManager;
    private NetworkInfo wifiInfo, mobileInfo;
    private boolean connected = false;
    private int TIMEOUT = 7000000;
    private static final String TAG = "WebServiceNetwork";
    //-----------------------------------------------

    public WebServiceNetwork(Context context) {
        this.context = context;
    }

    //-----------------------------------------------
    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;


        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }
    public void requestWebServiceByPost(String serviceUrl, String jsonBody, NetworkListener networkListener) {
        URL url;

        try {
            networkListener.onStart();
            if (!this.isOnline()) {
                networkListener.onErrorInternetConnection();
                return;
            } else {
                url = new URL(serviceUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type","application/json");
                conn.setReadTimeout(TIMEOUT);
                conn.setConnectTimeout(TIMEOUT);
                conn.setRequestProperty("charset", "utf-8");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Length", Integer.toString(jsonBody.getBytes(StandardCharsets.UTF_8).length));
                OutputStream op =conn.getOutputStream();
                BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(op,"UTF-8"));
                dos.write(jsonBody);
                op.flush();
                dos.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    conn.disconnect();
                    networkListener.onFinish(response.toString());
                } else {
                    conn.disconnect();
                    InputStreamReader errorStream = new InputStreamReader(conn.getErrorStream());
                    BufferedReader bufferedReader = new BufferedReader(errorStream);
                    StringBuilder sb = new StringBuilder();
                    String output;
                    while((output= bufferedReader.readLine())!=null){
                        sb.append(output);
                    }
                    System.out.println(sb);
                    if(responseCode==HttpURLConnection.HTTP_NOT_FOUND){
                        networkListener.onErrorServer(sb.toString());
                    }else{
                        networkListener.onErrorServer(sb.toString());
                    }
                }

            }
        } catch (Exception e) {
            networkListener.onErrorServer(e.getMessage());
        }
    }

    public String requestWebServiceByGet(String serviceUrl,String dbName, @Nullable HashMap<String, String> postDataParams,NetworkListener networkListener) {
        URL url;
        String response = "";
        String ConfigCoockie = String.format("Configuration=%s", dbName);
        networkListener.onStart();
        if (!this.isOnline()) {
            networkListener.onErrorInternetConnection();
            return null;
        } else {
            if (postDataParams == null) {
                try {
                    url = new URL(serviceUrl);
                } catch (MalformedURLException e) {
                    networkListener.onErrorServer(e.toString());
                    return null;
                }
            } else {
                try {
                    url = new URL(serviceUrl + "?" + getPostDataString(postDataParams));
                } catch (MalformedURLException e) {
                    networkListener.onErrorServer(e.toString());
                    return null;
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                networkListener.onErrorServer(e.toString());
                return null;
            }
            conn.setReadTimeout(TIMEOUT);
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            if(dbName!=null){
                conn.setRequestProperty("Cookie", ConfigCoockie);
            }
            conn.setConnectTimeout(TIMEOUT);
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                networkListener.onErrorServer(e.toString());
                return null;
            }
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            int responseCode = 0;
            try {
                responseCode = conn.getResponseCode();
            } catch (IOException e) {
                networkListener.onErrorServer(e.toString());
                return null;
            }
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } catch (IOException e) {
                    networkListener.onErrorServer(e.toString());
                    return null;
                }
                while (true) {
                    try {
                        if (!((line = br.readLine()) != null)) break;
                    } catch (IOException e) {
                        networkListener.onErrorServer(e.toString());
                        break;
                    }
                    response += line;
                }
                networkListener.onFinish(response);
                return response;
            } else if (responseCode == HttpsURLConnection.HTTP_NOT_FOUND) {
                networkListener.onErrorServer("404 not found");
                return null;
            } else {
                String line;
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    try {
                        if ((line = br.readLine()) == null) break;
                    } catch (IOException e) {
                        networkListener.onErrorServer(sb.toString());
                        break;
                    }
                    sb.append(line);
                }
                networkListener.onErrorServer(sb.toString());
                return null;
            }
        }
    }

    //-----------------------------------------------
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(Keyboard.convertPersianNumberToEngNumber(String.valueOf(entry.getValue())), "UTF-8"));
        }
        //String finalResult = Keyboard.convertPersianNumberToEngNumber(result.toString());
        return result.toString();
        //return finalResult;
    }
    private String getJsonDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(Keyboard.convertPersianNumberToEngNumber(String.valueOf(entry.getValue())), "UTF-8"));
        }
        //String finalResult = Keyboard.convertPersianNumberToEngNumber(result.toString());
        return result.toString();
        //return finalResult;
    }


    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }
    private SSLSocketFactory createSslSocketFactory() throws Exception {
//        CertificateFactory cf = null;
//        try {
//            cf = CertificateFactory.getInstance("X.509");
//            InputStream caInput = context.getResources().openRawResource(R.raw.respina_certificate);
//            Certificate ca;
//            try {
//                ca = cf.generateCertificate(caInput);
//                // Log.e("CERT", "ca=" + ((X509Certificate) ca).getSubjectDN());
//            } finally {
//                caInput.close();
//            }
//
//
//            String keyStoreType = KeyStore.getDefaultType();
//            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("ca", ca);
//
//
//            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//            tmf.init(keyStore);
//
//
//            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//
//                    Log.e("CipherUsed", session.getCipherSuite());
//                    return hostname.compareTo("respina24.ir") == 0; //The Hostname of your server
//
//                }
//            };
//
//
//            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
//            SSLContext context = null;
//            context = SSLContext.getInstance("TLS");
//
//            context.init(null, tmf.getTrustManagers(), null);
//            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
//
//            SSLSocketFactory sf = context.getSocketFactory();
//
//
//            return sf;
//
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//
//        return  null;
        TrustManager[] byPassTrustManagers = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        } };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, byPassTrustManagers, new SecureRandom());
        return sslContext.getSocketFactory();

    }

}
