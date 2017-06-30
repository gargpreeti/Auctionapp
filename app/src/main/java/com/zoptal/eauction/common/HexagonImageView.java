package com.zoptal.eauction.common;

/**
 * Created by preeti on 6/26/17.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zoptal.eauction.R;

//public class HexagonImageView extends ImageView {
//    public HexagonImageView(Context context) {
//        super(context);
//    }
//
//    public HexagonImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public HexagonImageView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        Drawable drawable = getDrawable();
//        if (drawable == null || getWidth() == 0 || getHeight() == 0) {
//            return;
//        }
//
//        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
//        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
//
//        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.width); // (width and height of ImageView)
//        Bitmap drawnBitmap = drawCanvas(bitmap, dimensionPixelSize);
//        canvas.drawBitmap(drawnBitmap, 0, 0, null);
//    }
//
//    private Bitmap drawCanvas(Bitmap recycledBitmap, int width) {
//        final Bitmap bitmap = verifyRecycledBitmap(recycledBitmap, width);
//
//        final Bitmap output = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
//        final Canvas canvas = new Canvas(output);
//
//        final Rect rect = new Rect(0, 0, width, width);
//        final int offset = (int) (width / (double) 2 * Math.tan(30 * Math.PI / (double) 180)); // (width / 2) * tan(30deg)
//        final int length = width - (2 * offset);
//
//        final Path path = new Path();
//        path.moveTo(width / 2, 0); // top
//        path.lineTo(0, offset); // left top
//        path.lineTo(0, offset + length); // left bottom
//        path.lineTo(width / 2, width); // bottom
//        path.lineTo(width, offset + length); // right bottom
//        path.lineTo(width, offset); // right top
//        path.close(); //back to top
//
//        Paint paint = new Paint();
//        paint.setStrokeWidth(4);
//        canvas.drawARGB(0, 0, 0, 0);
//        canvas.drawPath(path, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint); // draws the bitmap for the image
//
//        paint.reset();
//        paint.setColor(Color.parseColor("white"));
//        paint.setStrokeWidth(4);
//        paint.setDither(true);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setPathEffect(new CornerPathEffect(10));
//        paint.setAntiAlias(true); // draws the border
//
//        canvas.drawPath(path, paint);
//
//        path.reset();
//        path.moveTo(0, offset + 2); // message so border doesn't over extend
//        path.lineTo(0, offset + length - 2); // massage so border doesn't over extend
//        path.close();
//        paint.setStrokeWidth(8);
//        paint.setPathEffect(new CornerPathEffect(5));
//        canvas.drawPath(path, paint); // draw left border a bit thicker
//
//        path.reset();
//        path.moveTo(width, offset + 2);  // massage so border doesn't over extend
//        path.lineTo(width, offset + length - 2);  // massage so border doesn't over extend
//        path.close();
//        canvas.drawPath(path, paint); // draw right border a bit thicker
//
//        return output;
//    }
//
//    private static Bitmap verifyRecycledBitmap(Bitmap bitmap, int width) {
//        final Bitmap recycledBitmap;
//        if (bitmap.getWidth() != width || bitmap.getHeight() != width) {
//            recycledBitmap = Bitmap.createScaledBitmap(bitmap, width, width, false);
//        } else {
//            recycledBitmap = bitmap;
//        }
//        return recycledBitmap;
//    }
//}

public class HexagonImageView extends ImageView {

    public HexagonImageView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();

        Bitmap roundBitmap = getRoundedCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        Point point1_draw = new Point(150, 0);
        Point point2_draw = new Point(0, 100);
        Point point3_draw = new Point(0, 200);
        Point point4_draw = new Point(150, 300);
        Point point5_draw = new Point(300, 200);
        Point point6_draw = new Point(300, 100);

        Path path = new Path();
        path.moveTo(point1_draw.x, point1_draw.y);
        path.lineTo(point2_draw.x, point2_draw.y);
        path.lineTo(point3_draw.x, point3_draw.y);
        path.lineTo(point4_draw.x, point4_draw.y);
        path.lineTo(point5_draw.x, point5_draw.y);
        path.lineTo(point6_draw.x, point6_draw.y);

        path.close();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }

}

