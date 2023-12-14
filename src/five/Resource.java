package five;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private String resourceName;
    private List<ResourceDetail> resourceDetails=new ArrayList<>();

    public Resource() {
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<ResourceDetail> getResourceDetails() {
        return resourceDetails;
    }

    public void setResourceDetails(List<ResourceDetail> resourceDetails) {
        this.resourceDetails = resourceDetails;
    }

    public long GetNextSourceLocation(long currentSourceLocation) {
        for (int i = 0; i <resourceDetails.size() ; i++) {
            ResourceDetail resourceDetail = resourceDetails.get(i);
            if(resourceDetail.InRangeOfLocation(currentSourceLocation))
            {
                return resourceDetail.GetNextResourceLocation(currentSourceLocation);
            }

        }
        return currentSourceLocation;
    }
}
