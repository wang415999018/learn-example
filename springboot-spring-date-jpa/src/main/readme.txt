spring date

jpa
    与hibernate的关系：jpa是标准，实际上，jpa只是定义了接口，实现都是hibernate在做。
    与mybatis的区别 ：手写sql 更灵活，jpa  开发效率更高，不用手写sql
    1.通过实现接现实简单的CURD口和通过方法名实现复杂的查询
        Repository：最顶层的接口，是一个空接口，目的是为了统一所有的Repository的类型，且能让组件扫描时自动识别。
        CrudRepository: Repository的子接口，提供CRUD的功能。
        PagingAndSortingRepository: CrudRepository的子接口, 添加分页排序。
        JpaRepository: PagingAndSortingRepository的子接口,增加批量操作等。
        JpaSpecificationExecutor: 用来做复杂查询的接口。

    继承 JpaRepository接口
    方法名构造方法
    find+全局修饰+By+实体的属性名称+限定词+连接词+ ...(其它实体属性)+OrderBy+排序属性+排序方向
    例如：findDistinctByFirstNameIgnoreCaseAndLastNameOrderByAgeDesc(String firstName,String lastName){......}
    其中：Distinct是全局修饰（非必须），FirstName和LastName是实体的属性名，And是连接词，IgnoreCase是限定词，
    Age是排序属性，Desc是排序方向，限定词和连接词统称为“关键词”。
    常用关键词如下：
    全局修饰：Distinct，Top，First
    关键词：IsNull，IsNotNull，Like，NotLike，Containing，In，NotIn，IgnoreCase，Between，Equals，LessThan，GreaterThan，After，Before...
    排序方向：Asc，Desc
    连接词：And，Or
    嵌套实体方法命名规则
    构词法：主实体中子实体的名称+ _ +子实体的属性名称
    例如：List<Person> findByAddress_ZipCode(ZipCode zipCode) 表示查询所有 Address（地址）的zipCode（邮编）为指定值的所有Person(人员)
    2.通过@Query注解实现查询  支持hql 和 原始sql (nativeQuery = true)

    总结 ： 对于业务关系比较简单，表关联少，使用jpa 能提高开发效率。 最基本的CRUD 封装比较好，不用写实现基本满足使用


