package com.edmundofuentes.notifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

class HttpHelper
{
	private static final String LOG = "HTTP Helper";
	
	private int connectionTimeout = 5000;
	private int soTimeout = 30000;
	
	/**
	 * Load a GET Response to a string
	 * @param url String
	 * @return String
	 */
	public String loadHttpGet(String url)
	{
		StringBuilder builder = new StringBuilder();
	    
	    // Timeout settings
	    HttpParams httpParameters = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(httpParameters, this.connectionTimeout);
	    HttpConnectionParams.setSoTimeout(httpParameters, this.soTimeout);
	    
	    // Initialize HTTP Client
	    HttpClient client = new DefaultHttpClient(httpParameters);
	    
	    // GET request setup
	    HttpGet httpGet = new HttpGet(url);
	    
	    try {
	    	HttpResponse response = client.execute(httpGet);
	    	StatusLine statusLine = response.getStatusLine();
		    int statusCode = statusLine.getStatusCode();
		    if (statusCode == 200) {
		    	Log.v(LOG, "HTTP GET OK");
		    	HttpEntity entity = response.getEntity();
		        InputStream content = entity.getContent();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		        String line;
		        while ((line = reader.readLine()) != null) {
		        	builder.append(line);
		        }
		    } else {
		    	Log.e("HTTP", "HTTP GET error: " + statusCode);
		    }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    if(builder.toString().isEmpty())
	    {
	    	Log.w(LOG, "Empty HTTP Response");
	    }
	    
	    return builder.toString();
		
	}
	
	/**
	 * Send a POST request with a JSON payload
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public String sendHttpPostJson(String endpoint, String json)
	{	
		StringBuilder builder = new StringBuilder();
	    
	    // Timeout settings
	    HttpParams httpParameters = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(httpParameters, this.connectionTimeout);
	    HttpConnectionParams.setSoTimeout(httpParameters, this.soTimeout);
	    
	    // Initialize HTTP Client
	    HttpClient client = new DefaultHttpClient(httpParameters);
	    
	    // POST request setup
	    HttpPost httpPost = new HttpPost(endpoint);
	    // Set MIME type
	    httpPost.addHeader("content-type", "application/json");
	    // Set payload
		try {
			StringEntity payload = new StringEntity(json);
			httpPost.setEntity(payload);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			Log.e(LOG, "Unsupported payload encoding.");
		}
	    
	    try {
	    	HttpResponse response = client.execute(httpPost);
	    	StatusLine statusLine = response.getStatusLine();
		    int statusCode = statusLine.getStatusCode();
		    if (statusCode == 200) {
		    	Log.v(LOG, "HTTP POST OK");
		    	HttpEntity entity = response.getEntity();
		        InputStream content = entity.getContent();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		        String line;
		        while ((line = reader.readLine()) != null) {
		        	builder.append(line);
		        }
		    } else {
		        Log.e(LOG, "HTTP POST error: " + statusCode);
		    }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    if(builder.toString().isEmpty())
	    {
	    	Log.w(LOG, "Empty HTTP Response");
	    }
	    
	    return builder.toString();
	}
	
	/* DEPRECATED
	private String parseUrl(String url)
	{
		try {
			// Autogenerate URI from string
			URI uri = new URI( url );
			
			Log.d(LOG, "URI Scheme: " + uri.getScheme());
			Log.d(LOG, "URI Host: " + uri.getHost());
			Log.d(LOG, "URI Raw Path: " + uri.getRawPath());
			Log.d(LOG, "URI Path: " + uri.getPath());
			Log.d(LOG, "URI Raw Query: " + uri.getRawQuery());
			Log.d(LOG, "URI Query: " + uri.getQuery());
			
			// Manual string replacement.. this is UGLY. FIX ASAP.
			String safeQuery = uri.getQuery();
			safeQuery = safeQuery.replace("/", "%2F");
			safeQuery = safeQuery.replace(":", "%3A");
			safeQuery = safeQuery.replaceAll("^&", ""); // Ugly bug fix, remove first '&'
			Log.d(LOG, "URI Safe Query: " + safeQuery);
			
			// Replace URI
			Uri.Builder builder = new Uri.Builder();
			builder.scheme(uri.getScheme())
					.authority(uri.getAuthority())
					.path(uri.getPath());
			
			// Build new URL
			String safeUrl = builder.build().toString() + "?" + safeQuery;
			Log.d(LOG, "Safe URL: " + safeUrl);
			
			// Build new URI
			URI safeUri = new URI( safeUrl );
			Log.d(LOG, "Safe URI: " + safeUri.toString());
			
			return safeUri.toString();
		//} catch (URISyntaxException e) {
		} catch (Exception e) {
			return null;
		}
		
	}
	*/
}