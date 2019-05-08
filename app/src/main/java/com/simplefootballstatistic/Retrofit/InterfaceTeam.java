package com.simplefootballstatistic.Retrofit;

import com.simplefootballstatistic.Respon.Chart;
import com.simplefootballstatistic.Respon.Rest;
import com.simplefootballstatistic.Respon.RestAddTeam;
import com.simplefootballstatistic.Respon.RestListPemain;
import com.simplefootballstatistic.Respon.RestListTeam;
import com.simplefootballstatistic.Respon.RestPenilaian;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface InterfaceTeam {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("team")
    Observable<RestAddTeam> addTeam(@FieldMap HashMap<String, String> hashMap);

    @GET("pemain/{team_id}")
    Observable<RestListPemain> getListPemain(@Path("team_id") String id_team);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("pemain/{team_id}")
    Observable<Rest> addPemain(@Path("team_id") String id_team,
                               @FieldMap HashMap<String, String> hashMap);

    @GET("team")
    Observable<RestListTeam> getListTeam();

    @DELETE("pemain/{pemain_id}")
    Observable<Rest> deletePemain(@Path("pemain_id")String id_pemain);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @PATCH("pemain/{pemain_id}")
    Observable<Rest> updatePemain(@Path("pemain_id")String id_pemain,
                                  @FieldMap HashMap<String, String> hashMap);


    @GET("nilai/{team_id}")
    Observable<RestPenilaian> getPenilaianTim(@Path("team_id") String id_team);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @PATCH("nilai/{nilai_id}")
    Observable<Rest> updateNilai(@Path("nilai_id")String id_nilai,
                                  @FieldMap HashMap<String, String> hashMap);

    @Streaming
    @GET("print/{team_id}")
    Call<ResponseBody> downloadNilai(@Path("team_id") String id_team);

    @DELETE("team/{team_id}")
    Observable<Rest> deleteTim(@Path("team_id") String id_team);

    @GET("chart/{team_id}")
    Observable<Chart> getChart(@Path("team_id") String id_team);

}
