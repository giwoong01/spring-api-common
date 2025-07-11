# spring-api-common

[![](https://jitpack.io/v/giwoong01/spring-api-common.svg)](https://jitpack.io/#giwoong01/spring-api-common)
[![](https://img.shields.io/github/release/giwoong01/spring-api-common.svg)](https://github.com/giwoong01/spring-api-common/releases)
[![](https://img.shields.io/github/actions/workflow/status/giwoong01/spring-api-common/tag-release.yml?branch=main)](https://github.com/giwoong01/spring-api-common/actions)

반복되는 공통 응답 템플릿과 예외 처리 로직을 하나의 라이브러리로 구성하여, 프로젝트 간 코드 재사용성과 일관성을 높이기 위해 제작한 Spring Boot 기반 공용 라이브러리입니다.

## ✨ 제작 이유

반복적으로 작성하게 되는 **공통 응답 템플릿**과 **에러 핸들링 로직**을 효율적으로 관리하고자, 이를 라이브러리 형태로 분리하였습니다.  
이 라이브러리를 통해 매 프로젝트마다 중복되는 코드를 줄이고, **일관된 응답 구조와 예외 처리 방식을 유지**할 수 있습니다.  
또한 실무 및 개인 프로젝트에서 자주 사용하는 기능들을 지속적으로 확장하고 반영할 예정입니다.

---

## 📦 설치 방법

Gradle 기준:

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' } <- 추가
}

dependencies {
    implementation 'com.github.giwoong01:spring-api-common:{version}' <- 추가
}

```

`{version} 예시 -> v0.1.2`

※ 최신 릴리스 버전은 [Releases](https://github.com/giwoong01/spring-api-common/releases) 탭을 참고하세요.

---

## ⚙️ 적용 방법

Spring Boot 애플리케이션에서 별도의 설정이 필요하지 않습니다.

라이브러리 내부에서 @ComponentScan과 함께 @Configuration이 적용된 AutoConfig 클래스가 제공되므로, 별도의 설정 없이 자동 등록됩니다.
```java
@Configuration
@ComponentScan("com.github.giwoong01.springapicommon")
public class AutoConfig {}
```

---

## 📘 기능별 사용법

공통 응답 템플릿, 에러 커스텀 처리 등 기능별 자세한 사용 방법은 아래 문서를 참고해주세요:

👉 기능별 사용법 보기 [Notion](https://alabaster-broom-41b.notion.site/22d61205022e804185a5ffdde1e92813?source=copy_link)

---

## License
MIT © giwoong01
