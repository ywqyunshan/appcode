package com.iigeo.appcode.effectivebooks;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.PhantomReference;
import java.security.PublicKey;
import java.sql.Date;

public class Elvis {
    public final static Elvis sElvis=new Elvis();
    private Elvis(){};

    public void getName(){
        InputStream inputStream=new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

    }
}
