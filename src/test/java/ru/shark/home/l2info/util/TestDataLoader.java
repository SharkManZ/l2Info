package ru.shark.home.l2info.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ru.shark.home.l2info.dao.dto.ClassDto;
import ru.shark.home.l2info.dao.entity.ClassEntity;
import ru.shark.home.l2info.dao.entity.RaceEntity;
import ru.shark.home.l2info.dao.entity.WeaponEntity;
import ru.shark.home.l2info.dao.repository.ClassRepository;
import ru.shark.home.l2info.dao.repository.RaceRepository;
import ru.shark.home.l2info.dao.repository.WeaponRepository;
import ru.shark.home.l2info.enums.ClassType;
import ru.shark.home.l2info.util.dto.ClassTestDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Component
public class TestDataLoader {
    private static final ObjectMapper mapper = new JsonMapper();
    private static final List<String> cleanUpLst = Arrays.asList(
            "L2_WEAPON",
            "L2_CLASS",
            "L2_RACE"
    );

    @Autowired
    private TestEntityFinder entityFinder;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ClassRepository classRepository;

    @PersistenceContext
    private EntityManager em;

    /**
     * Загружает файлы с данными для тестов.
     *
     * @param files массив
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void loadWeapons(String... files) {
        for (String file : files) {
            try {
                File fl = new File(this.getClass().getResource("/testData/" + file).toURI());
                List<WeaponEntity> list = mapper.readValue(fl, new TypeReference<List<WeaponEntity>>() {
                });
                list.forEach(entity -> {
                    weaponRepository.save(entity);
                });
            } catch (URISyntaxException e) {
                System.out.println("missing file: " + "/json/" + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Загружает файлы с данными для тестов.
     *
     * @param files массив
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void loadRace(String... files) {
        for (String file : files) {
            try {
                File fl = new File(this.getClass().getResource("/testData/" + file).toURI());
                List<RaceEntity> list = mapper.readValue(fl, new TypeReference<List<RaceEntity>>() {
                });
                list.forEach(entity -> {
                    raceRepository.save(entity);
                });
            } catch (URISyntaxException e) {
                System.out.println("missing file: " + "/json/" + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Загружает файлы с данными для тестов.
     *
     * @param files массив
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void loadClass(String... files) {
        for (String file : files) {
            try {
                File fl = new File(this.getClass().getResource("/testData/" + file).toURI());
                List<ClassTestDto> list = mapper.readValue(fl, new TypeReference<List<ClassTestDto>>() {
                });
                list.forEach(dto -> classRepository.save(mapClassDtoToEntity(dto)));
            } catch (URISyntaxException e) {
                System.out.println("missing file: " + "/json/" + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ClassEntity mapClassDtoToEntity(ClassTestDto dto) {
        ClassEntity entity = new ClassEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(ClassType.valueOf(dto.getType()));
        entity.setLevel(dto.getLevel());
        entity.setRace(raceRepository.findRaceByName(dto.getRace()));
        if (!ObjectUtils.isEmpty(dto.getParent())) {
            entity.setParent(classRepository.findClassByName(dto.getParent()));
        }

        return entity;
    }

    /**
     * Удаляет все данные из определенного списка таблиц
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void cleanUp() {
        cleanUpLst.forEach(item -> {
            em.createNativeQuery(" delete from " + item).executeUpdate();
        });
    }
}
