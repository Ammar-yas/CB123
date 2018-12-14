package com.intdv.cb123;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @Headers({"Authorization:Bearer lEcOxg5GoEw.cwA.nAA.YJTjVSPX8c-IxoNRtj0_G9EuDjJYCIjMNiv5-VF5rn4"})
    @POST("conversations")
    Observable<Conversation> startNewConversation();

    @Headers({"Authorization:Bearer lEcOxg5GoEw.cwA.nAA.YJTjVSPX8c-IxoNRtj0_G9EuDjJYCIjMNiv5-VF5rn4", "Content-Type:application/json"})
    @POST("conversations/{conversationId}/activities")
    Observable<Object> sendMessage(@Path("conversationId") String conversationId, @Body Message message);
}
