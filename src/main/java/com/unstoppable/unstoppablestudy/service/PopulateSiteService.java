package com.unstoppable.unstoppablestudy.service;

import com.unstoppable.unstoppablestudy.dao.entity.PopulateSite;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 站点信息表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-07-13
 */
public interface PopulateSiteService extends IService<PopulateSite> {
    /**
     * 测试一下
     */
    public void testPopulateSiteSaveTransaction();

    /**
     * 测试调用事务
     */
    public void testTransaction();
}
