package com.example.espino.scaneat.interfaces;

import android.os.Bundle;

/**
 * Created by espino on 3/01/17.
 */

public interface IHomeListener {
    void onClick(int id);
    void loadDataFragment(Bundle args, String fragment);
}
