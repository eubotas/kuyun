package com.kuyun.common.base;

import com.kuyun.common.db.DataSourceEnum;
import com.kuyun.common.db.DynamicDataSource;
import com.kuyun.common.util.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现BaseService抽象类
 * Created by kuyun on 2017/01/07.
 */
public abstract class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example> {

	public Mapper mapper;


	@Override
	public int countByExample(Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method countByExample = mapper.getClass().getDeclaredMethod("countByExample", example.getClass());
			Object result = countByExample.invoke(mapper, example);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int deleteByExample(Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method deleteByExample = mapper.getClass().getDeclaredMethod("deleteByExample", example.getClass());
			Object result = deleteByExample.invoke(mapper, example);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method deleteByPrimaryKey = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
			Object result = deleteByPrimaryKey.invoke(mapper, id);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int insert(Record record) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method insert = mapper.getClass().getDeclaredMethod("insert", record.getClass());
			Object result = insert.invoke(mapper, record);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public void batchInsert(@Param("items") List<Record> items){
		if (items != null && !items.isEmpty()){
			try {
				DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());

				Method batchInsert = mapper.getClass().getDeclaredMethod("batchInsert", List.class);
				batchInsert.invoke(mapper, items);
			} catch (Exception e) {
				e.printStackTrace();
			}
			DynamicDataSource.clearDataSource();
		}
	}


	@Override
	public int insertSelective(Record record) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method insertSelective = mapper.getClass().getDeclaredMethod("insertSelective", record.getClass());
			Object result = insertSelective.invoke(mapper, record);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public List<Record> selectByExampleWithBLOBs(Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs", example.getClass());
			Object result = selectByExampleWithBLOBs.invoke(mapper, example);
			return (List<Record>) result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return new ArrayList<>();
	}

	@Override
	public List<Record> selectByExample(Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
			Object result = selectByExample.invoke(mapper, example);
			return (List<Record>) result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return new ArrayList<>();
	}

	@Override
	public Record selectFirstByExample(Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
			List<Record> result = (List<Record>) selectByExample.invoke(mapper, example);
			if (null != result && result.size() > 0) {
				return result.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return null;
	}

	@Override
	public Record selectFirstByExampleWithBLOBs(Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs", example.getClass());
			List<Record> result = (List<Record>) selectByExampleWithBLOBs.invoke(mapper, example);
			if (null != result && result.size() > 0) {
				return result.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return null;
	}

	@Override
	public Record selectByPrimaryKey(Integer id) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectByPrimaryKey = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
			Object result = selectByPrimaryKey.invoke(mapper, id);
			return (Record) result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return null;
	}

	@Override
	public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByExampleSelective = mapper.getClass().getDeclaredMethod("updateByExampleSelective", record.getClass(), example.getClass());
			Object result = updateByExampleSelective.invoke(mapper, record, example);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int updateByExampleWithBLOBs(@Param("record") Record record, @Param("example") Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("updateByExampleWithBLOBs", record.getClass(), example.getClass());
			Object result = updateByExampleWithBLOBs.invoke(mapper, record, example);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByExample = mapper.getClass().getDeclaredMethod("updateByExample", record.getClass(), example.getClass());
			Object result = updateByExample.invoke(mapper, record, example);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Record record) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByPrimaryKeySelective = mapper.getClass().getDeclaredMethod("updateByPrimaryKeySelective", record.getClass());
			Object result = updateByPrimaryKeySelective.invoke(mapper, record);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Record record) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByPrimaryKeyWithBLOBs = mapper.getClass().getDeclaredMethod("updateByPrimaryKeyWithBLOBs", record.getClass());
			Object result = updateByPrimaryKeyWithBLOBs.invoke(mapper, record);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Record record) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByPrimaryKey = mapper.getClass().getDeclaredMethod("updateByPrimaryKey", record.getClass());
			Object result = updateByPrimaryKey.invoke(mapper, record);
			return Integer.parseInt(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int deleteByPrimaryKeys(String ids) {
		try {
			if (StringUtils.isBlank(ids)) {
				return 0;
			}
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			String[] idArray = ids.split("-");
			int count = 0;
			for (String idStr : idArray) {
				if (StringUtils.isBlank(idStr)) {
					continue;
				}
				Integer id = Integer.parseInt(idStr);
				Method deleteByPrimaryKey = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
				Object result = deleteByPrimaryKey.invoke(mapper, id);
				count += Integer.parseInt(String.valueOf(result));
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public void initMapper() {
		this.mapper = SpringContextUtil.getBean(getMapperClass());
	}

	/*******************************  Change part from base    *******************************************/
	/**
	 * 获取类泛型class
	 * @return
	 */
	public Class<Mapper> getMapperClass() {
        Object  obj=getClass().getGenericSuperclass();
        if(!(obj instanceof  ParameterizedType) )
            return getSuperMapperClass();
        else
		    return (Class<Mapper>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}


    @Override
    public Record insertSelectiveCust(Record record) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method insertSelective = mapper.getClass().getDeclaredMethod("insertSelective", record.getClass());
            Object result = insertSelective.invoke(mapper, record);
            return record;
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    public Class<Mapper> getSuperMapperClass() {
        return (Class<Mapper>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}