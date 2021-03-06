/*
 * Moresby Coffee Bean
 *
 * Copyright (coffee) ${year}, Barnabas Sudy (barnabas.sudy@gmail.com)
 * Copyright (coffee) ${year}, Balazs Balazs (balazs.balazs80@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * � � * Redistributions of source code must retain the above copyright
 * � � � notice, this list of conditions and the following disclaimer.
 * � � * Redistributions in binary form must reproduce the above copyright
 * � � � notice, this list of conditions and the following disclaimer in the
 * � � � documentation and/or other materials provided with the distribution.
 * � � * Neither the name of the <organization> nor the
 * � � � names of its contributors may be used to endorse or promote products
 * � � � derived from this software without specific prior written permission.
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

import com.google.android.gms.maps.model.LatLng;
import com.turbomanage.httpclient.AsyncCallback;
import com.turbomanage.httpclient.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.moresbycoffee.measteregg.android.entity.Egg;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EggApi {
    public interface GetEggsCallback {
        public void onComplete(List<Egg> eggs);
    }

    private final static String TAG = EggApi.class.getSimpleName();

    private ApiCaller2 mApiCaller;

    public EggApi(ApiCaller2 apiCaller) {
        mApiCaller = apiCaller;
    }

    public void getEggs(LatLng location, String userId, final GetEggsCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("lat", Double.toString(location.latitude));
        params.put("lon", Double.toString(location.longitude));
        params.put("userId", userId);
        mApiCaller.makeRequest("eggs", params, new AsyncCallback() {
            @Override
            public void onComplete(HttpResponse httpResponse) {
                Log.i("BB", "response: " + httpResponse.getBodyAsString());
                List<Egg> eggs = new ArrayList<Egg>();
                try {
                    JSONArray root = new JSONArray(httpResponse.getBodyAsString());
                    for (int i = 0; i < root.length(); i++) {
                        Egg egg = Egg.createFromJson(root.getJSONObject(i));
                        eggs.add(egg);
                    }
                    callback.onComplete(eggs);
                } catch (JSONException e) {
                    Log.i("BB", "Error", e);
                }
            }
        });
    }
}
