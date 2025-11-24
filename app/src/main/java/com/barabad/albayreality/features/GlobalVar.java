package com.barabad.albayreality.features;

import android.app.Application;

public class GlobalVar extends Application{
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}