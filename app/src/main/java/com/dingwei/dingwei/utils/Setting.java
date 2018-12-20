package com.dingwei.dingwei.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Setting {
    private SharedPreferences mSharedPreferences;
    private Editor editor;

    public Setting(Context context, String name) {
        mSharedPreferences = context.getSharedPreferences(name,
                Activity.MODE_PRIVATE);
    }

    /**
     * 加载一个String数据
     * @param key 目标数据的key
     * @return 目标数据, 无则返回空字符串
     */
    public String loadString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    /**
     * 加载一个int类型的数据
     * @param key 目标数据的key
     * @return 目标数据, 无则返回-1
     */
    public int loadInt(String key) {
        return mSharedPreferences.getInt(key, -1);
    }

    /**
     * 加载一个int类型的数据
     *
     * @param key 目标数据的key
     * @return 目标数据, 无则返回-1
     */
    public int loadInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }


    /**
     * 加载一个long类型的数据
     *
     * @param key 目标数据的key
     * @return 目标数据, 无则返回-1
     */
    public long loadLong(String key) {
        return mSharedPreferences.getLong(key, -1);
    }

    /**
     * 加载一个long类型的数据
     *
     * @param key 目标数据的key
     * @return 目标数据, 无则返回-1
     */
    public long loadLong(String key,long defvalue) {
        return mSharedPreferences.getLong(key, defvalue);
    }

    /**
     * 加载一个boolean类型的数据
     *
     * @param key 目标数据的key
     * @return 目标数据, 无则返回false
     */
    public boolean loadBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }
    public boolean loadBoolean(String key, boolean state) {
        return mSharedPreferences.getBoolean(key, state);
    }


    /**
     * 加载一个float类型的数据
     *
     * @param key 目标数据的key
     * @return 目标数据, 无则返回-1
     */
    public float loadFloat(String key) {
        return mSharedPreferences.getFloat(key, -1);
    }

    /**
     * 以k-v形式保存一个String数据
     */
    public void saveString(String key, String value) {
        if (editor == null)
            editor = mSharedPreferences.edit();

        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 以k-v保存一个int类型的数据
     */
    public void saveInt(String key, int value) {
        if (editor == null)
            editor = mSharedPreferences.edit();

        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 以k-v保存一个long类型的数据
     */
    public void saveLong(String key, long value) {
        if (editor == null)
            editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 以k-v保存一个boolean类型的数据
     */
    public void saveBoolean(String key, boolean value) {
        if (editor == null)
            editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 以k-v保存一个float类型的数据
     */
    public void saveFloat(String key, float value) {
        if (editor == null)
            editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 存放实体类以及任意类型
     * @param key
     * @param obj
     */
    public void saveBean(String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(),
                        0));
                if (editor == null)
                    editor = mSharedPreferences.edit();
                if ("".equals(obj)){
                    editor.putString(key, "").commit();
                }else {
                    editor.putString(key, string64).commit();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException(
                    "the obj must implement Serializble");
        }

    }

    public Object loadBean(String key) {
        Object obj = null;
        try {
            String base64 = mSharedPreferences.getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 清空数据
     */
    public void clear() {
        if (editor == null)
            editor = mSharedPreferences.edit();

        editor.clear();
        editor.commit();
    }

}
