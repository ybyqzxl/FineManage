package iti.org.finemanage.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iti.org.finemanage.R;
import iti.org.finemanage.base.ActivityCollector;
import iti.org.finemanage.base.BaseActivity;
import iti.org.finemanage.callback.UserTypeCallBack;
import iti.org.finemanage.constant.Constant;
import iti.org.finemanage.utils.NetWorkUtils;
import iti.org.finemanage.utils.SpUtils;
import okhttp3.Call;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_userName)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.cb_rememberPassword)
    CheckBox mCbRememberPassword;
    @BindView(R.id.cb_autoLogin)
    CheckBox mCbAutoLogin;

    private String mUserName;
    private String mPassWord;
    private ProgressDialog mDialog;

    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        boolean isLogOut = getIntent().getBooleanExtra("logout", false);
        if (isLogOut) {
            SpUtils.putBoolean(LoginActivity.this, Constant.ISAUTOLOGIN, false);
        }

        mEtUserName.setText(SpUtils.getString(LoginActivity.this, "j_userName", ""));     //显示之前登录的用户名
        if (SpUtils.getBoolean(LoginActivity.this, Constant.ISREMEMBERPWD, false)) {    //如果之前设置了记住密码
            mPassWord = SpUtils.getString(LoginActivity.this, "j_passWord", "");
            mEtPassword.setText(mPassWord);
            mCbRememberPassword.setChecked(true);
        } else {
            //mEtPassword.setText(mPassWord);
            mCbRememberPassword.setChecked(false);
        }

        if (SpUtils.getBoolean(LoginActivity.this, Constant.ISAUTOLOGIN, false)) {  //如果之前设置了自动登录
            mUserName = SpUtils.getString(LoginActivity.this, "j_userName", "");
            mPassWord = SpUtils.getString(LoginActivity.this, "j_passWord", "");
            mCbAutoLogin.setChecked(true);
            login(mUserName, mPassWord);    //登录
        }


        mCbAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {    //选中自动登录按钮，就让记住密码选中
                    mCbRememberPassword.setChecked(true);
                }
            }
        });

    }

    private void showDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("温馨提示");
        mDialog.setMessage("正在登录，请稍后...");
        mDialog.show();
    }

    private void dissmissDialog() {
        mDialog.dismiss();
        mDialog = null;
    }

    @OnClick(R.id.btn_Login)
    public void onClick() {
        //确定登录按钮事件
        mUserName = mEtUserName.getText().toString();
        mPassWord = mEtPassword.getText().toString();

        if (!mUserName.equals("") && !mPassWord.equals("")) {       //判断用户名密码不能为空
            SpUtils.putString(LoginActivity.this, "j_userName", mUserName);   //记住用户名
            //判断是否记住密码或者自动登录
            if (mCbRememberPassword.isChecked()) {   //如果记住密码
                SpUtils.putBoolean(LoginActivity.this, Constant.ISREMEMBERPWD, true);
                SpUtils.putString(LoginActivity.this, "j_passWord", mPassWord);
            } else {      //不记住密码
                SpUtils.putBoolean(LoginActivity.this, Constant.ISREMEMBERPWD, false);  //不记住密码
                SpUtils.remove(LoginActivity.this, "j_passWord");  //删除密码
            }
            login(mUserName, mPassWord);
        } else {
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登陆
     */
    private void login(String name, String j_passWord) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            Toast.makeText(this, "网络不可用，请检查网络设置！", Toast.LENGTH_SHORT).show();
            return;
        }
        showDialog();
        OkHttpUtils.post().url("http://sc.hebut.edu.cn/FineManagement/j_spring_security_check").addParams
                ("j_username", name).addParams("j_password", j_passWord).build()
                .execute(new UserTypeCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //连接失败
                        Toast.makeText(LoginActivity.this, "用户名或者密码错误！", Toast.LENGTH_SHORT).show();
                        mEtUserName.setText(mUserName);
                        mEtPassword.setText("");
                        dissmissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (mCbAutoLogin.isChecked()) {       //如果自动登录选中
                            SpUtils.putBoolean(LoginActivity.this, Constant.ISAUTOLOGIN, true);
                        } else {      //不自动登录
                            SpUtils.putBoolean(LoginActivity.this, Constant.ISAUTOLOGIN, false);
                        }
                        switch (response) {
                            case "student":
                                dissmissDialog();
                                //学生登录成功,跳转到主页面
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                                break;
                            case "teacher":
                                //辅导员录成功
                                dissmissDialog();
                                Toast.makeText(LoginActivity.this, "此类型用户暂时没有开放", Toast.LENGTH_SHORT).show();
                                break;
                            case "admin":
                                //校团委登录页面
                                dissmissDialog();
                                Toast.makeText(LoginActivity.this, "此类型用户暂时没有开放", Toast.LENGTH_SHORT).show();
                                break;
                            case "secondadmin":
                                //院团委登录页面
                                dissmissDialog();
                                Toast.makeText(LoginActivity.this, "此类型用户暂时没有开放", Toast.LENGTH_SHORT).show();
                                break;
                            case "leader":
                                //领导登录页面
                                dissmissDialog();
                                Toast.makeText(LoginActivity.this, "此类型用户暂时没有开放", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                dissmissDialog();
                                break;
                        }
                    }
                });
    }

    /**
     * 双击返回退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                if (!mCbRememberPassword.isChecked()){
                    SpUtils.putBoolean(LoginActivity.this, Constant.ISREMEMBERPWD, false);  //不记住密码
                    SpUtils.remove(LoginActivity.this, "j_passWord");  //删除密码

                }
                ActivityCollector.finishAll();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
