package com.yzc.springcloud.entity.esQuery;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery {
    //https://blog.csdn.net/HiBoyljw/article/details/89951019 示例参考 https://blog.csdn.net/csdn_20150804/article/details/105618933
    private BoolQueryBuilder goodsQueryBuilder = QueryBuilders.boolQuery();
    private boolean isElasticsearch = false;

    public NativeSearchQueryBuilder getNativeSearchQueryBuilder() {
        return new NativeSearchQueryBuilder().withQuery(goodsQueryBuilder);
    }
    public UserQuery byName(String name) {
        if (StrUtil.isNotEmpty(name)) {
            goodsQueryBuilder.must(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", name)));
        }
        return this;
    }

    public UserQuery byTel(String tel) {
        if (StrUtil.isNotEmpty(tel)) {
            goodsQueryBuilder.must(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("tel", tel)));
        }
        return this;
    }

    public UserQuery bySex(String sex) {
        if (StrUtil.isNotEmpty(sex)) {
            goodsQueryBuilder.must(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("sex", sex)));
        }
        return this;
    }

    public UserQuery gtAge(Integer ageValue) {
        if (null != ageValue ) {
            goodsQueryBuilder.must(QueryBuilders.rangeQuery("age").gt(ageValue));
        }
        return this;
    }

}
