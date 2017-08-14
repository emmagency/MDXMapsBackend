package org.backend.mdxmaps.services.filters;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Emmanuel Keboh on 18/03/2017.
 */
public interface Binders {

    @NameBinding
    @Retention(RetentionPolicy.RUNTIME)
    @interface CampusDirection {
    }

}