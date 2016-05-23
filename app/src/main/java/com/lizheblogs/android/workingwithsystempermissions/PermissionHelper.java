/*
 * Copyright 2016 Li Zhe <pulqueli@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lizheblogs.android.workingwithsystempermissions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Permission util
 * Created by Norman.Li on 5/23/2016.
 */
public class PermissionHelper {

    private List<String> permissionsNeeded;
    private List<String> permissionsList;
    private CheckPermissionCallBack mCallBack;
    private int requestCode = 1000;
    private int[] tags;

    public PermissionHelper() {
        permissionsNeeded = new ArrayList<String>();
        permissionsList = new ArrayList<String>();
    }

    public interface CheckPermissionCallBack {
        void PermissionResult(boolean isGot);
    }

    public void checkPermission(Activity activity, CheckPermissionCallBack mCallBack, @NonNull int[] tags) {
        this.tags = tags;
        checkPermission(activity);
        if (permissionsList.size() > 0) {
            this.mCallBack = mCallBack;
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), requestCode);
        } else if (permissionsNeeded.size() > 0) {
            showPermissionDialog(activity, false);
        } else {
            if (mCallBack != null)
                mCallBack.PermissionResult(true);
        }
    }

    private void checkPermission(Activity activity) {
        clearList(permissionsNeeded);
        clearList(permissionsList);
        for (int permission : tags) {
            int index = containsList(permission);
            if (index != -1) {
                if (!addPermission(activity, permissionsList, Constains.systemPermissions[index]))
                    permissionsNeeded.add(activity.getString(permission));
            }
        }
    }

    private int containsList(int tag) {
        if (Constains.permissions != null) {
            for (int i = 0; i < Constains.permissions.length; i++) {
                if (tag == Constains.permissions[i]) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void clearList(List<String> permissionsList) {
        if (permissionsList != null && permissionsList.size() > 0) {
            permissionsList.clear();
        }
    }

    private boolean addPermission(Activity activity, List<String> permissionsList, @NonNull String permission) {
        if (!checkSelfPermission(activity, permission)) {
                permissionsList.add(permission);
        }
        return true;
    }

    public static boolean checkSelfPermission(Activity activity, @NonNull String permission) {
        int hasPermission = ActivityCompat.checkSelfPermission(activity, permission);
        return hasPermission == PackageManager.PERMISSION_GRANTED;
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == this.requestCode) {
            checkPermission(activity);
            requestCheckNeed(activity);
        }
    }

    private void requestCheckNeed(Activity activity) {
        if (permissionsNeeded.size() > 0 || permissionsList.size() > 0) {
            for (String string : permissionsList) {
                permissionsNeeded.add(switchString(string, activity));
            }
            showPermissionDialog(activity, false);
        } else {
            if (mCallBack != null) {
                mCallBack.PermissionResult(true);
            }
        }
    }

    private String switchString(String string, Activity activity) {
        if (TextUtils.isEmpty(string)) {
            return string;
        } else {
            for (int i = 0; i < Constains.systemPermissions.length; i++) {
                if (string.equalsIgnoreCase(Constains.systemPermissions[i])) {
                    return activity.getString(Constains.permissions[i]);
                }
            }
            return string;
        }
    }

    private void showPermissionDialog(Activity activity, final boolean isCallBack) {
        StringBuilder mBuffer = new StringBuilder();
        for (String string : permissionsNeeded) {
            mBuffer.append(string);
            mBuffer.append("\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.permission_denied);
        builder.setMessage(mBuffer.toString());
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isCallBack && mCallBack != null) {
                    mCallBack.PermissionResult(isCallBack);
                }
            }
        });
        builder.show();
    }
}
