package co.rahulchowdhury.addtothings.data.model.remote

import com.google.gson.annotations.SerializedName

data class MailgunResponse(
    @SerializedName("id") val id: String,
    @SerializedName("message") val message: String
)
