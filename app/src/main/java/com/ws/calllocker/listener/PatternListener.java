package com.ws.calllocker.listener;

/**
 * Created by ws on 2017-05-06.
 */

public interface PatternListener {
    public void onAccept();
    public void onReject();
    public void onSavePattern();
}
