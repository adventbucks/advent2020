package com.advent.utility;

import com.advent.data.DataSet;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("datasetGetter")
public class DatasetGetter {

    private static Logger logger = LoggerFactory.getLogger(DatasetGetter.class);

    @Value("${dataset.filename.format}")
    private String fileFormat;

    @Value("${dataset.location}")
    private String location;

    public List<String> getStringListDataset(String day){
        List<String> returnList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        DataSet set = null;
        try {
            set = mapper.readValue(getClass().getResource(location+day+fileFormat), DataSet.class);

        }catch (Exception e){
            logger.error("Something done gone wrong", e);
        }

        return set == null ? returnList : set.getData() ;
    }


}
