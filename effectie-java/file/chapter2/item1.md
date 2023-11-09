아이템 1 - 생성자 대신 정적 팩토리 메서드를 고려하라
========================================
---------------------------------------------------------------------------------------------

핵심 정리
---------------------------------------------------------------------------------------------

> 무지성으로 public 생성자를 만드는 습관을 버리고 정적 팩토리 메서드를 사용하자

---------------------------------------------------------------------------------------------


사용법
---------------------------------------------------------------------------------------------
> 생성자를 private으로 선언
```java
  private Car(String type) {
    this.type = type;
  }
```
---------------------------------------------------------------------------------------------

명명 방식
---------------------------------------------------------------------------------------------

### from
###### 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형 변환 메서드
```java
Date d = Date.from(instant);
```
### of
###### 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드
```java
Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
```
### valueOf
###### from 과 of 의 더 자세한 버전
```java
BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
```
### instance/getInstance
###### (매개변수를 받는다면) 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않는다.
```java
StackWalker luke = StackWalker.getInstance(options);
```
### create/newInstance
###### instance 혹은 getInstance 와 비슷하지만, 매번 새로운 인스턴스를 생성하여 반환함을 보장한다.
```java
Object newArray = Array.newInstance(classObject, arrayLen);
```
### getType
###### getInstance 와 같으나, 현재 클래스가 아닌 다른 클래스의 인스턴스를 생성할 때 사용한다. Type 은 팩터리 메서드가 반환할 객체의 타입을 적는다.
```java
FileStore fs = Files.getFileStore(path);
```
### newType
###### createInstance 와 같으나, 현재 클래스가 아닌 다른 클래스의 인스턴스를 생성할 때 사용한다. Type 은 팩터리 메서드가 반환할 객체의 타입을 적는다.
```java
BufferedReader br = Files.newBufferedReader(path);
```
### type
###### getType 과 newType 의 간결한 버전
```java
List<Complaint> litany = Collections.list(legacyLitany);
```
---------------------------------------------------------------------------------------------

장점
---------------------------------------------------------------------------------------------


### 1. 이름을 가질 수 있다
> 반환될 객체의 특성을 명확하게 알 수 있다.
```java
 BigInteger.probablePrime(); // 값이 소수인 BingInteger를 반환한다
```
<br>

### 2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
>  미리 불변 객체로 만들어 놓고 재활용하면 성능 및 속도 향상

```java
public static final Boolean TRUE = new Boolean(true);
public static final Boolean FALSE = new Boolean(false);
...
public static Boolean valueOf(boolean b) {
     return (b ? TRUE : FALSE);
 }
```
<br>

### 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다
> API가 유연해지고 프로그래머가 이해하기 쉬워 진다.
```java
interface Vehicle {

static Vehicle car() {
    return new Car();
}

static Vehicle bike() {
    return new Bike();
}

  void move();
}
```
<br>

### 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
> 구체적인 내용을 몰라도 사용하는데 문제가 없다.
```java
public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
  ...
  // 원소의 수에 따라 두 가지 하위 클래스 중 하나의 인스턴스를 반환
  if (universe.length <= 64)
      return new RegularEnumSet<>(elementType, universe);
  else
      return new JumboEnumSet<>(elementType, universe);
}
```
<br>

### 5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
> 협업하기 좋다는 것 같음. 내용이 어려우니 아래 링크를 확인하자   
https://plposer.tistory.com/61
> 

```java
// JDBC 
Class.forName("MyDriver");
DriverManager.getConnection(url, user, password);
```
---
단점
---------------------------------------------------------------------------------------------

### 1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩토리 메서드만 제공하면 하위 클래스를 만들 수 없다.
> 상속보다는 컴포지션을 사용 하도록 유도, 불변 타입을 유지한다는 점에서 오히려 장점

<br>

### 2. 정적 팩토리 메서드는 프로그래머가 찾기 어렵다.
> 문서(JavaDoc)에서 보기 힘들다.
---------------------------------------------------------------------------------------------