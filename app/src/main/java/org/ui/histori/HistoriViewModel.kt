package org.ui.histori

import androidx.lifecycle.ViewModel
import org.db.GajiDao

class HistoriViewModel(db: GajiDao) : ViewModel() {
    val data = db.getLastGaji()
}