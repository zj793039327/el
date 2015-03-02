package com.dz.englishliveservice.test.client;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
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
     

        // 下面的代码调用了getArray方法，并返回一维String数组
        // ///////////////////////////////////////
        QName opAddEntry = new QName("http://firststep.test.englishliveservice.dz.com", "getArray");
        String[] strArray = (String[]) serviceClient.invokeBlocking(opAddEntry,
                new Object[] {}, new Class[]
                { String[].class })[0];
        for (String s : strArray)
            System.out.print(s + "  ");
        System.out.println();
        // ///////////////////////////////////////

        // 下面的代码调用了getMDArray方法，并返回一维String数组
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

        // 下面的代码调用了getDataForm方法，并返回DataForm对象实例
        // ///////////////////////////////////////
        opAddEntry = new QName("http://firststep.test.englishliveservice.dz.com", "getDataForm");
        com.dz.englishliveservice.test.bean.DataForm df = (com.dz.englishliveservice.test.bean.DataForm) serviceClient.invokeBlocking(
                opAddEntry, new Object[] {}, new Class[]
                { com.dz.englishliveservice.test.bean.DataForm.class })[0];
        System.out.println(df.getAge());
        // ///////////////////////////////////////

        // 下面的代码调用了getDataFormBytes方法，并返回字节数组，最后将返回的字节数组反序列化后，转换成DataForm对象实例
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
