package tr.com.stackdeveloper.lib;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class Utils   
{
	public static HttpClient getHttpsClient(HttpClient client)
	{
		try
		{
			   X509TrustManager x509TrustManager = new X509TrustManager() 
			   {
					@Override
					public void checkClientTrusted(X509Certificate[] chain,
							String authType) throws CertificateException 
				    {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void checkServerTrusted(X509Certificate[] chain,
							String authType) throws CertificateException 
					{
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public X509Certificate[] getAcceptedIssuers() 
					{
						// TODO Auto-generated method stub
						return null;
					} 	           
					
		        };
		        
		        SSLContext sslContext = SSLContext.getInstance("TLS");
		        sslContext.init(null, new TrustManager[]{x509TrustManager}, null);
		        SSLSocketFactory sslSocketFactory = new ExSSLSocketFactory(sslContext);
		        sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		        ClientConnectionManager clientConnectionManager = client.getConnectionManager();
		        SchemeRegistry schemeRegistry = clientConnectionManager.getSchemeRegistry();
		        schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));
		        return new DefaultHttpClient(clientConnectionManager, client.getParams());
		    } 
		    catch (Exception ex) 
		    {
		        return null;
		    }
	}
}
