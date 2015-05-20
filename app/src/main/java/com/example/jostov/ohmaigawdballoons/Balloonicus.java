package com.example.jostov.ohmaigawdballoons;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Random;

public class Balloonicus extends Activity{
    int height, width = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balloonicus);
        final FrameLayout fl = (FrameLayout)findViewById(R.id.balloonSpace);
        fl.addView(new Balloon(this), 0);
        Button b1 = (Button) findViewById(R.id.addBalloon);
        b1.setOnClickListener(new View.OnClickListener() {
            Balloon b = (Balloon) fl.getChildAt(0);
            @Override
            public void onClick(View v) {
                b.addBalloon();
                b.invalidate();
            }
        });
        Button b2 = (Button) findViewById(R.id.popBalloons);
        b2.setOnClickListener(new View.OnClickListener() {
            Balloon b = (Balloon) fl.getChildAt(0);
            @Override
            public void onClick(View v) {
                b.popBalloons();
                b.invalidate();
            }
        });
    }
    protected int getDimens(){
        if (height <= 0){
            height = findViewById(R.id.balloonSpace).getHeight();
        }
        return height;
    }
    protected int getWidth(){
        if (width <= 0){
            width = findViewById(R.id.balloonSpace).getWidth();
        }
        return width;
    }
    private class Balloon extends View
    {
        private Random random = new Random();
        private Paint paint = new Paint();
        private ArrayList<int[]> balloons = new ArrayList<>();
        public Balloon(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            for (int[] each : balloons){
                drawBALLOON(canvas, each);
            }
        }
        protected int[] gimmeBALLOON(){
            int[] ball = new int[4];
            ball[0] = random.nextInt(200);
            ball[1] = ball[0] + random.nextInt(getHeight()- 2  * ball[0]);
            ball[2] = ball[0] + random.nextInt(getWidth()-2 * ball[0]);
            ball[3] = random.nextInt(255255255);
            return ball;
        }
        protected void drawBALLOON(Canvas canvas, int[] ball){
            paint.setColor(Color.argb(255, (ball[3] % 1000), ((ball[3]/1000) % 1000), (ball[3]/1000000)%1000));
            canvas.drawCircle(ball[2], ball[1], ball[0], paint);
        }
        public void addBalloon(){
            balloons.add(gimmeBALLOON());
        }
        public void popBalloons(){
            balloons = new ArrayList<>();
        }
    }
}
