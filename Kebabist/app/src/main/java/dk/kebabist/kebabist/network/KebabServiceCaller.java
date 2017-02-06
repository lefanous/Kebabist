package dk.kebabist.kebabist.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LeFanous on 06-02-2017.
 */

public class KebabServiceCaller {
    KebabService service;

    public KebabServiceCaller() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ayg.dk/ajjawi/Kebabist/Database/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(KebabService.class);
    }

    public KebabService getService() {
        return service;
    }

    public void setService(KebabService service) {
        this.service = service;
    }
}
