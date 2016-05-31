package com.living.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.emokit.sdk.InitListener;
import com.emokit.sdk.basicinfo.AdvancedInformation;
import com.emokit.sdk.heartrate.EmoRateListener;
import com.emokit.sdk.heartrate.RateDetect;
import com.emokit.sdk.record.EmotionDetect;
import com.emokit.sdk.record.EmotionVoiceListener;
import com.emokit.sdk.senseface.ExpressionDetect;
import com.emokit.sdk.senseface.ExpressionListener;
import com.emokit.sdk.util.SDKAppInit;
import com.emokit.sdk.util.SDKConstant;
import com.living.R;
import com.living.util.JsonUtil;
import com.living.util.LogUtil;
import com.living.util.StringUtils;

/**
 * @author Tobin
 * @since 2016-05-30
 */
public class EmokitActivity extends BaseAppCompatActivity implements View.OnClickListener{

    private Context context;

    private EmotionDetect emotionDetect;
    private ExpressionDetect expressDetect;
    RateDetect rt;
    TextView txt_test_result;
    private String txtResult = "",enTxtResult = "";

    Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDKConstant.VIEWFINSIH:
                    String[] str = ((String) msg.obj).split("&&");
                    LogUtil.e("tobin 语音识别监听器返回结果((String) msg.obj).split(\"&&\").length: " + str.length);
                    analyzeResult(str[0]);
                    break;
                case 1901:
                    LogUtil.e("tobin test Result: " +  msg.obj);
                    if (msg.obj != null) {
                        analyzeResult((String) msg.obj);
                    }else{
                        txt_test_result.setText("");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void analyzeResult(String result){
        if (JsonUtil.json2JsonObject(result).get("resultcode")!= null && "200".equals(JsonUtil.json2JsonObject(result).get("resultcode"))){
            String str = JsonUtil.json2JsonObject(result).get("rc_main").toString();

            switch (str){
                case "K":
                    txtResult = "平静；放松；专注；出神";
                    enTxtResult = "Calm";
                    break;
                case "D":
                    txtResult = "忧愁；疑惑；迷茫；无助";
                    enTxtResult = "Confused";
                    break;
                case "C":
                    txtResult = "伤感；郁闷；痛心；压抑";
                    enTxtResult = "Sad";
                    break;
                case "Y":
                    txtResult = "生气；蔑视；兴奋；失控；";
                    enTxtResult = "Angry";
                    break;
                case "M":
                    txtResult = "开心；甜蜜；欢快；舒畅";
                    enTxtResult = "Happy";
                    break;
                case "W":
                    txtResult = "害怕；焦虑；紧张；激情";
                    enTxtResult = "Fear";
                    break;
                case "T":
                    txtResult = "厌恶；反感；意外；惊讶";
                    enTxtResult = "Disgust";
                    break;
                case "KA+":
                    txtResult = "接纳；包容；吸收；柔顺";
                    enTxtResult = "accepting; embracing; absorbing";
                    break;
                case "KP+":
                    txtResult = "专注；平静；出神；孤单";
                    enTxtResult = "Focused; peaceful; spellbound; lonely";
                    break;
                case "KR+":
                    txtResult = "痛快；爽快；释放；放松";
                    enTxtResult = "piquant; straightforward; venting; relaxed";
                case "CT+":
                    txtResult = "豪放；从容；开朗；豁达";
                    enTxtResult = "bold and unconstrained; calm; extroverted; open-minded";
                case "CG+":
                    txtResult = "决断；果敢；坚定；爽快";
                    enTxtResult = "resolute; bold; firm; willingly";
                case "YC+":
                    txtResult = "平和；美好；理智；祥和";
                    enTxtResult = "moderate; splendid; sane; harmonious";
                case "YL+":
                    txtResult = "舒适；轻松；自然；顺心";
                    enTxtResult = "comfortable; easy; natural; satisfactory";
                case "YV+":
                    txtResult = "欢快；欢畅；舒畅；舒心";
                    enTxtResult = "cheerful; delightful; pleased; eased";
                case "ML+":
                    txtResult = "怜爱；同情；关心；甜蜜";
                    enTxtResult = "affectionate; sympathetic; caring; sweet";
                case "MS+":
                    txtResult = "喜欢；开心；高兴；心动";
                    enTxtResult = "fond; happy; glad; touched";
                case "WS+":
                    txtResult = "积极；阳光；高涨；激情";
                    enTxtResult = "motivated; positive; upsurging; passionate";
                case "WC+":
                    txtResult = "无畏；泰然；面对；激动";
                    enTxtResult = "fearless; poised; confronting; excited";
                case "KA-":
                    txtResult = "急躁；着急；憋躁；憋闷: 躁狂症倾向";
                    enTxtResult = "impatient; worried; restless; stifling";
                case "KP-":
                    txtResult = "心乱；分心；空想；思念: 强迫症倾向";
                    enTxtResult = "discomposed; distracted; airy-fairy; missed";
                case "KR-":
                    txtResult = "暴躁；烦躁；憋火；悸动: 焦虑症倾向";
                    enTxtResult = "irritable; hot-tempered; holding anger";
                case "CT-":
                    txtResult = "伤感；哭泣；痛心；低落: 抑郁症倾向";
                    enTxtResult = "sentimental; crying; heartbroken; down";
                    break;
                case "CG-":
                    txtResult = "怯懦；犹豫；纠结；郁闷: 强迫症倾向";
                    enTxtResult = "cowardly; hesitating; depressed";
                    break;
                case "YC-":
                    txtResult = "生气；指责；攻击；冲动：敌对症倾向";
                    enTxtResult = "angry; accusatory; offensive; excited.";
                    break;
                case "YL-":
                    txtResult = "紧张；失调；失控；宣泄: 人际关系敏感症倾向";
                    enTxtResult = "nervous; disordered; uncontroled; wreaking";
                    break;
                case "YV-":
                    txtResult = "压抑；窝心；别扭；想念: 抑郁症倾向";
                    enTxtResult = "repressed; annoyed; awkward; missed.";
                    break;
                case "ML-":
                    txtResult = "哀伤；失落；幽怨；寂寞: 偏执症倾向";
                    enTxtResult = "grieved; frustrated; hidden bitterness; lonely.";
                    break;
                case "MS-":
                    txtResult = "记恨；怨恨；仇恨；哀怨: 偏执症倾向";
                    enTxtResult = "grudge; resentful; hatred; plaintive";
                    break;
                case "WS-":
                    txtResult = "消极；灰暗；低迷；颓废: 抑郁症倾向";
                    enTxtResult = "negative; dark; downturn; dispirited";
                    break;
                case "WC-":
                    txtResult = "恐惧；害怕；惊恐；焦虑: 恐怖症倾向";
                    enTxtResult = "afeared; scared; frightened; anxious";
                    break;
                default:
                    txtResult = "长得太丑，识别不了！";
                    enTxtResult = "Emotion recognition failure";
                    break;
            }
        }else{
            txtResult = JsonUtil.json2JsonObject(result).get("reason").toString() + "\n长得太丑，吓到人了!";
            enTxtResult = "Emotion recognition failure";
        }
        String time = StringUtils.changeDateStr(JsonUtil.json2JsonObject(result).get("servertime").toString());
        txt_test_result.setVisibility(View.VISIBLE);
        txt_test_result.setText("测试结果：\n\n中文描述：\n" + txtResult + "\n\nEnglish description：\n" + enTxtResult+"\n\n服务时间：\n" + time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emokit);
        context = this;

        emotionDetect = EmotionDetect.createRecognizer(this, mInitListener);
        rt = RateDetect.createRecognizer(this, mInitListener);

        expressDetect = ExpressionDetect.createRecognizer(this, mInitListener);
        findViewById(R.id.btn_isr_expressdetect).setOnClickListener(this);
        findViewById(R.id.btn_isr_recognize).setOnClickListener(this);
        findViewById(R.id.btn_isr_recognize_stop).setOnClickListener(this);
        findViewById(R.id.btn_isr_facedetect).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);

        txt_test_result = (TextView) findViewById(R.id.txt_test_result);
        txt_test_result.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 测试表情
            case R.id.btn_isr_expressdetect:
                expressDetect.setParameter(SDKConstant.FACING, SDKConstant.CAMERA_FACING_FRONT);
                expressDetect.startRateListening(expressListener);
                break;
            // 停止分析
            case R.id.btn_isr_recognize_stop:
                emotionDetect.stopListening();
                break;
            // 语音分析
            case R.id.btn_isr_recognize:
                emotionDetect.startListening(mRecognizerListener);
                break;
            // 测试心率
            case R.id.btn_isr_facedetect:
                rt.setParameter(SDKConstant.FACING, SDKConstant.CAMERA_FACING_FRONT);
                rt.startRateListening(recoginze_listener);
                break;
            case R.id.iv_back:
                this.finish();
            default:
                break;
        }
    }

    /**
     * 面部表情识别器
     */
    private ExpressionListener expressListener = new ExpressionListener() {

        @Override
        public void endDetect(String result) {
            Message msg = new Message();
            msg.what = 1901;
            msg.obj = result;
            mainHandler.sendMessage(msg);
        }
        @Override
        public void beginDetect() {

        }
    };
    /**
     * 语音识别监听器。
     */
    private EmotionVoiceListener mRecognizerListener = new EmotionVoiceListener() {

        @Override
        public void onVolumeChanged(int volume) {
//            LogUtil.e("当前正在说话，音量大小：", volume + "");
        }

        @Override
        public void onEndOfSpeech() {
            LogUtil.e("EmotionVoiceListener", "结束说话");
        }

        @Override
        public void onBeginOfSpeech() {
            LogUtil.e("EmotionVoiceListener", "开始说话");
        }

        @Override
        public void onVoiceResult(String Result) {
            Message msg = new Message();
            msg.what = SDKConstant.VIEWFINSIH;
            msg.obj = Result;
            mainHandler.sendMessage(msg);
        }

    };

    /**
     * 心率识别监听器。
     */
    EmoRateListener recoginze_listener = new EmoRateListener() {

        @Override
        public void beginDetect() {

        }

        @Override
        public void endDetect(String result) {
            Message msg = new Message();
            msg.what = 1901;
            msg.obj = result;
            mainHandler.sendMessage(msg);
        }

        @Override
        public void monitor(double rgb) {
            LogUtil.e("recognizetag", "" + rgb);
        }
    };

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            // 获取设备ID
            AdvancedInformation pp = AdvancedInformation.getSingleton(context);
            // 注册用户信息: platflag 应用名; userName 用户名或设备 ID;password 用户登录密码(可为空)
            SDKAppInit.registerforuid("Living", pp.getp().getSimSerial(), "123456");
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            return false;
        } else if(keyCode == KeyEvent.KEYCODE_MENU) {
            return false;
        } else if(keyCode == KeyEvent.KEYCODE_HOME) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
