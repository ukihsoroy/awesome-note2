package org.ko.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panhai.sys.bo.FilePath;
import com.panhai.sys.builder.FilePathBuilder;
import com.panhai.sys.utils.ExcelHelper;
import com.panhai.sys.utils.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.ko.api.bo.PlanBo;
import org.ko.api.command.PlanCommand;
import org.ko.api.constants.PlanConstants;
import org.ko.api.entity.Plan;
import org.ko.api.entity.PlanExample;
import org.ko.api.repository.PlanRepository;
import com.panhai.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.ko.api.constants.PlanConstants.EXCEL_EN_NAME;
import static org.ko.api.constants.PlanConstants.EXCEL_ZH_NAME;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PlanService {

    private static final Logger _LOGGER = LoggerFactory.getLogger(PlanService.class);

    @Autowired private PlanRepository planRepository;

    @Autowired private FilePathBuilder filePathBuilder;

    public Result<List<PlanBo>> list (PlanCommand command, int page, int limit) {
        Result<List<PlanBo>> result = new Result<>();
        PageHelper.startPage(page, limit);
        List<PlanBo> planBos = planRepository.queryList(command);
        result.setSuccess(true);
        result.setData(planBos);
        result.setCount(new PageInfo(planBos).getTotal());
        return result;
    }

    /**
     * <p>获取详细信息</p>
     * @param id 计划主键
     */
    public Result<PlanBo> detail (String id) {
        PlanBo plan = planRepository.queryDetail(id);

        Result<PlanBo> result = new Result<>();
        result.setSuccess(true);
        result.setData(plan);
        return result;
    }

    /**
     * <p>新增计划</p>
     * @param planBo 计划详细
     */
    public Result save (PlanBo planBo) {
        Plan plan = new Plan();
        BeanUtils.copyProperties(planBo, plan);
        plan.setId(UUIDUtil.getUUID());
        int ret = planRepository.insert(plan);
        if (ret == 0) {
            throw new RuntimeException("Happen error.");
        }

        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * <p>更新计划</p>
     * @param planBo 计划详细
     */
    public Result update (PlanBo planBo) {
        PlanExample pe = new PlanExample();
        pe.createCriteria()
                .andIdEqualTo(planBo.getId())
                .andVersionNEqualTo(planBo.getVersionN())
                .andDeleteIEqualTo("N");

        Plan plan = new Plan();
        BeanUtils.copyProperties(planBo, plan);
        plan.setVersionN(plan.getVersionN() + 1);

        int ret = planRepository.updateByExampleSelective(plan, pe);
        if (ret == 0) {
            throw new RuntimeException("Happen error.");
        }

        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * <p>逻辑删除计划</p>
     * @param id 计划主键
     * @param versionN 版本号
     */
    public Result remove (String id, Long versionN) {
        PlanExample pe = new PlanExample();
        pe.createCriteria()
                .andIdEqualTo(id)
                .andVersionNEqualTo(versionN)
                .andDeleteIEqualTo("N");

        Plan plan = new Plan();
        plan.setDeleteI("Y");
        plan.setVersionN(versionN + 1);

        int ret = planRepository.updateByExampleSelective(plan, pe);
        if (ret == 0) {
            throw new RuntimeException("Happen error.");
        }

        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    public void export(PlanCommand command, HttpServletRequest request, HttpServletResponse response) {
        List<PlanBo> planBos = planRepository.queryList(command);
        if (CollectionUtils.isNotEmpty(planBos)) {
            List<String> headers = Arrays.asList(
                    "序号",
                    "计划编码",
                    "计划名称",
                    "提交人",
                    "提交时间",
                    "计划状态",
                    "逻辑删除",
                    "版本号",
                    "创家人",
                    "创建时间",
                    "更新人",
                    "更新时间"
            );

            List<List<String>> rows = new ArrayList<>();
            for (int i = 0; i < planBos.size(); i++) {
                List<String> row = new ArrayList<>();
                PlanBo planBo = planBos.get(i);
                row.add(String.valueOf(i + 1));
                row.add(planBo.getPlanCode());
                row.add(planBo.getPlanName());
                row.add(planBo.getSubmitUser());
                row.add(ExcelHelper.formatDateTime(planBo.getSubmitDt()));
                row.add(PlanConstants.PlanStatus.format(planBo.getPlanStatus()));
                row.add(planBo.getDeleteI());
                row.add(planBo.getCreateUserId());
                row.add(ExcelHelper.formatDateTime(planBo.getCreateDt()));
                row.add(planBo.getModifyUserId());
                row.add(ExcelHelper.formatDateTime(planBo.getModifyDt()));
                rows.add(row);
            }
            FilePath excel = filePathBuilder.buildArchivePath(EXCEL_EN_NAME);
            try {
                ExcelHelper.export(headers, rows, excel.getLocalPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            filePathBuilder.download(request, response, excel.getLocalPath(), EXCEL_ZH_NAME);
        }
    }
}