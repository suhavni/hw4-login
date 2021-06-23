package io.muic.ssc.assn.a4.webapp;

import io.muic.ssc.assn.a4.webapp.service.SecurityService;

public interface Routable {

    String getMapping();

    void setSecurityService(SecurityService securityService);
}
