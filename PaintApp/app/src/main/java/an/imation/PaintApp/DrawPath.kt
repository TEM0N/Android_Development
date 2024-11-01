package an.imation.PaintApp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class DrawPath @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val path = Path()
    private val pathList = ArrayList<Path>()
    private val paintBrush = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 16f
        alpha = 0xff
    }

    private var mX = 0f
    private var mY = 0f

    private fun touchStart(x: Float, y: Float) {
        path.reset()
        path.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = abs(x - mX)
        val dy = abs(y - mY)
        if (dx >= 4f || dy >= 4f) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
    }

    private fun touchUp() {
        path.lineTo(mX, mY)
        pathList.add(Path(path))
    }

    fun clearLines() {
        pathList.clear()
        path.reset()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        for (p in pathList) {
            canvas.drawPath(p, paintBrush)
        }
        canvas.drawPath(path, paintBrush)
    }
}