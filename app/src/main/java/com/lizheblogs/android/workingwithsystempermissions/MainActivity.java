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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button allPermissionsBut = (Button) findViewById(R.id.allPermissionsBut);
        if (allPermissionsBut != null) {
            allPermissionsBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PermissionHelper mPermission = getMPermission();
                    mPermission.checkPermission(MainActivity.this, new PermissionHelper.CheckPermissionCallBack() {
                        @Override
                        public void PermissionResult(boolean isGot) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle(getString(R.string.result));
                            if (isGot)
                                builder.setMessage(R.string.success);
                            else
                                builder.setMessage(R.string.permission_denied);
                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }
                    }, Constains.permissions);
                }
            });
        }
        ListView mListView = (ListView) findViewById(R.id.checkPermissionListView);
        PermissionAdapter mPermissionAdapter = new PermissionAdapter(this);
        if (mListView != null) {
            mListView.setAdapter(mPermissionAdapter);
        }
    }

    public PermissionHelper getMPermission() {
        if (permissionHelper == null)
            permissionHelper = new PermissionHelper();
        return permissionHelper;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        }
    }
}
