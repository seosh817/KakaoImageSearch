# 레진코믹스 Android 개발자 기술 과제

# 앱 실행 화면

|  검색 화면 |  북마크 화면 |
|---|---|
|<img src="https://github.com/seosh817/KakaoImageSearch/blob/release/1.0.0/previews/Search.gif?raw=true" width="300">| <img src="https://github.com/seosh817/KakaoImageSearch/blob/release/1.0.0/previews/Bookmarks.gif?raw=true" width="300">|

|  테마 변경 |  Localization |
|---|---|
|<img src="https://github.com/seosh817/KakaoImageSearch/blob/release/1.0.0/previews/ThemeMode.gif?raw=true" width="300">| <img src="https://github.com/seosh817/KakaoImageSearch/blob/release/1.0.0/previews/Localization.gif?raw=true" width="300">|

# 작성한 앱에 대해서 사용한 기술, 활용한 라이브러에 대한 설명 및 사용 사유, 발생할 수 있는 문제점 등을 README에 기록해주세요.

- **Asynchronous**:
  - **Coroutines/Flow**:
    - Coroutines를 사용하면 좋은 이유는 RxJava와 비교했을 때 스레드를 중지하는 것이 아닌 코루틴을 일시정지/재개 시키는 것이므로 성능적인 이점이 있습니다.
    - 또한 Coroutines를 사용하면 RxJava의 복잡한 체이닝 스타일을 사용하지 않을 수 있습니다.
    - 개인적으로 RxJava에 익숙하기 때문에 매우 유사한 Flow를 이용하여 ViewModel에서 검색어 -> 페이징 데이터를 받아오는 부분에서 체이닝하는 방식으로 코딩하였습니다.
- **Networking**:
  - **Retrofit/Okhttp**:
    - Retrofit과 Okhttp를 통합하여 사용할 수 있고, 통합하면 아래와 같은 여러 이점들이 존재합니다.
    - Okhttp의 Interceptor를 활용하면 API의 모니터링 및 헤더 추가에 용이합니다.
    - Retrofit을 사용하면 @Parameter, @Query, @Header 등의 어노테이션으로 API 매핑 작업을 용이하게 할 수 있습니다.
- **Serialization**:
  - **Kotlinx Serialization**:
    - 직렬화 라이브러리는 kotlinx-serialization을 이용하였는데, kotlin data class에 default value를 설정할 수 있다는 점이 가장 뛰어난 장점이라고 생각합니다.
    - 만약, 서버에서 내려온 Json의 필드 값이 null인 경우 타입 안정성을 보장받을 수 없으므로 nullable한 값으로 설정하지 않으면 앱이 죽어버리는 경우가 있을 수 있습니다. 이러한 면에서 default value를 설정할 수 있는 것이 kotlinx-serialization의 가장 좋은 장점이라고 생각합니다.
    - 또한 retrofit2-kotlinx-serialization-converter 라이브러리를 사용하여 Retrofit과 통합하여 사용할 수 있습니다.
- **Image loading**:
  - **Coil**
    - 이미지 로딩 라이브러리 중에서는 Glide가 가장 인지도가 높지만, 요즘 인기있는 Coil은 Kotlin으로 구현되어 있으며, 내부적으로 Coroutines을 사용하여 경량화되어 있다고 알고 있어서 사용해 보았습니다.
    - 성능은 그래픽 처리에 사용되는 메모리는 비슷하나 Coroutines를 사용하기 때문에 Native code의 호출 횟수가 적다고 합니다.
- **Dependency Injection**:
  - **Hilt**:
    - 의존성 주입 라이브러리는 Hilt를 사용하였는데 Annotation Proceesor를 사용하여 컴파일 타임에 코드 생성 및 의존성 주입을 하기 때문에 런타임에 주입하는 Koin과 비교하면 런타임 오류가 없으므로 안전합니다.
    - 또한, 사용하기 까다로웠던 Dagger2를 래핑하여 안드로이드에서 사용할 수 있는 Component와 Scope를 정의해두었기 때문에 보일러 플레이트 코드가 적고, 사용하기 쉽기 때문에 자주 사용하는 편입니다.
