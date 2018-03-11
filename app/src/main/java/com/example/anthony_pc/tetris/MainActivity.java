package com.example.anthony_pc.tetris;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

import static android.widget.ImageView.ScaleType.CENTER;
import static android.widget.ImageView.ScaleType.FIT_XY;

public class MainActivity extends AppCompatActivity {
    int[][] logicMatrix = new int[20][12];
    GridLayout gridLayout;
    Boolean blockOnCourse = false;
    int blockPlaying = 0;
    View v1 = null;
    String blockColorPlaying = "block_blue";
    int blockState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridLY);
        setMatrix();
        v1 = getWindow().getDecorView().getRootView();
        setPiezaAzul();
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {

                if(blockOnCourse) {
                    Log.e("adsf", "dsf");
                    bajarPieza(null);
                }else{
                    setPiezaAzul();
                }
                handler.postDelayed(this, 500);
            }
        };

        handler.postDelayed(r, 500);

    }

    public void setMatrix(){


        for(int i = 0; i<20; i++){
            for(int j = 0; j<12; j++) {
                if (i == 0 || j == 0 || j == 11 || i == 19) {
                    ImageView imgview = new ImageView(this);
                    imgview.setImageResource(R.drawable.bloque);
                    logicMatrix[i][j] = -1;
                    gridLayout.addView(imgview);
                }else{
                    logicMatrix[i][j] = 1;
                    ImageView imgview = new ImageView(this);
                    imgview.setTag(String.valueOf(i)+","+String.valueOf(j));
                    Log.e("tag",imgview.getTag().toString());
                    gridLayout.addView(imgview);
                }
            }
        }
    }

    public void setAllUnavlbl(){
        for(int i = 19; i>0; i--){
            for(int j = 11; j>0; j--) {
                if(logicMatrix[i][j] == blockPlaying){
                    logicMatrix[i][j] = -1;
                }
            }
        }
    }

    public boolean checkWreckDown(){
        boolean flag = true;
        printLogicMatrix();
        for(int i = 19; i>0; i--){
            for(int j = 11; j>0; j--){
                if(logicMatrix[i][j] == blockPlaying && (logicMatrix[i+1][j] == -1)) {
                    flag = false;
                    setAllUnavlbl();
                }
            }
        }
        if(!flag)
            blockOnCourse = false;
        return flag;
    }

    public boolean checkWreckRight(){
        for(int i = 0; i<20; i++){
            for(int j = 0; j<12; j++) {
                if(logicMatrix[i][j] == blockPlaying && logicMatrix[i][j+1] == -1){
                    return false;}}
        }
        return true;
    }

    public boolean checkWreckLeft(){

         for(int i = 0; i<20; i++){
            for(int j = 0; j<12; j++) {
                if(logicMatrix[i][j] == blockPlaying && logicMatrix[i][j-1] == -1){
                    return false;}}
        }
        return true;
    }

    public void setPiezaAzul(){
        ImageView image1 = (ImageView)gridLayout.getChildAt(17);
        ImageView image2 = (ImageView)gridLayout.getChildAt(18);
        ImageView image3 = (ImageView)gridLayout.getChildAt(29);
        ImageView image4 = (ImageView)gridLayout.getChildAt(30);
        ImageView image5 = (ImageView)gridLayout.getChildAt(41);
        ImageView image6 = (ImageView)gridLayout.getChildAt(42);
        ImageView image7 = (ImageView)gridLayout.getChildAt(53);
        ImageView image8 = (ImageView)gridLayout.getChildAt(28);

        switch(random(1,6)){
            case 1:

                //square  2
                image1.setImageResource(R.drawable.block_blue);
                logicMatrix[1][5] = 2;
                image2.setImageResource(R.drawable.block_blue);
                logicMatrix[1][6] = 2;
                image3.setImageResource(R.drawable.block_blue);
                logicMatrix[2][5] = 2;
                image4.setImageResource(R.drawable.block_blue);
                logicMatrix[2][6] = 2;
                blockOnCourse = true;
                blockPlaying = 2;
                blockState = 0;
                blockColorPlaying = "block_blue";

                break;
            case 2:
                //L 3
                blockOnCourse = true;
                image1.setImageResource(R.drawable.block_yelow);
                logicMatrix[1][5] = 3;
                image3.setImageResource(R.drawable.block_yelow);
                logicMatrix[2][5] = 3;
                image5.setImageResource(R.drawable.block_yelow);
                logicMatrix[3][5] = 3;
                image6.setImageResource(R.drawable.block_yelow);
                logicMatrix[3][6] = 3;
                blockPlaying = 3;
                blockColorPlaying = "block_yelow";
                blockState = 1;
                break;
            case 3:
                //Stick 4
                image1.setImageResource(R.drawable.block_lightblue);
                logicMatrix[1][5] = 4;
                image3.setImageResource(R.drawable.block_lightblue);
                logicMatrix[2][5] = 4;
                image5.setImageResource(R.drawable.block_lightblue);
                logicMatrix[3][5] = 4;
                image7.setImageResource(R.drawable.block_lightblue);
                logicMatrix[4][5] = 4;
                blockOnCourse = true;
                blockPlaying = 4;
                blockColorPlaying = "block_lightblue";
                blockState = 1;
                break;
            case 4:
                //Lighting  5

                image1.setImageResource(R.drawable.block_green);
                logicMatrix[1][5] = 5;
                image3.setImageResource(R.drawable.block_green);
                logicMatrix[2][5] = 5;
                image4.setImageResource(R.drawable.block_green);
                logicMatrix[2][6] = 5;
                image6.setImageResource(R.drawable.block_green);
                logicMatrix[3][6] = 5;
                blockOnCourse = true;
                blockPlaying = 5;
                blockColorPlaying = "block_green";
                blockState = 1;
                break;
            case 5:
                //Tank 6
                image1.setImageResource(R.drawable.block_red);
                logicMatrix[1][5] = 6;
                image3.setImageResource(R.drawable.block_red);
                logicMatrix[2][5] = 6;
                image4.setImageResource(R.drawable.block_red);
                logicMatrix[2][6] = 6;
                image8.setImageResource(R.drawable.block_red);
                logicMatrix[2][4] = 6;
                blockOnCourse = true;
                blockPlaying = 6;
                blockColorPlaying = "block_red";
                blockState = 1;
                break;
            case 6:
                // HarryPotter Scar 7

                image2.setImageResource(R.drawable.block_pink);
                logicMatrix[1][6] = 7;
                image3.setImageResource(R.drawable.block_pink);
                logicMatrix[2][5] = 7;
                image4.setImageResource(R.drawable.block_pink);
                logicMatrix[2][6] = 7;
                image5.setImageResource(R.drawable.block_pink);
                logicMatrix[3][5] = 7;
                blockOnCourse = true;
                blockPlaying = 7;
                blockColorPlaying = "block_pink";
                blockState = 1;
                break;
        }
    }

    public int random(int i, int j){
        Random rand = new Random();
        int n = rand.nextInt(j-i+1)+i; // Gives n such that 0 <= n < 20
        return n;
    }

    public void downAllPiece(){
       // printLogicMatrix();
        for(int k = 19;k>0;k--){
            for(int n = 11;n>0;n--){
                if(logicMatrix[k][n] == blockPlaying){
                    logicMatrix[k+1][n] = blockPlaying;
                    logicMatrix[k][n] = 1;
                    ImageView imageView = v1.findViewWithTag(String.valueOf(k)+","+String.valueOf(n));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(k+1)+","+String.valueOf(n));
                    imageView.setImageResource(0);
                    imageView2.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                }
            }
        }
    }

    public void bajarPieza(View view){
        for(int i = 19; i>0; i--){
            for(int j = 11; j>0; j--) {
                if(logicMatrix[i][j] == blockPlaying) {
                    if (checkWreckDown()) {
                        downAllPiece();
                        return;
                    }
                }
            }
        }
    }

    public void moveRight(View view){
        if(checkWreckRight()){
            for(int i = 19; i>0; i--){
                for(int j = 11; j>0; j--) {
                    if (logicMatrix[i][j] == blockPlaying){
                            logicMatrix[i][j+1] = blockPlaying;
                            logicMatrix[i][j] = 1;
                            ImageView imageView = v1.findViewWithTag(String.valueOf(i)+","+String.valueOf(j));
                            ImageView imageView2 = v1.findViewWithTag(String.valueOf(i)+","+String.valueOf(j+1));
                            imageView.setImageResource(0);
                        imageView2.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    }
                }
            }
        }
    }

    public void moveLeft(View view){
        if(checkWreckLeft()) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 12; j++) {
                    if (logicMatrix[i][j] == blockPlaying) {
                        logicMatrix[i][j - 1] = blockPlaying;
                        logicMatrix[i][j] = 1;
                        ImageView imageView = v1.findViewWithTag(String.valueOf(i) + "," + String.valueOf(j));
                        ImageView imageView2 = v1.findViewWithTag(String.valueOf(i) + "," + String.valueOf(j - 1));
                        imageView.setImageResource(0);
                        imageView2.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    }
                }
            }
        }
    }

    public boolean checkPosition(int a, int s,int d, int f, int g,int h, int j, int k){
        Log.e("asdf","ggggggggggggggggggggggg");
        if(logicMatrix[a][s] != -1 && logicMatrix[d][f] != -1 && logicMatrix[g][h] != -1 &&
                logicMatrix[j][k] != -1){
            return true;
        }
        return false;
    }

    public void rotateBlock(View view){
        int cont = 0;
        int[] positions = new int[8];
        for(int i = 0; i < 20; i++){
            for(int j = 0;j<12;j++){
                if(logicMatrix[i][j] == blockPlaying) {
                    positions[cont] = i;
                    cont++;
                    positions[cont] = j;
                    cont++;
                }

            }
        }
        switch (blockPlaying){
            case 3:
                rotateL(positions);
                break;
            case 4:
                rotateStick(positions);
                break;
            case 5:
                rotateLightning(positions);
                break;
            case 6:
                rotateTank(positions);
                break;
            case 7:
                rotateHP(positions);
                break;
        }
    }

    public void rotateL(int[] positions){
        switch (blockState){
            case 1:
                if(checkPosition(positions[0]+1,positions[1]+2, positions[2],positions[3]+1,positions[4],positions[5],positions[6],positions[7])){
                    logicMatrix[positions[0]+1] [positions[1]+2] = blockPlaying;
                    logicMatrix[positions[2]] [positions[3]+1] = blockPlaying;
                    logicMatrix[positions[0]] [positions[1]] = 1;
                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[0]+1) + "," + String.valueOf(positions[1]+2));
                    imageView.setImageResource(0);
                    imageView2.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]+1));
                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));
                    imageView5.setImageResource(0);
                    logicMatrix[positions[6]] [positions[7]] = 1;
                    blockState = 2;
                }

                break;
            case 2:
                if(checkPosition(positions[0]-1,positions[1], positions[2]-1,positions[3],positions[2]+1,positions[3],positions[6],positions[7])){
                    logicMatrix[positions[0]-1] [positions[1]] = blockPlaying;
                    logicMatrix[positions[2]-1] [positions[3]] = blockPlaying;
                    logicMatrix[positions[2]+1] [positions[3]] = blockPlaying;
                    logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[4]] [positions[5]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]));
                    ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]-1) + "," + String.valueOf(positions[1]));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[2]-1) + "," + String.valueOf(positions[3]));
                    ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[2]+1) + "," + String.valueOf(positions[3]));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 3;
                }

                break;
            case 3:
                if(checkPosition(positions[0],positions[1]+2, positions[4],positions[5]-1,positions[4],positions[5]+1,positions[6],positions[7])){
                    logicMatrix[positions[0]] [positions[1]+2] = blockPlaying;
                    logicMatrix[positions[4]] [positions[5]-1] = blockPlaying;
                    logicMatrix[positions[4]] [positions[5]+1] = blockPlaying;
                    logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[2]] [positions[3]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]));
                    ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]+2));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]-1));
                    ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]+1));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 4;
                }

                break;
            case 4:
                if(checkPosition(positions[0],positions[1]-1, positions[4]+1,positions[5],positions[6]+1,positions[7],positions[6],positions[7])){
                    logicMatrix[positions[0]] [positions[1]-1] = blockPlaying;
                    logicMatrix[positions[4]+1] [positions[5]] = blockPlaying;
                    logicMatrix[positions[6]+1] [positions[7]] = blockPlaying;
                    logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[2]] [positions[3]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]));
                    ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]-1));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[4]+1) + "," + String.valueOf(positions[5]));
                    ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[6]+1) + "," + String.valueOf(positions[7]));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 1;
                }
                break;
        }
    }
    public void rotateTank(int[] positions){
        switch (blockState){
            case 1:
                if(checkPosition(positions[0],positions[1], positions[2],positions[3],positions[4]+1,positions[5],positions[6],positions[7])){
                    //logicMatrix[positions[0]] [positions[1]] = blockPlaying;
                    logicMatrix[positions[4]+1] [positions[5]] = blockPlaying;
                    //logicMatrix[positions[6]] [positions[7]] = blockPlaying;
                    //logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[2]] [positions[3]] = 1;
                   // logicMatrix[positions[6]] [positions[7]] = 1;

                   // ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]));
                    //ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    //imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                   // imageView3.setImageResource(0);

                   // ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]-1));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[4]+1) + "," + String.valueOf(positions[5]));
                    //ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[6]+1) + "," + String.valueOf(positions[7]));

                   // imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                   // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 2;
                }
                break;
            case 2:
                if(checkPosition(positions[0],positions[1], positions[2],positions[3]-1,positions[4],positions[5],positions[6],positions[7])){
                    //logicMatrix[positions[0]] [positions[1]] = blockPlaying;
                    logicMatrix[positions[2]] [positions[3]-1] = blockPlaying;
                    //logicMatrix[positions[6]] [positions[7]] = blockPlaying;
                    //logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[0]] [positions[1]] = 1;
                    // logicMatrix[positions[6]] [positions[7]] = 1;

                    // ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    //ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    //imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    // imageView3.setImageResource(0);

                    // ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]-1));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]-1));
                    //ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[6]+1) + "," + String.valueOf(positions[7]));

                    // imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 3;
                }
                break;
            case 3:
                if(checkPosition(positions[0],positions[1], positions[2]-1,positions[3],positions[4],positions[5],positions[6],positions[7])){
                    //logicMatrix[positions[0]] [positions[1]] = blockPlaying;
                    logicMatrix[positions[2]-1] [positions[3]] = blockPlaying;
                    //logicMatrix[positions[6]] [positions[7]] = blockPlaying;
                    //logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[4]] [positions[5]] = 1;
                    // logicMatrix[positions[6]] [positions[7]] = 1;

                    // ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]));
                    //ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    //imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    // imageView3.setImageResource(0);

                    // ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]-1));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[2]-1) + "," + String.valueOf(positions[3]));
                    //ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[6]+1) + "," + String.valueOf(positions[7]));

                    // imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 4;
                }
                break;
            case 4:
                if(checkPosition(positions[0],positions[1], positions[2],positions[3],positions[4],positions[5]+1,positions[6],positions[7])){
                    //logicMatrix[positions[0]] [positions[1]] = blockPlaying;
                    logicMatrix[positions[4]] [positions[5]+1] = blockPlaying;
                    //logicMatrix[positions[6]] [positions[7]] = blockPlaying;
                    //logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;
                    // logicMatrix[positions[6]] [positions[7]] = 1;

                    // ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));
                    //ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    //imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    // imageView3.setImageResource(0);

                    // ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]-1));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]+1));
                    //ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[6]+1) + "," + String.valueOf(positions[7]));

                    // imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 1;
                }
                break;
        }

    }
    public void rotateStick(int[] positions){
        switch (blockState){
            case 1:
                if(checkPosition(positions[2],positions[3], positions[2],positions[3]-1,positions[2],positions[3]-1,positions[2],positions[3]+1)){
                    logicMatrix[positions[2]] [positions[3]-2] = blockPlaying;
                    logicMatrix[positions[2]] [positions[3]-1] = blockPlaying;
                    logicMatrix[positions[2]] [positions[3]+1] = blockPlaying;

                    logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[4]] [positions[5]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]));
                    ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]-2));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]-1));
                    ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]+1));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 2;
                }
                break;
            case 2:
                if(checkPosition(positions[4]+1,positions[5], positions[4]-1,positions[5],positions[4]+2,positions[5],positions[4],positions[5])){
                    logicMatrix[positions[4]-1] [positions[5]] = blockPlaying;
                    logicMatrix[positions[4]+1] [positions[5]] = blockPlaying;
                    logicMatrix[positions[4]+2] [positions[5]] = blockPlaying;
                    logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[2]] [positions[3]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]));
                    ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[4]-1) + "," + String.valueOf(positions[5]));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[4]+1) + "," + String.valueOf(positions[5]));
                    ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[4]+2) + "," + String.valueOf(positions[5]));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 1;
                }
                break;
        }

    }
    public void rotateLightning(int[] positions){
        switch (blockState){
            case 1:
                if(checkPosition(positions[0],positions[1]+1, positions[2],positions[3]-1,positions[0],positions[1]+1,positions[0],positions[1]+1)){
                    logicMatrix[positions[0]] [positions[1]+1] = blockPlaying;
                    logicMatrix[positions[2]] [positions[3]-1] = blockPlaying;
                    //logicMatrix[positions[4]] [positions[5]] = blockPlaying;
                    logicMatrix[positions[4]] [positions[5]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;
                    //logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));
                   // ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                   // imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]+1));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]-1));
                   // ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[4]+2) + "," + String.valueOf(positions[5]));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                   // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 2;
                }
                break;
            case 2:
                if(checkPosition(positions[0]-1,positions[1], positions[2]+1,positions[3],positions[0]-1,positions[1],positions[0]-1,positions[1])){
                    logicMatrix[positions[0]-1] [positions[1]] = blockPlaying;
                    logicMatrix[positions[2]+1] [positions[3]] = blockPlaying;
                    //logicMatrix[positions[4]] [positions[5]] = blockPlaying;
                    logicMatrix[positions[4]] [positions[5]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;
                    //logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));
                    // ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    // imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[0]-1) + "," + String.valueOf(positions[1]));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[2]+1) + "," + String.valueOf(positions[3]));
                    // ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[4]+2) + "," + String.valueOf(positions[5]));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 1;
                }
                break;
        }

    }
    public void rotateHP(int[] positions){
        switch (blockState){
            case 1:
                if(checkPosition(positions[2],positions[3]-1, positions[6],positions[7]+1,positions[2],positions[3],positions[2],positions[3])){
                    logicMatrix[positions[2]] [positions[3]-1] = blockPlaying;
                    logicMatrix[positions[6]] [positions[7]+1] = blockPlaying;
                    //logicMatrix[positions[4]] [positions[5]] = blockPlaying;
                    logicMatrix[positions[0]] [positions[1]] = 1;
                    logicMatrix[positions[4]] [positions[5]] = 1;
                    //logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[0]) + "," + String.valueOf(positions[1]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]));
                    // ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    // imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[2]) + "," + String.valueOf(positions[3]-1));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]+1));
                    // ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[4]+2) + "," + String.valueOf(positions[5]));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 2;
                }
                break;
            case 2:
                if(checkPosition(positions[2]-1,positions[3], positions[0]+1,positions[1],positions[2],positions[3],positions[2],positions[3])){
                    logicMatrix[positions[2]-1] [positions[3]] = blockPlaying;
                    logicMatrix[positions[0]+1] [positions[1]] = blockPlaying;
                    //logicMatrix[positions[4]] [positions[5]] = blockPlaying;
                    logicMatrix[positions[4]] [positions[5]] = 1;
                    logicMatrix[positions[6]] [positions[7]] = 1;
                    //logicMatrix[positions[6]] [positions[7]] = 1;

                    ImageView imageView = v1.findViewWithTag(String.valueOf(positions[4]) + "," + String.valueOf(positions[5]));
                    ImageView imageView2 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));
                    // ImageView imageView3 = v1.findViewWithTag(String.valueOf(positions[6]) + "," + String.valueOf(positions[7]));

                    imageView.setImageResource(0);
                    imageView2.setImageResource(0);
                    // imageView3.setImageResource(0);

                    ImageView imageView4 = v1.findViewWithTag(String.valueOf(positions[2]-1) + "," + String.valueOf(positions[3]));
                    ImageView imageView5 = v1.findViewWithTag(String.valueOf(positions[0]+1) + "," + String.valueOf(positions[1]));
                    // ImageView imageView6 = v1.findViewWithTag(String.valueOf(positions[4]+2) + "," + String.valueOf(positions[5]));

                    imageView4.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    imageView5.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));
                    // imageView6.setImageResource(getResources().getIdentifier(blockColorPlaying, "drawable", getPackageName()));

                    blockState = 1;
                }
                break;
        }

    }

    public void checkLine(){
        int cont = 0;
        for(int i = 19; i>0; i--) {
            for (int j = 11; j > 0; j--) {
                if(logicMatrix[i][j] == -1){
                    cont++;
                }
                if(cont==10){

                }
            }
        }
    }

    public void printLogicMatrix(){
        for(int i = 0; i<20; i++){
            for(int j = 0; j<12; j++) {
                System.out.print(logicMatrix[i][j]);}
            System.out.print("\n");}

    }
}
