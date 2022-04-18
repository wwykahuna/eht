package com.eht.evetrade.service.run;

import java.util.concurrent.atomic.AtomicBoolean;

public class Active {

    private static AtomicBoolean signal = new AtomicBoolean(true);

    public static boolean getStatus() {
        return signal.get();
    }

    public static void setStatus(boolean flag) {
        signal.set(flag);
    }


}
