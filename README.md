# MapKit SDK

This SDK includes Google Map and Huawei Map enhancements.Recently, with the release of Huawei App Gallery, many
applications are performing map integration.

## Releases

[![](https://jitpack.io/v/iDroidDev/MapKit.svg)](https://jitpack.io/#iDroidDev/MapKit)
[![SDK Test](https://github.com/iDroidDev/MapKit/actions/workflows/sdk-test.yml/badge.svg)](https://github.com/iDroidDev/MapKit/actions/workflows/sdk-test.yml)

### ArnyminerZ ([Github](https://github.com/ArnyminerZ/MapKit))

[![](https://jitpack.io/v/ArnyminerZ/MapKit.svg)](https://jitpack.io/#ArnyminerZ/MapKit)
[![SDK Test](https://github.com/ArnyminerZ/MapKit/actions/workflows/sdk-test.yml/badge.svg)](https://github.com/ArnyminerZ/MapKit/actions/workflows/sdk-test.yml)

### Our main goal now aims to solve your map related problems. You will have completed both integrations using only our libraries.

## Installing

Add the code block to your project build.gradle file: (Ex: build.gradle(Project:XXX)

```groovy
maven { url 'https://developer.huawei.com/repo/' }
```

Add the dependency to your project build.gradle file: (Ex: build.gradle(Project:XXX)

```groovy
classpath 'com.huawei.agconnect:agcp:1.2.0.300'
classpath 'com.google.gms:google-services:4.3.8'
```

Add the allprojects to your project build.gradle file: (Ex: build.gradle(Project:XXX)

```groovy
maven { url 'https://developer.huawei.com/repo/' }
```

Sample Build Gradle Preview

```groovy
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    ext.kotlin_version = '1.4.32'
    repositories {
        google()
        jcenter()
        maven { url 'https://developer.huawei.com/repo/' }

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:4.0.0'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath 'com.huawei.agconnect:agcp:1.2.0.300'
        classpath 'com.google.gms:google-services:4.3.8'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
        maven { url 'https://developer.huawei.com/repo/' }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

### Add this plugin into your project build.gradle file: (Ex: build.gradle(app:XXX) 

```groovy
apply plugin: 'com.huawei.agconnect'

apply plugin: 'com.google.gms.google-services'
```

### Add the dependency to your project build.gradle file:

```groovy
implementation 'com.github.iDroidDev:MapKit:1.0.1'
```

## Usage
### Add Custom MapKit into XML file

```xml

<idroid.android.mapskit.ui.HuaweiGoogleMapView
    android:id="@+id/mapView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

### Configure map in your class

```kotlin
mapView.onCreate(mapViewBundle)
mapView.getMapAsync {
    it.addMarker("Marker", "Snippet", 41.000000F, 29.00000F)
    it.moveCamera(41.000000F, 29.00000F, 20f)
}
```

### Don't Forget this code block

```kotlin
override fun onStart() {
    super.onStart()
    mapView.onStart()
}

override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
}

override fun onPause() {
    super.onPause()
    mapView.onPause()
}

override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
}

override fun onStop() {
    super.onStop()
    mapView.onStop()
}
```

**Our LocationKit SDK link:** www.github.com/iDroidDev/LocationKit

# Documentation

[![](https://img.shields.io/static/v1?label=dokka&message=mapskit&color=orange&style=for-the-badge&logo=kotlin)](https://arnyminerz.github.io/MapKit/html/)

# Authors

* **Kaan KÜN** - *Mobile Application Developer*
* **Mahmut YETİŞİR** - *Mobile Application Developer*

## Contributors

* **Arnau Mora** (*~ArnyminerZ*) - [Github](https://github.com/ArnyminerZ)

## Don't worry

You can always contact us.

* **Kaan KÜN** Email : kaanforum4@gmail.com
* **Mahmut YETİŞİR** Email : mahmutyetisir03@gmail.com
