package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsapp.models.Article


@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase(){

    abstract fun getArticleDao():ArticleDao

    companion object{
        @Volatile //volatile annotation means one changes made by one thread immediatly visible by other thread.
        private var instance:ArticleDatabase?=null

        private val LOCK=Any()

        private fun createDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()

        operator fun invoke(context: Context)= instance?:synchronized(LOCK){
            instance?:createDatabase(context).also {
                instance=it
            }
        }
    }
}