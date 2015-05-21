package com.sun.jersey.json.impl.provider.entity;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;

@Produces("text/javascript; charset=UTF-8")
@Consumes("text/javascript; charset=UTF-8")
public final class JsonProvider extends JSONRootElementProvider {

    public JsonProvider(@Context Providers ps) {
        super(ps);
    }
}
