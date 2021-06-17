package org.ui.histori

import androidx.lifecycle.ViewModel
import org.db.GajiDao

class HistoriViewModel(db: GajiDao, jam: Int) : ViewModel() {
    val jamHr = jam
    val gaji = jamHr * 1000000


}