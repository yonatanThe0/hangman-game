package com.example.hangman7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class HangmanView extends View {
    private Paint p;
    private int step = 0,i = 0;
    private final Handler handler = new Handler();


    public HangmanView(Context context) {
        super(context);
        init();
    }
    private void init() {
        p = new Paint();
        p.setStrokeWidth(10);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the base
        canvas.drawLine(100, 500, 300, 500, p); // Base
        canvas.drawLine(200, 500, 200, 100, p); // Vertical pole
        canvas.drawLine(200, 100, 400, 100, p); // Top pole
        canvas.drawLine(400, 100, 400, 150, p); // Rope

        // Draw hangman step by step
        if (step > 1) canvas.drawCircle(400, 200, 50, p); // Head
        if (step > 2) canvas.drawLine(400, 250, 400, 400, p); // Body
        if (step > 3) canvas.drawLine(400, 300, 350, 350, p); // Left Arm
        if (step > 4) canvas.drawLine(400, 300, 450, 350, p); // Right Arm
        if (step > 5) canvas.drawLine(400, 400, 350, 500, p); // Left Leg
        if (step > 6) canvas.drawLine(400, 400, 450, 500, p); // Right Leg
        if (step > 7) canvas.drawLine(380, 180, 420, 180, p); // eyes

        if (step==1){
            canvas.drawCircle(400, 200, i, p); // Head
            animationCircle(i,50);
        }

        if (step==2) {
            canvas.drawLine(400, 250, 400, 250+i, p);
            animationLine(i, 150);
        }
        if (step==3){
            canvas.drawLine(400,300,400-i,300+i,p);
            animationLine(i,50);
        }
        if (step==4){
            canvas.drawLine(400, 300, 400+i, 300+i, p);
            animationLine(i,50);
        }
        if (step == 5) {
            canvas.drawLine(400,400,400-i,400 +2*i,p);
            animationLine(i,50);
        }
        if (step == 6) {
            canvas.drawLine(400,400,400+i,400+2*i,p);
            animationLine(i,50);
        }
    }

    private void animationLine (int i1,int d){
        if (i1< d){
            i++;
            handler.postDelayed(() -> invalidate(), 1);
        }
    }
    private void animationCircle(int i1,int r) {
        if (i1< r){
            i++;
            handler.postDelayed(() -> invalidate(), 1);
        }
    }

    public void setWrongGuesses(int mistake) {
        this.step = mistake; // Update the number of mistakes
        i=0;
        invalidate(); // Redraw the view
    }
}
