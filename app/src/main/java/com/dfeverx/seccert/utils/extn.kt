package com.dfeverx.seccert.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream

fun Bitmap.saveImg(
    context: Context,
    directoryName: String = "dockify",
    quality: Int = 100,
    fileName: String = "tmp_" + System.currentTimeMillis().toString() + ".jpeg"
): Uri? {


    return try {
        val dirPath =
            context.getExternalFilesDir(directoryName)?.absolutePath /*+ "/$directoryName"*/
        Log.d("TAG", "direPath: $dirPath")
//        val dirPath = context.getExternalFilesDir(null)?.absolutePath + "/$directoryName"
        val dir = File(dirPath)

        /*Check directory exits*/
        if (!dir.exists()) {
            val directory =
                File(dirPath)
            directory.mkdirs()
        }

        /*Create file*/
        val file = File(dirPath, fileName)

        if (file.exists()) {
            file.delete()
        }


        val out = FileOutputStream(file)
        this.compress(Bitmap.CompressFormat.JPEG, quality, out)
        out.flush()
        out.close()
        Log.d("TAG", "saveImg: \"$dirPath/$fileName")

        Uri.parse("file://$dirPath/$fileName")

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}