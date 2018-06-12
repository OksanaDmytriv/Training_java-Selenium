package multi.utils;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.Classpath("app.properties")
public interface ConfigReader {
    @Property("app.browser")
    String getBrowserName();

    @Property("app.isRemote")
    Boolean isRemote();

    @Property("app.browser.remote.url")
    String getRemoteBrowserUrl();
}