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

public class WHAT_AM_I_GONNA_DO_WITH_ALL_THESE_BALLOONS extends Activity{
    int height = -1;
    int width = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what__am__i__gonna__do__with__all__these__balloons);
        final FrameLayout fl = (FrameLayout)findViewById(R.id.balloonSpace);
        fl.addView(new BALLOONS(this), 0);
        Button b1 = (Button) findViewById(R.id.addBalloon);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BALLOONS b = (BALLOONS)fl.getChildAt(0);
                b.addBalloon();
                b.invalidate();
            }
        });
        Button b2 = (Button) findViewById(R.id.popBalloons);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BALLOONS b = (BALLOONS) fl.getChildAt(0);
                b.POP_ALL_THE_BALLOONS_OH_NO_PARTY_IS_OVER_NOW();
                b.invalidate();
            }
        });
    }
    protected int getHeight(){
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
    private class BALLOONS extends View
    {
        private Random random;
        private Paint paint;
        private ArrayList<int[]> balloons = new ArrayList<>();//LET THE GAMES BEGIN!
        public BALLOONS(Context context){
            super(context);
            random = new Random();
        }
        //WHAT_AM_I_GONNA_DO_WITH_ALL_THESE_BALLOONS.this.getWidth()/getHeight()
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            paint = new Paint();
            for (int[] each : balloons){
                drawBALLOON(canvas, each);
            }
        }
        protected int[] gimmeBALLOON(){//I think I'm going to start leaving really emotional comments
            int[] BALLOON = new int[4]; //Like, I was really proud of the next bit.
            BALLOON[0] = random.nextInt(200);//I have no right to be, really. But I rationalized it as being data efficient
            BALLOON[1] = BALLOON[0] + random.nextInt(WHAT_AM_I_GONNA_DO_WITH_ALL_THESE_BALLOONS.this.getHeight()- 2  * BALLOON[0]); //It's not data efficient.
            BALLOON[2] = BALLOON[0] + random.nextInt(WHAT_AM_I_GONNA_DO_WITH_ALL_THESE_BALLOONS.this.getWidth()-2 * BALLOON[0]); //It's not data efficient.
            BALLOON[3] = random.nextInt(255255255);//It's not even java appropriate.
            return BALLOON; //SO WHAT IF MY BALLOONS ARE SHITTY THE COLORS ARE 3* AS DATA EFFICIENT!
        }
        protected int[] gimmeCOLOR(int somecolor){
            int[] color = new int[3];
            color[0] = somecolor - (somecolor / 1000);
            color[1] = color[0] - (color[0]/ 1000);
            color[2] = color[1] - (color[1]/ 1000);
            return color;
        }
        protected void drawBALLOON(Canvas canvas, int[] BALLOON){
            int[] color = gimmeCOLOR(BALLOON[3]);
            paint.setColor(Color.argb(255, color[0], color[1], color[2]));
            canvas.drawCircle(BALLOON[2], BALLOON[1], BALLOON[0], paint);
        }
        public void addBalloon(){
            balloons.add(gimmeBALLOON());
        }
        public void POP_ALL_THE_BALLOONS_OH_NO_PARTY_IS_OVER_NOW(){
            balloons = new ArrayList<>();
        }
    }
}
