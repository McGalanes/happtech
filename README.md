# Happtech

[![Build → Test → Lint](https://github.com/mcgalanes/happtech/actions/workflows/ci.yml/badge.svg)](https://github.com/mcgalanes/happtech/actions/workflows/ci.yml)

# Setup

#### Requirement
- Java 22
  
#### Install app
```
./gradlew installDebug
```

#### Run Unit Tests
```
./gradlew testDebugUnitTest
```

# Features
- Search
- Art Object Listing (Offline)
- Art Object Detail
- Landscape / Portrait


# Architecture & Patterns
```mermaid
flowchart LR

  classDef appModule fill:#AEFFDA,color:#000
  classDef featureModule fill:#FFDAAE,color:#000
  classDef coreModule fill:#DAAEFF,color:#000
  
  subgraph :app
    app([:app]):::appModule
  end
  subgraph :feature
    app --> museumcollection:list([:museumcollection:list]):::featureModule
    app --> museumcollection:detail([:museumcollection:detail]):::featureModule
  end
  subgraph :core 
    museumcollection:list --> domain([:domain]):::coreModule
    museumcollection:list --> design([:design]):::coreModule
    museumcollection:detail --> design([:design]):::coreModule
    data --> domain([:domain]):::coreModule
    data --> network([:network]):::coreModule
    data --> database([:database]):::coreModule
    database --> domain([:domain]):::coreModule 
    app --> data([:data]):::coreModule
    app --> database([:database]):::coreModule
    app --> design([:design]):::coreModule
    app --> navigation([:navigation]):::coreModule
    app --> network([:network]):::coreModule
  end
```

- Jetpack Compose
- Multi modules
- Clean Architecture
- MVVM (Model-View-ViewModel)
- Repository Pattern
   - Remote (Ktor)
   - Local (Room)   
- Dependency Injection (Koin)
- Gradle Convention Plugins
