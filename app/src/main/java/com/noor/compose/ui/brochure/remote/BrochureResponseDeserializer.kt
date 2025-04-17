package com.noor.compose.ui.brochure.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BrochureResponseDeserializer : JsonDeserializer<BrochureResponse.EmbeddedContent> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BrochureResponse.EmbeddedContent {
        val jsonObject = json!!.asJsonObject

        val contentType = jsonObject["contentType"].asString
        val contentElement = jsonObject["content"]

        val contentList = mutableListOf<BrochureResponse.BrochureContent>()

        if (contentElement.isJsonArray) {
            contentElement.asJsonArray.forEach {
                val content = context!!.deserialize<BrochureResponse.BrochureContent>(it.asJsonObject["content"], BrochureResponse.BrochureContent::class.java)
                contentList.add(content)
            }
        } else if (contentElement.isJsonObject) {
            val content = context!!.deserialize<BrochureResponse.BrochureContent>(contentElement, BrochureResponse.BrochureContent::class.java)
            contentList.add(content)
        }

        return BrochureResponse.EmbeddedContent(
            contentType = contentType,
            content = contentList
        )
    }
}