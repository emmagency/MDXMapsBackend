package org.backend.mdxmaps.Model;

import org.backend.mdxmaps.Services.ResponseService;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */
public interface OperationFactory {
    ResponseService getRoute();
}
