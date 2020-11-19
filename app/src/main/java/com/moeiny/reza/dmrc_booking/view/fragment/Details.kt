package com.moeiny.reza.dmrc_booking.view.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.moeiny.reza.dmrc_booking.model.DataSource
import com.moeiny.reza.dmrc_booking.GraphProcessor
import com.moeiny.reza.dmrc_booking.model.Node

import com.moeiny.reza.dmrc_booking.R
import java.io.File
import java.io.FileOutputStream


class Details(var start: Node?, var destination: Node?, var result: SearchResult) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var processor = GraphProcessor(DataSource.nodes, DataSource.arcs);
        var r = processor.process(start!!.id, destination!!.id)
        var sb = StringBuffer()
        var totalCost = 0
        for (n in r) {
            sb.append(n.name).append(", ")
            if (n.isExchange) {
                sb.append("5.00")
                totalCost += 5
            } else {
                sb.append("2.00")
                totalCost += 2
            }
            sb.append("\n")
        }
        sb.append("total cost = ").append(totalCost)
        val inflate = inflater.inflate(R.layout.fragment_details, container, false)
        var info = inflate.findViewById<TextView>(R.id.info)
        info.text = sb.toString()
        var close = inflate.findViewById<Button>(R.id.close)
        close.setOnClickListener {
            result.onFail()
        }
        var share = inflate.findViewById<Button>(R.id.share)
        share.setOnClickListener {
            val cachePath = File(context!!.cacheDir, "images")
            cachePath.mkdirs();
            var imgFile = File(cachePath, "img1.jpg")
            var bitmap = Bitmap.createBitmap(info.getWidth(), info.getHeight(), Bitmap.Config.ARGB_8888)
            var canvas = Canvas(bitmap)
            info.draw(canvas);
            //canvas.drawText()
            var fos =FileOutputStream (imgFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, fos)
            fos.close()

            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            val uriShareFile: Uri = Uri.fromFile(imgFile)
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriShareFile)
            shareIntent.type = "*/*"
            activity!!.startActivity(Intent.createChooser(shareIntent, "Share Ticket"))
        }
        return inflate
    }

}
