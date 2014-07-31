package com.interzonedev.dataporter.process;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.interzonedev.herokusupport.client.DefaultHerokuSupportClient;
import com.interzonedev.herokusupport.client.HerokuSupportClient;
import com.interzonedev.herokusupport.webserver.WebServerParams;
import com.interzonedev.herokusupport.webserver.WebServerType;

public class Webapp {

    private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    private final HerokuSupportClient herokuSupportClient = new DefaultHerokuSupportClient();

    void process(String[] args) throws Exception {
        log.debug("process: Launching Jetty server");

        WebServerParams webServerParams = new WebServerParams(5000);

        herokuSupportClient.startWebServer(WebServerType.JETTY, webServerParams);
    }

    public static void main(String[] args) throws Exception {
        (new Webapp()).process(args);
    }

}
