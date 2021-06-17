package org.db

import androidx.room.Entity

@Entity(tableName = "gaji")
data class GajiEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jam: Float,
    var jabatan: Float,
    var isMale: Boolean
)
