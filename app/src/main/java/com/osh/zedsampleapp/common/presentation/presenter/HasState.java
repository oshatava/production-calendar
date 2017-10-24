package com.osh.zedsampleapp.common.presentation.presenter;

import android.os.Bundle;

/**
 * Created by oleg on 1/26/2017.
 */
public interface HasState  {
    void saveState(Bundle bundle);
    void restoreState(Bundle bundle);
}
