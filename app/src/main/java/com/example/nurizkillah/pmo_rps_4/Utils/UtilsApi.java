package com.example.nurizkillah.pmo_rps_4.Utils;

import com.example.nurizkillah.pmo_rps_4.RetrofitClient.RetroFitApiClient;

public class UtilsApi {
    public static final String BASE_URL_API ="http://www.noobdev.biz/index.php/Api/pmo2/";
    //public static final String BASE_URL_API ="http://www.noobjr.tk/index.php/";

    //deklarasi apiinterface
   public static InterfaceApi getApiSerivce() {
       return RetroFitApiClient.getClient(BASE_URL_API).create(InterfaceApi.class);

   }
}
