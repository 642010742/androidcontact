package com.dwz.threadapplication.contact;
import com.dwz.threadapplication.contact.helper.ContactHelper;
import com.dwz.threadapplication.contact.listener.DBListener;
import com.dwz.threadapplication.contact.model.ContactPersonModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public class ContactPersonDao implements DBListener {

    public static ContactPersonDao dbListener;

    public static DBListener getInstance(){
         if(dbListener == null){
             dbListener = new ContactPersonDao();
         }
         return dbListener;
    }


    @Override
    public List<ContactPersonModel> queryAll() {
        return ContactDaoHelper.getInstance().query_all();
    }

    @Override
    public List<ContactPersonModel> queryOnlyOne(String conditions) {
        return ContactDaoHelper.getInstance().query_(conditions);
    }

    @Override
    public void delAll() {

    }

    @Override
    public void delOnlyOne(String[] conditions) {

    }

    @Override
    public void upContacts(List<ContactPersonModel> list) {
        // 更新本地用户数据库

        ContactDaoHelper.getInstance().up_(list);

    }

    @Override
    public void add(List<ContactPersonModel> list) {
        ContactDaoHelper.getInstance().add_(list);
    }

    @Override
    public void add(ContactPersonModel contactPersonModel) {
        List<ContactPersonModel> list = new ArrayList<>();
        list.add(contactPersonModel);
        ContactDaoHelper.getInstance().add_(list);
    }
}
