package com.attra.networkingapp.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHelper {



    public static String downloadUrl(String urlAddress) throws IOException{

        InputStream inputStream=null;

        try {
            URL url=new URL(urlAddress);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();

            // sets read timeout of the connection
            connection.setReadTimeout(10000);

            // sets connection timeout
            connection.setConnectTimeout(15000);

            // means we are expecting data from server
            connection.setDoInput(true);

            // Need to define method from which method we are connecting to server
            connection.setRequestMethod("GET");

            // Here creates the actual connection
            connection.connect();

            // Getting the response code from server for GET Request it will 200 return code
            int responseCode=connection.getResponseCode();

            if(responseCode !=200){

                throw new Exception("Error got response code "+ responseCode);
            }

            inputStream=connection.getInputStream();
            return readStream(inputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream!=null){
                inputStream.close();
            }
        }

        return null;
    }

    private static String readStream(InputStream inputStream)throws IOException {

        byte[] buffer=new byte[1024];

        ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
        BufferedOutputStream out=null;

        try {
            int length=0;
            out=new BufferedOutputStream(byteArray);
            while ((length=inputStream.read(buffer)) > 0){

                out.write(buffer,0,length);
            }

            out.flush();
            return byteArray.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(out!=null){
                out.close();
            }
        }
    }
}
