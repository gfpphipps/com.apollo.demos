/*
 * �˴��봴���� 2015��12��26�� ����1:44:39��
 */
package com.apollo.demos.osgi.service.api.bad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPhone {

    private static final Logger s_logger = LoggerFactory.getLogger(IPhone.class);

    public static final String ID = "1.0.0";

    public IPhone() {
        s_logger.info("New.");
    }

    public String sayHello() {
        return "Hello, I am an IPhone 6s Plus 16GB. [ID = " + ID + "]"; //�����IDʼ�ն����ϵģ�����ID�Ƿ�����˱����Ż�����Ӱ��������ΪsayHello���������ϵġ�
    }

}