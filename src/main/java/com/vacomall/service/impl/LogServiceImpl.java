package com.vacomall.service.impl;

import com.vacomall.entity.Log;
import com.vacomall.mapper.LogMapper;
import com.vacomall.service.LogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
