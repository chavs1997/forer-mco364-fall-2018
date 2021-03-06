/*
package forer.earthquake.net;

import java.io.IOException;

import forer.earthquake.EarthquakeFeed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthquakeRetrofitClient {
    public static void main(String[] args) throws IOException {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://earthquake.usgs.gov")
                .addConverterFactory(GsonConverterFactory.create()).build();
        USGSEarthquakeService service = retrofit.create(USGSEarthquakeService.class);

        Call<EarthquakeFeed> call = service.getAllMonth();
        call.enqueue(new Callback<EarthquakeFeed>() {

            @Override
            public void onFailure(Call<EarthquakeFeed> call, Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onResponse(Call<EarthquakeFeed> call, Response<EarthquakeFeed> response) {
                EarthquakeFeed feed = response.body();

                System.out.println(feed.getFeatures().stream().filter(e -> e.getProperties().getMag() >= 3).count());
                System.exit(0);
            }

        });

    }

}*/
