package br.java.mvp.host.mock;

import br.java.mvp.service.mock.ClienteServiceMock;
import com.google.gson.Gson;

public class ClienteHostMock {

    public static String getClienteResponse() {
        Gson json = new Gson();
        return json.toJson(ClienteServiceMock.getClienteResponse());
    }

}