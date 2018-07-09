package com.xmkj.md;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.CommonCallback;
import com.xmkj.md.http.OkHttpHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.xmkj.md", appContext.getPackageName());
    }

    @Test
    public void httpTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(appContext);
        Map<String, Object> params = new HashMap<>();
        params.put("userName", "胡歌");
        params.put("userPassword", "1");
        httpHelper.post(Constants.BASE_URL + "/Login", params, new CommonCallback(appContext) {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, Object o) {

            }
        });
    }
}
