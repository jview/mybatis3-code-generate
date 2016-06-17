/*
 *  Copyright 2008 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mybatis.generator.internal;

import java.util.Date;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @author Jeff Butler
 * @Description @todo
 * @changeLog:
 * <p>
 * 
 * </p>
 * 
 */
public class CommentChineseGenerator extends DefaultCommentGenerator {

   
    private boolean suppressChineseComments;
    private String classComment;
    private String methodComment;
    

    public CommentChineseGenerator() {
        super();
        suppressChineseComments = false;
    }

    public void addFieldComment(Field field,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if(suppressChineseComments){
        	if(introspectedColumn.getRemarks()!=null)
        		field.addJavaDocLine("/** "+introspectedColumn.getRemarks()+" */"); //$NON-NLS-1$
        }
        else{
        	super.addFieldComment(field, introspectedTable, introspectedColumn);
        }
    }
    
    public void addClassComment(InnerClass innerClass,
            IntrospectedTable introspectedTable) {
//    	System.out.println("-----addClassComment1--classComment="+classComment);
    	if(suppressChineseComments && classComment!=null){
    		String comment=this.getComment(classComment);
//    		System.out.println("-------comment="+comment);
        	innerClass.addJavaDocLine(comment); //$NON-NLS-1$
//        	addJavadocTag(innerClass, false);
        }
    	else{
    		super.addClassComment(innerClass, introspectedTable);
    	}
    }
    
    public void addClassComment(InnerClass innerClass,
            IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
//    	System.out.println("-----addClassComment2--classComment="+classComment);
    	if(suppressChineseComments && classComment!=null){
        	innerClass.addJavaDocLine(this.getComment(classComment)); //$NON-NLS-1$
        }
    	else{
    		super.addClassComment(innerClass, introspectedTable);
    	}
    }
    
    private static String getComment(String classComment){
    	classComment=classComment.replace("${date}",  new Date().toString());
		String[] lines=classComment.split("\\\\n");
		String comment="";
		for(String line:lines){
			comment+= "	 * "+line+"\n";
		}
		
    	comment="/** \n"+comment+"	 */";
    	return comment;
    }

    public void addGeneralMethodComment(Method method,
            IntrospectedTable introspectedTable) {
       
    	if(suppressChineseComments && methodComment!=null){
        	method.addJavaDocLine(this.getComment(methodComment)); //$NON-NLS-1$
        }
    	else{
    		super.addGeneralMethodComment(method, introspectedTable);
    	}
    }



    
    public void addConfigurationProperties(Properties properties) {
       super.addConfigurationProperties(properties);
        this.suppressChineseComments = StringUtility.isTrue(properties.getProperty("suppressChineseComments"));
        this.classComment = properties.getProperty("classComment");
        this.methodComment = properties.getProperty("methodComment");
    }
    
//    public static void main(String[] args) {
//		String comment="@title: title \n@changeLog: \n";
//		System.out.println(getComment(comment));
//	}
}
