package apiTests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReadJsonObject {
    @Test
    public void aptTesting() throws Exception {
        try {
            URL url = new URL(
                    "http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221" );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod( "GET" );
            connection.setRequestProperty( "Accept", "application/json" );

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException( " HTTP error code : "
                        + connection.getResponseCode() );
            }

            Scanner scan = new Scanner( url.openStream() );
            String entireResponse = new String();
            while (scan.hasNext())
                entireResponse += scan.nextLine();

            System.out.println( "Response : " + entireResponse );

            scan.close();

            JSONObject obj = new JSONObject( entireResponse );
            String responseCode = obj.getString( "status" );
            System.out.println( "status : " + responseCode );

            JSONArray arr = obj.getJSONArray( "results" );
            for (int i = 0; i < arr.length(); i++) {
                String placeid = arr.getJSONObject( i ).getString( "place_id" );
                System.out.println( "Place id : " + placeid );
                String formatAddress = arr.getJSONObject( i ).getString(
                        "formatted_address" );
                System.out.println( "Address : " + formatAddress );

//validating Address as per the requirement
                if (formatAddress.equalsIgnoreCase( "Chicago, IL, USA" )) {
                    System.out.println( "Address is as Expected" );
                } else {
                    System.out.println( "Address is not as Expected" );
                }
            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}