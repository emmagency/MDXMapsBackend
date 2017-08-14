package org.backend.mdxmaps.model;

/**
 * Created by Emmanuel Keboh on 24/12/2016.
 */
public class ServerStatus {
    public int activeCount, corePoolSize, maximumPoolSize, poolSize;
    public long completedTaskCount, taskCount;

    public ServerStatus(int activeCount, long completedTaskCount, int corePoolSize, int maximumPoolSize, int poolSize, long taskCount) {
        this.activeCount = activeCount;
        this.completedTaskCount = completedTaskCount;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.poolSize = poolSize;
        this.taskCount = taskCount;
    }


}
