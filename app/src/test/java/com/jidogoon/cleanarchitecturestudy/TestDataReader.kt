package com.jidogoon.cleanarchitecturestudy

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jidogoon.cleanarchitecturestudy.data.Photo
import java.io.File
import java.lang.reflect.Type
import java.nio.charset.Charset

class TestDataReader {
    var photosData: List<Photo>
    var photoData: Photo

    init {
        val defaultPath = "testdata"
        val secondaryPath = "../testdata"

        val listType = object : TypeToken<List<Photo>>() {}.type
        photosData = loadData("photos.json", defaultPath, secondaryPath, listType)
        photoData = loadData("photo_01.json", defaultPath, secondaryPath, Photo::class.java)
    }

    private fun <T> loadData(fileName: String, defaultPath: String, secondaryPath: String, clazz: Type): T {
        var jsonFile = File("$defaultPath/$fileName")
        if (!jsonFile.exists())
            jsonFile = File("$secondaryPath/$fileName")
        val jsonData = jsonFile.readText(Charset.defaultCharset())

        val gson = GsonBuilder().create()
        return gson.fromJson(jsonData, clazz)
    }
}