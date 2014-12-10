package my.test.internal;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import my.test.Itest;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(enabled = true, immediate = true, service = { Itest.class })
public class ComponentWithGetEntry implements Itest {

    public ComponentWithGetEntry() {
    }

    @Activate
    public void activate(BundleContext bundleContext) {
        URL spConfigurationFileURL = bundleContext.getBundle().getEntry(
                "/configuration/my_resource.txt");

        System.out.println("URL found:" + spConfigurationFileURL);

        Enumeration<URL> ls = bundleContext.getBundle().findEntries("/",
                "my_resource.txt", true);
        print("Searching bundle for 'my_resource.txt'...", bundleContext
                .getBundle().findEntries("/", "my_resource.txt", true));
        print("Searching bundle for 'MANIFEST.MF'...", bundleContext
                .getBundle().findEntries("/", "MANIFEST.MF", true));
        print("Searching bundle for *.xml...", bundleContext.getBundle()
                .findEntries("/", "*.xml", true));

        InputStream is = getClass().getResourceAsStream(
                "/configuration/my_resource.txt");
        System.out.println("getResourceAsStream is:" + is.toString());

    }

    void print(String name, Enumeration<URL> list) {
        System.out.println(name);
        while (list.hasMoreElements()) {
            URL url = (URL) list.nextElement();
            System.out.println("Found:" + url);
        }
    }
}
