package dk.kebabist.kebabist.Service;

import java.util.List;

import dk.kebabist.kebabist.Model.Kebabbutikker;
import retrofit.Call;
import retrofit.http.GET;

public interface DatabaseService {

    @GET("ajjawi/Kebabist/Database/pullRequest.php")
    Call<List<Kebabbutikker>> getShopDetails();
}
