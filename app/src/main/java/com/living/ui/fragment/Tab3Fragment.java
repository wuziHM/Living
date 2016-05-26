package com.living.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.living.R;
import com.living.util.glide.GlideCircleTransform;
import com.living.util.glide.GlideImageUtil;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author tobin
 */
public class Tab3Fragment extends BaseFragment implements View.OnClickListener{
    private View rootView;
    private ImageView iv_header;

    public Tab3Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }


        initView();

        return rootView;
    }

    private void initView(){
        ((TextView) rootView.findViewById(R.id.tv_main_title)).setText("我");
        iv_header = (ImageView)rootView.findViewById(R.id.iv_header);
        iv_header.setOnClickListener(this);
        GlideImageUtil.setPhotoResourceId(getActivity(), GlideCircleTransform.getInstance(getActivity()),R.mipmap.tab3,iv_header);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                final Uri selectedUri = result.getData();
                if (selectedUri != null) {
                    UCrop uCrop = UCrop.of(selectedUri, Uri.fromFile(new File(getActivity().getCacheDir(), "u_crop.png"))).withMaxResultSize(200,200);
                    uCrop.start(getActivity());
                } else {
                    Toast.makeText(getActivity(), "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                final Uri resultUri = UCrop.getOutput(result);
//                iv_header.setImageURI(resultUri);
                GlideImageUtil.setPhotoFast(getActivity(), GlideCircleTransform.getInstance(getActivity()),resultUri.toString(),iv_header,R.mipmap.ic_launcher);
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
                            Toast.makeText(getActivity(),"该功能正在开发中",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();

            default:
                break;
        }
    }

}
