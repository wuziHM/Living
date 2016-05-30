uCrop 是一个让你可以裁剪图片以进一步使用的安卓库。主要功能包括：

缩放图片
旋转图片
改变裁剪的宽高比
支持触摸手势：单手指滚动和平移图片，双手指旋转图片，捏图变焦（放大缩小），双击变焦。
功能多样化的简便Activity，有精确化缩放和旋转的空间以及一套预定义的宽高比例（1:1, 4:3, 3:4, 2:3, 3:2, 16:9, 9:16 +原始图片的比例）。

在AndroidManifest.xml中添加UCropActivity：
<activity
    android:name="com.yalantis.ucrop.UCropActivity"
    android:screenOrientation="portrait"/>

uCrop的配置是使用的builder模式：
UCrop.of(sourceUri, destinationUri).withAspectRatio(16, 9).withMaxResultSize(maxWidth, maxHeight).start(context);


重写onActivityResult方法并处理裁剪的结果：
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK || requestCode == UCrop.REQUEST_CROP) {
        final Uri resultUri = UCrop.getOutput(data);
    } else if (resultCode == UCrop.RESULT_ERROR) {
        final Throwable cropError = UCrop.getError(data);
    }
}

如何自定义uCrop？
你可以改变如下设置：
compression format 压缩格式(e.g. PNG, JPEG, WEBP).
compression quality压缩质量 [0 - 100] (无损的PNG会忽略质量设置 )
support for simultaneous gestures
从原始图片解码的Bitmap的最大值（如果你想重写默认的行为）。
以及更多 (比如color palette)

https://github.com/Yalantis/uCrop