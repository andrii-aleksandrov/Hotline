package apiTests;

import baseTest.BaseTest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class TwitterAPI extends BaseTest {
    public TwitterAPI(String browser) {
        super( browser );
    }

    @Test
    public void aptTesting() throws Exception {

        String url1 = "http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221";
        String url2 = "http://restcountries.eu/rest/v1";
        String url3 = "https://dev.twitter.com/rest/reference/get/statuses/home_timeline";


        try {
//            URL url = new URL(
//                    "https://dev.twitter.com/rest/reference/get/statuses/home_timeline" );
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod( "GET" );
//            connection.setRequestProperty( "Accept", "application/json" );
//
//            if (connection.getResponseCode() != 200) {
//                throw new RuntimeException( " HTTP error code : "
//                        + connection.getResponseCode() );
//            }
//
//            Scanner scan = new Scanner( url.openStream() );
//            String entireResponse = new String();
//            while (scan.hasNext())
//                entireResponse += scan.nextLine();

            HttpClient clientApps = new DefaultHttpClient();
            HttpGet getApps = new HttpGet(url3);
            getApps.addHeader("Accept", "application/json");
            HttpResponse responseApps = clientApps.execute(getApps);
            String result = EntityUtils.toString(responseApps.getEntity());

            logger.info( result );
//
//
//            //logger.info( "Response : " + entireResponse );
//
////            scan.close();
////
////            JSONObject obj = new JSONObject( entireResponse );
////            String responseCode = obj.getString( "status" );
////            logger.info( "status : " + responseCode );
//
//            JSONArray arr = obj.getJSONArray( "results" );
//            for (int i = 0; i < arr.length(); i++) {
//                String createdAt = arr.getJSONObject( i ).getString( "created_at" );
//
//                logger.info( "Place id : " + createdAt );
//
//                String formatAddress = arr.getJSONObject( i ).getString(
//                        "formatted_address" );
//                System.out.println( "Address : " + formatAddress );
//
////validating Address as per the requirement
//                if (formatAddress.equalsIgnoreCase( "Chicago, IL, USA" )) {
//                    System.out.println( "Address is as Expected" );
//                } else {
//                    System.out.println( "Address is not as Expected" );
//                }
//            }
//
//            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
