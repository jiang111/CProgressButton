package com.jiang.android.pbutton;

/**
 * Created by jiang on 2017/3/10.
 */

public interface ProgressListener {

    void normal(int status);
    void startDownLoad();
    void download(int progress);
}
