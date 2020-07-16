package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Getsignature extends AppCompatActivity {
    Button NextBtn;
    private Button btnClear, btnSave;
    private File file;
    private LinearLayout canvasLL;
    private View view;
    private signature mSignature;
    private Bitmap bitmap;
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Documents/";
    //String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath,eidstring ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getsignature);

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)) {
            canvasLL = (LinearLayout) findViewById(R.id.canvasll);
            mSignature = new signature(getApplicationContext(), null);
            mSignature.setBackgroundColor(Color.WHITE);
            // Dynamically generating Layout through java code
            canvasLL.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            btnClear = (Button) findViewById(R.id.btnclear);
            btnSave = (Button) findViewById(R.id.btnsave);
            view = canvasLL;
            eidstring=getIntent().getExtras().getString("eidnumber");
            //add emirates id here
            StoredPath= DIRECTORY + eidstring + ".png";

            NextBtn=(Button)findViewById(R.id.gotouploaddoc);

            NextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent GotoSelectionActivity=new Intent(Getsignature.this,Upload_Documents.class);
                    GotoSelectionActivity.putExtra("eidnumber",eidstring);
                    startActivity(GotoSelectionActivity);
                    finish();
                }
            });


            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSignature.clear();
                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    view.setDrawingCacheEnabled(true);
                    mSignature.save(view,StoredPath);
                    Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();

                }
            });

            // Method to create Directory, if the Directory doesn't exists
            file = new File(DIRECTORY);
            if (!file.exists()) {
                file.mkdir();
            }
        }else{
            Intent gotologinscreen=new Intent(Getsignature.this,NoInternet.class);
            startActivity(gotologinscreen);
        }



    }

    public class signature extends View {

        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();
        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public signature(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v, String StoredPath) {
            Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(canvasLL.getWidth(), canvasLL.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmap);
            try {
                // Output the file
                FileOutputStream mFileOutStream = new FileOutputStream(StoredPath);
                v.draw(canvas);

                // Convert the output file to Image such as .png
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
                mFileOutStream.flush();
                mFileOutStream.close();

            } catch (Exception e) {
                Log.v("log_tag", e.toString());
            }

        }

        public void clear() {
            path.reset();
            invalidate();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - signature.HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - signature.HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + signature.HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + signature.HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string) {

            Log.v("log_tag", string);

        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }
}
