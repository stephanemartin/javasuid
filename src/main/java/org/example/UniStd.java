package org.example;

import com.sun.jna.Library;

public interface UniStd extends Library {
    int setuid(int uid);
}
