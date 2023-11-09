# Effective_Java 3/E

---
## 1장 - 들어가기

### 핵심 기본 원칙

- 명료성
- 단순성
- 컴포넌트는 가능한 한 작되, 그렇다고 너무 작아서는 안된다.

---

## 2장 - 객체 생성과 파괴
### 아이템 1 - 생성자 대신 정적 팩토리 메서드를 고려하라

#### 사용법

> 생성자를 private으로 선언
```java
  private Car(String type) {
    this.type = type;
  }
```
#### 장점
1. 이름을 가질 수 있다
    > 반환될 객체의 특성을 명확하게 알 수 있다.
    ```java
    BigInteger.probablePrime(); // 값이 소수인 BingInteger를 반환한다
    ```
   
2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
    >  미리 불변 객체로 만들어 놓고 재활용하면 성능 및 속도 향상
    ```java
   public static final Boolean TRUE = new Boolean(true);
   public static final Boolean FALSE = new Boolean(false);
   ...
   public static Boolean valueOf(boolean b) {
        return (b ? TRUE : FALSE);
    }
    ```
   
3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다
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
   
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
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
   
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
    > 협업하기 좋다는 것 같음. 내용이 어려우니 아래 링크를 확인하자
   <br> https://plposer.tistory.com/61

    ```java
    // JDBC 
    Class.forName("MyDriver");
    DriverManager.getConnection(url, user, password);
    ```

---

#### 단점
  - 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩토리 메서드만 제공하면 하위 클래스를 만들 수 없다.
    >   상속보다는 컴포지션을 사용 하도록 유도, 불변 타입을 유지한다는 점에서 오히려 장점
  - 정적 팩토리 메서드는 프로그래머가 찾기 어렵다.
    >   문서(JavaDoc)에서 보기 힘들다.
