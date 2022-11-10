# React-Spring 연동

- IntelliJ, Node.js, Spring 모두 설치 되었다고 가정
- 기존에 Thymeleaf,JSP등으로 view부분 구성했는데, React는 SPA(Single Page Application)으로 완전히 view를 분리하는게 목표
- 버튼을 눌렀을때 페이지 이동이 아닌 필요 없는 부분을 숨기는 방식으로 한 페이지로 동작
## Spring 프로젝트 생성
- start.spring.io에서 spring project build(Gradle, Java, group,artifact명 적절히 설정, Java 11)
- 원하는 폴더에 gradle파일 압축 풀고 IntelliJ로 폴더 열기
- File-Settings-Build,Execution,Development에 Build and run using, Run tests using을 IntelliJ로 변경
## React 프로젝트 생성
- Terminal에 cd src/main
- npx create-react-app front입력하여 front프로젝트 셋팅
## 연동
### React
- src/main/front/package.json에 다음 코드 추가
```
"proxy" : "https://localhost:8080"
```
### Spring
- build.gradle에 다음 코드 추가
```
def frontendDir = "$projectDir/src/main/front"

sourceSets {
    main {
        resources { srcDirs = ["$projectDir/src/main/resources"]
        }
    }
}

processResources { dependsOn "copyReactBuildFiles" }

task installReact(type: Exec) {
    workingDir "$frontendDir"
    inputs.dir "$frontendDir"
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
        commandLine "npm.cmd", "audit", "fix"
        commandLine 'npm.cmd', 'install' }
    else {
        commandLine "npm", "audit", "fix" commandLine 'npm', 'install'
    }
}

task buildReact(type: Exec) {
    dependsOn "installReact"
    workingDir "$frontendDir"
    inputs.dir "$frontendDir"
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
        commandLine "npm.cmd", "run-script", "build"
    } else {
        commandLine "npm", "run-script", "build"
    }
}

task copyReactBuildFiles(type: Copy) {
    dependsOn "buildReact"
    from "$frontendDir/build"
    into "$projectDir/src/main/resources/static"
}
```
## Build
- IntelliJ 우측 상단 gradle클릭 -> 우측 상단 Tasks-build-build
- [프로젝트명]에서 [프로젝트명]Application으로 바꾸고 run
- 8080포트에서 오류 안뜨는지 확인

### 참조
- https://www.youtube.com/watch?v=1sw8UTWC8kc&t=73s

## React
- Vscode에서 front폴더 열어서 개발 진행
- npm start -> Ctrl + s로 알아서 자동 컴파일, 3000포트에 퍼블리싱

#### build 안될때
- 다음 명령어로 build 한다.
```
./gradlew --debug build
```
- 참조 : https://sang12.co.kr/148/gradlew-Execution-failed-for-task-':test'-%EC%98%A4%EB%A5%98