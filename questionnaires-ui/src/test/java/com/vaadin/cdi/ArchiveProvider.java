/*
 * Copyright 2000-2013 Vaadin Ltd. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */

package com.vaadin.cdi;

import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;

import com.vaadin.cdi.access.AccessControl;
import com.vaadin.cdi.access.JaasAccessControl;
import com.vaadin.cdi.internal.BeanStoreContainer;
import com.vaadin.cdi.internal.ContextDeployer;
import com.vaadin.cdi.internal.UIBeanStore;
import com.vaadin.cdi.internal.UIScopedContext;
import com.vaadin.cdi.internal.VaadinExtension;
// import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
// import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

/**
 */
public class ArchiveProvider {

    public final static Class FRAMEWORK_CLASSES[] = new Class[] { AccessControl.class, BeanStoreContainer.class,
            CDIUIProvider.class, CDIViewProvider.class, ContextDeployer.class, JaasAccessControl.class,
            UIBeanStore.class, UIScopedContext.class, CDIUI.class };

    public static WebArchive createWebArchive(String warName, Class... classes) {
        WebArchive archive = base(warName);
        archive.addClasses(classes);
        System.out.println(archive.toString(true));
        return archive;
    }

    static WebArchive base(String warName) {
        PomEquippedResolveStage resolver = Maven.resolver().loadPomFromFile("pom.xml");
        return ShrinkWrap
                .create(WebArchive.class, warName + ".war")
                .addClasses(FRAMEWORK_CLASSES)
                .addAsLibraries(resolver.resolve("com.vaadin:vaadin-server:7.1.6").withoutTransitivity().asSingleFile())
                .addAsLibraries(resolver.resolve("com.vaadin:vaadin-shared:7.1.6").withoutTransitivity().asSingleFile())
                .addAsWebInfResource(new ByteArrayAsset(VaadinExtension.class.getName().getBytes()),
                        ArchivePaths.create("services/javax.enterprise.inject.spi.Extension"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        /*-
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom(
                "pom.xml");
               
        return ShrinkWrap
                .create(WebArchive.class, warName + ".war")
                .addClasses(FRAMEWORK_CLASSES)
                .addAsLibraries(resolver.artifact("com.vaadin:vaadin-server:7.1.6").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.vaadin:vaadin-shared:7.1.6").resolveAsFiles())
                .addAsWebInfResource(new ByteArrayAsset(VaadinExtension.class.getName().getBytes()),
                        ArchivePaths.create("services/javax.enterprise.inject.spi.Extension"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
         */

    }
    /*-
     public static Archive<?> createWebArchive() {
     return Archives
     .create("test.jar", JavaArchive.class)
     .addClasses(FRAMEWORK_CLASSES)
     .addClasses(QuestionnairesUI.class, QuestionnairsClient.class)
     .addManifestResource(
     new ByteArrayAsset(
     "<decorators><class>net.sf.gazpachoquest.questionnaires</class></decorators>"
     .getBytes()), ArchivePaths.create("beans.xml"));
     }
     */
}