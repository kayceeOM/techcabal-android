package onyekachi.me.techcabal;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Onyekachy on 05/01/2015.
 */
public interface TechcabalService
{
    @GET("/")
    void response(Callback<Response> cb);
}
