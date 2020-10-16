package com.example.surfaceexample

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class MainActivity : AppCompatActivity() {
    //Created 06.10.2020 by Pavel Maltsev

    private var TAG: String = "Main activity"
    private var myContext: Context = this
    private val playing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Anim().start()
    }



    inner class Anim : Thread(), SurfaceHolder.Callback {

        var counter = 0
        var img_ids: IntArray = intArrayOf
	(
                R.drawable.dancer_1,
                R.drawable.dancer_2,
                R.drawable.dancer_3,
                R.drawable.dancer_4,
                R.drawable.dancer_5,
                R.drawable.dancer_6
	)


        override fun run() {
            var lastTimeUpdated: Long = 0
            val delay: Long = 100

            while (true) {

                if (playing) {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime >= lastTimeUpdated + delay) {
                        if (counter >= 6) {
                            counter = 0
                        }
                        draw()
                        lastTimeUpdated = currentTime
                        counter += 1
                    }
                }
            }
        }


        private fun draw() {
            var surfaceView: SurfaceView = findViewById(R.id.surface_view)
            var myHolder: SurfaceHolder = surfaceView.holder
            myHolder.addCallback(this).run {
                var canvas: Canvas? = myHolder.lockCanvas()
                if (canvas != null) {
                    canvas.drawColor(Color.WHITE)
                    var paint = Paint()
                    var bitmap: Bitmap = BitmapFactory.decodeResource(myContext.resources, img_ids[counter])
                    canvas.drawBitmap(bitmap, 1f, 1f, paint)
                    myHolder.unlockCanvasAndPost(canvas)
                }
            }

        }


        override fun surfaceCreated(holder: SurfaceHolder) {
            Log.i(TAG, "surfaceCreated entered")
        }

        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            Log.i(TAG, "surfaceChanged created")
        }

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            Log.i(TAG, "surfaceDestroyed entered")
        }

    }

}
