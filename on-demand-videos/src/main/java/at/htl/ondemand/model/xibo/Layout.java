package at.htl.ondemand.model.xibo;

import java.util.List;

public class Layout {

    public Long layoutId;
    public String layout;
    public Integer parentId;
    public Integer publishedStatusId;
    public List<Region> regions;
    public List<Display> displays;
}
