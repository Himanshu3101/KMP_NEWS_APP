package org.himanshu.kmp_news.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.himanshu.kmp_news.data.model.Source

class SourceTypeConverter {

    @TypeConverter
    fun fromSourceToString(value : Source) : String{
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToSource(value : String) : Source{
        return Json.decodeFromString(value)
    }

}