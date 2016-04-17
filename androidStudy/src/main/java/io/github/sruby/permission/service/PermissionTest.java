package io.github.sruby.permission.service;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by sruby on 16/4/10.
 */
public class PermissionTest
{
    public static final String PERMISSION_SIGN = "io.github.sruby.permission.aty.permission.PermissionTest";

    public void sayHello(Context context)
    {
        //权限检测
        int checkResult = context.checkCallingOrSelfPermission(PERMISSION_SIGN);

        /**
         * PERMISSION_GRANTED表示已授权
         * PERMISSION_DENIED表示拒绝
         */
        if(checkResult != PackageManager.PERMISSION_GRANTED)
        {
            throw new SecurityException("没有权限访问");
        }
    }
}
