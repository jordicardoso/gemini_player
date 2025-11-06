package com.gbook.api;

import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger; // o java.util.logging.Logger
import org.slf4j.LoggerFactory; // o java.util.logging.Logger

@ApplicationScoped
public class SslConfiguration {

    // Puedes externalizar estas propiedades si lo deseas
    @ConfigProperty(name = "custom.ssl.truststore.resource", defaultValue = "classpath:KeyStore.jks")
    String truststoreResource;

    @ConfigProperty(name = "custom.ssl.truststore.password", defaultValue = "CARNrosada14")
    String truststorePassword;

    @Produces
    @Named("sslContext") // Nombre del bean para referenciarlo
    public SSLContextParameters sslContextParameters() {
        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource(truststoreResource);
        ksp.setPassword(truststorePassword);
        // ksp.setType("JKS"); // Opcional, Camel suele detectarlo

        TrustManagersParameters tmp = new TrustManagersParameters();
        tmp.setKeyStore(ksp);

        SSLContextParameters scp = new SSLContextParameters();
        scp.setTrustManagers(tmp);

        return scp;
    }
}
