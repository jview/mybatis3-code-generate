# mybatis3-code-generate
https://github.com/mybatis/generator


# a test mybatis code generate for maven
 Change MyBatis Generator (MBG) for my project
---------------------------------------------
1,Support table comment to class field comment  
2,Support define exampleTargetPackage on javaModelGenerator
3,Support generate class add VO,such as T_USER to UserVO 
4,columnOverride.type.column=typeValue  
	columnOverride.javaType.cid=java.lang.Long  
5,Support copy class to diff fold  ﻿



# for using
need add /bin/mybatis-generator-core-1.3.3.jar to private nexus repositories  

Code generate on run maven test  
mvn test