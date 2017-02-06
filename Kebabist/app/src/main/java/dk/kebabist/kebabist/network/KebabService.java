package dk.kebabist.kebabist.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by LeFanous on 06-02-2017.
 */

public interface KebabService {
    @GET("pullRequest.php")
    Call<List<KebabEntry>> listKebabPlaces();
}
