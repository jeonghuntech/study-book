아이템 14 - 생성자 대신 정적 팩토리 메서드를 고려하라
========================================
---------------------------------------------------------------------------------------------

핵심 정리
---------------------------------------------------------------------------------------------

> - Comparable : 기본(default)한 정렬 순서가 필요 때 사용
> - Comparator : 특별(specific)한 정렬 순서가 필요 할 때 사용

---------------------------------------------------------------------------------------------


사용법
---------------------------------------------------------------------------------------------
- 정적 compare 메서드를 활용한 비교자
```java
static Comparator<Object> hashCodeOrder = new Comparator<>() {
    public int compare(Object o1, Object o2) {
        return Integer.compare(o1.hashCode(), o2.hashCode());
    }
};
```

- 비교자 생성 메서드를 활용한 비교자
```java
static Comparator<Object> hashCodeOrder =
        Comparator.comparingInt(o -> o.hashCode());

```
---------------------------------------------------------------------------------------------
