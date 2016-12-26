package org.backend.mdxmaps.Model;

/**
 * Created by Emmanuel Keboh on 24/12/2016.
 */
public class ServerStatusObject {
    public int activeCount, corePoolSize, maximumPoolSize, poolSize;
    public long completedTaskCount, taskCount;

    public ServerStatusObject(int activeCount, long completedTaskCount, int corePoolSize, int maximumPoolSize, int poolSize, long taskCount) {
        this.activeCount = activeCount;
        this.completedTaskCount = completedTaskCount;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.poolSize = poolSize;
        this.taskCount = taskCount;
    }


}
