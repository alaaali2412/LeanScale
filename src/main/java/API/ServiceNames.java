package API;

public enum ServiceNames {


    leanScaleCategories("rest/V1/categories");


    protected String serviceName;


    ServiceNames(String serviceName) {
        this.serviceName = serviceName;
    }

    protected String getServiceName() {
        return serviceName;
    }


}

