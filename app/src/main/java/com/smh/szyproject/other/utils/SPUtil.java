package com.smh.szyproject.other.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class SPUtil {

    private static final String SP_NAME = "LXbao";

    public static void putBoolean(String key, boolean value, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putString(String key, String value, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defValue, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(String key, int value, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defValue, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void remove(String key, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public static void clear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


    public static void putEncryptedString(String key, String value, Context ctx) {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EncryptedSharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                    .create(SP_NAME
                            , masterKeyAlias
                            , ctx,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sharedPreferences.edit().putString(key, value).commit();
    }



    public static String getEncryptedString(String key, String defValue, Context ctx) {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EncryptedSharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                    .create(SP_NAME
                            , masterKeyAlias
                            , ctx,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sharedPreferences.getString(key, defValue);
    }


    public static void putEncryptedInt(String key, int value, Context ctx) {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EncryptedSharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                    .create(SP_NAME
                            , masterKeyAlias
                            , ctx,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getEncryptedInt(String key, int defValue, Context ctx) {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EncryptedSharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                    .create(SP_NAME
                            , masterKeyAlias
                            , ctx,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sharedPreferences.getInt(key, defValue);
    }



    public static void putEncryptedBoolean(String key, boolean value, Context ctx)  {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EncryptedSharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                    .create(SP_NAME
                            , masterKeyAlias
                            , ctx,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getEncryptedBoolean(String key, boolean defValue, Context ctx) {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EncryptedSharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                    .create(SP_NAME
                            , masterKeyAlias
                            , ctx,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void clearEncrypted(String key, String defValue, Context ctx){
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EncryptedSharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences
                    .create(SP_NAME
                            , masterKeyAlias
                            , ctx,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor sharedPrefsEditor = sharedPreferences.edit();
        sharedPrefsEditor.clear();
        sharedPrefsEditor.commit();
    }
}
