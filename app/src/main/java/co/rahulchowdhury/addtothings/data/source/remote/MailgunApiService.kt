package co.rahulchowdhury.addtothings.data.source.remote

import co.rahulchowdhury.addtothings.data.model.remote.MailgunResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MailgunApiService {
    @POST("/messages")
    @FormUrlEncoded
    suspend fun sendEmail(
        @Field("from") from: String,
        @Field("to") to: String,
        @Field("subject") subject: String,
        @Field("text") message: String
    ): MailgunResponse
}
