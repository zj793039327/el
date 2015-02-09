package com.dz.englishliveservice.test.client;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: �����Ϲ⻪������ɷ����޹�˾<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-9-6
 */
public class ComplexTypeRPCClient
{
    public static void main(String[] args) throws Exception
    {
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8080/el-mvn/services/complexTypeService");
        options.setTo(targetEPR);
     

        // ����Ĵ��������getArray������������һάString����
        // ///////////////////////////////////////
        QName opAddEntry = new QName("http://firststep.test.englishliveservice.dz.com", "getArray");
        String[] strArray = (String[]) serviceClient.invokeBlocking(opAddEntry,
                new Object[] {}, new Class[]
                { String[].class })[0];
        for (String s : strArray)
            System.out.print(s + "  ");
        System.out.println();
        // ///////////////////////////////////////

        // ����Ĵ��������getMDArray������������һάString����
        // ///////////////////////////////////////
        opAddEntry = new QName("http://firststep.test.englishliveservice.dz.com", "getMDArray");
        strArray = (String[]) serviceClient.invokeBlocking(opAddEntry,
                new Object[] {}, new Class[]
                { String[].class })[0];
        for (String s : strArray)
        {
            String[] array = s.split(",");
            for (String ss : array)
                System.out.print("<" + ss + "> ");
            System.out.println();
        }
        System.out.println();
        // ///////////////////////////////////////

        // ����Ĵ��������getDataForm������������DataForm����ʵ��
        // ///////////////////////////////////////
        opAddEntry = new QName("http://firststep.test.englishliveservice.dz.com", "getDataForm");
        com.dz.englishliveservice.test.bean.DataForm df = (com.dz.englishliveservice.test.bean.DataForm) serviceClient.invokeBlocking(
                opAddEntry, new Object[] {}, new Class[]
                { com.dz.englishliveservice.test.bean.DataForm.class })[0];
        System.out.println(df.getAge());
        // ///////////////////////////////////////

        // ����Ĵ��������getDataFormBytes�������������ֽ����飬��󽫷��ص��ֽ����鷴���л���ת����DataForm����ʵ��
        // ///////////////////////////////////////
        opAddEntry = new QName("http://firststep.test.englishliveservice.dz.com", "getDataFormBytes");
        byte[] buffer = (byte[]) serviceClient.invokeBlocking(opAddEntry,
                new Object[] {}, new Class[]
                { byte[].class })[0];
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                new java.io.ByteArrayInputStream(buffer));
        df = (com.dz.englishliveservice.test.bean.DataForm) ois.readObject();
        System.out.println(df.getName());
        // ////////////////////////////////////////
    }
}
