package at.htl.ondemand.model.xibo;

import java.util.Objects;

public class WidgetOption {
    public String option;
    public String value;


    @Override
    public int hashCode() {
        return Objects.hash(option, value);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof WidgetOption)) return false;
        WidgetOption that = (WidgetOption) obj;
        return option.equals(that.option) &&
                Objects.equals(value, that.value);
    }

}
