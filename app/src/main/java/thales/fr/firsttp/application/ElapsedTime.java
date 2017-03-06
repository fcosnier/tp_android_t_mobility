package thales.fr.firsttp.application;

import android.util.Log;

public class ElapsedTime {
    String mName;
    long msStart;
    long msEnd;

    public ElapsedTime(String name) {
        mName = name;
        msStart = 0;
        msEnd = 0;
    }

    public ElapsedTime start() {
        msStart = System.currentTimeMillis();
        return this;
    }

    public ElapsedTime end() {
        msEnd = System.currentTimeMillis();
        return this;
    }

    public long getElapsedMs() {
        return msEnd - msStart;
    }

    public void print() {
        Log.d("Elapsedtime", mName + ": " + getElapsedMs() + " ms");
    }
}
