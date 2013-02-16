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

package org.moresbycoffee.measteregg.android.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Egg {

    private double lat;
    private double lon;
    private boolean found;

    public Egg() {
    }

    public Egg(double lat, double lon, boolean found) {
        this.lat = lat;
        this.lon = lon;
        this.found = found;
    }

    public static Egg createFromJson(JSONObject json) throws JSONException {
        Egg egg = new Egg();
        egg.lat = json.getDouble("lat");
        egg.lon = json.getDouble("lon");
        egg.found = json.getBoolean("found");
        return egg;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (found ? 1231 : 1237);
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Egg other = (Egg)obj;
        if (found != other.found)
            return false;
        if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
            return false;
        if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Egg [lat=" + lat + ", lon=" + lon + ", found=" + found + "]";
    }
}
