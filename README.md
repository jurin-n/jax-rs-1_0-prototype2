# jax-rs-1_0-prototype2
JAX-RS1.1試してます。JPAも使います。

作ったRestサービスの仕様とか使った技術などメモる予定。


##参考文献
###JavaEE6仕様
* Java EE 6 Technologies http://www.oracle.com/technetwork/java/javaee/tech/javaee6technologies-1955512.html

###Web
* 実践に向けたドメイン駆動設計のエッセンス http://www.slideshare.net/masuda220/3-48659634
* Java EE 6: Understanding Contexts and Dependency Injection (CDI), Part 1 https://blogs.oracle.com/nishigaya/entry/javaee6_understanding_cdi_part_1
* Java EE 6: Understanding Contexts and Dependency Injection (CDI), Part 2 https://blogs.oracle.com/nishigaya/entry/javaee6_understanding_cdi_part_2

###書籍
* 実践ドメイン駆動設計 (Object Oriented Selection) http://www.amazon.co.jp/%E5%AE%9F%E8%B7%B5%E3%83%89%E3%83%A1%E3%82%A4%E3%83%B3%E9%A7%86%E5%8B%95%E8%A8%AD%E8%A8%88-Object-Oriented-Selection-%E3%83%B4%E3%82%A1%E3%83%BC%E3%83%B3%E3%83%BB%E3%83%B4%E3%82%A1%E3%83%BC%E3%83%8E%E3%83%B3/dp/479813161X


###JSR
* JSR 299: Contexts and Dependency Injection for the JavaTM EE platform https://jcp.org/en/jsr/detail?id=299


##仕様
###SoccerTeamリソース
* MemberにはFW,MF,DF,GK,CTがいる。
* MemberにはFW,MF,DF合わせて10人以上いる。
* MemberにはGKが1人以上いる。
* MemberにはCTが1人以上いる。
