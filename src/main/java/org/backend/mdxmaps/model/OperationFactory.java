package org.backend.mdxmaps.model;

import org.backend.mdxmaps.service.ResponseService;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */
public interface OperationFactory {
    ResponseService getRoute();
}
