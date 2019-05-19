package com.wqb.website.controllers.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wqb.website.supports.util.ReflectHelper;
import com.wqb.website.supports.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Shoven
 * @since 2019-05-17 15:48
 */
public interface SelectPageMethod<T> extends ControllerMethod<T> {

    /**
     * 查询分页记录
     *
     * @param query 分页查询对象
     * @param entity 实体类
     * @return
     */
    @GetMapping
    default ResponseEntity selectPage(Query<T> query, T entity){
        Class<T> cls = ReflectHelper.getInterfaceGenericType(getClass(), 0);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();

        // 列查询
        Function<String, String> columnLookup = (property) -> {
            if (Objects.equals(tableInfo.getKeyProperty(), property)) {
                return tableInfo.getKeyColumn();
            }
            return fieldList.stream()
                    .filter(field ->  Objects.equals(field.getEl(), property))
                    .findFirst()
                    .map(TableFieldInfo::getColumn)
                    .orElseThrow(IllegalArgumentException::new);
        };

        // 是否有效值
        Predicate<Object> isValidValue = value -> {
            if (value == null ) {
                return true;
            }
            if (value instanceof String) {
                return StringUtils.isBlank((String) value);
            }
            return true;
        };

        // 是否有冲突
        // 如果实体设置的属性和模糊查询的有冲突，则以实体属性为主，实体属性的条件为等值条件
        Predicate<String> isConflict = (key) -> {
            Object equalValue = null;
            try {
                equalValue = ReflectHelper.getProperty(entity, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isValidValue.test(equalValue);
        };

        // 属性转换器
        Function<T, Map<String, Object>> propertiesConvert = obj -> {
            HashMap<String, Object> properties = new HashMap<>();
            try {
                Map<String, Object> map = ReflectHelper.objectToMap(obj);
                if (map == null) {
                    return properties;
                }
                map.entrySet().stream()
                        .filter(isValidValue)
                        .forEach(entry -> properties.put(entry.getKey(), entry.getValue()));
            } catch (Exception ignored) { }
            return properties;
        };

        // 设置排序
        BiConsumer<QueryWrapper<T>, String[]> setOrder = (queryWrapper, orders) -> {
            if (ArrayUtils.isEmpty(orders)) {
                return;
            }
            for (String order : orders) {
                String column = columnLookup.apply(order);
                queryWrapper.orderByAsc(column);
            }
        };

        // 设置模糊查询
        BiConsumer< QueryWrapper<T>, T> setLike = (queryWrapper, likes) -> {
            if (likes == null) {
                return;
            }
            Map<String, Object> conditions = propertiesConvert.apply(likes);
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (StringUtils.isBlank(key) && isValidValue.test(value) &&isConflict.test(key)) {
                    continue;
                }
                String column = columnLookup.apply(key);
                queryWrapper.like(column, value);
            }
        };

        // 设置大于等于
        BiConsumer< QueryWrapper<T>, T> setGt = (queryWrapper, gts) -> {
            if (gts == null) {
                return;
            }
            Map<String, Object> conditions = propertiesConvert.apply(gts);
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (StringUtils.isBlank(key) && isValidValue.test(value) && isConflict.test(key)) {
                    continue;
                }
                String column = columnLookup.apply(key);
                queryWrapper.ge(column, value);
            }
        };

        // 设置小于等于
        BiConsumer< QueryWrapper<T>, T> getLt = (queryWrapper, lts) -> {
            if (lts == null) {
                return;
            }
            Map<String, Object> conditions = propertiesConvert.apply(lts);
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (StringUtils.isBlank(key) && isValidValue.test(value) && isConflict.test(key)) {
                    continue;
                }
                String column = columnLookup.apply(key);
                queryWrapper.le(column, value);
            }
        };

        // 应用查询条件
        QueryWrapper<T> queryWrapper = Wrappers.query(entity);
        setOrder.accept(queryWrapper, query.ascs());
        setOrder.accept(queryWrapper, query.descs());
        setLike.accept(queryWrapper, query.getLike());
        setGt.accept(queryWrapper, query.getGt());
        getLt.accept(queryWrapper, query.getLt());

        IPage pageData = getBaseService().page(query, queryWrapper);
        if (pageData.getTotal() == 0) {
            return ResponseUtils.ok("暂无数据！", pageData);
        }
        return ResponseUtils.ok(pageData);
    }
}
