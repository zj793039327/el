package com.dz.englishliveservice.test.firststep;

import com.dz.englishliveservice.test.bean.DataForm;

public class ComplexTypeService
{
    //  ����һά�ַ�������
    public String[] getArray()
    {
        String[] strArray = new String[]{ "���г�", "�ɻ�", "���" };
        return strArray;
    } 
    //  ���ض�ά�ַ�������
    public String[] getMDArray()
    {
        String[] strArray = new String[]{ "���г�,�ɻ�,���","�й�,����,�¹�", "����,֩����,������" } ;
        return strArray;
    }
    //  ����DataForm��Ķ���ʵ��
    public DataForm getDataForm()
    {
        return new DataForm();
    }
    //  ��DataForm��Ķ���ʵ�����л������������л�����ֽ�����
    public byte[] getDataFormBytes() throws Exception 
    {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(new DataForm());        
        return baos.toByteArray();
    }    
}