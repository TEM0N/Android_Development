package an.imation.weatherapp.api

import an.imation.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "3b36d90e724bd340953b075bd124320d"
object WeatherApiProvider {
    private var retrofit: Retrofit = createRetrofit()

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun createClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {chain ->
                val url = chain.request().url
                    .newBuilder()
                    .addQueryParameter("appid", API_KEY)
                    .build()

                val request = chain.request().newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
    }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

    fun getWeatherApi(): WeatherApi = retrofit.create(WeatherApi::class.java)
}