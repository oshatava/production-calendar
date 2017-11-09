package com.osh.mvp.screen;

import android.os.Bundle;

/**
 * Created by olegshatava on 01.11.17.
 */

public interface Screen {

    void onSaveState(Bundle bundle);

    void onRestoreState(Bundle bundle);

    void onStart(ScreenContainer container);

    void onPause();

    void onStop();

    void onDestroy();

}
