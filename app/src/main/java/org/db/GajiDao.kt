package org.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface GajiDao {
    @Insert
    fun insert(gaji: GajiEntity)
    @Query("SELECT * FROM gaji ORDER BY id DESC")
    fun getLastGaji(): LiveData<List<GajiEntity>>
}