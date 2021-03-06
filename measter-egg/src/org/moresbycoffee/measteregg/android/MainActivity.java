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

package org.moresbycoffee.measteregg.android;

import com.google.android.gms.maps.model.LatLng;

import org.moresbycoffee.measteregg.android.comm.ApiCaller;
import org.moresbycoffee.measteregg.android.comm.ApiCaller2;
import org.moresbycoffee.measteregg.android.comm.EggApi;
import org.moresbycoffee.measteregg.android.comm.EggApi.GetEggsCallback;
import org.moresbycoffee.measteregg.android.entity.Egg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void getEggs(View view) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                ApiCaller2 apiCaller = new ApiCaller2();
                EggApi eggApi = new EggApi(apiCaller);
                eggApi.getEggs(new LatLng(1, 1), "userId", new GetEggsCallback() {

                    @Override
                    public void onComplete(List<Egg> eggs) {
                        for (Egg egg : eggs) {
                            Log.i("BB", "egg:" + egg.toString());
                        }
                    }
                });
            }

        }).start();
    }
}
