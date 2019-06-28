package i.am.whp.mapper;

import i.am.whp.model.PhoneNumberDeviceRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PhoneNumberDeviceRelationMapper {

    PhoneNumberDeviceRelation getById(Integer id);

}
