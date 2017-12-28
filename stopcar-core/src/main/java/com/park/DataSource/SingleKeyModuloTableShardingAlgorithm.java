package com.park.DataSource;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.routing.strategy.standard.StandardShardingStrategy;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.LinkedHashSet;
public  class SingleKeyModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    private int tableCount = 3;
    @Override
    public String doSharding(final Collection<String> availableTargetNames,final PreciseShardingValue<String> shardingValue) {
        for (String  each : availableTargetNames) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@  each="+each+" : shardingValue.getValue()="+shardingValue.getValue());
            if (each.endsWith(Math.abs(shardingValue.getValue().hashCode()) % tableCount + "")) {
                System.out.println("availableTargetNames = [" + availableTargetNames + "], shardingValue = [" + shardingValue + "]");
                System.out.println("each="+each);
                return each;
            }
        }

        return null;
    }


    /**
     * 设置分表的个数
     *
     * @param tableCount
     */
    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }


}
