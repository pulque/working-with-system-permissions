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

import android.Manifest;

/**
 * Constains
 * Created by Norman.Li on 5/23/2016.
 */
public class Constains {

    public static final int[] permissions = {
            //CALENDAR
            R.string.read_calendar,
            R.string.write_calendar,
            //CAMERA
            R.string.camera,
            //CONTACTS
            R.string.read_contacts,
            R.string.write_contacts,
            R.string.get_accounts,
            //LOCATION
            R.string.access_fine_location,
            R.string.access_coarse_location,
            //MICROPHONE
            R.string.record_audio,
            //PHONE
            R.string.read_phone_state,
            R.string.call_phone,
            R.string.read_call_log,
            R.string.write_call_log,
//            R.string.add_voicemail,
            R.string.use_sip,
            R.string.process_outgoing_calls,
            //SENSORS
            R.string.body_sensors,
            //SMS
            R.string.send_sms,
            R.string.receive_sms,
            R.string.read_sms,
            R.string.receive_wap_push,
            R.string.receive_mms,
            //STORAGE
            R.string.read_external_storage,
            R.string.write_external_storage
    };

    public static final String[] systemPermissions = {
            //CALENDAR
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            //CAMERA
            Manifest.permission.CAMERA,
            //CONTACTS
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            //LOCATION
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            //MICROPHONE
            Manifest.permission.RECORD_AUDIO,
            //PHONE
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
//            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            //SENSORS
            Manifest.permission.BODY_SENSORS,
            //SMS
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            //STORAGE
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
}
