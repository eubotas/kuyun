package com.kuyun.common.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by user on 2017-08-31.
 */
public class DeletePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element,
                                                            IntrospectedTable introspectedTable) {

        for (int i = 0; i < element.getElements().size(); i++) {
            Element elem = element.getElements().get(i);
            if (elem instanceof TextElement
                    && elem.getFormattedContent(0).contains("where")){

            }
                replaceWhereStatement(element, i);
            break;
        }

        return true;
    }

    private void replaceWhereStatement(XmlElement element, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(" delete_flag = 0 ");
    }

    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element,
                                                         IntrospectedTable introspectedTable) {

        replaceDeleteStatement(element, introspectedTable);
        return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
    }

    private static void replaceDeleteStatement(XmlElement element, IntrospectedTable introspectedTable) {
        element.getElements().remove(0);
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        sb.append(" set delete_flag = 1 ");
        element.addElement(0, new TextElement(sb.toString()));
    }

    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element,
                                                            IntrospectedTable introspectedTable) {
        replaceDeleteStatement(element, introspectedTable);
        return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
    }

}
