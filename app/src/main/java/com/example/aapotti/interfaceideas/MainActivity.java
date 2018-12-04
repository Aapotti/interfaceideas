package com.example.aapotti.interfaceideas;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView formula1;
    HorizontalScrollView scroller;
    ConstraintLayout mainLayout;
    LinearLayout popupSubfolder;

    TextView subFolderText1;
    TextView subFolderText2;
    TextView subFolderText3;
    TextView subFolderText4;

    Timer timer;
    TimerTask timerTask;
    Handler handler = new Handler();
    int timerLoopAmount = 0;

    boolean formula1MenuOpen = false;
    boolean readyToOpenF1 = true;
    boolean readyToCloseF1 = false;

    //To start timer
    private void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask()
        {
            public void run() {
                handler.post(new Runnable()
                {
                    public void run()
                    {
                        if(timerLoopAmount == 0)
                        {
                            readyToOpenF1 = false;
                            subFolderText4.animate().alpha(1).setDuration(200);
                            subFolderText4.animate().translationYBy(-200).setDuration(100);
                        }

                        if(timerLoopAmount == 1)
                        {
                            subFolderText3.animate().alpha(1).setDuration(200);
                            subFolderText3.animate().translationXBy(200).setDuration(100);
                        }

                        if(timerLoopAmount == 2)
                        {
                            subFolderText2.animate().alpha(1).setDuration(200);
                            subFolderText2.animate().translationXBy(-200).setDuration(100);
                        }

                        if(timerLoopAmount == 3)
                        {
                            subFolderText1.animate().alpha(1).setDuration(200);
                            subFolderText1.animate().translationYBy(200).setDuration(100);
                        }

                        timerLoopAmount++;

                        if(timerLoopAmount == 5)
                        {
                            stopTimer();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 100, 100);
    }

    //To stop timer
    private void stopTimer()
    {
        timer.cancel();
        timerLoopAmount = 0;
        readyToCloseF1 = true;
    }

    public void formulaSub(View view)
    {
        float placeToInstantiateX = formula1.getTranslationX();
        float laceToInstantiateY = formula1.getTranslationY();

        int scrollX = (view.getLeft() - (mainLayout.getWidth() / 2)) + (view.getWidth() / 2 + 50);

        if(formula1MenuOpen == false && readyToOpenF1)
        {
            scroller.smoothScrollTo(scrollX, 0);
            formula1MenuOpen = true;
            startTimer();
        }
        else if(readyToCloseF1)
        {
            formula1MenuOpen = false;
            disAppearFormulaMenu();
        }
    }

    public void disAppearFormulaMenu()
    {
        subFolderText1.animate().alpha(0).setDuration(100);
        subFolderText1.animate().translationYBy(-200).setDuration(100);

        subFolderText2.animate().alpha(0).setDuration(100);
        subFolderText2.animate().translationXBy(200).setDuration(100);

        subFolderText3.animate().alpha(0).setDuration(100);
        subFolderText3.animate().translationXBy(-200).setDuration(100);

        subFolderText4.animate().alpha(0).setDuration(100);
        subFolderText4.animate().translationYBy(200).setDuration(100);

        readyToOpenF1 = true;
        readyToCloseF1 = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formula1 = (TextView)findViewById(R.id.formula1);
        scroller = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        mainLayout = (ConstraintLayout)findViewById(R.id.mainLayout);
        popupSubfolder = (LinearLayout)findViewById(R.id.popupSubfolder);

        subFolderText1 = (TextView)findViewById(R.id.subFolderText1);
        subFolderText2 = (TextView)findViewById(R.id.subFolderText2);
        subFolderText3 = (TextView)findViewById(R.id.subFolderText3);
        subFolderText4 = (TextView)findViewById(R.id.subFolderText4);

        subFolderText1.setAlpha(0);
        subFolderText1.setTranslationY(-200);

        subFolderText2.setAlpha(0);
        subFolderText2.setTranslationX(200);

        subFolderText3.setAlpha(0);
        subFolderText3.setTranslationX(-200);

        subFolderText4.setAlpha(0);
        subFolderText4.setTranslationY(200);
    }
}
