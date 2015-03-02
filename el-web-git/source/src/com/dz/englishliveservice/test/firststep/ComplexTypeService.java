package com.dz.englishliveservice.test.firststep;

import com.dz.englishliveservice.test.bean.DataForm;

public class ComplexTypeService
{
    //  返回一维字符串数组
    public String[] getArray()
    {
        String[] strArray = new String[]{ "自行车", "飞机", "火箭" };
        return strArray;
    } 
    //  返回二维字符串数组
    public String[] getMDArray()
    {
        String[] strArray = new String[]{ "自行车,飞机,火箭","中国,美国,德国", "超人,蜘蛛侠,钢铁侠" } ;
        return strArray;
    }
    //  返回DataForm类的对象实例
    public DataForm getDataForm()
    {
        return new DataForm();
    }
    //  将DataForm类的对象实例序列化，并返回序列化后的字节数组
    public byte[] getDataFormBytes() throws Exception 
    {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(new DataForm());        
        return baos.toByteArray();
    }    
}