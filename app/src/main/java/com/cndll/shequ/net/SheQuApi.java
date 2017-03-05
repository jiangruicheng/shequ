package com.cndll.shequ.net;


import com.cndll.shequ.bean.GroupImageBean;
import com.cndll.shequ.bean.LoadUpResponse;
import com.cndll.shequ.bean.RequestGroupImage;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by jiang_ruicheng on 16/10/27.
 */
public interface SheQuApi {
    @POST("/shequ/public/index.php/app")
    Observable<GroupImageBean> downloadImage(@Body RequestGroupImage image);

    @Multipart
    @POST("/shequ/public/index.php/app")
    Observable<LoadUpResponse> uploadImage(@PartMap Map<String, RequestBody> pa);
}