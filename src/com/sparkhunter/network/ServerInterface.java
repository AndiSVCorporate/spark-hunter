/**
 * Copyright (c) 2010 Mujtaba Hassanpur.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sparkhunter.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Class: ServerInterface - Provides static methods to abstract the server
 * calls. This makes it easy for calling classes to use these functions without
 * worrying about the details of the server communication.
 * 
 * @author Mujtaba Hassanpur
 */
public class ServerInterface {

        // Declared Constants
        public static final String SERVER_URL = "http://www.vtlan.net/test.php";

        public static String getList() throws UnsupportedEncodingException {
                String data = "command=" + URLEncoder.encode("getBattles","UTF-8");
                return executeHttpRequest(data);
        }

		public static String addBattle(String name) throws UnsupportedEncodingException{
            String data = "command=" + URLEncoder.encode("addBattle","UTF-8");
            data += "&name=" + URLEncoder.encode(name,"UTF-8");
            return executeHttpRequest(data);
		}
        /**
         * Helper function used to communicate with the server by sending/receiving
         * POST commands.
         * @param data String representing the command and (possibly) arguments.
         * @return String response from the server.
         */
        private static String executeHttpRequest(String data) {
                String result = "";
                try {
                        URL url = new URL(SERVER_URL);
                        URLConnection connection = url.openConnection();
                        
                        /*
                         * We need to make sure we specify that we want to provide input and
                         * get output from this connection. We also want to disable caching,
                         * so that we get the most up-to-date result. And, we need to 
                         * specify the correct content type for our data.
                         */
                        connection.setDoInput(true);
                        connection.setDoOutput(true);
                        connection.setUseCaches(false);
                        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                        // Send the POST data
                        DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
                        dataOut.writeBytes(data);
                        dataOut.flush();
                        dataOut.close();

                        // get the response from the server and store it in result
                        BufferedReader dataIn = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
                        String inputLine;
                        while ((inputLine = dataIn.readLine()) != null) {
                                result += inputLine;
                        }
                        dataIn.close();
                } catch (IOException e) {
                        /*
                         * In case of an error, we're going to return a null String. This
                         * can be changed to a specific error message format if the client
                         * wants to do some error handling. For our simple app, we're just
                         * going to use the null to communicate a general error in
                         * retrieving the data.
                         */
                        e.printStackTrace();
                        result = null;
                }

                return result;
        }
}