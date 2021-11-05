package ru.shark.home.l2info.datamanager;

import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.common.PageableList;
import ru.shark.home.l2info.dao.common.RequestCriteria;
import ru.shark.home.l2info.dao.dto.ClassDto;
import ru.shark.home.l2info.dao.entity.ClassEntity;
import ru.shark.home.l2info.dao.service.ClassDao;

@Component
public class ClassDataManager extends BaseDataManager<ClassEntity, ClassDto> {
    protected ClassDataManager(ClassDao dao) {
        super(dao, ClassDto.class);
    }

    public PageableList<ClassDto> getWithPagination(RequestCriteria request) {
        ClassDao dao = (ClassDao) getDao();
        return getConverterUtil().entityListToDtoPageableList(dao.getWithPagination(request), ClassDto.class);
    }
}