- **Database**:
  - **Room**:
    - SQLite 데이터베이스의 ORM 버전의 라이브러리 입니다.
    - 컴파일 타임에 쿼리 검사를 할 수 있어 런타임 에러를 줄일 수 있고, 어노테이션을 활용하여 보일러 플레이트를 줄일 수 있습니다.
    - Coroutines 및 Flow와 통합하여 바로 사용할 수 있으므로 데이터 베이스의 변경을 관찰할 수 있으며, Paging3와 통합하여 PagingSource를 받을 수 있습니다.
  - **DataStore/Protobuf**:
    - Datastore는 Coroutines와 Flow를 기반으로 하였으므로 데이터의 변경을 관찰할 수 있습니다.
    - 비동기적이고 일관된 트랜잭션 방식으로 데이터를 저장하여 SharedPreferences의 단점을 극복할 수 있습니다.
    - 또한, 작업은 내부적으로 Dispatchers.IO 스레드에서 동작하기 때문에 안전합니다.
    - Datastore의 단점 중 하나는 스키마를 할 수 없고, 올바른 타입으로 키에 액세스할 방법이 없습니다.
    - 하지만, Protocol buffer를 사용하면 스키마를 정의하는 방식으로 문제를 해결할 수 있습니다. proto에 저장된 타입을 인식하고 제공하기 때문에 키를 사용할 필요가 없게 됩니다.
  - **Gradle**:
    - **Version Catalog**:
      - Version Catalog를 사용하면 하나의 파일로 여러 프로젝트 혹은 모듈에서 라이브러리 및 버전을 사용할 수 있게됩니다.
    - **Gradle Convention Plugins**:
      - Gradle Convention Plugins을 사용하면 여러 프로젝트 혹은 모듈에서 공통된 빌드 구성을 사용할 수 있습니다.
- **Continuous Integration**:
  - **GitHub Actions**
    - 깃허브에서 제공하는 CI 툴이므로 사용하기 쉽고 짜놓은 script도 존재하기 때문에 사용하였습니다.


# 질의 답변 사항 (README에 답변을 정리해주세요.)

# Kotlin Coroutine Flow 관련
## Cold Flow란?
- `Cold Flow`는 구독자가 있을 때만 데이터를 생성하고 방출하는 특성을 가진 Flow입니다.
- 즉, Flow를 collect할 때 데이터 스트림이 시작됩니다.
- 매번 collect할 때마다 새로운 데이터 스트림이 생성되므로, 각각의 구독자는 독립적인 데이터를 받습니다.
- 대표적인 예로는 Flow가 있습니다.

## Hot Flow란?
- `Hot Flow`는 구독자가 없어도 데이터를 계속 방출하는 Flow입니다.
- 구독자가 생기면 이미 방출 중인 데이터 스트림을 구독하게 됩니다. 그래서, 구독자가 여러 명일 경우, 동일한 데이터 스트림을 공유하게 됩니다.
- 즉, 이미 Hot Flow에 0이라는 값이 방출되고난 후에 구독자가 구독을 시작했다면 값을 받지 못합니다.
- 대표적인 예로는 Channel과 ViewModel에서 사용할 수 있는 StateFlow, SharedFlow가 있습니다.

## StateFlow와 SharedFlow에 대해 자세히 설명해주세요.
### StateFlow
- `StateFlow`는 업데이트를 담당하는 MutableStateFlow를 통해 상태를 저장하고 관리할 수 있으며 초기값이 필요합니다.
- 항상 최신 상태를 유지하며, 새로운 구독자는 현재 상태를 즉시 받게 됩니다.
- LiveData는 View가 STOPPED 상태가 되면 자동으로 소비자가 collect를 취소하는 반면에, StateFlow는 자동으로 collect를 취소하지 않습니다. 그러므로, `Lifecycle.repeatOnLifecycle`를 사용하여 collect해야 합니다.
- Flow를 StateFlow를 변환하고 싶다면 `stateIn` 메소드를 활용할 수 있습니다.
- 기본적으로 단일 값 상태를 유지하기 때문에 `UI 상태 관리`에 유용합니다.

### SharedFlow
- `SharedFlow`는 업데이트를 담당하는 MutableSharedFlow를 통해 여러 구독자에게 데이터를 공유할 수 있는 Flow이며 초기값이 필요 없습니다.
- 파라미터로 `replay`가 있는데 디폴트 값은 0이며, 이전에 내보낸 값을 새로운 구독자들에게 여러 값을 보내고 싶다면 이 replay 값을 설정하면 됩니다.
- `extraBufferCapacity` 파라미터를 통해 버퍼 값을 설정할 수 있으며, `onBufferOverflow`를 통해 버퍼가 가득찼을 때 적용할 정책을 지정할 수 있습니다.
- Flow를 StateFlow를 변환하고 싶다면 `sharedIn` 메소드를 활용할 수 있습니다.
- 상태관리 보다는 `이벤트`를 여러 구독자가 데이터를 동시에 받아야 할 때 유용합니다.

