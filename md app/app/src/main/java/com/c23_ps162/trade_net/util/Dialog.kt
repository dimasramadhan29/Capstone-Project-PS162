package com.c23_ps162.trade_net.util

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showExitConfirmationDialog() {
    val dialog = AlertDialog.Builder(requireContext())
        .setTitle("Konfirmasi Keluar")
        .setMessage("Anda yakin ingin keluar dari aplikasi?")
        .setPositiveButton(
            "Batal"
        ) { d, btn ->
            if (btn == DialogInterface.BUTTON_POSITIVE)
                d.dismiss()
        }
        .setNegativeButton("Keluar") { d, btn ->
            if (btn == DialogInterface.BUTTON_NEGATIVE) {
                d.dismiss()
                activity?.moveTaskToBack(false)
            }
        }

    dialog.show()
}