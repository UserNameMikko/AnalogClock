package com.mikko.clock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Suppress("DEPRECATION")
class AnalogClockView : View {
    private var currentHeight = 0
    private var currentWidth = 0
    private var padding = 0
    private var fontSize = 0
    private val numeralSpacing = 0
    private var handTruncation = 0
    private var hourHandTruncation = 0
    private var radius = 0
    private var paint: Paint? = null
    private var isInit = false
    private val numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private val rect = Rect()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    private fun init() {
        currentHeight = height
        currentWidth = width
        padding = numeralSpacing + 50
        fontSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 13f,
            resources.displayMetrics
        ).toInt()
        val min = min(currentHeight, currentWidth)
        radius = min / 2 - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        paint = Paint()
        isInit = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            init()
        }
        drawCircle(canvas)
        drawCenter(canvas)
        drawNumeral(canvas)
        drawHands(canvas)
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawCircle(canvas: Canvas) {
        paint!!.reset()
        paint!!.color = resources.getColor(R.color.white)
        paint!!.strokeWidth = 5f
        paint!!.style = Paint.Style.STROKE
        paint!!.isAntiAlias = true
        canvas.drawCircle(
            (currentWidth.toFloat() / 2),
            (currentHeight.toFloat() / 2),
            (radius.toFloat() + padding - 10),
            paint!!
        )
    }

    private fun drawCenter(canvas: Canvas) {
        paint!!.style = Paint.Style.FILL
        canvas.drawCircle(
            (currentWidth.toFloat() / 2),
            (currentHeight.toFloat() / 2),
            12f, paint!!
        )
    }

    private fun drawNumeral(canvas: Canvas) {
        paint!!.textSize = fontSize.toFloat()
        for (number in numbers) {
            val tmp = number.toString()
            paint!!.getTextBounds(tmp, 0, tmp.length, rect)
            val angle = Math.PI / 6 * (number - 3)
            val x = (currentWidth / 2 + cos(angle) * radius - rect.width() / 2)
            val y = (currentHeight / 2 + sin(angle) * radius + rect.height() / 2)
            canvas.drawText(tmp, x.toFloat(), y.toFloat(), paint!!)
        }
    }

    private fun drawHands(canvas: Canvas) {
        val c = Calendar.getInstance()
        var hour = c[Calendar.HOUR_OF_DAY].toFloat()
        Log.i("Date", "${c.get(Calendar.HOUR_OF_DAY)} " +
                "|| ${c.get(Calendar.MINUTE)} || ${c.get(Calendar.SECOND)}")
        hour = if (hour >= 12) hour - 12 else hour
        drawHand(canvas, (hour + c[Calendar.MINUTE] / 60) * 5.toDouble(),
            isHour = true,
            isSecond = false
        )
        drawHand(canvas, c[Calendar.MINUTE].toDouble(), isHour = false, isSecond = false)
        drawHand(canvas, c[Calendar.SECOND].toDouble(), isHour = false, isSecond = true)
    }

    private fun drawHand(canvas: Canvas, time: Double, isHour: Boolean, isSecond: Boolean) {
        val angle = Math.PI * time / 30 - Math.PI / 2
        val handRadius =
            if (isHour) radius - handTruncation - hourHandTruncation else radius - handTruncation
        Log.i("cos(angle)*handRadius"," ${cos(angle) * handRadius}")
        if (isSecond){ // drawing second hand by red color
            paint!!.color = Color.RED
        }
        if (isHour) { // changing the thickness of the hour hand
            paint!!.strokeWidth = 10f
        }
        canvas.drawLine(
            (currentWidth / 2).toFloat(),
            (currentHeight / 2).toFloat(),
            (currentWidth / 2 + cos(angle) * handRadius).toFloat(),
            (currentHeight / 2 + sin(angle) * handRadius).toFloat(),
            paint!!
        )
        paint!!.color = Color.WHITE
        paint!!.strokeWidth = 5f
    }
}