package com.smh.szyproject.test.adapterTon;

import com.smh.szyproject.utils.L;

/**
 * 适配器实现思路，适配器继承标准类Usber，这个Useber实现了Usb
 *
 */
public class UsbAdapter  implements Ps2{

    @Override
    public void isPs2() {
        L.e("是ps2");
    }
}
