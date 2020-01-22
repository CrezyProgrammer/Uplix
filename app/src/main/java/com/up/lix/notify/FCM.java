package com.up.lix.notify;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by CodeSlu on 9/4/2018.
 */

public interface FCM {
    @Headers({

            "Content-Type: application/json",
            "Authorization:key=AAAA7d0agEE:APA91bGfwNEbpcKF6kW4_L5-T9sfBaTTfsJSJd-7E4wqA2JuPoxTV2J7sP9cjIdbPUlugR7h5LgySChHnLHu4PvFE-nFESbQ2NsR52Vsa9d2bD4wUa4L5D7HyVY1Di6iQulOuhcfNggR"
    })
    @POST("fcm/send")
    Call<FCMresp> send(@Body Sender body);
}
