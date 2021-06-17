package org.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.data.HasilGaji
import org.db.BmiEntity
import org.db.GajiDao
import org.db.GajiEntity

class HitungViewModel(private val db: GajiDao) : ViewModel() {
    // Hasil Gaji bisa null jika pengguna belum menghitung Gaji
    private val hasilGaji = MutableLiveData<HasilGaji?>()
    // Variabel ini sudah berupa LiveData (tidak mutable),
    // sehingga tidak perlu dijadikan private
    val data = db.getLastGaji()
    fun hitungGaji(jam: Int, jabatan: Int, isMale: Boolean) {
        val jamHr = jam * 1000000
        val gaji = jabatan.toFloat() / (jam * jam)
        val kategori = if (isMale) {
            when {
                gaji <=1000000 -> KategoriGaji.UMRRENDAH
                gaji > 1000000 -> KategoriGaji.UMRTINGGI
                else -> KategoriGaji.UMR
            }
        }
        hasilGaji.value = HasilGaji(gaji, kategori)
        val viewModelScope
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataGaji = GajiEntity(
                    jam = jam.toFloat(),
                    jabatan = jabatan.toFloat(),
                    isMale = isMale
                )
                db.insert(dataGaji)
            }
        }
    }
    fun getHasilGaji() : LiveData<HasilGaji?> = hasilGaji
}

class KategoriGaji {
    object UMRRENDAH {

    }

}
