package com.advent.data;

        import com.fasterxml.jackson.annotation.JsonProperty;

        import java.util.List;

public class DataSet {

    @JsonProperty
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
