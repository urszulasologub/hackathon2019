package com.niewycompany.klejton.studentbook;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class REST_Integration extends AsyncTask<String, Void, String>
{
    String endpoint = "http://77618c1e.ngrok.io/api/";
    String result;

    @Override
    protected String doInBackground(String... params)
    {
        String recordId = params[0];
        String objectType = params[1];
        String helperRecordId = "";
        if(params.length==3)
        {
            helperRecordId = params[2];
        }
        String result = null;
        try
        {
            String urlString = this.endpoint + objectType + "/" + recordId;
            if(!helperRecordId.isEmpty())
            {
                urlString += '/' + helperRecordId;
            }
            URL endpoint = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)endpoint.openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            Integer responseCode = connection.getResponseCode();

            if(connection.getResponseCode() == 200)
            {
                InputStream stream = connection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String inputLine;
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            else
            {
                //fecc
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        this.result = result;
        return result;
    }

    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
    }
}
