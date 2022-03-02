# Example for solutions of n+1 problem with Spring and JPA/Hibernate

## With NamedGraph

```java
public interface PostRepository extends CrudRepository<Post, Long> {
    @EntityGraph(value = "post-entity-graph")
    List<Post> findByUser(User user);
};
```

```
select post0_.id as id1_1_0_, user1_.id as id1_2_1_, comments2_.id as id1_0_2_, post0_.subject as subject2_1_0_, post0_.user_id as user_id3_1_0_, user1_.email as email2_2_1_, user1_.name as name3_2_1_, comments2_.post_id as post_id3_0_2_, comments2_.reply as reply2_0_2_, comments2_.user_id as user_id4_0_2_, comments2_.post_id as post_id3_0_0__, comments2_.id as id1_0_0__ from post post0_ left outer join user user1_ on post0_.user_id=user1_.id left outer join comment comments2_ on post0_.id=comments2_.post_id where post0_.user_id=?
```

## Without NamedGraph

```java
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByUser(User user);
}
```
**N+1 query problem occurs**
```
Hibernate: select post0_.id as id1_1_, post0_.subject as subject2_1_, post0_.user_id as user_id3_1_ from post post0_ where post0_.user_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
Hibernate: select comments0_.post_id as post_id3_0_0_, comments0_.id as id1_0_0_, comments0_.id as id1_0_1_, comments0_.post_id as post_id3_0_1_, comments0_.reply as reply2_0_1_, comments0_.user_id as user_id4_0_1_ from comment comments0_ where comments0_.post_id=?
```