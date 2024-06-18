package io.ukihsoroy.gatling.source.maxcompute;

import io.ukihsoroy.gatling.source.DatasourceProperties;

/**
 *
 */
public class MaxComputeProperties extends DatasourceProperties {

    private String endpoint = "jdbc:odps:https://service.odps.aliyun.com/api";

    /**
     * aliyun access id.
     */
    private String accessId;

    /**
     * aliyun access key.
     */
    private String accessKey;

    /**
     * dataworks project id.
     */
    private String project;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
