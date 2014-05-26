package com.chandu0101.core.deployment;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * Created by chandrasekharkode on 5/22/14.
 * <p>
 * It provides test webarchive for all modules testing
 */
public class TestDeployment {

    public static final String ORG_MONGODB_MONGO_JAVA_DRIVER = "org.mongodb:mongo-java-driver:2.11.4";
    private WebArchive webArchive;

    public TestDeployment(Package pkg) {
        this.webArchive = ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true,pkg)
                             .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                             .addAsLibraries(Maven.resolver().resolve("com.chandu0101.jaxrs:commonapi:1.0").withTransitivity().asFile());
    }

    /**
     * adds mongoDB driver to deployment
     *
     * @return
     */
    public TestDeployment withPersistance() {
        webArchive = webArchive.addAsLibraries(Maven.resolver().resolve(ORG_MONGODB_MONGO_JAVA_DRIVER).withTransitivity().asFile());
        return this;
    }

    public WebArchive getWebArchive() {
        return webArchive;
    }
}
