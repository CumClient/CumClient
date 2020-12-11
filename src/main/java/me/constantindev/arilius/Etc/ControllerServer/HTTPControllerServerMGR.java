package me.constantindev.arilius.Etc.ControllerServer;

import com.sun.net.httpserver.HttpServer;
import me.constantindev.arilius.Etc.ControllerServer.API.*;

import java.net.InetSocketAddress;

public class HTTPControllerServerMGR {
    HttpServer srv;

    public HTTPControllerServerMGR() throws Exception {
        srv = HttpServer.create(new InetSocketAddress(1337), 0);
        srv.createContext("/", new Main());
        srv.createContext("/playerinfo", new GetInfo());
        srv.createContext("/setpos", new SetPos());
        srv.createContext("/enabledModules", new EnabledModules());
        srv.createContext("/allModules", new AllModules());
        srv.createContext("/info", new Info());
        srv.createContext("/enableM", new EnableM());
        srv.createContext("/disableM", new DisableM());
        srv.setExecutor(null);
        srv.start();
    }
}
