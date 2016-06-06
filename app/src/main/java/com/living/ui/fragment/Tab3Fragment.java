package com.living.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.living.R;
import com.living.config.Constant;
import com.living.greendao.model.User;
import com.living.greendao.util.DbUtil;
import com.living.util.LogUtil;
import com.living.util.glide.GlideCircleTransform;
import com.living.util.glide.GlideImageUtil;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 第三个Tab界面显示 Fragment
 * @author tobin
 */
public class Tab3Fragment extends BaseFragment implements View.OnClickListener{
    private View rootView;
    private ImageView iv_header;

    private Uri selectedUri;

    public Tab3Fragment() {

    }

    public void testGreenDao(){
        User user=new User();
        user.setAccount("最会写代码的厨师，最会做饭的程序员");
        user.setHeight(168);

        DbUtil.getUserService().save(user);
        List<User> test = DbUtil.getUserService().queryAll();
        if (test != null && test.size()>0){
            LogUtil.e("User: " + test.get(0).toString() + " //getAccount: " + test.get(0).getAccount());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
            LogUtil.e("tobin Tab3Fragment onCreateView rootView == null");
        }else{
            LogUtil.e("tobin Tab3Fragment onCreateView rootView != null");
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        LogUtil.e("tobin Tab3Fragment onCreateView");
        initView();
        return rootView;
    }

    private void initView(){
        ((TextView) rootView.findViewById(R.id.tv_main_title)).setText("我");
        iv_header = (ImageView)rootView.findViewById(R.id.iv_header);
        iv_header.setOnClickListener(this);
        GlideImageUtil.setPhotoResourceId(getActivity(), GlideCircleTransform.getInstance(getActivity()),R.mipmap.test_header,iv_header);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                if (result != null){
                    final Uri imageUri = result.getData();
                    if (imageUri != null) {
                        UCrop uCrop = UCrop.of(imageUri, getOutputImageUri("u_crop.png")).withAspectRatio(1, 1).withMaxResultSize(200,200);
                        uCrop.start(getActivity());
                    } else {
                        Toast.makeText(getActivity(), "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    UCrop uCrop = UCrop.of(selectedUri, getOutputImageUri("u_crop.png")).withAspectRatio(1, 1).withMaxResultSize(200,200);
                    uCrop.start(getActivity());
                }

            } else if (requestCode == UCrop.REQUEST_CROP) {
                final Uri resultUri = UCrop.getOutput(result);

                LogUtil.e("Tobin UCrop.getOutput(result)： " + resultUri.toString());
                iv_header.setImageURI(resultUri);
//                GlideImageUtil.setPhotoFast(getActivity(), GlideCircleTransform.getInstance(getActivity()),resultUri.toString(),iv_header,R.mipmap.ic_launcher);
            }

        }
        if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(getActivity(),  "" + UCrop.getError(result).getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private static final int REQUEST_SELECT_PICTURE = 0x01;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_header:
                new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("修改头像！")
                    .setConfirmText("图库")
                    .setCancelText("拍照")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            startActivityForResult(Intent.createChooser(intent, "图片选择"), REQUEST_SELECT_PICTURE);
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            // create a file to save the image
                            selectedUri = getOutputImageUri("takePic");
                            // 此处这句intent的值设置关系到后面的onActivityResult中会进入那个分支，即关系到data是否为null，如果此处指定，则后来的data为null
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedUri);
                            startActivityForResult(cameraIntent, REQUEST_SELECT_PICTURE);
//                            Toast.makeText(getActivity(),"该功能正在开发中",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
                break;
            default:
                break;
        }
    }

    private Uri getOutputImageUri(String ImageName) {
        File mediaStorageDir = null;
        try {
            mediaStorageDir = new File(Constant.FILE_IMG_CACHE, "take_image");
            LogUtil.e("Successfully created mediaStorageDir: " + mediaStorageDir);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("Error in Creating mediaStorageDir: " + mediaStorageDir);
        }
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                LogUtil.e("failed to create directory, check if you have the WRITE_EXTERNAL_STORAGE permission");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File  mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".png");
        return   Uri.fromFile(mediaFile);
    }

}
