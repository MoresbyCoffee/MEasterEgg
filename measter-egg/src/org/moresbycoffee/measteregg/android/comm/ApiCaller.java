/*
 * Moresby Coffee Bean
 *
 * Copyright (coffee) ${year}, Barnabas Sudy (barnabas.sudy@gmail.com)
 * Copyright (coffee) ${year}, Balazs Balazs (balazs.balazs80@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Ê Ê * Redistributions of source code must retain the above copyright
 * Ê Ê Ê notice, this list of conditions and the following disclaimer.
 * Ê Ê * Redistributions in binary form must reproduce the above copyright
 * Ê Ê Ê notice, this list of conditions and the following disclaimer in the
 * Ê Ê Ê documentation and/or other materials provided with the distribution.
 * Ê Ê * Neither the name of the <organization> nor the
 * Ê Ê Ê names of its contributors may be used to endorse or promote products
 * Ê Ê Ê derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.moresbycoffee.measteregg.android.comm;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;

public class ApiCaller {
    private final static String TAG = ApiCaller.class.getSimpleName();
    private static final String ENDPOINT = "http://192.168.0.1";

    public HttpResponse makeRequest(String path, Map params) throws Exception {
        //instantiates httpclient to make request
        DefaultHttpClient httpclient = new DefaultHttpClient();

        //url with the post data
        HttpPost httpost = new HttpPost(path);

        //convert parameters into JSON object
        JSONObject holder = getJsonObjectFromMap(params);

        //passes the results to a string builder/entity
        StringEntity se = new StringEntity(holder.toString());

        //sets the post request as the resulting string
        httpost.setEntity(se);
        //sets a request header so the page receving the request
        //will know what to do with it
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");

        //Handles what is returned from the page 
        ResponseHandler responseHandler = new BasicResponseHandler();
        return httpclient.execute(httpost, responseHandler);
    }

    private JSONObject getJsonObjectFromMap(Map params) throws JSONException {

        //all the passed parameters from the post request
        //iterator used to loop through all the parameters
        //passed in the post request
        Iterator iter = params.entrySet().iterator();

        //Stores JSON
        JSONObject holder = new JSONObject();

        //using the earlier example your first entry would get email
        //and the inner while would get the value which would be 'foo@bar.com' 
        //{ fan: { email : 'foo@bar.com' } }

        //While there is another entry
        while (iter.hasNext()) {
            //gets an entry in the params
            Map.Entry pairs = (Map.Entry)iter.next();

            //creates a key for Map
            String key = (String)pairs.getKey();

            //Create a new map
            Map m = (Map)pairs.getValue();

            //object for storing Json
            JSONObject data = new JSONObject();

            //gets the value
            Iterator iter2 = m.entrySet().iterator();
            while (iter2.hasNext()) {
                Map.Entry pairs2 = (Map.Entry)iter2.next();
                data.put((String)pairs2.getKey(), (String)pairs2.getValue());
            }

            //puts email and 'foo@bar.com'  together in map
            holder.put(key, data);
        }
        return holder;
    }

    public String call(String path, Map<String, String> params) {
        try {
            return makeRequest(ENDPOINT + "/ " + path, params).getEntity().toString();
        } catch (Exception ex) {
            Log.e(TAG, "Exception during call", ex);
            // TODO: error handling
            return null;
        }
    }
}
