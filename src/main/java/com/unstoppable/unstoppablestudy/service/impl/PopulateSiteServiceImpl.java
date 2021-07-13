package com.unstoppable.unstoppablestudy.service.impl;

import com.unstoppable.unstoppablestudy.dao.entity.PopulateSite;
import com.unstoppable.unstoppablestudy.dao.one.PopulateSiteMapper;
import com.unstoppable.unstoppablestudy.service.PopulateSiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 站点信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-07-13
 */
@Service
@Slf4j
public class PopulateSiteServiceImpl extends ServiceImpl<PopulateSiteMapper, PopulateSite> implements PopulateSiteService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void TestTransaction() {
        PopulateSite populateSite= PopulateSite.builder().populateSiteCatId(1).cataLogCode("momo")
                .domain("ceshi").siteName("hah").altSiteName("hahha6").status(1).isMarkup(1).handlingFee(BigDecimal.valueOf(0.04)).createBy("lijun")
                .updateBy("lijun").createDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        this.saveOrUpdate(populateSite);
        firstAfterCommit();
        secondAfterCommit();
     throw new RuntimeException("测试回归");
    }




    public void firstAfterCommit(){
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
                @Override
                public void afterCommit() {
                    log.info("第er次提交");
                }
            });
        }
    }
    public void secondAfterCommit(){
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                log.info("第一次提交");
            }
        });
    }
}
