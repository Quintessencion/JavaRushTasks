package com.javarush.task.task37.task3709.connectors;


import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector {
    private String resourceString;
    private SecurityChecker checker;
    private SimpleConnector connector;

    public SecurityProxyConnector(String s) {
        resourceString = s;
        checker = new SecurityCheckerImpl();
        connector = new SimpleConnector(resourceString);
    }

    @Override
    public void connect() {
        if (checker.performSecurityCheck()) {
            connector.connect();
        }
    }
}
