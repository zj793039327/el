package com.dz.englishliveservice.test.firststep;

import java.util.HashMap;

/**
 * axis2 first step
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * @author zj
 * @version 1.0 
 * @date 2013-9-2
 */
@SuppressWarnings("rawtypes")
public class StockQuoteService {
    private HashMap map = new HashMap();

    public double getPrice(String symbol) {
        Double price = (Double) map.get(symbol);
        if(price != null){
            return price.doubleValue();
        }
        return 42.00;
    }

    public void update(String symbol, double price) {
        map.put(symbol, new Double(price));
    }
}