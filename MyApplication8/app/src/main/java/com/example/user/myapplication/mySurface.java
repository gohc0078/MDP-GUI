package com.example.user.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static com.example.user.myapplication.Constants.ARENA_PLACING_ROBOT;
import static com.example.user.myapplication.Constants.ARENA_ROBOT_SIZE_COLUMN;
import static com.example.user.myapplication.Constants.ARENA_ROBOT_SIZE_ROW;

public class mySurface extends SurfaceView {
   private Bitmap icon;
   private SurfaceHolder holder;

    private int CELL_HEIGHT;
    private int CELL_WIDTH;
    private Cells[][] cells;

    private static Robot robot;

    int ARENA_ROBOT_SIZE_COLUMN = 3;
    int ARENA_ROBOT_SIZE_ROW = 3;
    int ARENA_COLUMN_COUNT = 20;
    int ARENA_ROW_COUNT = 15;


    int MIN_HEIGHT = 0;
    int MAX_HEIGHT;
    int MIN_WIDTH = 0;
    int MAX_WIDTH;


    public static int ROBOT_WIDTH;
    public static int ROBOT_HEIGHT;


    public static int ROBOT_WIDTH_OFFSET;
    public static int ROBOT_HEIGHT_OFFSET;



    public mySurface(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                MIN_HEIGHT = 0;
                MAX_HEIGHT = getHolder().getSurfaceFrame().height();
                MIN_WIDTH = 0;
                MAX_WIDTH = getHolder().getSurfaceFrame().width();

                //  Cell dimensions
                CELL_HEIGHT = (MAX_HEIGHT - MIN_HEIGHT) / ARENA_ROW_COUNT;
                CELL_WIDTH = (MAX_WIDTH - MIN_WIDTH) / ARENA_COLUMN_COUNT;

                //  Grid cells
                cells = new Cells[ARENA_ROW_COUNT][ARENA_COLUMN_COUNT];
                for (int i = 0; i < ARENA_ROW_COUNT; i++) {
                    for (int j = 0; j < ARENA_COLUMN_COUNT; j++) {
                        int x = (CELL_WIDTH / 2) + (j * CELL_WIDTH);
                        int y = (CELL_HEIGHT / 2) + (i * CELL_HEIGHT);
                        cells[i][j] = new Cells(x, y, CELL_HEIGHT, CELL_WIDTH, i, j);
                    }
                }


                ROBOT_WIDTH = CELL_WIDTH * ARENA_ROBOT_SIZE_COLUMN;
                ROBOT_HEIGHT = CELL_HEIGHT * ARENA_ROBOT_SIZE_ROW;
                ROBOT_WIDTH_OFFSET = ROBOT_WIDTH / 2;
                ROBOT_HEIGHT_OFFSET = ROBOT_HEIGHT / 2;




                robot = new Robot(cells[1][1].getX(),cells[1][1].getY(),ROBOT_WIDTH_OFFSET,ROBOT_HEIGHT_OFFSET,90);
                 Canvas c = holder.lockCanvas(null);
                  onDraw(c);
                holder.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


    }

@Override
   public void onDraw(Canvas canvas)
   {
       super.onDraw(canvas);
       canvas.drawColor(Color.WHITE);

       if (cells != null) {
           for (int i = 0; i < ARENA_ROW_COUNT; i++) {
               for (int j = 0; j < ARENA_COLUMN_COUNT; j++) {
                   cells[i][j].draw(canvas);
               }
           }
       }

       if (robot != null)
       {
           robot.draw(canvas);
       }





   }








}
