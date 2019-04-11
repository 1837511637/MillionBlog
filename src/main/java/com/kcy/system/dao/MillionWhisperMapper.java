package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionWhisper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MillionWhisperMapper extends BaseDao<MillionWhisper> {
    MillionWhisper getNewsMessage();
}