# Android ViewModel 관련
## owner에 대해 자세히 설명해주세요.
- `owner`는 ViewModel의 생명 주기를 관리하는 컴포넌트를 나타냅니다.
- 자세히 들여다보면, ViewModel의 생명주기인 `Scope`는 `ViewModelProvider`가 결정하는데, ViewModelProvider는 생성자 함수의 파라미터로 전달하는 `ViewModelStoreOwner`가 생명주기를 결정합니다.
- 보통 LifecycleOwner로 사용되며, Fragment나 Activity가 이에 해당합니다. 즉, ViewModel을 만드는 주체가 Activity, Fragment 중 어떤 owner로 생성하는지에 따라 ViewModel의 `Scope`가 결정됩니다.
- 이렇게 `ViewModelStoreOwner`를 구현하는 것은 ViewModelStore를 유지하게 만들고, 해당 Scope가 Destroy가 될때 ViewModelStore.clear()를 호출하게 됩니다.
- `ViewModelStore` 내부에서는 HashMap<String, ViewModel>를 두어 ViewModel을 관리하게 되는데, ViewModelStore는 화면 회전시에도 유지되는 인스턴스이므로, 화면 회전시 Activity나 Fragment가 Destroy되어도 Owner의 새로운 객체는 여전히 같은 ViewModelStore를 가지고, 이 ViewModelStore가 ViewModel을 관리하기 때문에 데이터 손실이 없습니다.
- 즉, ViewModelStoreOwner가 ViewModelStore를 생성하고 관리합니다.

## Compose Navigation과 Dagger Hilt를 같이 사용하는 경우, hiltViewModel()로 ViewModel instance를 가져올 때 owner를 어떻게 설정해야 하는지 자세히 설명해 주세요.
- Compose Navigation과 Dagger Hilt를 통합하여 사용할 경우, 일반적으로는 hiltViewModel()의 파라미터의 기본값으로 LocalViewModelStoreOwner.current가 들어있으므로 따로 지정해주지 않아도 됩니다.
- 하지만 만약, 원하는 Navigation Route나 Graph에 있는 인스턴스를 가져오고 싶은 경우 해당 NavBackStackEntry를 hiltViewModel()의 파라미터로 전달하면 됩니다.

```kotlin
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val startRoute = "example"
    val innerStartRoute = "exampleWithRoute"
    NavHost(navController, startDestination = startRoute) {
        navigation(startDestination = innerStartRoute, route = "Parent") {
            // ...
            composable("exampleWithRoute") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("Parent")
                }
                val parentViewModel = hiltViewModel<ParentViewModel>(parentEntry)
                ExampleWithRouteScreen(parentViewModel)
            }
        }
    }
}
```

# Paging 3 관련
## PagingSource란?
- `PagingSource`는 페이징 데이터를 로드하기 위한 클래스입니다. API나 데이터베이스에서 데이터를 가져오는 로직을 정의합니다. PagingData를 생성하기 위해 사용되며, 페이징 라이브러리가 데이터 소스를 효율적으로 관리할 수 있게 합니다.

## PagingSource의 load 함수의 파라미터와 리턴 값에 대해 자세히 설명해 주세요.
- `파라미터`로 LoadParams<Key>를 가지며, 로드할 키와 로드할 데이터의 크기를 가지고 있습니다.
- `리턴 값`은 LoadResult<Key, Value>로, 성공 시 LoadResult.Page를 반환합니다. 실패 시 LoadResult.Error를 반환합니다.
- `LoadResult.Page`는 해당 페이지의 데이터, prevKey, nextKey를 가지고 있습니다.
- `LoadResult.Error`는 에러를 전달하며 UI의 LoadState.Error를 통해 전달됩니다.

## PagingSource의 getRefreshKey 함수의 파라미터와 리턴 값에 대해 자세히 설명해 주세요.
- getRefreshkey 함수의 `파라미터`는 PagingState<Key, Value>이며 현재 가져온 페이징 데이터를 말합니다.
- PagingState는 로드한 페이지 List<Page<Key, Value>> 타입인 pages, 가장 마지막에 접근한 anchorPosition, PagingData 스트림의 정보를 담고있는 config을 가지고 있습니다.
- PagingState는 현재 가져온 페이징 데이터를 말하며 closestPageToPosition(anchorPosition: Int): Page<Key, Value>? 메소드를 통해 현재 anchorPosition 위치에 존재하는 Page의 prevKey와 nextKey를 가져올 수 있습니다.
- getRefreshKey 함수의 `리턴 값` Key는 Refresh 혹은 재 로드 시에 load 함수의 LoadParams.Key에 전달될 키 입니다. 만약, Key를 결정할 수 없는 경우 null값으로 설정하여 기본값을 사용하도록 만들어주면 됩니다.
- 따라서, getRefreshKey 함수는 Refresh 혹은 데이터 로드 중단 후 재 로드시 이전 position에서 중단된 key값을 가져오는 등 load에서 사용할 key 값을 가져오는 로직구현을 위한 함수입니다.