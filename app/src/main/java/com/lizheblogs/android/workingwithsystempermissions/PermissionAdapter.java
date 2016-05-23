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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Personalize photo Adapter
 * Created by Norman.Li on 12/24/2015.
 */
public class PermissionAdapter extends BaseAdapter {

    private Activity mActivity;

    public PermissionAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public int getCount() {
        return Constains.permissions.length;
    }

    public Object getItem(int position) {
        return Constains.permissions[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = holder.textView = new TextView(mActivity);
            holder.textView.setPadding(0, 20, 0, 20);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView.setText(mActivity.getString(Constains.permissions[position]));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity instanceof MainActivity) {
                    PermissionHelper mPermission = ((MainActivity) mActivity).getMPermission();
                    mPermission.checkPermission(mActivity, new PermissionHelper.CheckPermissionCallBack() {
                        @Override
                        public void PermissionResult(boolean isGot) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                            builder.setTitle(mActivity.getString(R.string.result) + ":" + mActivity.getString(Constains.permissions[position]));
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
                    }, new int[]{Constains.permissions[position]});
                }
            }
        });
        return convertView;
    }

    class Holder {
        TextView textView;
    }

}
