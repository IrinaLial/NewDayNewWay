package com.trip.newway.util;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class OwnRepositoryImpl<MODEL, ID> implements OwnRepository<MODEL, ID> {

    @Override
    public List<MODEL> findAll() {
        // get class of MODEL
        // Test car

        if (!TestCar.class.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Annotation Table is not present");
        }
        Table annotation = TestCar.class.getAnnotation(Table.class);
        String tableInDateBase = annotation.name();

        Field[] fields = TestCar.class.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column ann = field.getAnnotation(Column.class);
                String columnInTable = ann.name();
            }
        }
        // statement or prepared statement
        // ResultSet result = select * from tableInDateBase;
        // result.get(columnInTable)


        return null;
    }

    @Override
    public MODEL findOne(ID id) {
        return null;
    }

    @Override
    public MODEL save(MODEL model) {
        return null;
    }

    @Override
    public TestCarDTO findAllDirection() {

        // get class
        // TestCarDTO

        Method[] declaredMethods = OwnRepository.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Query.class)) {
                Query annotation = method.getAnnotation(Query.class);
                String queryToDateBase = annotation.value();


            }
        }

        return null;
    }
}
