package com.rushro2m.rx_master.android_rx_2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 16918 on 2017/12/10.
 */

public class JsonUtils {

    public static boolean parserJson(String json) {
        boolean flag = false;

        try {
            JSONObject object = new JSONObject(json);
            int result = object.getJSONObject("result").getInt("resultCode");
            if (result!=1) {
                flag = false;
            }else {
                flag = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
