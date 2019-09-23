package com.dwz.threadapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dwz.threadapplication.contact.api.FinallContactDataListener;
import com.dwz.threadapplication.contact.api.FinallyContactListener;
import com.dwz.threadapplication.contact.helper.ContactHelper;
import com.dwz.threadapplication.contact.model.ContactPersonModel;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FinallContactDataListener {

    private FinallyContactListener finallyContactImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finallyContactImp =
                ContactHelper.getFinallyContactImp(this,this);
        finallyContactImp.onCreate();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 150) {
            if (grantResults[0]==1) {
                finallyContactImp.registerContactChange();
                finallyContactImp.startServer();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finallyContactImp.onDestory();
    }

    /**
     *
     * @param list  手机联系人的全部数据
     * @param upList  新增和更改的数据
     */
    @Override
    public void contactData(List<ContactPersonModel> list,List<ContactPersonModel> upList) {
        // 更新到本地数据库（如果需要的话）
        finallyContactImp.reset(upList);
    }
}
