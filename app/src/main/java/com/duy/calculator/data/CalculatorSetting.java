/*
 * Copyright 2017 Tran Le Duy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duy.calculator.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.duy.calculator.R;
import com.duy.calculator.utils.ConfigApp;

/**
 * Setting for application
 * <p>
 * Created by Duy on 3/7/2016
 */
public class CalculatorSetting {

    public static final long serialVersionUID = 310L;

    public static final String preName = "app_data";
    public static final String INPUT_MATH = "inp_math";
    public static final String RESULT_MATH = "res_math";

    public static final String INPUT_BASE = "INPUT_BASE";
    public static final String RESULT_BASE = "RESULT_BASE";
    public static final String BASE = "BASE";

    public static final String USE_RADIANS = "USE_RADIANS";
    public static final String NEW_UPDATE = "new_update" + serialVersionUID;
    public static final String GO_TO_MARKET = "GO_TO_MARKET";

    public static final String CMPLX_ON = "COMPLEX";
    public static final String INPUT_STATISTIC = "INPUT_STATISTIC";
    /**
     * duplication with key_pref_fraction on mSetting.xml file
     */
    public static final String IS_FRACTION = "fraction_mode";

    public static final String IS_DEGREE = "IS_DEGREE";
    public static final String NOTIFY_UPDATE = "NOTIFY_UPDATE";
    private static final String IS_UPDATE = "IS_UPDATE_" + ConfigApp.VERSION_CODE;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private Context context;

    public CalculatorSetting(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = sharedPreferences.edit();
    }

    public CalculatorSetting(SharedPreferences mPreferences, Context context) {
        this.context = context;
        this.sharedPreferences = mPreferences;
        this.editor = sharedPreferences.edit();
    }

    public boolean useRadians() {
        return sharedPreferences.getBoolean(USE_RADIANS, false);
    }

    public boolean useComplex() {
        return sharedPreferences.getBoolean(CMPLX_ON, false);
    }

    public boolean useLightTheme() {
        return sharedPreferences.getBoolean("THEME_STYLE", false);
    }

    public boolean instantResult() {
        return sharedPreferences.getBoolean(context.getString(R.string.key_pref_instant_res), true);
    }

    public boolean useFraction() {
        return sharedPreferences.getBoolean(CalculatorSetting.IS_FRACTION, false);
    }

    public void setFraction(boolean b) {
        editor.putBoolean(IS_FRACTION, b).commit();
    }

    public boolean isUpdate(Context context) {
        return !(new CalculatorSetting(context).getBoolean(IS_UPDATE));
    }

    public void setUpdate(boolean b) {
        put(IS_UPDATE, b);
    }

    public void put(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void put(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return getInt(key, -1);
    }

    public int getInt(String key, int def) {
        try {
            return sharedPreferences.getInt(key, def);
        } catch (Exception e) {
            String value = getString(context.getString(R.string.key_pref_precision));
            try {
                return Integer.parseInt(value);
            } catch (Exception e1) {

            }
        }
        return def;
    }

    /**
     * get long value from key,
     *
     * @param key - key
     * @return -1 if not found
     */
    public long getLong(String key) {
        try {
            return sharedPreferences.getLong(key, -1);
        } catch (Exception e) {
            return -1;
        }
    }

    public String getString(String key) {
        try {
            return sharedPreferences.getString(key, "");
        } catch (Exception e) {
            return "";
        }
    }

    public String getString(String key, String def) {
        try {
            return sharedPreferences.getString(key, def);
        } catch (Exception e) {
            return def;
        }
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean isNewUpdate() {
        return sharedPreferences.getBoolean(NEW_UPDATE, false);
    }

    public void setVar(String name, String value) {
        editor.putString("var_" + name, value);
    }

    public void setNotifyUpdate(boolean b) {
        editor.putBoolean(NOTIFY_UPDATE, b).apply();
    }

    public boolean useFullScreen() {
        return getBoolean(context.getString(R.string.key_hide_status_bar));
    }

    public SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    public int getPrecision() {
        return getInt(context.getString(R.string.key_pref_precision));
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener
                                                                 onSharedPreferenceChangeListener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener
                                                                   onSharedPreferenceChangeListener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);

    }
}
