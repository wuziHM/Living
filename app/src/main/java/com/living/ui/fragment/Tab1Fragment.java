package com.living.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.living.R;
import com.living.ui.activity.ChatListActivity;
import com.living.ui.activity.NewsActivity;
import com.living.ui.activity.WeatherActivity;
import com.yalantis.ucrop.UCrop;

import java.io.File;

public class Tab1Fragment extends BaseFragment implements View.OnClickListener {
    View view;

    private ImageView iv_crop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        initView();
        return view ;
    }

    private void initView() {

        view.findViewById(R.id.iv_main_activity_back).setOnClickListener(this);

        iv_crop = (ImageView)view.findViewById(R.id.iv_crop);

        view.findViewById(R.id.ll_news).setOnClickListener(this);
        view.findViewById(R.id.ll_weather).setOnClickListener(this);
        view.findViewById(R.id.ll_robot).setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                final Uri selectedUri = result.getData();
                if (selectedUri != null) {
                    UCrop uCrop = UCrop.of(selectedUri, Uri.fromFile(new File(getActivity().getCacheDir(), "u_crop.png")));
                    uCrop.start(getActivity());
                } else {
                    Toast.makeText(getActivity(), "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                final Uri resultUri = UCrop.getOutput(result);
                iv_crop.setImageURI(resultUri);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(getActivity(), UCrop.getError(result).getMessage() + "", Toast.LENGTH_LONG).show();
        }

    }

    private static final int REQUEST_SELECT_PICTURE = 0x01;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_news:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
            case R.id.ll_weather:
                startActivity(new Intent(getActivity(), WeatherActivity.class));
                break;
            case R.id.iv_main_activity_back:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "图片选择"), REQUEST_SELECT_PICTURE);
                break;
            case R.id.ll_robot:
                startActivity(new Intent(getActivity(), ChatListActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
