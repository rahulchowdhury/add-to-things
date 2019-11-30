package co.rahulchowdhury.addtothings.di.module

import co.rahulchowdhury.addtothings.BuildConfig
import co.rahulchowdhury.addtothings.data.source.remote.MailgunApiService
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideMailgunApiService(get()) }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return httpLoggingInterceptor
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()

    // Logging interceptor
    okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)

    // Add basic authentication credentials
    okHttpClientBuilder.addInterceptor { chain ->
        val basicAuthCredentials = Credentials.basic(
            "api",
            BuildConfig.MAILGUN_API_KEY
        )
        val request = chain.request()
            .newBuilder()
            .header("Authorization", basicAuthCredentials)
            .build()

        chain.proceed(request)
    }

    return okHttpClientBuilder.build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.mailgun.net/v3/${BuildConfig.MAILGUN_DOMAIN}")
        .build()

fun provideMailgunApiService(
    retrofit: Retrofit
): MailgunApiService = retrofit.create(MailgunApiService::class.java)
