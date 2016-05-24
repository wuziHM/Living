package com.living;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.living.ui.activity.ChatListActivity;
import com.living.ui.activity.NewsActivity;
import com.living.ui.activity.WeatherActivity;

import com.yalantis.ucrop.UCrop;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_crop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        findViewById(R.id.iv_main_activity_back).setOnClickListener(this);

        iv_crop = (ImageView) findViewById(R.id.iv_crop);

        findViewById(R.id.ll_news).setOnClickListener(this);
        findViewById(R.id.ll_weather).setOnClickListener(this);
        findViewById(R.id.ll_robot).setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                final Uri selectedUri = result.getData();
                if (selectedUri != null) {
                    UCrop uCrop = UCrop.of(selectedUri, Uri.fromFile(new File(getCacheDir(), "u_crop.png")));
                    uCrop.start(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                final Uri resultUri = UCrop.getOutput(result);
                iv_crop.setImageURI(resultUri);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(MainActivity.this, UCrop.getError(result).getMessage() + "", Toast.LENGTH_LONG).show();
        }

    }

    private static final int REQUEST_SELECT_PICTURE = 0x01;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_news:
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
                break;
            case R.id.ll_weather:
                startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                break;
            case R.id.iv_main_activity_back:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "图片选择"), REQUEST_SELECT_PICTURE);
                break;
            case R.id.ll_robot:
                startActivity(new Intent(MainActivity.this, ChatListActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Are you sure you want to quit!")
                    .setConfirmText(" Yes ")
                    .setCancelText("Cancel")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            MainActivity.this.finish();
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
