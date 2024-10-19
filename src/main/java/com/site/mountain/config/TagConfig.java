package com.site.mountain.config;

import com.site.mountain.component.ArticleListTag;
import com.site.mountain.component.BlogTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TagConfig {
    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private BlogTag blogTag;
    @Autowired
    private ArticleListTag articleListTag;

    @PostConstruct
    public void shareVariable() {
        configuration.setSharedVariable("blogTag", blogTag);
        configuration.setSharedVariable("articleListTag", articleListTag);
    }
}
