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

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.google.android.gms.maps.model.LatLng;

import org.moresbycoffee.measteregg.android.entity.Egg;

import java.util.List;

import junit.framework.TestCase;

public class EggApiTest extends TestCase {
    public void testOneEgg() {
        ApiCaller apiCaller = mock(ApiCaller.class);
        when(apiCaller.call(anyString(), anyMap())).thenReturn("[{\"lat\":\"12\", \"lon\":\"12\", \"found\":\"true\"}]");
        EggApi api = new EggApi(apiCaller);
        List<Egg> eggs = api.getEggs(new LatLng(12, 12), "something");
        assertEquals(1, eggs.size());
        
    }
}
