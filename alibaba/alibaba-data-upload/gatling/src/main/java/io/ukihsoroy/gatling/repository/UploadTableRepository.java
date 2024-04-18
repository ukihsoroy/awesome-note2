package io.ukihsoroy.gatling.repository;


import io.ukihsoroy.gatling.entity.UploadTable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 上云表清单
 * @author K.O
 */
public interface UploadTableRepository extends PagingAndSortingRepository<UploadTable, Integer> {

    List<UploadTable> findUploadTablesByDataSyncTypeAndOriginTableTypeAndTargetTableTypeAndAndEnable(
            String dataSyncType,
            String originTableType,
            String targetTableType,
            Integer enable);

}
